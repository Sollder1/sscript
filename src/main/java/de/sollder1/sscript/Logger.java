package de.sollder1.sscript;

public class Logger {

    private static final byte LOG_LEVEL = 5;

    public static void separator() {
        if (LOG_LEVEL > 2) {
            System.out.println("\n-----------------------------------\n");
        }
    }

    public static void debug(String s) {
        if (LOG_LEVEL > 3) {
            System.out.println(s);
        }
    }

    public static void debugf(String s, Object... params) {
        if (LOG_LEVEL > 3) {
            System.out.printf(s, params);
        }
    }

    public static void info(String s) {
        if (LOG_LEVEL > 2) {
            System.out.println(s);
        }
    }


    public static void logCompilerException(CompilerException e) {
        System.err.printf("Compilation failed at line %d:%d, with the following Issue: %s\n", e.getLine(), e.getPos(), e.getIssue());
        if (e.getCause() != null) {
            e.printStackTrace();
        }
    }


}
