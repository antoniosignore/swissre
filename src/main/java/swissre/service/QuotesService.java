package com.swissre.service;

import com.swissre.model.QuoteResponse;

import java.util.Currency;

public interface QuotesService {
    QuoteResponse getQuoteResponse(String URI, String sym, Currency currency);
}
