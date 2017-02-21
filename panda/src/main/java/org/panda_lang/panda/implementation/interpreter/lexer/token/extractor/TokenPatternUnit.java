/*
 * Copyright (c) 2015-2017 Dzikoysk
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

package org.panda_lang.panda.implementation.interpreter.lexer.token.extractor;

import org.panda_lang.framework.interpreter.lexer.token.TokenType;
import org.panda_lang.framework.interpreter.lexer.token.defaults.EqualableToken;

public class TokenPatternUnit extends EqualableToken {

    public static final TokenPatternUnit GAP = new TokenPatternUnit(new TokenType("GAP"), "*");

    private final TokenType tokenType;
    private final String token;

    public TokenPatternUnit(TokenType tokenType, String token) {
        this.tokenType = tokenType;
        this.token = token;
    }

    public boolean isGap() {
        return GAP.getType().equals(tokenType);
    }

    @Override
    public String getTokenValue() {
        return token;
    }

    @Override
    public TokenType getType() {
        return tokenType;
    }

    @Override
    public String toString() {
        return tokenType.getTypeName() + ": " + token;
    }

}
