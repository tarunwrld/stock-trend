package com.stocktrend.stocktrend.trendentity;

public class StockActualEntity {
    private String stockName;
    private String price;
    private String chg;
    private String chgperc;
    private String prevclose;
    private String high;
    private String low;

    public StockActualEntity(String stockName, String price, String chg, String chgperc, String prevclose, String high, String low) {
        this.stockName = stockName;
        this.price = price;
        this.chg = chg;
        this.chgperc = chgperc;
        this.prevclose = prevclose;
        this.high = high;
        this.low = low;
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

    public String getPrevclose() {
        return prevclose;
    }

    public void setPrevclose(String prevclose) {
        this.prevclose = prevclose;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }
}
