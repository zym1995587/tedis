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
 * Hash����
 *
 * @author juxin.zj E-mail:juxin.zj@taobao.com
 * @since 2011-7-25 ����11:54:26
 * @version 1.0
 */
public interface HashCommands {

    /**
     * ɾ��<tt>key</tt>��Hash�����<tt>hashKey</tt>������
     *
     * @param namespace
     * @param key
     * @param hashKey
     */
    <H, HK, HV> void delete(int namespace, H key, Object... hashKey);

    /**
     * �ж�<tt>key</tt>��Hash�����<tt>hashKey</tt>�Ƿ����
     *
     * @param namespace
     * @param key
     * @param hashKey
     * @return ���ڷ���<tt>true</tt>�������ڷ���<tt>false</tt>
     */
    <H, HK, HV> Boolean hasKey(int namespace, H key, Object hashKey);

    /**
     * ȡ��<tt>key</tt>��Hash�����<tt>hashKey</tt>������
     *
     * @param namespace
     * @param key
     * @param hashKey
     * @return
     */
    <H, HK, HV> HV get(int namespace, H key, Object hashKey);

    /**
     * ����ȡ������
     *
     * @param namespace
     * @param key
     * @param hashKeys
     * @return
     */
    <H, HK, HV> Collection<HV> multiGet(int namespace, H key, Collection<HK> hashKeys);

    /**
     * ������������ <tt>delta</tt>��Ϊ��������0ֵ����ֵΪ���Ӽ�������ֵΪ���ټ�����0Ϊȡ�õ�ǰֵ
     *
     * @param namespace
     * @param key
     * @param hashKey
     * @param delta
     * @return
     */
    <H, HK, HV> Long increment(int namespace, H key, HK hashKey, long delta);

    /**
     * �õ�<tt>key</tt>��Hash���������key
     *
     * @param namespace
     * @param key
     * @return
     */
    <H, HK, HV> Set<HK> keys(int namespace, H key);

    /**
     * ȱ��<tt>key</tt>��Hash����Ĵ�С
     *
     * @param namespace
     * @param key
     * @return
     */
    <H, HK, HV> Long size(int namespace, H key);

    /**
     * ������������
     *
     * @param namespace
     * @param key
     * @param m
     */
    <H, HK, HV> void putAll(int namespace, H key, Map<? extends HK, ? extends HV> m);

    /**
     * �������ݣ�������ݴ��ڣ��򸲸ǣ�������ݲ����ڣ�������
     *
     * @param namespace
     * @param key
     * @param hashKey
     * @param value
     */
    <H, HK, HV> void put(int namespace, H key, HK hashKey, HV value);

    /**
     * �������ݣ�ֻ�����ݲ����ڲ������óɹ�
     *
     * @param namespace
     * @param key
     * @param hashKey
     * @param value
     * @return
     */
    <H, HK, HV> Boolean putIfAbsent(int namespace, H key, HK hashKey, HV value);

    /**
     * ȡ��<tt>key</tt>��Hash���������ֵ
     *
     * @param namespace
     * @param key
     * @return
     */
    <H, HK, HV> Collection<HV> values(int namespace, H key);

    /**
     * ȱ��<tt>key</tt>��Hash���������key��value
     *
     * @param namespace
     * @param key
     * @return
     */
    <H, HK, HV> Map<HK, HV> entries(int namespace, H key);

}
