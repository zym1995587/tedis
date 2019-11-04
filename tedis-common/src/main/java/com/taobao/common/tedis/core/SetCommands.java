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
import java.util.Set;

/**
 * Set����
 *
 * @author juxin.zj E-mail:juxin.zj@taobao.com
 * @since 2011-7-25 ����11:54:39
 * @version 1.0
 */
public interface SetCommands {

    /**
     * ȡ��<tt>key</tt>��Set�в�������<tt>otherKey</tt>��Set������<br />
     * ���磺 <code>
     * key1 = {a,b,c,d}
     * key2 = {c,d}
     * difference(key1, key2) = {a,b}
     * </code>
     *
     * @param namespace
     * @param key
     * @param otherKey
     * @return
     */
    <K, V> Set<V> difference(int namespace, K key, K otherKey);

    /**
     * ȡ��<tt>key</tt>��Set�в�������<tt>otherKeys</tt>��Set������<br />
     * ���磺 <code>
     * key1 = {a,b,c,d}
     * key2 = {c}
     * key3 = {a,d}
     * otherKeys = {key2,key3}
     * difference(key1, otherKeys) = {b,d}
     * </code>
     *
     * @param namespace
     * @param key
     * @param otherKeys
     * @return
     */
    <K, V> Set<V> difference(int namespace, K key, Collection<K> otherKeys);

    /**
     * ȡ��<tt>key</tt>��Set�в�������<tt>otherKey</tt>��Set�����ݣ������ݴ浽<tt>destKey</tt> ���磺
     * <code>
     * key1 = {a,b,c,d}
     * key2 = {a,c}
     * destKey = differenceAndStore(key1, key2)
     * destKey = {b,d}
     * </code>
     *
     * @param namespace
     * @param key
     * @param otherKey
     * @param destKey
     */
    <K, V> void differenceAndStore(int namespace, K key, K otherKey, K destKey);

    /**
     * ȡ��<tt>key</tt>��Set�в�������<tt>otherKeys</tt>��Set�����ݣ������ݴ浽<tt>destKey</tt>
     * ���磺 <code>
     * key1 = {a,b,c,d}
     * key2 = {a}
     * key3 = {a,c}
     * otherKeys = {key2,key3}
     * destKey = differenceAndStore(key1, otherKeys)
     * destKey = {b,d}
     * </code>
     *
     * @param namespace
     * @param key
     * @param otherKeys
     * @param destKey
     */
    <K, V> void differenceAndStore(int namespace, K key, Collection<K> otherKeys, K destKey);

    /**
     * ȡ��<tt>key</tt>��Set��<tt>otherKey</tt>��Set�н��������� ���磺 <code>
     * key1 = {a,b,c,d}
     * key2 = {a,c}
     * intersect(key1, key2) = {a,c}
     * </code>
     *
     * @param namespace
     * @param key
     * @param otherKey
     * @return
     */
    <K, V> Set<V> intersect(int namespace, K key, K otherKey);

    /**
     * ȡ��<tt>key</tt>��Set��<tt>otherKeys</tt>��Set�н��������� ���磺 <code>
     * key1 = {a,b,c,d}
     * key2 = {c}
     * key3 = {a,c,e}
     * otherKeys = {key2,key3}
     * intersect(key1, otherKeys) = {c}
     * </code>
     *
     * @param namespace
     * @param key
     * @param otherKeys
     * @return
     */
    <K, V> Set<V> intersect(int namespace, K key, Collection<K> otherKeys);

    /**
     * ȡ��<tt>key</tt>��Set��<tt>otherKey</tt>��Set�н��������ݣ������ݷ���<tt>destKey</tt> ���磺
     * <code>
     * key1 = {a,b,c,d}
     * key2 = {a,c}
     * intersectAndStore(key1, key2, destKey)
     * destKey = {a,c}
     * </code>
     *
     * @param namespace
     * @param key
     * @param otherKey
     * @param destKey
     */
    <K, V> void intersectAndStore(int namespace, K key, K otherKey, K destKey);

    /**
     * ȡ��<tt>key</tt>��Set��<tt>otherKeys</tt>��Set�н������ݣ������ݷ���<tt>destKey</tt> ���磺
     * <code>
     * key1 = {a,b,c,d}
     * key2 = {c}
     * key3 = {a,c,e}
     * otherKeys = {key2,key3}
     * intersectAndStore(key1, otherKeys, destKey)
     * destKey = {c}
     * </code>
     *
     * @param namespace
     * @param key
     * @param otherKeys
     * @param destKey
     */
    <K, V> void intersectAndStore(int namespace, K key, Collection<K> otherKeys, K destKey);

    /**
     * ȡ��<tt>key</tt>��Set��<tt>otherKey</tt>��Set�Ĳ������� ���磺 <code>
     * key1 = {a,b,c}
     * key2 = {c,d}
     * union(key1, key2) = {a,b,c,d}
     * </code>
     *
     * @param namespace
     * @param key
     * @param otherKey
     * @return
     */
    <K, V> Set<V> union(int namespace, K key, K otherKey);

    /**
     * ȡ��<tt>key</tt>��Set��<tt>otherKeys</tt>��Set�Ĳ������� ���磺 <code>
     * key1 = {a,b,c}
     * key2 = {c,d}
     * key3 = {a,c,e}
     * otherKeys = {key2,key3}
     * union(key1, otherKeys) = {a,b,c,d,e}
     * </code>
     *
     * @param namespace
     * @param key
     * @param otherKeys
     * @return
     */
    <K, V> Set<V> union(int namespace, K key, Collection<K> otherKeys);

    /**
     * ȡ��<tt>key</tt>��Set��<tt>otherKey</tt>��Set�Ĳ������ݣ������ݴ���<tt>destKey</tt> ���磺
     * <code>
     * key1 = {a,b,c}
     * key2 = {c,d}
     * unionAndStore(key1, key2, destKey)
     * destKey = {a,b,c,d}
     * </code>
     *
     * @param namespace
     * @param key
     * @param otherKey
     * @param destKey
     */
    <K, V> void unionAndStore(int namespace, K key, K otherKey, K destKey);

    /**
     * ȡ��<tt>key</tt>��Set��<tt>otherKeys</tt>��Set�Ĳ������ݣ������ݴ���<tt>destKey</tt> ���磺
     * <code>
     * key1 = {a,b,c}
     * key2 = {c,d}
     * key3 = {a,c,e}
     * otherKeys = {key2,key3}
     * union(key1, otherKeys, destKey)
     * destKey = {a,b,c,d,e}
     * </code>
     *
     * @param namespace
     * @param key
     * @param otherKeys
     * @param destKey
     */
    <K, V> void unionAndStore(int namespace, K key, Collection<K> otherKeys, K destKey);

    /**
     * ��<tt>key</tt>��Set����һ������
     *
     * @param namespace
     * @param key
     * @param value
     * @return
     */
    <K, V> Long add(int namespace, K key, V... value);

    /**
     * �ж�<tt>o</tt>�Ƿ�Ϊ<tt>key</tt>��Set�е�����
     *
     * @param namespace
     * @param key
     * @param o
     * @return
     */
    <K, V> Boolean isMember(int namespace, K key, Object o);

    /**
     * ȡ�����г�Ա
     *
     * @param namespace
     * @param key
     * @return
     */
    <K, V> Set<V> members(int namespace, K key);

    /**
     * �ƶ�<tt>key</tt>��Set�е�<tt>value</tt>��<tt>destKey</tt>
     *
     * @param namespace
     * @param key
     * @param value
     * @param destKey
     * @return
     */
    <K, V> Boolean move(int namespace, K key, V value, K destKey);

    /**
     * ���ȡ��һ������
     *
     * @param namespace
     * @param key
     * @return
     */
    <K, V> V randomMember(int namespace, K key);

    /**
     * ɾ��һ������
     *
     * @param namespace
     * @param key
     * @param o
     * @return
     */
    <K, V> Long remove(int namespace, K key, Object... o);

    /**
     * ɾ����ȡ����һ������
     *
     * @param namespace
     * @param key
     * @return
     */
    <K, V> V pop(int namespace, K key);

    /**
     * ȡ��Set��С
     *
     * @param namespace
     * @param key
     * @return
     */
    <K, V> Long size(int namespace, K key);

}
