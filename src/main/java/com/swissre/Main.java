package com.swissre;

import com.swissre.factory.FileReaderFactory;
import com.swissre.factory.QuoteServiceFactory;
import com.swissre.filereader.FileReader;
import com.swissre.service.CLIService;
import com.swissre.service.QuotesService;


public class Main {

    final static FileReader fileReader = FileReaderFactory.newInstance("StreamFileReader");
    final static QuotesService quotesService = QuoteServiceFactory.newInstance("CRYPTOCOMPARE");

    public static void main(String[] args) {
        new CLIService(System.out, System.in, fileReader, quotesService).start();
    }
}
