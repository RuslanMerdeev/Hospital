package com.haulmont.utils;

/**
 * Class for logging
 * @author r.merdeev
 */
public class Log {

    /**
     * Logs message
     * @param place place where occurred
     * @param str message
     */
    public static void message (String place, String str) {
        System.out.println(place + ": " + str);
    }
    
    /**
     * Logs exception
     * @param place place where occurred
     * @param e exception
     */
    public static void exception (String place, Exception e) {
        System.out.println(place + ": " + e.getMessage());
    }
}