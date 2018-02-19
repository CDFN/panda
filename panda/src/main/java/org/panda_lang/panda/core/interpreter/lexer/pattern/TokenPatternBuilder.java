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

package org.panda_lang.panda.core.interpreter.lexer.pattern;

import org.panda_lang.panda.framework.language.interpreter.lexer.Syntax;
import org.panda_lang.panda.framework.language.interpreter.token.Token;
import org.panda_lang.panda.framework.language.interpreter.token.TokenType;

import java.util.ArrayList;
import java.util.List;

public class TokenPatternBuilder {

    private final List<TokenPatternUnit> units;
    private boolean keepOpposites;
    private boolean lastIndexAlgorithm;

    public TokenPatternBuilder() {
        this.units = new ArrayList<>();
        this.keepOpposites = true;
    }

    public TokenPatternBuilder keepOpposites(boolean keepOpposites) {
        this.keepOpposites = keepOpposites;
        return this;
    }

    public TokenPatternBuilder lastIndexAlgorithm(boolean lastIndexAlgorithm) {
        this.lastIndexAlgorithm = lastIndexAlgorithm;
        return this;
    }

    public TokenPatternBuilder unit(Token token) {
        return unit(token.getType(), token.getTokenValue());
    }

    public TokenPatternBuilder unit(TokenType type, String token) {
        TokenPatternUnit unit = new TokenPatternUnit(type, token);
        units.add(unit);
        return this;
    }

    public TokenPatternBuilder simpleHollow() {
        units.add(TokenPatternUnit.SIMPLE_HOLLOW);
        return this;
    }

    public TokenPatternBuilder hollow() {
        units.add(TokenPatternUnit.HOLLOW);
        return this;
    }

    public TokenPatternBuilder compile(Syntax syntax, String expression) {
        TokenPatternCompiler compiler = new TokenPatternCompiler(this, syntax);
        compiler.compile(expression);
        return this;
    }

    public TokenPattern build() {
        TokenPatternUnit[] unitsArray = new TokenPatternUnit[units.size()];
        units.toArray(unitsArray);

        return new TokenPattern(unitsArray, keepOpposites, lastIndexAlgorithm);
    }

}
