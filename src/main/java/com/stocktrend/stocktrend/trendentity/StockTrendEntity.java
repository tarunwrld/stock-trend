package com.stocktrend.stocktrend.trendentity;

public class StockTrendEntity {
    private String stockName;
    private String price;
    private String chg;
    private String chgperc;
    private String trend;

    public StockTrendEntity(String stockName, String price, String chg, String chgperc, String trend) {
        this.stockName = stockName;
        this.price = price;
        this.chg = chg;
        this.chgperc = chgperc;
        this.trend = trend;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getChg() {
        return chg;
    }

    public void setChg(String chg) {
        this.chg = chg;
    }

    public String getChgperc() {
        return chgperc;
    }

    public void setChgperc(String chgperc) {
        this.chgperc = chgperc;
    }

    public String getTrend() {
        return trend;
    }

    public void setTrend(String trend) {
        this.trend = trend;
    }
}
