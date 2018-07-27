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

package org.panda_lang.panda.language.interpreter.parsers;

import org.panda_lang.panda.design.interpreter.parser.pipeline.registry.ParserRegistration;
import org.panda_lang.panda.language.interpreter.parsers.prototype.parsers.ClassPrototypeParser;

/**
 * Used by {@link ParserRegistration}
 */
public class PandaPipelines {

    /**
     * Used by {@link ClassPrototypeParser}
     */
    public static final String PROTOTYPE = "prototype";

    /**
     * Used by {@link org.panda_lang.panda.design.interpreter.parser.defaults.ScopeParser}
     */
    public static final String SCOPE = "scope";

    /**
     * Used by {@link org.panda_lang.panda.language.interpreter.parsers.statement.scope.block.BlockParser}
     */
    public static final String BLOCK = "block";

    /**
     * Used by {@link org.panda_lang.panda.language.interpreter.parsers.statement.StatementParser}
     */
    public static final String STATEMENT = "statement";

}
