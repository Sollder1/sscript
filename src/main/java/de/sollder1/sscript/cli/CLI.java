package de.sollder1.sscript.cli;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CLI {

    public static CompilerConfig loadConfig(String[] args) throws IOException {
        if (args.length != 1) {
            throw new IllegalArgumentException("Must provide exactly one parameter (ABSOLUTE path to file to compile)");
        }
        var lines = Files.readAllLines(Paths.get(args[0]));
        return new CompilerConfig(lines);

    }


}
