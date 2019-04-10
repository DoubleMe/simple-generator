
package com.chm.generator.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期公共类
 *
 * @author jianyun.zheng
 * @Type DateUtils
 * @Desc 日期公共方法类
 * @date 2012-9-26
 * @Version V1.0
 */
public class DateUtils {


    public static final String DATE_FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_YMDHM = "yyyy-MM-dd HH:mm";


    /**
     * 用yyyy-MM-dd来格式化日期字符串
     *
     * @param format
     * @param dateStr
     * @return 如果s==null或者格式异常，返回null
     */
    public static Date getDateByFormat(String format, String dateStr) {

        try {
            return new SimpleDateFormat(format).parse(dateStr);
        } catch (ParseException e) {
            System.out.println("日期格式转换失败");
        }
        return null;
    }

    /**
     * 获取日期指定的格式的字符串
     *
     * @param date
     * @param pattern
     * @return Date 对象类型字符串形式
     */
    public static String formatDate(Date date, String pattern) {
        if (null != date) {
            DateFormat dateFomat = new SimpleDateFormat(pattern);
            return dateFomat.format(date);
        }
        return "";
    }

}
