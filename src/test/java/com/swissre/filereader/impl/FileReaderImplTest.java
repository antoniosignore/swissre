package com.swissre.filereader.impl;

import com.swissre.factory.FileReaderFactory;
import com.swissre.filereader.FileReader;
import com.swissre.model.Portfolio;
import com.swissre.service.PortfolioService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FileReaderImplTest {

    FileReader reader;

    @Before
    public void setUp() throws Exception {
        reader = FileReaderFactory.newInstance("StreamFileReader");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_detect_empty_portfolio() throws Exception {
        PortfolioService portfolioService =
                reader.process("src/test/resources/portfolio-empty.txt", System.out);
        Assert.assertNotNull(portfolioService);

        Portfolio portfolio = portfolioService.getPortfolio();
        Assert.assertEquals(0, portfolio.size());
    }

    @Test
    public void should_detect_portfolio_one_line() throws Exception {
        PortfolioService portfolioService =
                reader.process("src/test/resources/portfolio-empty.txt", System.out);
        Assert.assertNotNull(portfolioService);

        Portfolio portfolio = portfolioService.getPortfolio();
        Assert.assertEquals(0, portfolio.size());
    }


    @Test
    public void should_skip_empty_lines() throws Exception {

        PortfolioService service = reader.process(
                "src/test/resources/portfolio-with-empty-lines.txt", System.out);

        Assert.assertNotNull(service);

        Portfolio portfolio = service.getPortfolio();
        Assert.assertEquals(3, portfolio.size());
    }

}