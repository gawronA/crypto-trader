package org.mtt.stages;

import org.mtt.dto.CandleDto;
import org.mtt.dto.MovingAverageDto;
import org.mtt.pipeline.Stage;

import java.util.Arrays;

public class OpenMovingAverageStreamer extends Stage<MovingAverageDto> {

    private final int period;
    private final double[] buffer;

    public OpenMovingAverageStreamer(int period) {
//        super(inputStage, MovingAverageDto.class);
        this.period = period;
        buffer = new double[period];
        Arrays.fill(buffer, 0);
    }

    @Override
    public boolean hasNext() {
        return inputStage.hasNext();
    }

    @Override
    public MovingAverageDto next() {
        CandleDto candle = (CandleDto) inputStage.next();

        //Push and shift buffer
        System.arraycopy(buffer, 0, buffer, 1, buffer.length-1);
        buffer[0] = candle.getOpen();

        MovingAverageDto movingAverage = new MovingAverageDto();
        movingAverage.setValue(Arrays.stream(buffer).sum()/period);
        movingAverage.setCandle(candle);

        return movingAverage;
    }
}
