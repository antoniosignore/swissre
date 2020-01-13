package com.swissre.helpers;

import java.util.Currency;

public class URLBuilder {

    public static String buildUrl(String URI, String sym, Currency currency) {
        return URI + "?fsym=" + sym + "&tsyms=" + currency.getCurrencyCode();
    }
}
