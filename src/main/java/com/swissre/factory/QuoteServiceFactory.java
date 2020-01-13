package com.swissre.factory;

import com.swissre.service.QuotesService;
import com.swissre.service.impl.QuotesServiceImpl;

public class QuoteServiceFactory {

    public static QuotesService newInstance(String quoteService) {
        if (quoteService.equals("CRYPTOCOMPARE")) {
            return new QuotesServiceImpl();
        }
        return null;
    }
}
