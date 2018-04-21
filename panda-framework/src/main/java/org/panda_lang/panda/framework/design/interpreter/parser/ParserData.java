/*
 * Copyright (c) 2015-2018 Dzikoysk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.panda_lang.panda.framework.design.interpreter.parser;

public interface ParserData {

    /**
     * Clone ParserData to a new instance with old components
     */
    ParserData fork();

    /***
     * @param componentName a name of the specified component
     * @return selected component
     */
    <T> T getComponent(String componentName);

    /**
     * @param componentName a name of the specified component
     */
    void setComponent(String componentName, Object component);

}
