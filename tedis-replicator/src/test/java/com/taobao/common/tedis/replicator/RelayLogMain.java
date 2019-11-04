/**
 * (C) 2011-2012 Alibaba Group Holding Limited.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * version 2 as published by the Free Software Foundation.
 *
 */
package com.taobao.common.tedis.replicator;

import org.apache.log4j.BasicConfigurator;

import com.taobao.common.tedis.replicator.manage.ReplicatorManager;

public class RelayLogMain {
    public static void main(String[] args) throws Exception {
        BasicConfigurator.configure();
        ReplicatorManager relay = new ReplicatorManager("relay");
        relay.start();
    }

}