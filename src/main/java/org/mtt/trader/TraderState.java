package org.mtt.trader;

import org.mtt.dto.CandleDto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TraderState {
    private static TraderState instance;

    private boolean isLongOpened;
    private boolean isShortOpened;


    private final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private final BufferedWriter longDataWriter = new BufferedWriter(new FileWriter("out/longData.csv"));
    private final BufferedWriter shortDataWriter = new BufferedWriter(new FileWriter("out/shortData.csv"));

    private TraderState() throws IOException {}

    public static TraderState getInstance() {
        if(instance == null) {
            try {
                instance = new TraderState();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    public void openLong(CandleDto candle) {
        if(!isLongOpened) {
            isLongOpened = true;
            isShortOpened = false;
            System.out.println("Open long at " + candle.getOpen() + " " + df.format(candle.getDate()));

            String row = df.format(candle.getDate()) + "," + candle.getOpen();
            try {
                longDataWriter.write(row);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void openShort(CandleDto candle) {
        if(!isShortOpened) {
            isShortOpened = true;
            isLongOpened = true;
            System.out.println("Open short at " + candle.getOpen() + " " + df.format(candle.getDate()));

            String row = df.format(candle.getDate()) + "," + candle.getOpen();
            try {
                shortDataWriter.write(row);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void closeWriters() {
        try {
            longDataWriter.close();
            shortDataWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
