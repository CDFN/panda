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

package org.panda_lang.panda.design.architecture.dynamic.conditional;

import org.panda_lang.panda.framework.design.architecture.dynamic.Block;
import org.panda_lang.panda.framework.design.architecture.value.Value;
import org.panda_lang.panda.framework.language.interpreter.parser.PandaParserException;
import org.panda_lang.panda.framework.design.runtime.ExecutableBranch;
import org.panda_lang.panda.framework.design.runtime.expression.Expression;
import org.panda_lang.panda.design.architecture.dynamic.PandaBlock;

public class ConditionalBlock extends PandaBlock {

    private final Expression condition;
    private Block elseBlock;

    public ConditionalBlock(Expression condition) {
        if (!condition.getReturnType().isClassOf("Boolean")) {
            throw new PandaParserException("Condition has to return boolean");
        }

        this.condition = condition;
    }

    @Override
    public void execute(ExecutableBranch branch) {
        Value value = condition.getExpressionValue(branch);
        boolean flag = value.getValue();

        if (flag) {
            branch.call(super.getStatementCells());
            return;
        }

        if (elseBlock != null) {
            branch.call(elseBlock);
        }
    }

    public void setElseBlock(Block elseBlock) {
        this.elseBlock = elseBlock;
    }

}
