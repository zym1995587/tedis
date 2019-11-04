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
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.taobao.common.tedis.binary.DataType;
import com.taobao.common.tedis.serializer.TedisSerializer;
import com.taobao.common.tedis.util.SortParams;

/**
 * ����������ڣ�������
 * <ul>
 *  <li>�����Ļ���key�Ĳ���</li>
 *  <li>ȡ�����������ݽṹ�������</li>
 *  <li>����</li>
 *  <li>ȡ�õ�ǰ���л���ʽ</li>
 * </ul>
 * @author juxin.zj E-mail:juxin.zj@taobao.com
 * @since 2011-7-25 ����11:54:51
 * @version 1.0
 */
public interface TedisManager {

    /**
     * <tt>key</tt>�Ƿ����
     * @param namespace
     * @param key
     * @return
     */
    <K> Boolean hasKey(int namespace, K key);

    /**
     * ɾ��ָ��<tt>key</tt>
     * @param namespace
     * @param key
     */
    <K> void delete(int namespace, K key);

    /**
     * ����ɾ��<tt>key</tt>
     * @param namespace
     * @param keys
     */
    <K> void delete(int namespace, Collection<K> keys);

    /**
     * ȡ��<tt>key</tt>������
     * @param namespace
     * @param key
     * @return
     */
    <K> DataType type(int namespace, K key);

    /**
     * ȡ��<tt>pattern</tt>ƥ���<tt>key</tt>����
     * @param namespace
     * @param pattern
     * @return
     */
    <K> Set<K> keys(int namespace, String pattern);

    /**
     * ������<tt>key</tt>
     * @param namespace
     * @param oldKey
     * @param newKey
     */
    <K> void rename(int namespace, K oldKey, K newKey);

    /**
     * ���<tt>oldKey</tt>������������<tt>oldKey</tt>Ϊ<tt>newKey</tt>
     * @param namespace
     * @param oldKey
     * @param newKey
     * @return
     */
    <K> Boolean renameIfAbsent(int namespace, K oldKey, K newKey);

    /**
     * ����<tt>key</tt>�Ĺ���ʱ��
     * @param namespace
     * @param key
     * @param timeout
     * @param unit
     * @return
     */
    <K> Boolean expire(int namespace, K key, long timeout, TimeUnit unit);

    /**����<tt>key</tt>�Ĺ���ʱ��
     * @param namespace
     * @param key
     * @param date
     * @return
     */
    <K> Boolean expireAt(int namespace, K key, Date date);

    /**
     * ȡ��<tt>key</tt>�Ĺ�������
     * @param namespace
     * @param key
     * @return
     */
    <K> Boolean persist(int namespace, K key);

    /**
     * ȡ��<tt>key</tt>�Ĺ���ʱ��
     * @param namespace
     * @param key
     * @return
     */
    <K> Long getExpire(int namespace, K key);

    /**
     * ȡ�ü����������
     * @see AtomicCommands
     * @return
     */
    AtomicCommands getAtomicCommands();

    /**
     * ȡ��String�������
     * @see StringCommands
     * @return
     */
    StringCommands getStringCommands();

    /**
     * ȡ��һ��key value�������
     * @see ValueCommands
     * @return
     */
    ValueCommands getValueCommands();

    /**
     * ȡ��Hash�������
     * @see HashCommands
     * @param <HK>
     * @param <HV>
     * @return
     */
    HashCommands getHashCommands();

    /**
     * ȡ��List�������
     * @see ListCommands
     * @return
     */
    ListCommands getListCommands();

    /**
     * ȡ��Set�������
     * @see SetCommands
     * @return
     */
    SetCommands getSetCommands();

    /**
     * ȡ������Set�������
     * @see ZSetCommands
     * @return
     */
    ZSetCommands getZSetCommands();

    /**
     * ����
     * @param namespace
     * @param query
     * @return
     */
    <K, V> List<V> sort(int namespace, K key, SortParams params);

    /**
     * ����
     * @param namespace
     * @param query
     * @param storeKey
     * @return
     */
    <K> Long sort(int namespace, K key, SortParams query, K storeKey);

    /**
     * ȡ��<tt>value</tt>���л�����
     * @return
     */
    TedisSerializer<?> getValueSerializer();

    /**
     * ȡ��<tt>key</tt>���л�����
     * @return
     */
    TedisSerializer<?> getKeySerializer();

    /**
     * ������Դ��ֹͣ�����̳߳�
     */
    void destroy();

}
