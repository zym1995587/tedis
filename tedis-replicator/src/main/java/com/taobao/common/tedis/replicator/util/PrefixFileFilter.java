/**
 * (C) 2011-2012 Alibaba Group Holding Limited.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * version 2 as published by the Free Software Foundation.
 *
 */
package com.taobao.common.tedis.replicator.util;

import java.io.File;
import java.io.FileFilter;

public class PrefixFileFilter implements FileFilter {
    private String prefix;

    public PrefixFileFilter(String prefix) {
        this.prefix = prefix;
    }

    public boolean accept(File file) {
        return !file.isDirectory() && file.getName().startsWith(prefix);
    }
}
