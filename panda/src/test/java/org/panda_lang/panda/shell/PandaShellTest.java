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

package org.panda_lang.panda.shell;

import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

final class PandaShellTest {

    private static final PandaShell SHELL = new PandaShell(() -> LoggerFactory.getLogger(PandaShellTest.class), System.in);

    @Test
    void test() throws Exception {
        SHELL.invoke("--level=info", "../examples/package_manager/panda-module-dependency/panda.cdn");
    }

}
