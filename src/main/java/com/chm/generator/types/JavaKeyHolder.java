package com.chm.generator.types;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen-hongmin
 * @version V1.0
 * @since 2017/9/30 15:15.
 */
public class JavaKeyHolder {

    public static Set<String> keySet = new HashSet();

    static {
        keySet.add("public");
        keySet.add("protected");
        keySet.add("private");
        keySet.add("class");
        keySet.add("implements");
        keySet.add("abstract");
        keySet.add("extends");
        keySet.add("new");
        keySet.add("import");
        keySet.add("package");
        keySet.add("byte");
        keySet.add("char");
        keySet.add("boolean");
        keySet.add("short");
        keySet.add("int");
        keySet.add("float");
        keySet.add("long");
        keySet.add("double");
        keySet.add("void");
        keySet.add("null");
        keySet.add("true");
        keySet.add("false");
        keySet.add("if");
        keySet.add("else");
        keySet.add("while");
        keySet.add("for");
        keySet.add("switch");
        keySet.add("case");
        keySet.add("default");
        keySet.add("do");
        keySet.add("break");
        keySet.add("continue");
        keySet.add("return");
        keySet.add("instanceof");
        keySet.add("static");
        keySet.add("final");
        keySet.add("super");
        keySet.add("this");
        keySet.add("strictfp");
        keySet.add("synchronized");
        keySet.add("transient");
        keySet.add("volatile");
        keySet.add("catch");
        keySet.add("try");
        keySet.add("finally");
        keySet.add("throw");
        keySet.add("throws");
        keySet.add("enum");
        keySet.add("assert");
    }

    public static boolean containKey(String name){

        return keySet.contains(name);
    }
}
