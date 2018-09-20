package com.chm.generator.generate.enums;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen-hongmin
 * @date 2018/9/20 13:53
 * @since V1.0
 */
public final class IgnoreColumn {

    private IgnoreColumn() {

    }

    public static Set<String> globalIgnore;

    public static Set<String> updateIgnore;

    static {
        globalIgnore = new HashSet<>();
        updateIgnore = new HashSet<>();

        globalIgnore.add("update_time");
        globalIgnore.add("create_time");

        updateIgnore.add("update_time");
        updateIgnore.add("create_time");
        updateIgnore.add("del_flag");
        updateIgnore.add("id");
    }



}
