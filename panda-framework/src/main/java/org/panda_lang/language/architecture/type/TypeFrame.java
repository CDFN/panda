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

package org.panda_lang.language.architecture.type;

import org.panda_lang.language.runtime.Process;
import org.panda_lang.language.architecture.dynamic.AbstractFrame;

import java.util.concurrent.atomic.AtomicInteger;

public final class TypeFrame extends AbstractFrame<TypeScope> {

    public static final AtomicInteger ID = new AtomicInteger();

    protected final int id;
    protected final Process process;
    protected final Object[] base;

    public TypeFrame(Process process, TypeScope scope, Object[] base) {
        super(scope, scope.getType().getFields().getDeclaredProperties().size());

        this.id = ID.getAndIncrement();
        this.process = process;
        this.base = base;
    }

    public Object[] getBase() {
        return base;
    }

    public Process getProcess() {
        return process;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return framedScope.getType().getSimpleName() + "#" + String.format("%06X", id & 0xFFFFF);
    }

}
