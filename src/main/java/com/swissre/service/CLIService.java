package com.swissre.service;

import com.swissre.filereader.FileReader;
import com.swissre.model.Portfolio;

import java.io.*;

public class CLIService {

    private PrintStream out;
    private BufferedReader in;
    private com.swissre.filereader.FileReader fileReader;
    private QuotesService quotesService;

    public CLIService(PrintStream out, InputStream in, FileReader fileReader, QuotesService quotesService) {
        this.out = out;
        this.in = new BufferedReader(new InputStreamReader(in));
        this.fileReader = fileReader;
        this.quotesService = quotesService;
    }

    public void start() {
        try {

            out.print("Enter your portfolio filename  :");
            final String portfolioFileName = in.readLine();

            if (portfolioFileName == null || portfolioFileName.length() == 0)
                out.print("You must to enter a valid portfolio filename");
            else {
                PortfolioService portofolioService = fileReader.process(portfolioFileName.trim(), out);
                Portfolio portfolio = portofolioService.getPortfolio();
                portfolio.print(quotesService, out);
            }

        } catch (IOException e) {
            out.println("An error occurred. Please start over.");
        }
    }
}
