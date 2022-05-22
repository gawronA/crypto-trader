package org.mtt.trader;

import org.mtt.dto.CandleDto;
import org.mtt.dto.MovingAverageDto;
import org.mtt.pipeline.Pipeline;
import org.mtt.stages.FileStreamer;
import org.mtt.stages.MovingAverageAnalyzer;
import org.mtt.stages.OpenMovingAverageStreamer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;

public class Trader {

    public static void main(String[] args) throws Exception {
        final String path = "/coin_Bitcoin.csv";
        final int period = 50;
        int a = 0;

//        final File dataFile = new File("out/data.csv");
//        dataFile.createNewFile();

        final BufferedWriter writer = new BufferedWriter(new FileWriter("out/data.csv"));
        final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        Pipeline<MovingAverageDto> pipeline = new Pipeline<>();
        pipeline.addStage(new FileStreamer(path));
        pipeline.addStage(new OpenMovingAverageStreamer(period));
        pipeline.addStage(new MovingAverageAnalyzer(
                dto -> TraderState.getInstance().openLong(dto.getCandle()),
                dto -> TraderState.getInstance().openShort(dto.getCandle()),
                1000
        ));

//        pipeline.batch();
        for(MovingAverageDto dto : pipeline) {
            a++;
            CandleDto candle = dto.getCandle();
            String row = df.format(candle.getDate()) + "," +
                    candle.getOpen() + "," +
                    candle.getClose() + "," +
                    candle.getHigh() + "," +
                    candle.getLow() + "," +
                    dto.getValue() + "\n";
            writer.write(row);
        }

        writer.close();
        TraderState.getInstance().closeWriters();
    }
}
