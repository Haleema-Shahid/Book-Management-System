package com.springboot.bookmanagementsystem.utils;

public class RangeUtils {
    public static boolean isLengthInRange(String value, Integer length)
    {
        return value.length() <= length;
    }
}
