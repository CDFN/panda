package org.panda_lang.panda.implementation.interpreter;

import org.panda_lang.core.interpreter.Interpreter;
import org.panda_lang.core.interpreter.SourceFile;
import org.panda_lang.core.interpreter.SourceSet;
import org.panda_lang.core.structure.Script;
import org.panda_lang.panda.Panda;
import org.panda_lang.panda.PandaComposition;
import org.panda_lang.panda.composition.parser.ParserComposition;
import org.panda_lang.panda.implementation.interpreter.parser.PandaParser;
import org.panda_lang.panda.implementation.structure.PandaApplication;

public class PandaInterpreter implements Interpreter {

    private final Panda panda;
    private final SourceSet sourceSet;
    private final PandaApplication application;

    public PandaInterpreter(Panda panda, SourceSet sourceSet) {
        this.panda = panda;
        this.sourceSet = sourceSet;
        this.application = new PandaApplication();
    }

    @Override
    public void interpret() {
        PandaComposition pandaComposition = panda.getPandaComposition();
        ParserComposition parserComposition = pandaComposition.getParserComposition();
        PandaParser pandaParser = new PandaParser(this, parserComposition.getPipeline());

        for (SourceFile sourceFile : sourceSet.getSourceFiles()) {
            Script script = pandaParser.parse(sourceFile);
            application.addPandaScript(script);
        }
    }

    @Override
    public PandaApplication getApplication() {
        return application;
    }

    public SourceSet getSourceSet() {
        return sourceSet;
    }

    public Panda getPanda() {
        return panda;
    }

}
