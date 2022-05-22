package org.mtt.pipeline;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Pipeline<O> implements Iterable<O>{
    private final List<Stage<?>> stages = new ArrayList<>();
    private Stage<?> lastStage = null;

    public void addStage(Stage<?> stage) {
        if(lastStage != null) {
            stage.inputStage = lastStage;
        }
        lastStage = stage;
        stages.add(stage);
    }

    @Override
    public Iterator<O> iterator() {
        return (Iterator<O>) lastStage;
    }

    public void batch() {
        this.forEach(x -> {});
    }
}
