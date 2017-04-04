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

package org.panda_lang.panda.language.structure.prototype.structure.parameter;

import org.panda_lang.panda.core.structure.value.Value;
import org.panda_lang.panda.core.structure.value.Variable;

import java.util.List;

public class ParameterUtils {

    public static void assignValues(Value[] variables, Value[] parameterValues) {
        if (variables.length < parameterValues.length) {
            throw new RuntimeException("Incompatible number of parameters");
        }

        System.arraycopy(parameterValues, 0, variables, 0, parameterValues.length);
    }

    public static void addAll(List<Variable> variables, List<Parameter> parameters, int nestingLevel) {
        for (Parameter parameter : parameters) {
            Variable variable = parameter.toVariable(nestingLevel);
            variables.add(variable);
        }
    }

}
