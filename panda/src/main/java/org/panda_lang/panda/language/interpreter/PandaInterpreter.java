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

package org.panda_lang.panda.language.interpreter;

import org.panda_lang.framework.PandaFramework;
import org.panda_lang.framework.design.architecture.Application;
import org.panda_lang.framework.design.architecture.Environment;
import org.panda_lang.panda.language.architecture.PandaApplication;
import org.panda_lang.framework.design.interpreter.Interpretation;
import org.panda_lang.framework.design.interpreter.Interpreter;
import org.panda_lang.framework.design.interpreter.messenger.MessengerLevel;
import org.panda_lang.framework.language.interpreter.pattern.descriptive.extractor.ExtractorWorker;
import org.panda_lang.framework.design.interpreter.source.Source;
import org.panda_lang.framework.design.resource.Language;
import org.panda_lang.framework.language.interpreter.PandaInterpretation;
import org.panda_lang.framework.language.interpreter.parser.expression.PandaExpressionParser;
import org.panda_lang.panda.language.interpreter.parser.ApplicationParser;
import org.panda_lang.utilities.commons.TimeUtils;

import java.util.Optional;

public class PandaInterpreter implements Interpreter {

    private final Environment environment;
    private final Language language;

    protected PandaInterpreter(PandaInterpreterBuilder builder) {
        this.environment = builder.environment;
        this.language = builder.elements;
    }

    @Override
    public Optional<Application> interpret(Source source) {
        Interpretation interpretation = new PandaInterpretation(language, environment, this);
        long uptime = System.nanoTime();

        ApplicationParser parser = new ApplicationParser(interpretation);
        PandaApplication application = parser.parse(source);

        if (!interpretation.isHealthy()) {
            environment.getMessenger().sendMessage(MessengerLevel.FAILURE, "Interpretation failed, cannot parse specified sources");
            return Optional.empty();
        }

        String parseTime = TimeUtils.toMilliseconds(System.nanoTime() - uptime);

        PandaFramework.getLogger().debug("");
        PandaFramework.getLogger().debug("--- Parse details ");
        PandaFramework.getLogger().debug("• Amount of references: " + environment.getModulePath().countReferences());
        PandaFramework.getLogger().debug("• Amount of used prototypes: " + environment.getModulePath().countUsedPrototypes());
        PandaFramework.getLogger().debug("• Descriptive Pattern Time: " + TimeUtils.toMilliseconds(ExtractorWorker.fullTime));
        PandaFramework.getLogger().debug("• Expression Parser Time: " + TimeUtils.toMilliseconds(PandaExpressionParser.time) + " (" +  PandaExpressionParser.amount + ")");
        PandaFramework.getLogger().debug("• Pipeline Handle Time: " + TimeUtils.toMilliseconds(environment.getResources().getPipelinePath().getTotalHandleTime()));
        PandaFramework.getLogger().debug("");
        PandaFramework.getLogger().debug("--- Interpretation details ");
        PandaFramework.getLogger().debug("• Parse time: " + parseTime);
        PandaFramework.getLogger().debug("");

        ExtractorWorker.fullTime = 0;
        PandaExpressionParser.time = 0;
        PandaExpressionParser.amount = 0;

        return Optional.of(application);
    }

    public static PandaInterpreterBuilder builder() {
        return new PandaInterpreterBuilder();
    }

}
