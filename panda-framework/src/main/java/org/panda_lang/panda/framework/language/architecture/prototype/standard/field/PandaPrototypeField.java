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

package org.panda_lang.panda.framework.language.architecture.prototype.standard.field;

import org.panda_lang.panda.framework.design.architecture.prototype.field.PrototypeField;
import org.panda_lang.panda.framework.design.architecture.value.Value;
import org.panda_lang.panda.framework.design.runtime.expression.Expression;
import org.panda_lang.panda.framework.language.architecture.prototype.standard.parameter.PandaParameterizedExecutable;

public class PandaPrototypeField extends PandaParameterizedExecutable implements PrototypeField {

    private final int fieldIndex;
    private final boolean isStatic;
    private final boolean isNative;
    private final boolean mutable;
    private final boolean nullable;

    private Expression defaultValue;
    private Value staticValue;

    protected PandaPrototypeField(PandaPrototypeFieldBuilder builder) {
        // super(builder.type, builder.name, 0, builder.mutable, builder.nullable);
        super(builder);

        this.fieldIndex = builder.fieldIndex;
        this.isStatic = builder.isStatic;
        this.isNative = builder.isNative;
        this.mutable = builder.mutable;
        this.nullable = builder.nullable;
    }

    @Override
    public void setDefaultValue(Expression defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public void setStaticValue(Value staticValue) {
        this.staticValue = staticValue;
    }

    @Override
    public boolean isNullable() {
        return nullable;
    }

    @Override
    public boolean isMutable() {
        return mutable;
    }

    @Override
    public boolean isStatic() {
        return isStatic;
    }

    @Override
    public boolean isNative() {
        return isNative;
    }

    @Override
    public boolean hasDefaultValue() {
        return defaultValue != null;
    }

    @Override
    public Value getStaticValue() {
        return staticValue;
    }

    @Override
    public Expression getDefaultValue() {
        return defaultValue;
    }

    @Override
    public int getFieldIndex() {
        return fieldIndex;
    }

    public static PandaPrototypeFieldBuilder builder() {
        return new PandaPrototypeFieldBuilder();
    }

    public static class PandaPrototypeFieldBuilder extends PandaParameterizedExecutable.PandaParametrizedExecutableBuilder<PandaPrototypeFieldBuilder> {

        protected int fieldIndex;
        protected boolean isStatic;
        protected boolean isNative;
        protected boolean mutable;
        protected boolean nullable;

        private PandaPrototypeFieldBuilder() { }

        public PandaPrototypeFieldBuilder fieldIndex(int fieldIndex) {
            this.fieldIndex = fieldIndex;
            return this;
        }

        public PandaPrototypeFieldBuilder mutable(boolean mutable) {
            this.mutable = mutable;
            return this;
        }

        public PandaPrototypeFieldBuilder nullable(boolean nullable) {
            this.nullable = nullable;
            return this;
        }

        public PandaPrototypeFieldBuilder isStatic(boolean isStatic) {
            this.isStatic = isStatic;
            return this;
        }

        public PandaPrototypeFieldBuilder isNative(boolean isNative) {
            this.isNative = isNative;
            return this;
        }

        public PandaPrototypeField build() {
            return new PandaPrototypeField(this);
        }

    }

}
