/**
 * (C) 2011-2012 Alibaba Group Holding Limited.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * version 2 as published by the Free Software Foundation.
 *
 */
package com.taobao.common.tedis.cache;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 * @author juxin.zj E-mail:juxin.zj@taobao.com
 * @since 2011-8-12 ����11:42:11
 * @version 1.0
 */
public interface LocalCache<K, V> {
    /**
     * ��������
     * @param key
     * @param value
     * @return
     */
    public V put(K key,V value);

    /**
     * ��������Ч�ڵ�����
     * @param key
     * @param value
     * @param ��Ч��
     * @return
     */
    public V put(K key,V value, Date expiry);

    /**
     * ��������Ч�ڵ�����
     * @param key
     * @param value
     * @param ���ݳ�ʱ������
     * @return
     */
    public V put(K key,V value, int TTL);

    /**
     * ��ȡ��������
     * @param key
     * @return
     */
    public V get(K key);

    /**
     * �Ƴ���������
     * @param key
     * @return
     */
    public V remove(K key);

    /**
     * ɾ�����л����ڵ�����
     * @return
     */
    public boolean clear();

    /**
     * ������������
     * @return
     */
    public int size();

    /**
     * �������е�key�ļ���
     * @return
     */
    public Set<K> keySet();

    /**
     * ���������value�ļ���
     * @return
     */
    public Collection<V> values();

    /**
     * �Ƿ������ָ��key������
     * @param key
     * @return
     */
    public boolean containsKey(K key);

    /**
     * �ͷ�Cacheռ�õ���Դ
     */
    public void destroy();
}
