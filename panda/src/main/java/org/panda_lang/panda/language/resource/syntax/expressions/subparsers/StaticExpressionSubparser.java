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

package org.panda_lang.panda.language.resource.syntax.expressions.subparsers;

import org.jetbrains.annotations.Nullable;
import org.panda_lang.language.interpreter.parser.Components;
import org.panda_lang.language.interpreter.parser.Context;
import org.panda_lang.language.interpreter.parser.expression.ExpressionContext;
import org.panda_lang.language.interpreter.parser.expression.ExpressionResult;
import org.panda_lang.language.interpreter.parser.expression.ExpressionSubparserWorker;
import org.panda_lang.language.interpreter.token.TokenInfo;
import org.panda_lang.language.architecture.expression.StaticExpression;
import org.panda_lang.language.architecture.type.utils.VisibilityComparator;
import org.panda_lang.language.interpreter.parser.expression.PartialResultSubparser;
import org.panda_lang.language.resource.syntax.TokenTypes;

public final class StaticExpressionSubparser implements PartialResultSubparser {

    @Override
    public ExpressionSubparserWorker createWorker(Context context) {
        return new StaticWorker().withSubparser(this);
    }

    @Override
    public String getSubparserName() {
        return "static";
    }

    private static final class StaticWorker extends AbstractExpressionSubparserWorker {

        @Override
        public @Nullable ExpressionResult next(ExpressionContext context, TokenInfo token) {
            if (token.getType() != TokenTypes.UNKNOWN || context.hasResults() || !context.getSynchronizedSource().hasNext()) {
                return null;
            }

            return context.toContext().getComponent(Components.IMPORTS)
                    .forName(token.getValue())
                    .filter(reference -> VisibilityComparator.requireAccess(reference, context.toContext(), token))
                    .map(type -> ExpressionResult.of(new StaticExpression(type)))
                    .getOrNull();
        }

    }

}
