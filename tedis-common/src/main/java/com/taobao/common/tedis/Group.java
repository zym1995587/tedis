/**
 * (C) 2011-2012 Alibaba Group Holding Limited.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * version 2 as published by the Free Software Foundation.
 *
 */
package com.taobao.common.tedis;

import com.taobao.common.tedis.binary.RedisCommands;
import com.taobao.common.tedis.config.ConfigManager;

/**
 * ��Ⱥ���������²����ϸ��
 * @author juxin.zj E-mail:juxin.zj@taobao.com
 * @since 2012-4-23 ����10:20:11
 * @version 1.0
 */
public interface Group {

    /**
     * ��ȡ��Ⱥ�������
     * @return
     */
    RedisCommands getTedis();

    /**
     * ע����Ⱥʵ��
     */
    void destroy();

    /**
     * �������ù�������Ĭ��ʵ���У�Diamond��ZK���ļ�
     * @param cm
     */
    void setConfigManager(ConfigManager cm);

    /**
     * ʹ�ü�Ⱥ֮ǰ��Ҫ�ȳ�ʼ��
     */
    void init();

}
