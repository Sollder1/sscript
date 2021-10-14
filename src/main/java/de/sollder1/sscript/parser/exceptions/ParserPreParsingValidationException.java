package de.sollder1.sscript.parser.exceptions;

import de.sollder1.sscript.CompilerException;

public class ParserPreParsingValidationException extends CompilerException {


    public ParserPreParsingValidationException(String issue) {
        super(-1, -1, issue);
    }
}
