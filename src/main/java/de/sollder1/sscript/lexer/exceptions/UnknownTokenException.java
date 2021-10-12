package de.sollder1.sscript.lexer.exceptions;

import de.sollder1.sscript.CompilerException;

public class UnknownTokenException extends CompilerException {
    public UnknownTokenException(long line, long pos, String currentLineView) {
        super(line, pos, String.format("Invalid token here: '%s'<<<", currentLineView));
    }
}
