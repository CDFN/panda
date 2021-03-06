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

package org.panda_lang.language.interpreter.parser.stage.pipeline;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.panda_lang.language.interpreter.parser.stage.StageType;
import org.panda_lang.language.interpreter.parser.stage.PandaStageController;

import java.util.Arrays;

class StagePhaseControllerTest {

    private static final PandaStageController generation = new PandaStageController();

    @BeforeAll
    public static void createPipelines() {
        generation.initialize(Arrays.asList(
                new StageType("b", 2.0),
                new StageType("a", 1.0),
                new StageType("c", 3.0))
        );
    }

    @Test
    public void testPipelineGeneration() {
        StringBuilder outputBuilder = new StringBuilder();

        generation.getCycle("b").nextPhase().delegate((pipeline, context) -> outputBuilder.append("b "), null);
        generation.getCycle("a").nextPhase().delegate((pipeline, context) -> outputBuilder.append("a "), null);
        generation.getCycle("c").nextPhase().delegate((pipeline, context) -> outputBuilder.append("c "), null);

        generation.getCycle("b").nextPhase().delegate((pipeline, delegatedContext) -> {
            outputBuilder.append("b2 ");

            pipeline.nextPhase().delegate((pipeline1, delegatedContext1) -> {
                pipeline1.stage().getCycle("a").nextPhase().delegate((pipeline2, delegatedContext2) -> outputBuilder.append("a2 "), delegatedContext1);
                outputBuilder.append("b3 ");
                return null;
            }, delegatedContext);

            return null;
        }, null);

        generation.launch();
        Assertions.assertEquals("a b b2 b3 a2 c", outputBuilder.toString().trim());

        outputBuilder.setLength(0);
        generation.launch();
        Assertions.assertEquals("", outputBuilder.toString());
    }

}
