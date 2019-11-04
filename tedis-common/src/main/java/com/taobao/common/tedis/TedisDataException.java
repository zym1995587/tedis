/**
 * (C) 2011-2012 Alibaba Group Holding Limited.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * version 2 as published by the Free Software Foundation.
 *
 */
package com.taobao.common.tedis;

/**
 * Tedis���ݲ������⣬���׳����쳣
 * @author juxin.zj E-mail:juxin.zj@taobao.com
 * @since 2012-4-24 ����11:24:25
 * @version 1.0
 */
public class TedisDataException extends TedisException {

    private static final long serialVersionUID = 7530985946271632187L;

    public TedisDataException(String message) {
        super(message);
    }

    public TedisDataException(Throwable cause) {
        super(cause);
    }

    public TedisDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
