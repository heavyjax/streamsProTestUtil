package ru.sbrf.util;

import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
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

    public static Long amountFormat(String amount) {
        final String q = amount;
        try {
            amount = amount.replaceAll(" ", "");

            if (StringUtils.isBlank(amount))
                return null;
            if ("0.0".equals(amount) || "0".equals(amount))
                return 0l;

            if (amount.charAt(amount.length() - 3) == ',') {
                amount = amount.replaceAll("\\.", "");
                amount = amount.replace(",", ".");
            } else if (amount.charAt(amount.length() - 3) == '.') {
                amount = amount.replaceAll(",", "");
            }

            BigDecimal amt = new BigDecimal(amount);
            return amt.multiply(new BigDecimal(100)).longValue();
        } catch (Exception e) {
            return null;
        }
    }
}
