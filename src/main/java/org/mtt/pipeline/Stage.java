package org.mtt.pipeline;

import java.util.Iterator;

public abstract class Stage<O> implements Iterator<O> {
    protected Stage<?> inputStage = null;

    public final Stage<?> getInputStage() {
        return inputStage;
    }

    public final void setInputStage(Stage<?> inputStage) {
        this.inputStage = inputStage;
    }
}
