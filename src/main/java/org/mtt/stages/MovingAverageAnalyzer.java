package org.mtt.stages;

import org.mtt.dto.CandleDto;
import org.mtt.dto.MovingAverageDto;
import org.mtt.pipeline.Stage;

public class MovingAverageAnalyzer extends Stage<MovingAverageDto> {

    private final PositionEvent longEvent;
    private final PositionEvent shortEvent;

    public MovingAverageAnalyzer(PositionEvent longEvent, PositionEvent shortEvent) {
        this.longEvent = longEvent;
        this.shortEvent = shortEvent;
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

        if(candle.getOpen() > value && candle.getClose() > value) longEvent.invoke(dto);
        else if(candle.getOpen() < value && candle.getClose() < value) shortEvent.invoke(dto);

        return dto;
    }
}
