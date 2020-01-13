package com.swissre.service;


import com.swissre.model.Portfolio;

import java.io.PrintStream;

public interface PortfolioService {

    void accept(String line, PrintStream out);

    void combine(QuotesService other);

    Portfolio getPortfolio();
}
