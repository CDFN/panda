/*
 * Copyright (c) 2015-2019 Dzikoysk
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

package org.panda_lang.panda.framework.language.resource.syntax.operator;

import org.jetbrains.annotations.Nullable;
import org.panda_lang.panda.framework.design.interpreter.token.TokenRepresentation;
import org.panda_lang.panda.framework.design.interpreter.token.TokenType;
import org.panda_lang.panda.framework.design.interpreter.token.snippet.Snippet;
import org.panda_lang.panda.framework.design.interpreter.token.snippet.Snippetable;

import java.util.List;
import java.util.Objects;

public class OperatorUtils {

    /**
     * Check if operator has the given family
     *
     * @param operator the operator to check
     * @param family the family to compare
     * @return true if the operator has the given family
     */
    public static boolean isMemberOf(@Nullable Operator operator, @Nullable String family) {
        if (operator == null) {
            return false;
        }

        return Objects.equals(operator.getFamily(), family);
    }

    /**
     * Get index of operator with the given family
     *
     * @param snippetable the source to search in
     * @param family the family to search for
     * @return index of operator with the given family, otherwise -1
     */
    public static int indexOf(Snippetable snippetable, @Nullable String family) {
        Snippet snippet = snippetable.toSnippet();
        List<TokenRepresentation> representations = snippet.getTokensRepresentations();

        for (int index = 0; index < representations.size(); index++) {
            TokenRepresentation representation = representations.get(index);

            if (representation.getType() != TokenType.OPERATOR) {
                continue;
            }

            Operator operator = representation.toToken();

            if (isMemberOf(operator, family)) {
                return index;
            }
        }

        return -1;
    }

}
