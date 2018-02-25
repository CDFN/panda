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

package org.panda_lang.panda.core.interpreter.lexer.pattern;

import org.panda_lang.panda.core.interpreter.parser.util.Components;
import org.panda_lang.panda.framework.implementation.interpreter.parser.PandaParserException;
import org.panda_lang.panda.framework.implementation.interpreter.token.reader.PandaTokenReader;
import org.panda_lang.panda.framework.language.interpreter.parser.ParserInfo;
import org.panda_lang.panda.framework.language.interpreter.token.TokenUtils;
import org.panda_lang.panda.framework.language.interpreter.token.TokenizedSource;
import org.panda_lang.panda.framework.language.interpreter.token.distributor.SourceStream;
import org.panda_lang.panda.framework.language.interpreter.token.extractor.Extractor;
import org.panda_lang.panda.framework.language.interpreter.token.reader.TokenReader;

import java.util.List;

public class TokenPatternUtils {

    public static TokenPatternHollows extract(TokenPattern pattern, ParserInfo parserInfo) {
        return extract(pattern, parserInfo.<SourceStream> getComponent(Components.SOURCE_STREAM));
    }

    public static TokenPatternHollows extract(TokenPattern pattern, SourceStream source) {
        Extractor extractor = pattern.extractor();
        TokenReader reader = source.toTokenReader();
        List<TokenizedSource> gaps = extractor.extract(reader);

        if (gaps == null) {
            throw new PandaParserException("Cannot parse source at line " + TokenUtils.getLine(source.toTokenizedSource()));
        }

        source.readDifference(reader);
        return new TokenPatternHollows(gaps);
    }

    public static boolean match(TokenPattern pattern, TokenReader reader) {
        TokenReader copyOfReader = new PandaTokenReader(reader);
        Extractor extractor = pattern.extractor();

        List<TokenizedSource> hollows = extractor.extract(copyOfReader);
        return hollows != null && hollows.size() == pattern.getAmountOfHollows();
    }

    public static int countGaps(TokenPatternUnit[] units) {
        int gaps = 0;

        for (TokenPatternUnit unit : units) {
            if (unit.isGap()) {
                ++gaps;
            }
        }

        return gaps;
    }

}
