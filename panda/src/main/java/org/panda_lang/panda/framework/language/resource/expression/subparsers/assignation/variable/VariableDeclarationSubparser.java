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

package org.panda_lang.panda.framework.language.resource.expression.subparsers.assignation.variable;

import org.jetbrains.annotations.Nullable;
import org.panda_lang.panda.framework.design.architecture.dynamic.assigner.Assigner;
import org.panda_lang.panda.framework.design.architecture.statement.Scope;
import org.panda_lang.panda.framework.design.architecture.value.Variable;
import org.panda_lang.panda.framework.design.interpreter.parser.Context;
import org.panda_lang.panda.framework.design.interpreter.parser.PandaPipelines;
import org.panda_lang.panda.framework.design.interpreter.parser.bootstrap.BootstrapInitializer;
import org.panda_lang.panda.framework.design.interpreter.parser.bootstrap.annotations.Autowired;
import org.panda_lang.panda.framework.design.interpreter.parser.bootstrap.annotations.Component;
import org.panda_lang.panda.framework.design.interpreter.parser.loader.Registrable;
import org.panda_lang.panda.framework.design.interpreter.parser.pipeline.Channel;
import org.panda_lang.panda.framework.design.interpreter.parser.pipeline.ParserHandler;
import org.panda_lang.panda.framework.design.interpreter.token.TokenRepresentation;
import org.panda_lang.panda.framework.design.interpreter.token.TokenType;
import org.panda_lang.panda.framework.design.interpreter.token.snippet.Snippet;
import org.panda_lang.panda.framework.design.interpreter.token.stream.SourceStream;
import org.panda_lang.panda.framework.design.runtime.expression.Expression;
import org.panda_lang.panda.framework.language.architecture.dynamic.assigner.VariableAssignerUtils;
import org.panda_lang.panda.framework.language.resource.expression.subparsers.assignation.AssignationPriorities;
import org.panda_lang.panda.framework.language.resource.expression.subparsers.assignation.AssignationSubparserBootstrap;
import org.panda_lang.panda.framework.language.resource.syntax.keyword.Keywords;

import java.util.Objects;
import java.util.Optional;

@Registrable(pipeline = PandaPipelines.ASSIGNER_LABEL, priority = AssignationPriorities.VARIABLE_DECLARATION)
public class VariableDeclarationSubparser extends AssignationSubparserBootstrap {

    private static final VariableParser VARIABLE_PARSER = new VariableParser();

    @Override
    protected BootstrapInitializer<@Nullable Assigner<?>> initialize(Context context, BootstrapInitializer<@Nullable Assigner<?>> initializer) {
        return initializer;
    }

    @Override
    protected Object customHandle(ParserHandler handler, Context context, Channel channel, Snippet source) {
        if (source.size() < 2) {
            return false;
        }

        TokenRepresentation name = Objects.requireNonNull(source.getLast());

        if (name.getType() != TokenType.UNKNOWN) {
            return false;
        }

        Optional<Snippet> typeValue = DeclarationUtils.readTypeBackwards(source.subSource(0, source.size() - 1));

        if (!typeValue.isPresent()) {
            return false;
        }

        Snippet type = typeValue.get();
        Snippet modifiers = source.subSource(0, source.size() - 1 - type.size());

        // max amount of modifiers: mut, nil
        if (modifiers.size() > 2) {
            return false;
        }

        int mutable = modifiers.indexOf(Keywords.MUT);

        if (mutable != Snippet.NOT_FOUND) {
            modifiers.remove(mutable);
        }

        int nillable = modifiers.indexOf(Keywords.NIL);

        if (nillable != Snippet.NOT_FOUND) {
            modifiers.remove(nillable);
        }

        if (modifiers.size() > 0) {
            return false;
        }

        channel.put("elements", new Elements(type, name, mutable != Snippet.NOT_FOUND, nillable != Snippet.NOT_FOUND));
        return true;
    }

    @Autowired
    Assigner<?> parse(Context context, @Component Scope scope, @Component SourceStream source, @Component Channel channel, @Component Expression expression) {
        Elements elements = channel.get("elements", Elements.class);

        Variable variable = VARIABLE_PARSER.createVariable(context, scope, elements.mutable, elements.nillable, elements.type, elements.name);
        return VariableAssignerUtils.of(context, scope, variable, expression);
    }

    static class Elements {

        private TokenRepresentation name;
        private Snippet type;
        private boolean mutable;
        private boolean nillable;

        Elements(Snippet type, TokenRepresentation name, boolean mutable, boolean nillable) {
            this.type = type;
            this.name = name;
            this.mutable = mutable;
            this.nillable = nillable;
        }

    }

}
