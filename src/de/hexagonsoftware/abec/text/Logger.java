package de.hexagonsoftware.abec.text;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public static void info(String msg) {
        System.out.println(dtf.format(LocalDateTime.now())+" [INFO] "+msg+ANSIColors.ANSI_RESET);
    }

    public static void warn(String msg) {
        System.out.println(ANSIColors.ANSI_YELLOW+dtf.format(LocalDateTime.now())+" [WARN] "+msg+ANSIColors.ANSI_RESET);
    }

    public static void error(String msg) {
        System.out.println(ANSIColors.ANSI_RED+dtf.format(LocalDateTime.now())+" [ERROR] "+msg+ANSIColors.ANSI_RESET);
    }

    public static void fatal(String msg) {
        System.err.println(ANSIColors.ANSI_RED+dtf.format(LocalDateTime.now())+" [FATAL] "+msg+ANSIColors.ANSI_RESET);
    }
}
