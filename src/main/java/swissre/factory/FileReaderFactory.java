package com.swissre.factory;

import com.swissre.filereader.FileReader;
import com.swissre.filereader.impl.FileReaderImpl;

public class FileReaderFactory {

    public static FileReader newInstance(String fileReader) {

        if (fileReader.equals("StreamFileReader")) {
            return new FileReaderImpl();
        }

        return null;
    }
}
