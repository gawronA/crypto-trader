package org.mtt.stages;

import org.mtt.dto.CandleDto;
import org.mtt.pipeline.Stage;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FileStreamer extends Stage<CandleDto> {
    private final BufferedReader reader;

    public FileStreamer(String path) throws IOException {
//        super(null, CandleDto.class);

        InputStream in = getClass().getResourceAsStream(path);
        this.reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
        this.reader.readLine();
    }

    @Override
    public boolean hasNext() {
        try {
            return reader.ready();
        }
        catch (IOException e) {
            return false;
        }
    }

    @Override
    public CandleDto next() {
        try {
            String line = reader.readLine();
            if(line == null) return null;

            String[] values = line.split(",");
            CandleDto candle = new CandleDto();
            candle.setOpen(Double.parseDouble(values[6]));
            candle.setClose(Double.parseDouble(values[7]));
            candle.setHigh(Double.parseDouble(values[4]));
            candle.setLow((Double.parseDouble(values[5])));

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            candle.setDate(dateFormat.parse(values[3]));

            return candle;
        }
        catch (IOException | ParseException e) { return null; }
    }
}
