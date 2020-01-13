package com.swissre.service.impl;

import com.swissre.model.QuoteResponse;
import com.swissre.service.QuotesService;

import java.math.BigDecimal;
import java.util.Currency;

public class QuoteServiceMock implements QuotesService {

    @Override
    public QuoteResponse getQuoteResponse(String URI, String sym, Currency currency) {
        final QuoteResponse quoteResponse = new QuoteResponse();
        quoteResponse.setValue(new BigDecimal("0.1"));
        quoteResponse.setStatus(200);
        return quoteResponse;
    }
}
