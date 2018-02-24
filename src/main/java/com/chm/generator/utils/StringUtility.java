/**
 * Copyright 2006-2016 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.chm.generator.utils;

/**
 *
 * @author chen-hongmin
 */
public class StringUtility {


    private StringUtility() {
        super();
    }

    public static boolean stringHasValue(String s) {
        return s != null && s.length() > 0;
    }


    public static boolean isTrue(String s) {
        return "true".equalsIgnoreCase(s);
    }

    public static boolean isFalse(String s) {
        return "false".equalsIgnoreCase(s);
    }

    public static String getStrOfNull(String str) {

        return str == null ? "" : str;
    }

    public static void main(String[] args) {
        String property = System.getProperty("user.name");
        System.out.println(property);
    }


}
