package de.sollder1.sscript;

import de.sollder1.sscript.cli.CLI;
import de.sollder1.sscript.lexer.Lexer;

public class Main {

    public static void main(String[] args) {

        try {

            Logger.info("Loading Config...");
            var config = CLI.loadConfig(args);
            Logger.debugf("Config loaded: %s! \n", config);

            Logger.separator();

            Logger.info("Starting Lexer...");
            Lexer lexer = new Lexer(config.getFileContent());
            lexer.tokenize();
            Logger.debug("Lexer is done, result:");
            lexer.printInternalState();

            Logger.separator();


        } catch (CompilerException e) {
            Logger.logCompilerException(e);
        } catch (Throwable throwable) {
            System.out.println("Compilation failed, unexpected exception has been thrown...");
            throwable.printStackTrace();
        }
    }
}
