package org.mtt.stages;

import org.mtt.dto.CandleDto;
import org.mtt.dto.MovingAverageDto;
import org.mtt.pipeline.Stage;

public class MovingAverageAnalyzer extends Stage<MovingAverageDto> {

    private final PositionEvent longEvent;
    private final PositionEvent shortEvent;
    private int delay;

    public MovingAverageAnalyzer(PositionEvent longEvent, PositionEvent shortEvent, int delay) {
        this.longEvent = longEvent;
        this.shortEvent = shortEvent;
        this.delay = delay;
    }

    @Override
    public boolean hasNext() {
        return inputStage.hasNext();
    }

    @Override
    public MovingAverageDto next() {
        MovingAverageDto dto = (MovingAverageDto) inputStage.next();
        CandleDto candle = dto.getCandle();
        double value = dto.getValue();

        if(delay > 0) delay--;
        else if(candle.getOpen() > value && candle.getClose() > value) longEvent.invoke(dto);
        else if(candle.getOpen() < value && candle.getClose() < value) shortEvent.invoke(dto);

        return dto;
    }
}
