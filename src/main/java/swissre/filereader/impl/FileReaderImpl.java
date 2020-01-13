package com.swissre.filereader.impl;

import com.swissre.filereader.FileReader;
import com.swissre.service.PortfolioService;
import com.swissre.service.impl.PortfolioServiceImpl;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReaderImpl implements FileReader {

    @Override
    public PortfolioService process(String fileName, PrintStream out) throws IOException {

        PortfolioServiceImpl portfolioService = new PortfolioServiceImpl();

        Files.lines(Paths.get(fileName))
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .forEach(line1 -> portfolioService.accept(line1, out));

        return portfolioService;

    }
}
