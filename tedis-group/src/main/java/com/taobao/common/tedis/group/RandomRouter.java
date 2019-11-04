/**
 * (C) 2011-2012 Alibaba Group Holding Limited.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * version 2 as published by the Free Software Foundation.
 *
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taobao.common.tedis.group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.taobao.common.tedis.Single;
import com.taobao.common.tedis.atomic.TedisSingle;
import com.taobao.common.tedis.binary.RedisCommands;
import com.taobao.common.tedis.config.HAConfig.ServerProperties;
import com.taobao.common.tedis.config.Router;

/**
 * ����õ���Single,����ʱ�׳��쳣����Ѹ�Single�ŵ����������ԣ���ɾ��·�ɱ������ ���ȷ������ʧ�ܣ���ӹ�������ɾ����
 *
 * ��������Ӻ�·�ɷ��뿪����ô��������ɾ���� ����л�ȡ��������ʧ�ܣ���֪ͨ���ߣ����߿��ܻ��в�һ�¡�
 *
 * @author jianxing <jianxing.qx@taobao.com>
 */
public final class RandomRouter implements Router {

    static final Log logger = LogFactory.getLog(RandomRouter.class);
    Random random = new Random();
    private List<ServerProperties> all_props;// �����ʼ���ص�����,���������ʧЧ�ķ�������
    boolean failover;
    /**
     * �����ڳ����쳣��ʱ���޸ĵ��� Ȼ���ʹ��copy on write �ķ�ʽ��3s
     * checkһ�Σ�����ڵ�ظ����ͻὫ�������б�Ҳ���ǻᱻ�޸Ļ�����
     */
    volatile RouteData routeData;

    private RouteData allRouteData;// �����ʼ���ص�����,���������ʧЧ�ķ�������
    /**
     * single
     */
    final Map<String, TedisSingle> singleCache = new HashMap<String, TedisSingle>();
    ExecutorService executor_retry = Executors.newSingleThreadExecutor();
    final Retry retry = new Retry();

    public RandomRouter(List<ServerProperties> props, boolean failover) {
        this.all_props = props;
        this.failover = failover;
        routeData = createRandomData(props);
        allRouteData = createRandomData(props);
        startRetry();
    }

    private RouteData createRandomData(List<ServerProperties> props) {
        int[] weights = new int[props.size()];
        TedisSingle[] group = new TedisSingle[props.size()];
        int prev = 0;
        for (int i = 0; i < props.size(); i++) {
            group[i] = getAtomic(props.get(i));
            weights[i] += prev + props.get(i).readWeight;
            prev = weights[i];
        }

        return new RouteData(props, weights, group);
    }

    // ����ⲿ���ֱ�����Ͱѱ��������������������ֹ�ظ�������
    // ����������·���б���ȥ����single.
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public synchronized void onError(Single single) {
        if (!failover) {
            return;
        }
        logger.warn("onError:" + single);

        ServerProperties prop = single.getProperties();
        List<ServerProperties> new_props = (List<ServerProperties>) ((ArrayList) routeData.props).clone();
        new_props.remove(prop);
        routeData = createRandomData(new_props);

        // ���������߼�
        retry.addRetry(single);
    }

    /**
     * ʧ�ܵ����ӣ������������ˡ� ��Ҫ�ڵ�ǰʹ�õ��б����һ����
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private synchronized void onReturn(TedisSingle single) {

        logger.warn("onReturn:" + single);

        ServerProperties prop = single.getProperties();
        List<ServerProperties> new_props = (List<ServerProperties>) ((ArrayList) routeData.props).clone();
        if (!new_props.contains(single.getProperties())) {
            new_props.add(prop);
        }
        routeData = createRandomData(new_props);
    }

    private Single route(RouteData routeData) throws Exception {
        int max_random = routeData.weights[routeData.weights.length - 1];
        int x = random.nextInt(max_random);
        for (int i = 0; i < routeData.weights.length; i++) {
            if (x < routeData.weights[i]) {
                return routeData.group[i];
            }
        }
        throw new Exception("routeData is empty");
    }

    @Override
    public Single route() throws Exception {
        return route(routeData);
    }

    @Override
    public RouteData getRouteData() {
        return routeData;
    }

    @Override
    public RouteData getAllRouteData() {
        return allRouteData;
    }

    synchronized TedisSingle getAtomic(ServerProperties prop) {
        TedisSingle s = singleCache.get(prop.generateKey());
        if (s == null) {
            s = new TedisSingle(prop);
            singleCache.put(prop.generateKey(), s);
        }
        return s;
    }

    @Override
    public Single getAtomic(String key) {
        return singleCache.get(key);
    }

    @Override
    public void destroy() {
        retry.exit = true;
        synchronized (retry) {
            retry.notify();
        }
        executor_retry.shutdownNow();
        synchronized (singleCache) {
            for (Map.Entry<String, TedisSingle> entry : singleCache.entrySet()) {
                entry.getValue().destroy();
            }
        }
    }

    final void startRetry() {
        executor_retry.execute(retry);
    }

    final class Retry implements Runnable {

        volatile boolean exit = false;
        CopyOnWriteArraySet<Single> set = new CopyOnWriteArraySet<Single>();

        public void addRetry(Single single) {
            set.add(single);
            synchronized (this) {
                this.notify();
            }
        }

        @Override
        public void run() {

            while (!exit) {
                for (Single s : set) {
                    logger.warn("retry:" + s);
                    try {
                        synchronized (singleCache) {
                            singleCache.remove(s.getProperties().generateKey()).destroy();
                        }
                    } catch (Exception e) {
                        logger.warn("", e);
                    }
                    TedisSingle ss = null;
                    try {
                        ss = new TedisSingle(s.getProperties());
                        RedisCommands tedis = ss.getTedis();
                        tedis.ping();
                        synchronized (singleCache) {
                            singleCache.put(ss.getProperties().generateKey(), ss);
                        }
                        // success
                        onReturn(ss);
                        set.remove(s);
                    } catch (Throwable t) {
                        s.getErrorCount().incrementAndGet();
                        if (ss != null) {
                            try {
                                ss.destroy();
                            } catch (Exception e) {
                            }
                        }
                        logger.warn("retry throwable : " + s, t);
                    }

                }
                try {
                    synchronized (Retry.this) {
                        wait(1000 * 20);
                    }
                } catch (InterruptedException ex) {
                    logger.warn("Retry Thread InterruptedException", ex);
                }
            }

        }
    }

    @Override
    public String toString() {
        return "RandomRouter{" + "all_props=" + all_props + ", routeData=" + routeData + '}';
    }
}
