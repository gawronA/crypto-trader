package org.mtt.dto;

import java.util.Date;

public class CandleDto {
    private double open;
    private double close;
    private double high;
    private double low;
    private Date date;

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CandleDto{" +
                "open=" + open +
                ", close=" + close +
                ", high=" + high +
                ", low=" + low +
                ", date=" + date +
                '}';
    }
}
