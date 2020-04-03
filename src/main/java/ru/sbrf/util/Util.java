package ru.sbrf.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Util {
    public static Properties getProperties(String pathToProperties) {
        Properties props = null;
        try (InputStream is = new FileInputStream(pathToProperties)) {
            props = new Properties();
            props.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }
}
