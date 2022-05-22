package org.mtt.trader;

import org.mtt.dto.MovingAverageDto;
import org.mtt.pipeline.Pipeline;
import org.mtt.stages.FileStreamer;
import org.mtt.stages.OpenMovingAverageStreamer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Trader {

    public static void main(String[] args) throws Exception {
        final String path = "/coin_Ethereum.csv";
        final int period = 50;

        Pipeline<MovingAverageDto> pipeline = new Pipeline<>();
        pipeline.addStage(new FileStreamer(path));
        pipeline.addStage(new OpenMovingAverageStreamer(period));
        for(MovingAverageDto dto: pipeline) {
            System.out.println(dto);
        }
    }
}
