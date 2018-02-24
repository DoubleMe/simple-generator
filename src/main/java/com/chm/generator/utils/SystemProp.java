package com.chm.generator.utils;

import java.util.Date;

/**
 * @author chen-hongmin
 * @date 2018/2/24 14:59
 * @since V1.0
 */
public class SystemProp {

    public static final String user = System.getProperty("user.name");


    public static String getSystemDate(){

        String s = DateUtils.formatDate(new Date(), DateUtils.DATE_FORMAT_YMDHMS);

        return s;
    }
}
