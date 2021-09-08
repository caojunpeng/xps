package com.cao.xps.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class XpsDateUtil {

    public static String localDateTimeToStr(LocalDateTime localDateTime, String format) {
        String str = "";
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            str = localDateTime.format(formatter);
        } catch (Exception e) {

        }
        return str;
    }

}
