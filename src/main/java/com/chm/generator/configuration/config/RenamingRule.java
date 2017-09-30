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
package com.chm.generator.configuration.config;


import com.chm.generator.configuration.config.xml.Attribute;
import com.chm.generator.configuration.config.xml.XmlElement;
import com.chm.generator.message.MessageSource;
import com.chm.generator.utils.StringUtility;

import java.util.List;


/**
 *
 * @author chen-hongmin
 *
 */
public class RenamingRule {

    private String searchString;

    private String replaceString;

    public String getReplaceString() {

        return replaceString;
    }

    public void setReplaceString(String replaceString) {

        this.replaceString = replaceString;
    }

    public String getSearchString() {

        return searchString;
    }

    public void setSearchString(String searchString) {

        this.searchString = searchString;
    }

    public void validate(List<String> errors, String tableName) {

        if (!StringUtility.stringHasValue(searchString)) {
            errors.add(MessageSource.getMessage("ValidationError.28", tableName)); //$NON-NLS-1$
        }
    }

    public XmlElement toXmlElement() {

        XmlElement xmlElement = new XmlElement("domainRenamingRule"); //$NON-NLS-1$
        xmlElement.addAttribute(new Attribute("searchString", searchString)); //$NON-NLS-1$

        if (replaceString != null) {
            xmlElement.addAttribute(new Attribute("replaceString", replaceString)); //$NON-NLS-1$
        }

        return xmlElement;
    }

    public String rename(String sourceName){

        if (searchString == null || "".equals(searchString)){
            return sourceName;
        }
        if (replaceString == null){
            replaceString = "";
        }
        sourceName = sourceName.replace(searchString,replaceString);

        return sourceName;
    }
}
