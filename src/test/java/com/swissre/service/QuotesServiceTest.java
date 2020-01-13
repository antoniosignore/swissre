package com.swissre.service;

import com.swissre.model.Portfolio;
import com.swissre.model.QuoteResponse;
import com.swissre.service.impl.QuotesServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.Currency;

public class QuotesServiceTest {

    @Test
    public void getQuoteResponse() {
    }

    @Test
    public void should_return_double_value() {

        QuotesServiceImpl service = new QuotesServiceImpl();
        final String s = service.extractValueFromJsonStringByRegex("{\"EUR\":129.81}");

        Assert.assertEquals("129.81", s);
    }

    @Test
    public void should_return_null_value() {

        QuotesServiceImpl service = new QuotesServiceImpl();
        final String s = service.extractValueFromJsonStringByRegex("{\"EUR\":bad}");

        Assert.assertNull(s);
    }

    @Test
    public void should_return_not_null_response_value() {

        QuotesService service = new QuotesServiceImpl();
        final QuoteResponse quoteResponse =
                service.getQuoteResponse(Portfolio.HTTPS_MIN_API_CRYPTOCOMPARE_COM_DATA_PRICE, "BTC", Currency.getInstance("EUR"));

        Assert.assertNotNull(quoteResponse);

        Assert.assertEquals(200, quoteResponse.getStatus());
    }

    @Test
    public void should_return_UnknownHostException_response_value() {

        QuotesService service = new QuotesServiceImpl();
        final QuoteResponse quoteResponse =
                service.getQuoteResponse("http://badbadbad", "BTC", Currency.getInstance("EUR"));

        Assert.assertNotNull(quoteResponse);

        Assert.assertEquals(0, quoteResponse.getStatus());
        Assert.assertEquals("Cannot connect to quote server", quoteResponse.getError());
    }

    @Test
    public void should_return_connection_response_value() {

        QuotesService service = new QuotesServiceImpl();
        final QuoteResponse quoteResponse =
                service.getQuoteResponse("http://badbadbad.com", "BTC", Currency.getInstance("EUR"));

        Assert.assertNotNull(quoteResponse);

        Assert.assertEquals(200, quoteResponse.getStatus());
        Assert.assertEquals("Cannot decode quote value", quoteResponse.getError());
    }

}