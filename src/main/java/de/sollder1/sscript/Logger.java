package de.sollder1.sscript;

public class Logger {


    public static void debug(String s) {
        System.out.println(s);
    }

    public static void debugf(String s, Object ... params) {
        System.out.printf(s, params);
    }

    public static void logCompilerException(CompilerException e) {
        System.err.printf("Compilation failed at line %d:%d, with the following Issue: %s\n", e.getLine(), e.getPos(), e.getIssue());
        if(e.getCause() != null){
            e.printStackTrace();
        }
    }


}
