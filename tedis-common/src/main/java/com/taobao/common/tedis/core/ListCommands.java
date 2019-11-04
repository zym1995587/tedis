/**
 * (C) 2011-2012 Alibaba Group Holding Limited.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * version 2 as published by the Free Software Foundation.
 *
 */
package com.taobao.common.tedis.core;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * List����,�����ʱ������Ե���һ������������
 * @author juxin.zj E-mail:juxin.zj@taobao.com
 * @since 2011-7-25 ����11:54:33
 * @version 1.0
 */
public interface ListCommands {

    /**
     * ȡ��List�д�<tt>start</tt>��<tt>end</tt>������<br />
     * ����<tt>start</tt>��<tt>end</tt>����ָ�������<br />
     * ���磺<code>range(1,somekey, 0, 10)</code>ȡ�õ���11������<br />
     * <tt>start</tt>��<tt>end</tt>��Ϊ������ʾ���е����ļ���<br />
     * ���磺<code>range(1,somekey, -2, -1)</code>ȡ�õ��ǵ���2������
     * @param namespace
     * @param key
     * @param start
     * @param end
     * @return
     */
    <K, V> List<V> range(int namespace, K key, long start, long end);

    /**
     * �ض�List��������<tt>start</tt>��ʼ��<tt>end</tt>������
     * @param namespace
     * @param key
     * @param start
     * @param end
     */
    <K, V> void trim(int namespace, K key, long start, long end);

    /**
     * ȡ��List��С
     * @param namespace
     * @param key
     * @return
     */
    <K, V> Long size(int namespace, K key);

    /**
     * �Ӷ���ͷ������һ������
     * @param namespace
     * @param key
     * @param value
     * @return
     */
    <K, V> Long leftPush(int namespace, K key, V... value);

    /**
     * �Ӷ�ieͷ������һ�����ݣ�������<tt>key</tt>�����ڲŲ�������
     * @param namespace
     * @param key
     * @param value
     * @return
     */
    <K, V> Long leftPushIfPresent(int namespace, K key, V value);

    /**
     * ��<tt>pivot</tt>֮ǰ����һ������
     * @param namespace
     * @param key
     * @param pivot
     * @param value
     * @return
     */
    <K, V> Long leftInsert(int namespace, K key, V pivot, V value);

    /**
     * �ڶ���β����һ������
     * @param namespace
     * @param key
     * @param value
     * @return
     */
    <K, V> Long rightPush(int namespace, K key, V... value);

    /**
     * �ڶ���β����һ�����ݣ�����<tt>key</tt>�����ڲŲ�������
     * @param namespace
     * @param key
     * @param value
     * @return
     */
    <K, V> Long rightPushIfPresent(int namespace, K key, V value);

    /**
     * ��<tt>pivot</tt>֮�����һ������
     * @param namespace
     * @param key
     * @param pivot
     * @param value
     * @return
     */
    <K, V> Long rightInsert(int namespace, K key, V pivot, V value);

    /**
     * ����λ��<tt>index</tt>��ֵ
     * @param namespace
     * @param key
     * @param index
     * @param value
     */
    <K, V> void set(int namespace, K key, long index, V value);

    /**
     * ɾ��List��<tt>i</tt>ֵΪ<tt>value</tt>����
     * <ul>
     *  <li>i����0���Ӷ���ͷ��ʼɾ��i������</li>
     *  <li>iС��0���Ӷ���β��ʼɾ��i������</li>
     *  <li>i����0��ɾ������ֵΪ<tt>value</tt>������</li>
     * </ul>
     * @param namespace
     * @param key
     * @param i
     * @param value
     * @return
     */
    <K, V> Long remove(int namespace, K key, long i, Object value);

    /**
     * ȡ��λ��<tt>index</tt>��������
     * @param namespace
     * @param key
     * @param index
     * @return
     */
    <K, V> V index(int namespace, K key, long index);

    /**
     * ɾ����ȡ������ͷ����һ�����ݣ������������������null
     * @param namespace
     * @param key
     * @return
     */
    <K, V> V leftPop(int namespace, K key);

    /**
     * ɾ����ȡ������ͷ����һ�����ݣ������������ȴ�ָ���ĳ�ʱʱ��<br />
     * <tt>timeout</tt>Ϊ0���ʾ������ʱ
     * @param namespace
     * @param key
     * @param timeout
     * @param unit
     * @return
     */
    <K, V> V leftPop(int namespace, K key, long timeout, TimeUnit unit);

    /**
     * ɾ����ȡ������β����һ�����ݣ��������������������null
     * @param namespace
     * @param key
     * @return
     */
    <K, V> V rightPop(int namespace, K key);

    /**
     * ɾ����ȡ������β����һ�����ݣ������������ȴ�ָ���ĳ�ʱʱ��<br />
     * <tt>timeout</tt>Ϊ0���ʾ������ʱ
     * @param namespace
     * @param key
     * @param timeout
     * @param unit
     * @return
     */
    <K, V> V rightPop(int namespace, K key, long timeout, TimeUnit unit);

    /**
     * ��<tt>sourceKey</tt>��β����һ������ɾ�������뵽<tt>destinationKey</tt>��ͷ�������ҽ������ݷ��أ�������ݲ���������������null
     * @param namespace
     * @param sourceKey
     * @param destinationKey
     * @return
     */
    <K, V> V rightPopAndLeftPush(int namespace, K sourceKey, K destinationKey);

    /**
     * ��<tt>sourceKey</tt>��β����һ������ɾ�������뵽<tt>destinationKey</tt>��ͷ�������ҽ������ݷ��أ�������ݲ�������ȴ�ָ��ʱ��
     * <tt>timeout</tt>Ϊ0���ʾ������ʱ
     * @param namespace
     * @param sourceKey
     * @param destinationKey
     * @param timeout
     * @param unit
     * @return
     */
    <K, V> V rightPopAndLeftPush(int namespace, K sourceKey, K destinationKey, long timeout, TimeUnit unit);

}
