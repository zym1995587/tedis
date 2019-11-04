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
 * String����
 * 
 * @author juxin.zj E-mail:juxin.zj@taobao.com
 * @since 2011-8-4 ����09:07:01
 * @version 1.0
 */
public interface StringCommands {

    /**
     * ��Ϊ<tt>key</tt>��String׷��<tt>value</tt>����µ��ַ�������������װ����ַ�������
     * 
     * @param namespace
     * @param key
     * @param value
     * @return
     */
    Long append(int namespace, String key, String value);

    /**
     * �õ�String��λ��<tt>start</tt>��<tt>end</tt>�����ַ���
     * 
     * @param namespace
     * @param key
     * @param start
     * @param end
     * @return
     */
    String get(int namespace, String key, long start, long end);

    /**
     * ��<tt>offset</tt>��ʼ�滻Ϊ<tt>value</tt>
     * 
     * @param namespace
     * @param key
     * @param value
     * @param offset
     */
    void set(int namespace, String key, String value, long offset);

    /**
     * ȡ���ַ�������
     * 
     * @param namespace
     * @param key
     * @return
     */
    Long size(int namespace, String key);

    /**
     * �������ݣ���������Ѿ����ڣ��򸲸ǣ���������ڣ�������
     * 
     * @param namespace
     *            �������ڵ�namespace
     * @param key
     * @param value
     */
    void set(int namespace, String key, String value);

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
    void set(int namespace, String key, String value, long timeout, TimeUnit unit);

    /**
     * �������ݣ���������Ѿ����ڣ�����ԭֵ����<tt>false</tt>����������ڣ�������������<tt>true</tt>
     * 
     * @param namespace
     *            �������ڵ�namespace
     * @param key
     * @param value
     * @return ������ݲ����ڷ���<tt>true</tt>�����򷵻�<tt>false</tt>
     */
    Boolean setIfAbsent(int namespace, String key, String value);

    /**
     * ������������
     * 
     * @param namespace
     * @param m
     */
    void multiSet(int namespace, Map<String, String> m);

    /**
     * �����������ݣ�ֻ�е����ݲ�����ʱ������
     * 
     * @param namespace
     * @param m
     */
    void multiSetIfAbsent(int namespace, Map<String, String> m);

    /**
     * ��ȡ���� ע�ⲻ���ô˷���ȥ��ȡincrement����������key
     * 
     * @param namespace
     * @param key
     * @return
     */
    String get(int namespace, Object key);

    /**
     * ��������ͬʱ���������ݣ��˷�����ԭ�Ӳ��� ע�ⲻ���ô˷���ȥ��ȡincrement����������key
     * 
     * @param namespace
     * @param key
     * @param value
     * @return
     */
    String getAndSet(int namespace, String key, String value);

    /**
     * ������ȡ���� ע�ⲻ���ô˷���ȥ��ȡincrement����������key
     * 
     * @param namespace
     * @param keys
     * @return
     */
    List<String> multiGet(int namespace, Collection<String> keys);

}
