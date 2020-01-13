package com.swissre.filereader;

import com.swissre.service.PortfolioService;

import java.io.IOException;
import java.io.PrintStream;

public interface FileReader {

    PortfolioService process(String fileName, PrintStream out) throws IOException;

}
