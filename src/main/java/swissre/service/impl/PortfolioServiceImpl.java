package com.swissre.service.impl;

import com.swissre.model.Portfolio;
import com.swissre.model.PortfolioEntry;
import com.swissre.service.PortfolioService;
import com.swissre.service.QuotesService;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Observable;

public class PortfolioServiceImpl extends Observable implements PortfolioService {

    private Portfolio portfolio = new Portfolio();
    private QuotesService quotesService;

    @Override
    public void accept(String line, PrintStream out) {

        String[] tokens = getTokens(line);

        final long amount;
        try {
            amount = Long.parseLong(tokens[1]);
            PortfolioEntry entry = new PortfolioEntry(tokens[0], amount, BigDecimal.ZERO);
            portfolio.add(entry);
        } catch (NumberFormatException e) {
            out.print("Ticker: " + tokens[0] + " quantity is not a natural number :" + tokens[1]);
        }
    }

    private String[] getTokens(String line) {
        return line.split("=");
    }

    @Override
    public void combine(QuotesService other) {
        //no parallel processing
    }

    @Override
    public Portfolio getPortfolio() {
        return portfolio;
    }

}