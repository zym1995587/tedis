/**
 * (C) 2011-2012 Alibaba Group Holding Limited.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * version 2 as published by the Free Software Foundation.
 *
 */
package com.taobao.common.tedis;

import java.util.concurrent.atomic.AtomicInteger;

import com.taobao.common.tedis.binary.RedisCommands;
import com.taobao.common.tedis.config.HAConfig.ServerProperties;

/**
 * @author juxin.zj E-mail:juxin.zj@taobao.com
 * @since 2012-4-23 ����10:20:03
 * @version 1.0
 */
public interface Single {

    /**
     * ��ȡ����Redis�������
     * @return
     */
    RedisCommands getTedis();

    /**
     * ��ȡ����ʵ��������
     * @return
     */
    ServerProperties getProperties();

    /**
     * ��ȡ����ʵ����ʧ�ܴ���
     * @return
     */
    AtomicInteger getErrorCount();

}
