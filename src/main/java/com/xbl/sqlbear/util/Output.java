package com.xbl.sqlbear.util;

public class Output {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public static String error(String message) {
        return Output.ANSI_RED + "Error: " + message + Output.ANSI_RESET;
    }
}
