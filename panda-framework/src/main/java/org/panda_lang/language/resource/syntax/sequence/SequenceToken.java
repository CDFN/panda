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

package org.panda_lang.language.resource.syntax.sequence;

import org.panda_lang.language.interpreter.token.PandaToken;

public final class SequenceToken extends PandaToken {

    private final Sequence sequence;

    public SequenceToken(Sequence sequence, String value) {
        super(sequence.getType(), sequence.getName().get(), value);
        this.sequence = sequence;
    }

    @Override
    public String toString() {
        return sequence.getSequenceStart() + super.getValue() + sequence.getSequenceEnd();
    }

}
