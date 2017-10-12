package com.chm.generator.generate;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen-hongmin
 * @since 2017/10/12 16:00
 */
public class GenerateMessageHolder {

    /**
     * 异常集合
     */
    public static Set<String> errors;

    /**
     * 警告集合
     */
    public static Set<String> warnings;

    static {
        errors = new HashSet<>();
        warnings = new HashSet<>();
    }

    public static void addError(String error){
        errors.add(error);
    }

    public static void addWarning(String warning){
        warnings.add(warning);
    }

    public static void clear(){
        errors.clear();
        warnings.clear();
    }

    public static Set<String> getErrors() {
        return errors;
    }

    public static Set<String> getWarnings() {
        return warnings;
    }

    public static boolean hasError(){
        return !errors.isEmpty();
    }

    public static boolean hasWarning(){
        return !warnings.isEmpty();
    }

    public static String getError(){

        String ls = System.getProperty("line.separator"); //$NON-NLS-1$
        if (ls == null) {
            ls = "\n"; //$NON-NLS-1$
        }
        String lineSeparator = ls;
        StringBuffer sb = new StringBuffer();
        for (String error : errors){
            sb.append("error: ").append(error).append(lineSeparator);
        }

        return sb.toString();
    }

    public static void outPutWarning(){

        for (String warning : warnings){
            System.out.println("warning :" + warning);
        }
    }
}
