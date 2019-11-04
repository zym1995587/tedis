/**
 * (C) 2011-2012 Alibaba Group Holding Limited.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * version 2 as published by the Free Software Foundation.
 *
 */
package com.taobao.common.tedis.commands;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.taobao.common.tedis.core.TedisManager;
import com.taobao.common.tedis.group.TedisGroup;
import com.taobao.common.tedis.serializer.TedisSerializer;

public class TedisManagerFactory {
    private static ConcurrentHashMap<String, TedisManager> managers = new ConcurrentHashMap<String, TedisManager>();

    /**
     * ����ȫ��Ψһ��ʵ��������Ѿ����ڣ��򷵻ش��ڵ�ʵ��
     *
     * @param appName
     * @param version
     * @return
     */
    public static synchronized TedisManager create(String appName, String version) {
        String key = appName + "-" + version;
        TedisManager manager = managers.get(key);
        if (manager == null) {
            TedisGroup tedisGroup = new TedisGroup(appName, version);
            tedisGroup.init();
            manager = new DefaultTedisManager(tedisGroup);
            managers.put(key, manager);
        }
        return manager;
    }

    /**
     * ����ȫ��Ψһ��ʵ��������Ѿ����ڣ��򷵻ش��ڵ�ʵ��
     *
     * @param appName
     * @param version
     * @param serializer
     * @return
     */
    public static synchronized TedisManager create(String appName, String version, TedisSerializer<?> serializer) {
        String key = appName + "-" + version;
        DefaultTedisManager manager = (DefaultTedisManager) managers.get(key);
        if (manager == null) {
            TedisGroup tedisGroup = new TedisGroup(appName, version);
            tedisGroup.init();
            manager = new DefaultTedisManager(tedisGroup);
            managers.put(key, manager);
        }
        manager.setKeySerializer(serializer);
        manager.setValueSerializer(serializer);
        manager.setHashKeySerializer(serializer);
        return manager;
    }

    /**
     * ����ָ����TedisManager��ע�⣺�ڶ��̻߳�������Ҫ�����Ƿ��������̻߳���ʹ�ã�����ᵼ�����⡣
     *
     * @param manager
     */
    public static synchronized void destroy(TedisManager manager) {
        Iterator<Entry<String, TedisManager>> it = managers.entrySet().iterator();
        while (it.hasNext()) {
            if (it.next().getValue().equals(manager)) {
                it.remove();
            }
        }
        manager.destroy();
    }

}
