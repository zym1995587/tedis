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
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * �򵥵�key value�������
 * 
 * @author juxin.zj E-mail:juxin.zj@taobao.com
 * @since 2011-7-25 ����11:54:59
 * @version 1.0
 */
public interface ValueCommands {

    /**
     * �������ݣ���������Ѿ����ڣ��򸲸ǣ���������ڣ�������
     * 
     * @param namespace
     *            �������ڵ�namespace
     * @param key
     * @param value
     */
    <K, V> void set(int namespace, K key, V value);

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
    <K, V> void set(int namespace, K key, V value, long timeout, TimeUnit unit);

    /**
     * �������ݣ���������Ѿ����ڣ�����ԭֵ����<tt>false</tt>����������ڣ�������������<tt>true</tt>
     * 
     * @param namespace
     *            �������ڵ�namespace
     * @param key
     * @param value
     * @return ������ݲ����ڷ���<tt>true</tt>�����򷵻�<tt>false</tt>
     */
    <K, V> Boolean setIfAbsent(int namespace, K key, V value);

    /**
     * ������������
     * 
     * @param namespace
     * @param m
     */
    <K, V> void multiSet(int namespace, Map<? extends K, ? extends V> m);

    /**
     * �����������ݣ�ֻ�е����ݲ�����ʱ������
     * 
     * @param namespace
     * @param m
     */
    <K, V> void multiSetIfAbsent(int namespace, Map<? extends K, ? extends V> m);

    /**
     * ��ȡ���� ע�ⲻ���ô˷���ȥ��ȡincrement����������key
     * 
     * @param namespace
     * @param key
     * @return
     */
    <K, V> V get(int namespace, K key);

    /**
     * ʹ��LocalCache�ķ�ʽ��ȡ���ݣ�����Ƶ��������ɵ�������ʧ<br/>
     * ���ȵ�LocalCache��ȥȡ���ݣ����ȡ��������Redis��ȡ�����Ұ�ȡ�õ�ֵ���ճ�ʱʱ�����LocalCache
     * 
     * @since V1.0.1
     * @param namespace
     * @param key
     * @param timeout
     * @param unit
     * @return
     */
    <K, V> V get(int namespace, K key, long timeout, TimeUnit unit);

    /**
     * ʹ��LocalCache�ķ�ʽ��ȡ���ݣ�����Ƶ��������ɵ�������ʧ<br/>
     * ���ȵ�LocalCache��ȥȡ���ݣ����ȡ��������Redis��ȡ�����Ұ�ȡ�õ�ֵ���ճ�ʱʱ�����LocalCache
     * 
     * @param <K>
     * @param <V>
     * @param namespace
     * @param key
     * @param expireAt
     * @return
     */
    <K, V> V get(int namespace, K key, Date expireAt);

    /**
     * ��������ͬʱ���������ݣ��˷�����ԭ�Ӳ��� ע�ⲻ���ô˷���ȥ��ȡincrement����������key
     * 
     * @param namespace
     * @param key
     * @param value
     * @return
     */
    <K, V> V getAndSet(int namespace, K key, V value);

    /**
     * ������ȡ���� ע�ⲻ���ô˷���ȥ��ȡincrement����������key
     * 
     * @param namespace
     * @param keys
     * @return
     */
    <K, V> List<V> multiGet(int namespace, Collection<K> keys);

    /**
     * ʹ��LocalCache�ķ�ʽ��ȡ���ݣ�����Ƶ��������ɵ�������ʧ<br/>
     * ���ȵ�LocalCache��ȥȡ���ݣ����ȡ��������Redis��ȡ�����Ұ�ȡ�õ�ֵ���ճ�ʱʱ�����LocalCache
     * 
     * @since V1.0.1
     * @param namespace
     * @param keys
     * @param timeout
     * @param unit
     * @return
     */
    <K, V> List<V> multiGet(int namespace, Collection<K> keys, long timeout, TimeUnit unit);

    /**
     * ʹ��LocalCache�ķ�ʽ��ȡ���ݣ�����Ƶ��������ɵ�������ʧ<br/>
     * ���ȵ�LocalCache��ȥȡ���ݣ����ȡ��������Redis��ȡ�����Ұ�ȡ�õ�ֵ���ճ�ʱʱ�����LocalCache
     * 
     * @since V1.0.1
     * @param <K>
     * @param <V>
     * @param namespace
     * @param keys
     * @param expireAt
     * @return
     */
    <K, V> List<V> multiGet(int namespace, Collection<K> keys, Date expireAt);

}
