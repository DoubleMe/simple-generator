package com.chm.generator.utils;

import com.chm.generator.message.MessageSource;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chen-hongmin
 * @version V1.0
 * @since 2017/9/29 17:32.
 */
public class ClassLoaderHolder {

    private static List<ClassLoader> externalClassLoaders = new ArrayList<>();


    public static synchronized void addExternalClassLoader(ClassLoader classLoader) {

        externalClassLoaders.add(classLoader);
    }

    public static synchronized void addExternalClassLoader(String classPathEntry) {

        ClassLoader customClassloader = getCustomClassloader(classPathEntry);

        externalClassLoaders.add(customClassloader);
    }

    public static Class<?> externalClassForName(String className) throws ClassNotFoundException {

        Class<?> clazz;

        for (ClassLoader classLoader : externalClassLoaders) {
            try {
                clazz = Class.forName(className, true, classLoader);
                return clazz;
            } catch (Throwable e) {
                // ignore - fail safe below
            }
        }

        return internalClassForName(className);
    }

    public static Class<?> internalClassForName(String type) throws ClassNotFoundException {

        Class<?> clazz = null;

        try {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            clazz = Class.forName(type, true, cl);
        } catch (Exception e) {
            // ignore - failsafe below
        }

        if (clazz == null) {
            clazz = Class.forName(type, true, ClassLoaderHolder.class.getClassLoader());
        }

        return clazz;
    }

    public static ClassLoader getCustomClassloader(String classPathEntry) {

        List<URL> urls = new ArrayList<>();
        File file;


        file = new File(classPathEntry);
        if (!file.exists()) {
            throw new RuntimeException(MessageSource.getMessage("RuntimeError.9", classPathEntry));
        }

        try {
            urls.add(file.toURI().toURL());
        } catch (MalformedURLException e) {
            // this shouldn't happen, but just in case...
            throw new RuntimeException(MessageSource.getMessage("RuntimeError.9", classPathEntry));
        }


        ClassLoader parent = Thread.currentThread().getContextClassLoader();

        URLClassLoader ucl = new URLClassLoader(urls.toArray(new URL[urls.size()]), parent);

        return ucl;
    }
}
