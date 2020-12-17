package ru.sbrf.file;


import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;

public class FileReader {
    private final String filePath;

    public FileReader(String filePath) {
        this.filePath = filePath;
    }

    public String readFile(String filePath) {
        String result = null;
        try {
            FileInputStream fis = new FileInputStream(filePath);
            result = IOUtils.toString(fis, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Can`t read file", e);
        }
        return result;
    }
}
