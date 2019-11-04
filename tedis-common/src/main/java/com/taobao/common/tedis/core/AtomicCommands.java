/**
 * (C) 2011-2012 Alibaba Group Holding Limited.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * version 2 as published by the Free Software Foundation.
 *
 */
package com.taobao.common.tedis.core;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * ��������
 * 
 * @author juxin.zj E-mail:juxin.zj@taobao.com
 * @since 2011-8-4 ����08:04:52
 * @version 1.0
 */
public interface AtomicCommands {

    /**
     * ������������ <tt>delta</tt>��Ϊ��������0ֵ����ֵΪ���Ӽ�������ֵΪ���ټ�����0Ϊȡ�õ�ǰֵ
     * 
     * @param namespace
     * @param key
     * @param delta
     * @return
     */
    Long increment(int namespace, Object key, long delta);

    /**
     * �������ݣ���������Ѿ����ڣ��򸲸ǣ���������ڣ�������
     * 
     * @param namespace
     *            �������ڵ�namespace
     * @param key
     * @param value
     */
    void set(int namespace, Object key, long value);

    /**
     * �������ݣ���������Ѿ����ڣ��򸲸ǣ���������ڣ�������
     * 
     * @param namespace
     *            �������ڵ�namespace
     * @param key
     * @param value
     * @param timeout
     *            ���ݵ���Чʱ��
     * @param unit
     *            ʱ�䵥λ
     */
    void set(int namespace, Object key, long value, long timeout, TimeUnit unit);

    /**
     * �������ݣ���������Ѿ����ڣ�����ԭֵ����<tt>false</tt>����������ڣ�������������<tt>true</tt>
     * 
     * @param namespace
     *            �������ڵ�namespace
     * @param key
     * @param value
     * @return ������ݲ����ڷ���<tt>true</tt>�����򷵻�<tt>false</tt>
     */
    Boolean setIfAbsent(int namespace, Object key, long value);

    /**
     * ������������
     * 
     * @param namespace
     * @param m
     */
    void multiSet(int namespace, Map<? extends Object, Long> m);

    /**
     * �����������ݣ�ֻ�е����ݲ�����ʱ������
     * 
     * @param namespace
     * @param m
     */
    void multiSetIfAbsent(int namespace, Map<? extends Object, Long> m);

    /**
     * ��ȡ����
     * 
     * @param namespace
     * @param key
     * @return
     */
    long get(int namespace, Object key);

    /**
     * ��������ͬʱ���������ݣ��˷�����ԭ�Ӳ���
     * 
     * @param namespace
     * @param key
     * @param value
     * @return
     */
    long getAndSet(int namespace, Object key, long value);

    /**
     * ������ȡ����
     * 
     * @param namespace
     * @param keys
     * @return
     */
    List<Long> multiGet(int namespace, Collection<? extends Object> keys);

}
