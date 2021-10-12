package de.sollder1.sscript;

import de.sollder1.sscript.lexer.Lexer;

import java.util.Collections;

public class Main {

    public static void main(String[] args) {

        try {

            Logger.debug("Starting Lexer...");
            Lexer lexer = new Lexer(Collections.singletonList("function ina"));
            lexer.tokenize();
            lexer.printInternalState();
            Logger.debug("Lexer is done!");




        } catch (CompilerException e) {
            Logger.logCompilerException(e);
        } catch (Throwable throwable) {
            System.out.println("Compilation failed unknown exception!");
            throwable.printStackTrace();
        }

    }


}
