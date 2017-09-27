/**
 *    Copyright 2006-2017 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.chm.generator.types;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author chen-hongmin
 */
public class JavaTypeResolver {



    protected static  Map<Integer, String> typeMap = new HashMap<>();

    static {
        typeMap.put(Types.ARRAY, Object.class.getName());
        typeMap.put(Types.BIGINT,Long.class.getName());
        typeMap.put(Types.BINARY,"byte[]"); //$NON-NLS-1$
        typeMap.put(Types.BIT, Boolean.class.getName());
        typeMap.put(Types.BLOB, "byte[]"); //$NON-NLS-1$
        typeMap.put(Types.BOOLEAN,Boolean.class.getName());
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
        typeMap.put(Types.LONGVARBINARY, "byte[]"); //$NON-NLS-1$
        typeMap.put(Types.LONGVARCHAR, String.class.getName());
        typeMap.put(Types.NCHAR, String.class.getName());
        typeMap.put(Types.NCLOB, String.class.getName());
        typeMap.put(Types.NVARCHAR,String.class.getName());
        typeMap.put(Types.NULL, Object.class.getName());
        typeMap.put(Types.NUMERIC, BigDecimal.class.getName());
        typeMap.put(Types.OTHER,Object.class.getName());
        typeMap.put(Types.REAL,Float.class.getName());
        typeMap.put(Types.REF, Object.class.getName());
        typeMap.put(Types.SMALLINT, Short.class.getName());
        typeMap.put(Types.STRUCT, Object.class.getName());
        typeMap.put(Types.TIME, Date.class.getName());
        typeMap.put(Types.TIMESTAMP, Date.class.getName());
        typeMap.put(Types.TINYINT, Byte.class.getName());
        typeMap.put(Types.VARBINARY,"byte[]"); //$NON-NLS-1$
        typeMap.put(Types.VARCHAR, String.class.getName());
    }

    public JavaTypeResolver() {

    }

    /**
     * 获取jdbcType 类型 未知类型 用Object
     * @param jdbcType
     * @return
     */
    public static String getJdbcTypeName(Integer jdbcType){

        String typeName = typeMap.get(jdbcType);

        if (typeName == null){
            typeName = Object.class.getName();
        }
        return typeName;
    }

}
