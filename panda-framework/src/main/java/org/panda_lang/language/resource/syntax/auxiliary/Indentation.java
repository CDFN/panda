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

package org.panda_lang.language.resource.syntax.auxiliary;

import org.panda_lang.language.interpreter.token.TokenType;
import org.panda_lang.language.interpreter.token.EqualableToken;
import org.panda_lang.language.resource.syntax.TokenTypes;
import org.panda_lang.utilities.commons.CharacterUtils;
import org.panda_lang.utilities.commons.StringUtils;

/**
 * Indentation determines the amount of whitespaces at the beginning of line. The tab character is exactly equal to 4 whitespaces.
 */
public final class Indentation extends EqualableToken {

    private final int size;

    public Indentation(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String getValue() {
        return StringUtils.buildSpace(size);
    }

    @Override
    public TokenType getType() {
        return TokenTypes.INDENTATION;
    }

    /**
     * Create indentation based on paragraph
     *
     * @param paragraph {@link StringUtils#extractParagraph(String)}
     * @return instance of indentation based on paragraph
     */
    public static Indentation valueOf(String paragraph) {
        int size = 0;

        for (char c : paragraph.toCharArray()) {
            if (c == CharacterUtils.TAB) {
                size += 4;
                continue;
            }

            size++;
        }

        return new Indentation(size);
    }

}
