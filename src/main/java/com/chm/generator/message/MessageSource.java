package com.chm.generator.message;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author chen-hongmin
 * @version V1.0
 * @since 2017/9/19 17:51.
 */
public class MessageSource {

    private static final String BUNDLE_NAME = "internal.message";

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    private MessageSource() {

    }

    /**
     * 从source 获取message
     * @param key msg key
     * @return message if not exist return key
     */
    public static String getMessage(String key) {

        try {
            String message = new String(RESOURCE_BUNDLE.getString(key).getBytes("ISO-8859-1"), "UTF-8");
            return message;
        } catch (MissingResourceException e) {
            e.printStackTrace();
            return key;
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
            return key;
        }
    }

    /**
     *  从source 获取message
     * @param key msg key
     * @param param 替换参数
     * @return message if not exist return key
     */
    public static String getMessage(String key, String... param) {

        try {
            String message = new String(RESOURCE_BUNDLE.getString(key).getBytes("ISO-8859-1"), "UTF-8");
            return MessageFormat.format(message, param);
        } catch (MissingResourceException e) {
            e.printStackTrace();
            return key;
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
            return key;
        }
    }

}
