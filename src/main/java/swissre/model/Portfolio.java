package com.swissre.model;

import com.swissre.service.QuotesService;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;

public class Portfolio extends ArrayList<PortfolioEntry> {

    public static final String HTTPS_MIN_API_CRYPTOCOMPARE_COM_DATA_PRICE = "https://min-api.cryptocompare.com/data/price";
    public static final String EUR = "EUR";
    BigDecimal m2m = BigDecimal.ZERO;

    public void print(QuotesService service, PrintStream out) {

        QuoteFunction function = portfolio -> {

            for (int i = 0; i < portfolio.size(); i++) {
                PortfolioEntry portfolioEntry = portfolio.get(i);

                final QuoteResponse marketValue =
                        service.getQuoteResponse(HTTPS_MIN_API_CRYPTOCOMPARE_COM_DATA_PRICE,
                                portfolioEntry.getTicker(),
                                Currency.getInstance(EUR));

                if (marketValue.getError() != null) {
                    portfolioEntry.setError(marketValue.getError());
                } else {
                    BigDecimal value = marketValue.getValue()
                            .multiply(new BigDecimal(portfolioEntry.getAmount()));
                    portfolioEntry.setValue(value);
                    portfolio.setM2m(portfolio.getM2m().add(value));
                }
            }
        };

        function.apply(this);
        out.println(this);
    }

    public BigDecimal getM2m() {
        return m2m;
    }

    public void setM2m(BigDecimal m2m) {
        this.m2m = m2m;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Total portfolio value:" + this.m2m + "\n");

        for (int i = 0; i < this.size(); i++) {
            PortfolioEntry portfolioEntry = this.get(i);
            sb.append(portfolioEntry.toString());
        }
        return sb.toString();
    }
}
