/**
 * Copyright 2006-2017 the original author or authors.
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
package com.chm.generator.types;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chen-hongmin
 */
public class JavaTypeResolver {


    protected static Map<Integer, String> typeMap = new HashMap<>();

    protected static Map<Integer, String> jdbcTypeMap = new HashMap<>();

    static {
        typeMap.put(Types.ARRAY, Object.class.getName());
        typeMap.put(Types.BIGINT, Long.class.getName());
        typeMap.put(Types.BINARY, "byte[]");
        typeMap.put(Types.BIT, Integer.class.getName());
        typeMap.put(Types.BLOB, "byte[]");
        typeMap.put(Types.BOOLEAN, Boolean.class.getName());
        typeMap.put(Types.CHAR, String.class.getName());
        typeMap.put(Types.CLOB, String.class.getName());
        typeMap.put(Types.DATALINK, Object.class.getName());
        typeMap.put(Types.DATE, Date.class.getName());
        typeMap.put(Types.DECIMAL, BigDecimal.class.getName());
        typeMap.put(Types.DISTINCT, Object.class.getName());
        typeMap.put(Types.DOUBLE, Double.class.getName());
        typeMap.put(Types.FLOAT, Double.class.getName());
        typeMap.put(Types.INTEGER, Integer.class.getName());
        typeMap.put(Types.JAVA_OBJECT, Object.class.getName());
        typeMap.put(Types.LONGNVARCHAR, String.class.getName());
        typeMap.put(Types.LONGVARBINARY, "byte[]");
        typeMap.put(Types.LONGVARCHAR, String.class.getName());
        typeMap.put(Types.NCHAR, String.class.getName());
        typeMap.put(Types.NCLOB, String.class.getName());
        typeMap.put(Types.NVARCHAR, String.class.getName());
        typeMap.put(Types.NULL, Object.class.getName());
        typeMap.put(Types.NUMERIC, BigDecimal.class.getName());
        typeMap.put(Types.OTHER, Object.class.getName());
        typeMap.put(Types.REAL, Float.class.getName());
        typeMap.put(Types.REF, Object.class.getName());
        typeMap.put(Types.SMALLINT, Short.class.getName());
        typeMap.put(Types.STRUCT, Object.class.getName());
        typeMap.put(Types.TIME, Date.class.getName());
        typeMap.put(Types.TIMESTAMP, Date.class.getName());
        typeMap.put(Types.TINYINT, Integer.class.getName());
        typeMap.put(Types.VARBINARY, "byte[]");
        typeMap.put(Types.VARCHAR, String.class.getName());

        jdbcTypeMap.put(Types.ARRAY, "ARRAY");
        jdbcTypeMap.put(Types.BIGINT, "BIGINT");
        jdbcTypeMap.put(Types.BINARY, "BINARY");
        jdbcTypeMap.put(Types.BIT, "BIT");
        jdbcTypeMap.put(Types.BLOB, "BLOB");
        jdbcTypeMap.put(Types.BOOLEAN, "BOOLEAN");
        jdbcTypeMap.put(Types.CHAR, "CHAR");
        jdbcTypeMap.put(Types.CLOB, "CLOB");
        jdbcTypeMap.put(Types.DATALINK, "DATALINK");
        jdbcTypeMap.put(Types.DATE, "DATE");
        jdbcTypeMap.put(Types.DECIMAL, "DECIMAL");
        jdbcTypeMap.put(Types.DISTINCT, "DISTINCT");
        jdbcTypeMap.put(Types.DOUBLE, "DOUBLE");
        jdbcTypeMap.put(Types.FLOAT, "FLOAT");
        jdbcTypeMap.put(Types.INTEGER, "INTEGER");
        jdbcTypeMap.put(Types.JAVA_OBJECT, "JAVA_OBJECT");
        jdbcTypeMap.put(Types.LONGNVARCHAR, "LONGNVARCHAR");
        jdbcTypeMap.put(Types.LONGVARBINARY, "LONGVARBINARY");
        jdbcTypeMap.put(Types.LONGVARCHAR, "LONGVARCHAR");
        jdbcTypeMap.put(Types.NCHAR, "NCHAR");
        jdbcTypeMap.put(Types.NCLOB, "NCLOB");
        jdbcTypeMap.put(Types.NVARCHAR, "NVARCHAR");
        jdbcTypeMap.put(Types.NULL, "NULL");
        jdbcTypeMap.put(Types.NUMERIC, "NUMERIC");
        jdbcTypeMap.put(Types.OTHER, "OTHER");
        jdbcTypeMap.put(Types.REAL, "REAL");
        jdbcTypeMap.put(Types.REF, "REF");
        jdbcTypeMap.put(Types.SMALLINT, "SMALLINT");
        jdbcTypeMap.put(Types.STRUCT, "STRUCT");
        jdbcTypeMap.put(Types.TIME, "TIME");
        jdbcTypeMap.put(Types.TIMESTAMP, "TIMESTAMP");
        jdbcTypeMap.put(Types.TINYINT, "INTEGER");
        jdbcTypeMap.put(Types.VARBINARY, "VARBINARY");
        jdbcTypeMap.put(Types.VARCHAR, "VARCHAR");
    }

    public JavaTypeResolver() {

    }

    /**
     * 获取jdbcType 类型 未知类型 用Object
     *
     * @param jdbcType
     * @return
     */
    public static String getJdbcTypeName(Integer jdbcType) {

        String typeName = typeMap.get(jdbcType);

        if (typeName == null) {
            typeName = Object.class.getName();
        }
        return typeName;
    }

    /**
     * 获取jdbcType 类型 未知类型 用Object
     *
     * @param jdbcType
     * @return
     */
    public static String getSimpleTypeName(Integer jdbcType) {

        String typeName = typeMap.get(jdbcType);

        if (typeName == null) {
            typeName = Object.class.getName();
        }
        int index = typeName.lastIndexOf(".");
        if (index != -1) {
            typeName = typeName.substring(index + 1, typeName.length());
        }
        return typeName;
    }

    /**
     * 获取mybatis jdbcType 类型
     *
     * @param jdbcType
     * @return
     */
    public static String getSqlJdbcTypeName(Integer jdbcType) {

        String typeName = jdbcTypeMap.get(jdbcType);

        return typeName;
    }

}
