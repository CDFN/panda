/*
 * Copyright (c) 2015-2018 Dzikoysk
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

package org.panda_lang.panda.language.structure.overall.module;

import org.panda_lang.panda.language.structure.prototype.structure.ClassPrototype;

import java.util.HashMap;
import java.util.Map;

public class ModuleRegistry {

    private static final ModuleRegistry instance = new ModuleRegistry();
    private final Map<String, Module> groups;

    public ModuleRegistry() {
        this.groups = new HashMap<>();
    }

    public Module getOrCreate(String groupName) {
        return groups.computeIfAbsent(groupName, Module::new);
    }

    public Module get(String groupName) {
        return groups.get(groupName);
    }

    public Map<String, Module> getGroups() {
        return groups;
    }

    public static ModuleRegistry getDefault() {
        return instance;
    }

    public static ClassPrototype forName(String prototypePath) {
        ModuleRegistry registry = getDefault();
        String[] parts = prototypePath.split(":");
        Module module = registry.get(parts[0]);

        if (module == null) {
            return null;
        }

        return module.get(parts[1]);
    }

    public static ClassPrototype forClass(Class<?> clazz) {
        ModuleRegistry registry = ModuleRegistry.getDefault();
        Package clazzPackage = clazz.getPackage();

        if (clazzPackage == null) {
            System.out.println("[static-indev-debug] ModuleRegistry.forClass called for class without package: " + clazz.getName());
            return null;
        }

        Module module = registry.get(clazzPackage.getName());

        if (module == null) {
            return null;
        }

        return module.get(clazz.getSimpleName());
    }

}
