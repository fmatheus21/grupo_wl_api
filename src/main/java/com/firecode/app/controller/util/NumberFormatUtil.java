package com.firecode.app.controller.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;

public class NumberFormatUtil {

    private final static Locale localeBR = new Locale("pt", "BR");
    
    @SuppressWarnings("unused")
    private final Locale localeUS = new Locale("en", "US");

    public String formatInteger(Integer value) {
        NumberFormat integer = NumberFormat.getInstance();
        return integer.format(value);
    }

    public static String formatMoney(BigDecimal value, int minimumDecimal, int maximumDecimal) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(localeBR);
        nf.setMinimumFractionDigits(minimumDecimal);
        nf.setMaximumFractionDigits(maximumDecimal);
        return nf.format(value);
    }

    public static String formatPercentage(Integer value) {
        return NumberFormat.getCurrencyInstance(localeBR).format(value);
    }

    public static String formatNumber(BigDecimal value, int minimumDecimal, int maximumDecimal) {
        NumberFormat nf = NumberFormat.getNumberInstance(localeBR);
        nf.setMinimumFractionDigits(minimumDecimal);
        nf.setMaximumFractionDigits(maximumDecimal);
        return nf.format(value);
    }

    public static BigDecimal converterStringForBigdecimal(String value) {
        DecimalFormat nf = (DecimalFormat) NumberFormat.getInstance(localeBR);
        nf.setParseBigDecimal(true);
        BigDecimal bigDecimal = (BigDecimal) nf.parse(value, new ParsePosition(0));
        return bigDecimal;
    }

    public static int converterBigdecimalForInteger(BigDecimal value) {
        return value.intValue();
    }

    public static Integer converterStringForInteger(String value) {      
        value = value.replaceAll("\\D+", "");
        return Integer.parseInt(value);
    }

    public static String converterIntegerForString(int value) {
        return String.valueOf(value);
    }

}
