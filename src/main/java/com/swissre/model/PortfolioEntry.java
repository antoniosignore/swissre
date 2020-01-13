package com.swissre.model;

import java.math.BigDecimal;

public final class PortfolioEntry {

    private String ticker;
    private Long amount;
    private BigDecimal value;
    private String error;

    public PortfolioEntry(String ticker, Long amount, BigDecimal value) {
        this.ticker = ticker;
        this.amount = amount;
        this.value = value;
    }

    public String getTicker() {
        return ticker;
    }

    public Long getAmount() {
        return amount;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        if (error == null)
            return "ticker='" + ticker + '\'' +
                    ", amount=" + amount +
                    ", value=" + value +
                    "\n";
        else
            return "ticker='" + ticker + '\'' +
                    ", amount=" + amount +
                    ", error=" + error +
                    "\n";
    }
}
