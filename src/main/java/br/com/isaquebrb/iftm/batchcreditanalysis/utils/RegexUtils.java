package br.com.isaquebrb.iftm.batchcreditanalysis.utils;

import org.apache.logging.log4j.util.Strings;

public class RegexUtils {
    private RegexUtils() {
        //Utility class
    }

    public static String removeNonNumeric(String text) {
        if (text.isBlank())
            return Strings.EMPTY;
        return text.replaceAll("[^0-9]", "");
    }
}
