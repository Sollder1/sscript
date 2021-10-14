package de.sollder1.sscript.parser;

import de.sollder1.sscript.lexer.tokens.TokenType;
import de.sollder1.sscript.parser.statements.BraceCloseStatement;
import de.sollder1.sscript.parser.statements.FunctionStatement;
import de.sollder1.sscript.parser.statements.Statement;

import java.util.HashMap;
import java.util.Map;

public class ParserConstants {

    public static Map<TokenType, Class<? extends Statement>> START_TOKEN_TO_STATEMENT_CLASS = new HashMap<>();

    static {
        START_TOKEN_TO_STATEMENT_CLASS.put(TokenType.FUNCTION, FunctionStatement.class);
        START_TOKEN_TO_STATEMENT_CLASS.put(TokenType.BRACE_CLOSE, BraceCloseStatement.class);
    }

}
