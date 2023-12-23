package com.geevin.cards.util;


import org.apache.commons.lang3.StringUtils;

public class Util {
    public static boolean isNull(Object obj) {
        if (obj != null) return false;
        else return true;
    }

    public static String leftPadNumber(long number) {
        String pad = String.valueOf(number);
        String padded = StringUtils.leftPad(pad, 4, "0");
        return padded;
    }
}