package com.chm.generator.message;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author chen-hongmin
 * @version V1.0
 * @since 2017/9/19 17:51.
 */
public class MessageSource {

    private static final String BUNDLE_NAME = "internal.message"; //$NON-NLS-1$

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

            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
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
            return MessageFormat.format(RESOURCE_BUNDLE.getString(key), new Object[]{param});
        } catch (MissingResourceException e) {
            return key;
        }
    }

    public static void main(String[] args) {

        System.out.println(getMessage("ValidationError.2","sadasdas","ssss"));

    }

}
