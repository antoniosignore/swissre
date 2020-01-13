package com.swissre.service.impl;

import com.swissre.model.QuoteResponse;
import com.swissre.service.QuotesService;

import java.util.Currency;

public class QuoteServiceErrorMock implements QuotesService {

    @Override
    public QuoteResponse getQuoteResponse(String URI, String sym, Currency currency) {
        final QuoteResponse quoteResponse = new QuoteResponse();
        quoteResponse.setError("oops.Something bad happened");
        quoteResponse.setStatus(0);
        return quoteResponse;
    }
}
