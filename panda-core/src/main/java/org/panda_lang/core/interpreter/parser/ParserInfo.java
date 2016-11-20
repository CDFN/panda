package org.panda_lang.core.interpreter.parser;

import org.panda_lang.core.interpreter.parser.util.Components;

public interface ParserInfo {

    /**
     * Default list of components is available here: {@link Components}
     *
     * @param componentName a name of the specified component
     * @return selected component
     */
    <T> T getComponent(String componentName);

    /**
     * @param componentName a name of the specified component
     */
    void setComponent(String componentName, Object component);

}
