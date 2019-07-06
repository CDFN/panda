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

package org.panda_lang.panda.framework.language.architecture.prototype.standard.parameter;

import org.panda_lang.panda.framework.design.architecture.prototype.ClassPrototypeReference;
import org.panda_lang.panda.framework.design.architecture.prototype.parameter.PrototypeParameter;
import org.panda_lang.panda.framework.design.architecture.value.Variable;
import org.panda_lang.panda.framework.language.architecture.value.PandaVariable;

public class PandaParameter implements PrototypeParameter {

    private final String name;
    private final ClassPrototypeReference reference;
    private final boolean varargs;

    public PandaParameter(ClassPrototypeReference reference, String name, boolean varargs) {
        this.name = name;
        this.reference = reference;
        this.varargs = varargs;
    }

    @Override
    public Variable toVariable() {
        return new PandaVariable(reference, name, false, false);
    }

    @Override
    public boolean isVarargs() {
        return varargs;
    }

    @Override
    public ClassPrototypeReference getType() {
        return reference;
    }

    @Override
    public String getName() {
        return name;
    }

}
