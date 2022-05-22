package org.mtt.pipeline;

import java.util.Iterator;

public abstract class Stage<O> implements Iterator<O> {
    protected Stage<?> inputStage = null;

//    protected Stage(Stage<?> inputStage) {
//        this.inputStage = inputStage;
//    }

    public Stage<?> getInputStage() {
        return inputStage;
    }

    public void setInputStage(Stage<?> inputStage) {
        this.inputStage = inputStage;
    }
}
