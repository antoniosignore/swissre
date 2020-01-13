package com.swissre.service.impl;

import com.swissre.helpers.URLBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.util.Currency;

public class URLBuilderTest {

    @Test
    public void buildUrl() {

        //   URL url = new URL("https://min-api.cryptocompare.com/data/price?fsym="+sym+"&tsyms="+tsym);
        final String url = URLBuilder
                .buildUrl("https://min-api.cryptocompare.com/data/price", "BTC", Currency.getInstance("EUR"));

        Assert.assertEquals("https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=EUR", url);
    }

}