/**
 * (C) 2011-2012 Alibaba Group Holding Limited.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * version 2 as published by the Free Software Foundation.
 *
 */
package com.taobao.common.tedis.support.matcher;

/**
 * ��AutoComplete�Ķ�����Ҫʵ�ִ˽ӿ�
 *
 * @author juxin.zj E-mail:juxin.zj@taobao.com
 * @since 2011-9-20 ����11:44:48
 * @version 1.0
 */
public interface Matchable {

    /**
     * @return ����ҪMatch���ַ�����������Ʒ�ı���ȵ�
     */
    String matchString();

    /**
     * @return ����Match�������tedis�洢��key��������Ʒ��Id�ȵ�
     */
    <K> K matchKey();

    /**
     * @return ����Match��score��match��ʱ���մ�score����
     */
    double matchScore();
}
