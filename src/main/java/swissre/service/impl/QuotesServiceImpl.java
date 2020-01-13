package com.swissre.service.impl;

import com.swissre.helpers.URLBuilder;
import com.swissre.model.QuoteResponse;
import com.swissre.service.QuotesService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Currency;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuotesServiceImpl implements QuotesService {

    @Override
    public QuoteResponse getQuoteResponse(String URI, String sym, Currency currency) {
        QuoteResponse response = new QuoteResponse();
        try {
            String urlStr = URLBuilder.buildUrl(URI, sym, currency);

            URL url = new URL(urlStr);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);

            int status = con.getResponseCode();
            response.setStatus(status);

            if (status == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();

                final BigDecimal bigDecimal;
                try {
                    bigDecimal = BigDecimal.valueOf(Double.parseDouble(extractValueFromJsonStringByRegex(content.toString())));
                    response.setValue(bigDecimal);
                } catch (NullPointerException e) {
                    response.setError("Cannot decode quote value");
                    return response;
                }
            }
            return response;

        } catch (IOException e) {
            response.setError("Cannot connect to quote server");
            return response;
        }
    }

    public String extractValueFromJsonStringByRegex(String json) {
        String result = null;
        String patternStr = "\"EUR\":(\\d+\\.\\d+)";

        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(json);

        while (matcher.find())
            result = matcher.group(1);

        return result;
    }
}
