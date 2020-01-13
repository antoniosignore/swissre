package com.swissre;

import com.swissre.factory.FileReaderFactory;
import com.swissre.factory.QuoteServiceFactory;
import com.swissre.filereader.FileReader;
import com.swissre.service.CLIService;
import com.swissre.service.QuotesService;
import com.swissre.service.impl.QuoteServiceErrorMock;
import com.swissre.service.impl.QuoteServiceMock;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;

public class CLITest {

    final FileReader fileReader = FileReaderFactory.newInstance("StreamFileReader");
    final QuotesService quotesService = QuoteServiceFactory.newInstance("CRYPTOCOMPARE");
    private InputStream in;
    private CLIService client;

    private ByteArrayInputStream inputString(byte[] bytes) {
        return new ByteArrayInputStream(bytes);
    }

    @Test
    public void should_detect_no_file() throws UnsupportedEncodingException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);

        in = inputString(("\n").getBytes());

        client = new CLIService(ps, in, fileReader, quotesService);
        client.start();

        String output = os.toString("UTF8");

        Assert.assertEquals(
                "Enter your portfolio filename  :You must to enter a valid portfolio filename",
                output);
    }

    @Test
    public void should_detect_not_existent_file() throws UnsupportedEncodingException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);

        in = inputString(("src/test/resources/not_existent.txt\n").getBytes());

        CLIService client = new CLIService(ps, in, fileReader, quotesService);
        client.start();

        String output = os.toString("UTF8");

        Assert.assertEquals(
                "Enter your portfolio filename  :An error occurred. Please start over.\n",
                output);
    }

    @Test
    public void should_detect_existent_fempty_ile() throws UnsupportedEncodingException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);

        in = inputString(("src/test/resources/portfolio-empty.txt\n").getBytes());

        CLIService client = new CLIService(ps, in, fileReader, quotesService);
        client.start();

        String output = os.toString("UTF8");

        Assert.assertEquals(
                "Enter your portfolio filename  :Total portfolio value:0\n\n",
                output);
    }

    @Test
    public void should_read_bob_file_mocked_quote_service() throws UnsupportedEncodingException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);

        QuotesService quotesService = new QuoteServiceMock();

        in = inputString(("src/test/resources/bob_crypto.txt\n").getBytes());

        CLIService client = new CLIService(ps, in, fileReader, quotesService);
        client.start();

        String output = os.toString("UTF8");

        Assert.assertTrue(output.contains("Enter your portfolio filename  :Total portfolio value:201.5\n" +
                "ticker='BTC', amount=10, value=1.0\n" +
                "ticker='ETH', amount=5, value=0.5\n" +
                "ticker='XRP', amount=2000, value=200.0\n"));
    }

    @Test
    public void should_read_bob_file_mocked_quote_service_with_errors() throws UnsupportedEncodingException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);

        QuotesService quotesService = new QuoteServiceErrorMock();

        in = inputString(("src/test/resources/bob_crypto.txt\n").getBytes());

        CLIService client = new CLIService(ps, in, fileReader, quotesService);
        client.start();

        String output = os.toString("UTF8");

        Assert.assertTrue(output.contains("Enter your portfolio filename  :Total portfolio value:0\n" +
                "ticker='BTC', amount=10, error=oops.Something bad happened\n" +
                "ticker='ETH', amount=5, error=oops.Something bad happened\n" +
                "ticker='XRP', amount=2000, error=oops.Something bad happened"));
    }
}