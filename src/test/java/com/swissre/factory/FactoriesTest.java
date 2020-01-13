package com.swissre.factory;

import com.swissre.filereader.FileReader;
import com.swissre.service.QuotesService;
import org.junit.Assert;
import org.junit.Test;

public class FactoriesTest {

    final FileReader fileReader = FileReaderFactory.newInstance("StreamFileReader");
    final QuotesService quotesService = QuoteServiceFactory.newInstance("CRYPTOCOMPARE");

    final FileReader fileReaderBad = FileReaderFactory.newInstance("bad");
    final QuotesService quotesServiceBad = QuoteServiceFactory.newInstance("bad");

    @Test
    public void should_create_factories() {

        Assert.assertNotNull(fileReader);
        Assert.assertNotNull(quotesService);

    }

    @Test
    public void should_not_create_factories() {

        Assert.assertNull(fileReaderBad);
        Assert.assertNull(fileReaderBad);

    }

}