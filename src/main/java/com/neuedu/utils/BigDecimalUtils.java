package com.neuedu.utils;

import ch.qos.logback.classic.gaffer.NestingType;

import java.math.BigDecimal;

public class BigDecimalUtils {

    /**
     * 加法
     * */
    public static BigDecimal add(double d1,Double d2){
        BigDecimal b1  = new BigDecimal(String.valueOf(d1));
        BigDecimal b2 = new BigDecimal(String.valueOf(d2));
        return b1.add(b2);
    }

    /**
     * 减法
     * */
    public static BigDecimal sub(double d1,Double d2){
        BigDecimal b1  = new BigDecimal(String.valueOf(d1));
        BigDecimal b2 = new BigDecimal(String.valueOf(d2));
        return b1.subtract(b2);
    }

    /**
     * 乘法
     * */
    public static BigDecimal mul(double d1,Double d2){
        BigDecimal b1  = new BigDecimal(String.valueOf(d1));
        BigDecimal b2 = new BigDecimal(String.valueOf(d2));
        return b1.multiply(b2);
    }

    /**
     * 除法
     * 两位小数，四舍五入
     * */
    public static BigDecimal div(double d1,Double d2){
        BigDecimal b1  = new BigDecimal(String.valueOf(d1));
        BigDecimal b2 = new BigDecimal(String.valueOf(d2));
        return b1.divide(b2,2,BigDecimal.ROUND_UP);
    }



}
