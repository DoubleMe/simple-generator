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
package com.chm.generator.configuration;

import com.chm.generator.configuration.config.properties.PropertyHolder;

/**
 * @author chen-hongmin
 */
public class JavaClientGeneratorConfiguration extends PropertyHolder {

    private String targetPackage;

    private String targetProject;

    /**
     *
     */
    public JavaClientGeneratorConfiguration() {

        super();
    }

    public String getTargetProject() {

        return targetProject;
    }

    public void setTargetProject(String targetProject) {

        this.targetProject = targetProject;
    }

    public String getTargetPackage() {

        return targetPackage;
    }

    public void setTargetPackage(String targetPackage) {

        this.targetPackage = targetPackage;
    }


}
