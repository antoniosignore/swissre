package com.swissre.service.impl;

import com.swissre.model.Portfolio;
import com.swissre.service.PortfolioService;
import com.swissre.service.QuotesService;

import java.io.PrintStream;

public class PortfolioServiceMock implements PortfolioService {

    @Override
    public void accept(String line, PrintStream out) {

    }

    public void combine(QuotesService other) {

    }

    @Override
    public Portfolio getPortfolio() {
        return null;
    }
}
