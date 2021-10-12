package de.sollder1.sscript.cli;

import java.util.List;

public class CompilerConfig {

    private final List<String> fileContent;

    public CompilerConfig(List<String> fileContent) {
        this.fileContent = fileContent;
    }

    public List<String> getFileContent() {
        return fileContent;
    }

    @Override
    public String toString() {
        return "CompilerConfig{" +
                "fileContent=" + fileContent +
                '}';
    }
}

