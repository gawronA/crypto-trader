package org.mtt.dto;

public class MovingAverageDto {
    private double value;
    private CandleDto candle;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public CandleDto getCandle() {
        return candle;
    }

    public void setCandle(CandleDto candle) {
        this.candle = candle;
    }

    @Override
    public String toString() {
        return "MovingAverageDto{" +
                "value=" + value +
                ", candle=" + candle +
                '}';
    }
}
