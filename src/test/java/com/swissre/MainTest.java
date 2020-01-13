package com.swissre;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class MainTest {
    private InputStream in;

    @Test
    public void testMain() {

        in = inputString(("\n").getBytes());
        System.setIn(in);
        String[] args = null;
        Main.main(args);

        Assert.assertNotNull(Main.fileReader);
        Assert.assertNotNull(Main.quotesService);

    }

    private ByteArrayInputStream inputString(byte[] bytes) {
        return new ByteArrayInputStream(bytes);
    }
}