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

    public static String amountFormat(String amount){
        try {
            amount = amount.replaceAll(" ", "");

            if (StringUtils.isBlank(amount))
                return "";
            if ("0.0".equals(amount) || "0".equals(amount))
                return "0";

            if (amount.charAt(amount.length() - 2) == ','){
                amount = amount.replaceAll("\\.", "");
                amount = amount.replace(",", ".");
            }

            else if (amount.charAt(amount.length() - 2) == '.'){
                amount = amount.replaceAll(",", "");
            }

            BigDecimal amt = new BigDecimal(amount);
            return amt.multiply(new BigDecimal(100)).toBigInteger().toString();
        } catch ( Exception e){
            e.printStackTrace();
            return "";
        }
    }
}
