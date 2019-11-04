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
import java.util.Map;
import java.util.Set;

/**
 * SortedSet����
 *
 * @author juxin.zj E-mail:juxin.zj@taobao.com
 * @since 2011-7-25 ����11:55:05
 * @version 1.0
 */
public interface ZSetCommands {

    /**
     * ȡ��<tt>key</tt>��<tt>otherKey</tt>�Ľ��������������<tt>destKey</tt>
     *
     * @param namespace
     * @param key
     * @param otherKey
     * @param destKey
     */
    <K, V> void intersectAndStore(int namespace, K key, K otherKey, K destKey);

    /**
     * ȡ��<tt>key</tt>��<tt>otherKeys</tt>�Ľ��������������<tt>destKey</tt>
     *
     * @param namespace
     * @param key
     * @param otherKeys
     * @param destKey
     */
    <K, V> void intersectAndStore(int namespace, K key, Collection<K> otherKeys, K destKey);

    /**
     * ȡ��<tt>key</tt>��<tt>otherKey</tt>�Ĳ��������������<tt>destKey</tt>
     *
     * @param namespace
     * @param key
     * @param otherKey
     * @param destKey
     */
    <K, V> void unionAndStore(int namespace, K key, K otherKey, K destKey);

    /**
     * ȡ��<tt>key</tt>��<tt>otherKeys</tt>�Ĳ��������������<tt>destKey</tt>
     *
     * @param namespace
     * @param key
     * @param otherKeys
     * @param destKey
     */
    <K, V> void unionAndStore(int namespace, K key, Collection<K> otherKeys, K destKey);

    /**
     * ��Ȩ����͵����ݿ�ʼ��ȡ��λ�ô�<tt>start</tt>��<tt>end</tt>������
     *
     * @param namespace
     * @param key
     * @param start
     * @param end
     * @return
     */
    <K, V> Set<V> range(int namespace, K key, long start, long end);

    /**
     * ��Ȩ����͵����ݿ�ʼ��ȡ��λ�ô�<tt>start</tt>��<tt>end</tt>������
     *
     * @param namespace
     * @param key
     * @param start
     * @param end
     * @return
     */
    <K, V> Map<V, Double> rangeWithScore(int namespace, K key, long start, long end);

    /**
     * ȡ��Ȩ�ش�<tt>min</tt>��<tt>max</tt>������
     *
     * @param namespace
     * @param key
     * @param min
     * @param max
     * @return
     */
    <K, V> Set<V> rangeByScore(int namespace, K key, double min, double max);

    /**
     * ȡ��Ȩ�ش�<tt>min</tt>��<tt>max</tt>������
     *
     * @param namespace
     * @param key
     * @param min
     * @param max
     * @param offset
     * @param count
     * @return
     */
    <K, V> Set<V> rangeByScore(int namespace, K key, double min, double max, int offset, int count);

    /**
     * ȡ��Ȩ�ش�<tt>min</tt>��<tt>max</tt>������
     *
     * @param namespace
     * @param key
     * @param min
     * @param max
     * @return
     */
    <K, V> Set<V> reverseRangeByScore(int namespace, K key, double min, double max);

    /**
     * ȡ��Ȩ�ش�<tt>min</tt>��<tt>max</tt>������
     *
     * @param namespace
     * @param key
     * @param min
     * @param max
     * @param offset
     * @param count
     * @return
     */
    <K, V> Set<V> reverseRangeByScore(int namespace, K key, double min, double max, int offset, int count);

    /**
     * ȡ��Ȩ�ش�<tt>min</tt>��<tt>max</tt>������
     *
     * @param namespace
     * @param key
     * @param min
     * @param max
     * @return
     */
    <K, V> Map<V, Double> rangeByScoreWithScore(int namespace, K key, double min, double max);

    /**
     * ��Ȩ����ߵ����ݿ�ʼ��ȡ��λ�ô�<tt>start</tt>��<tt>end</tt>������
     *
     * @param namespace
     * @param key
     * @param start
     * @param end
     * @return
     */
    <K, V> Set<V> reverseRange(int namespace, K key, long start, long end);

    /**
     * ��Ȩ����ߵ����ݿ�ʼ��ȡ��λ�ô�<tt>start</tt>��<tt>end</tt>������
     *
     * @param namespace
     * @param key
     * @param start
     * @param end
     * @return
     */
    <K, V> Map<V, Double> reverseRangeWithScore(int namespace, K key, long start, long end);

    /**
     * ����һ������
     *
     * @param namespace
     * @param key
     * @param value
     * @param score
     * @return
     */
    <K, V> Boolean add(int namespace, K key, V value, double score);

    /**
     * ���Ӷ������
     *
     * @param namespace
     * @param key
     * @param value
     * @param score
     * @return
     */
    <K, V> Long add(int namespace, K key, Map<V, Double> maps);

    /**
     * ����һ�����ݵ�Ȩ�أ����ҷ��ظ��º��Ȩ��ֵ
     *
     * @param namespace
     * @param key
     * @param value
     * @param delta
     * @return
     */
    <K, V> Double incrementScore(int namespace, K key, V value, double delta);

    /**
     * �õ�һ�����ݸ���Ȩ�ص�������������͵�RankֵΪ0
     *
     * @param namespace
     * @param key
     * @param o
     * @return
     */
    <K, V> Long rank(int namespace, K key, Object o);

    /**
     * �õ�һ�����ݸ���Ȩ�ص�������������ߵ�RankֵΪ0
     *
     * @param namespace
     * @param key
     * @param o
     * @return
     */
    <K, V> Long reverseRank(int namespace, K key, Object o);

    /**
     * �õ�һ�����ݵ�Ȩ��
     *
     * @param namespace
     * @param key
     * @param o
     * @return
     */
    <K, V> Double score(int namespace, K key, Object o);

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
     * ɾ�������<tt>start</tt>��<tt>end</tt>����������
     *
     * @param namespace
     * @param key
     * @param start
     * @param end
     */
    <K, V> void removeRange(int namespace, K key, long start, long end);

    /**
     * ɾ��Ȩ��ֵ��<tt>min</tt>��<tt>max</tt>����������
     *
     * @param namespace
     * @param key
     * @param min
     * @param max
     */
    <K, V> void removeRangeByScore(int namespace, K key, double min, double max);

    /**
     * ȡ��Ȩ��ֵ��<tt>min</tt>��<tt>max</tt>����������
     *
     * @param namespace
     * @param key
     * @param min
     * @param max
     * @return
     */
    <K, V> Long count(int namespace, K key, double min, double max);

    /**
     * ȡ��SortedSet��С
     *
     * @param namespace
     * @param key
     * @return
     */
    <K, V> Long size(int namespace, K key);

}
