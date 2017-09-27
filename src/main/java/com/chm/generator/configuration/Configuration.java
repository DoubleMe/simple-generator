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
package com.chm.generator.configuration;

import com.chm.generator.configuration.config.Context;
import com.chm.generator.configuration.config.properties.PropertyHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class Configuration.
 *
 * @author chen-hongmin
 */
public class Configuration extends PropertyHolder{

    /** The contexts. */
    private List<Context> contexts;

    /** The class path entries. */
    private String classPathEntries;

    public Configuration(){
        contexts = new ArrayList<>();
    }

    public List<Context> getContexts() {

        return contexts;
    }

    public void setContexts(List<Context> contexts) {

        this.contexts = contexts;
    }

    public String getClassPathEntries() {

        return classPathEntries;
    }

    public void setClassPathEntries(String classPathEntries) {

        this.classPathEntries = classPathEntries;
    }

    public void addContext(Context context){

        if (contexts != null){
            contexts.add(context);
        }
    }
}
