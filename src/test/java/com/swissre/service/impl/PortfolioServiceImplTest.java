package com.swissre.service.impl;

import com.swissre.model.Portfolio;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class PortfolioServiceImplTest {


    @Test
    public void accept_should_detect_error_value() throws UnsupportedEncodingException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);

        PortfolioServiceImpl portfolioService = new PortfolioServiceImpl();
        portfolioService.accept("BTC=bad", ps);
        portfolioService.combine(null);

        String output = os.toString("UTF8");
        Assert.assertEquals("Ticker: BTC quantity is not a natural number :bad", output);
    }

    @Test
    public void accept_should_add_protfolio_entry_in_portfolio() throws UnsupportedEncodingException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);

        PortfolioServiceImpl portfolioService = new PortfolioServiceImpl();
        portfolioService.accept("BTC=10", ps);
        String output = os.toString("UTF8");
        Assert.assertEquals("", output);
        Assert.assertEquals(1, portfolioService.getPortfolio().size());
    }

    @Test
    public void accept_should_add_protfolio_2_entry_in_portfolio() throws UnsupportedEncodingException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);

        PortfolioServiceImpl portfolioService = new PortfolioServiceImpl();
        portfolioService.accept("BTC=10", ps);
        portfolioService.accept("IBM=10", ps);

        String output = os.toString("UTF8");
        Assert.assertEquals("", output);
        Assert.assertEquals(2, portfolioService.getPortfolio().size());
    }

    @Test
    public void check_empty_portfolio_is_returned_not_null() {

        PortfolioServiceImpl portfolioService = new PortfolioServiceImpl();
        final Portfolio portfolio = portfolioService.getPortfolio();

        Assert.assertNotNull(portfolio);
    }

    @Test
    public void check_combine() {

        PortfolioServiceImpl portfolioService = new PortfolioServiceImpl();
        final Portfolio portfolio = portfolioService.getPortfolio();

        Assert.assertNotNull(portfolio);
    }
}