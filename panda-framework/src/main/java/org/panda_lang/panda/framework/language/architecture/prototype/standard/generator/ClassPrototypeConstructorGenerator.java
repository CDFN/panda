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

package org.panda_lang.panda.framework.language.architecture.prototype.standard.generator;

import org.jetbrains.annotations.Nullable;
import org.panda_lang.panda.framework.design.architecture.prototype.ClassPrototype;
import org.panda_lang.panda.framework.design.architecture.prototype.ClassPrototypeReference;
import org.panda_lang.panda.framework.design.architecture.prototype.constructor.PrototypeConstructor;
import org.panda_lang.panda.framework.design.architecture.value.Value;
import org.panda_lang.panda.framework.design.runtime.ExecutableBranch;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

final class ClassPrototypeConstructorGenerator {

    private final ClassPrototypeGenerator generator;
    private final ClassPrototype prototype;
    private final Constructor<?> constructor;

    ClassPrototypeConstructorGenerator(ClassPrototypeGenerator generator, ClassPrototype prototype, Constructor<?> constructor) {
        this.generator = generator;
        this.prototype = prototype;
        this.constructor = constructor;
    }

    protected PrototypeConstructor generate() {
        ClassPrototypeReference[] parameters = new ClassPrototypeReference[constructor.getParameterCount()];

        for (int i = 0; i < parameters.length; i++) {
            parameters[i] = generator.computeIfAbsent(prototype.getModule(), constructor.getParameterTypes()[i]);
        }

        // TODO: Generate bytecode
        constructor.setAccessible(true);

        return new PrototypeConstructor() {
            @Override
            public @Nullable Object createInstance(ExecutableBranch bridge, Value... values) {
                long start = System.nanoTime();

                try {
                    Object[] args = new Object[values.length];

                    for (int i = 0; i < values.length; i++) {
                        args[i] = values[i].getValue();
                    }

                    return constructor.newInstance(args);
                } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            public ClassPrototypeReference[] getParameterTypes() {
                return parameters;
            }
        };
    }

}
