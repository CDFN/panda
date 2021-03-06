/*
 * Copyright (c) 2020 Dzikoysk
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

package org.panda_lang.panda.language.interpreter.parser.autowired;

import org.panda_lang.language.interpreter.parser.pipeline.Handler;
import org.panda_lang.utilities.commons.function.Option;

interface AutowiredContent<P> {

    /**
     * Get associated with bootstrap initializer
     *
     * @return the interceptor
     */
    IterationInitializer<P> getInitializer();

    /**
     * Get associated parser handler
     *
     * @return the parser handler
     */
    Option<Handler> getHandler();

    /**
     * Get pattern object
     *
     * @return the pattern
     */
    Option<P> getPattern();

    /**
     * Get instance of class used by bootstrap
     *
     * @return the parser instance
     */
    Object getInstance();

}
