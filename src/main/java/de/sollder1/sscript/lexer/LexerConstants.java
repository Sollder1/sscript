package de.sollder1.sscript.lexer;

import de.sollder1.sscript.lexer.tokens.TokenType;

import java.util.*;

public class LexerConstants {

    public static final Map<String, TokenType> KEYWORDS = new HashMap();

    static {
        KEYWORDS.put("function", TokenType.FUNCTION);
        KEYWORDS.put("while", TokenType.WHILE);
        KEYWORDS.put("if", TokenType.IF);
        KEYWORDS.put("int", TokenType.INT);
        KEYWORDS.put("float", TokenType.FLOAT);
        KEYWORDS.put("bool", TokenType.BOOL);
        KEYWORDS.put("string", TokenType.STRING);

    }


    public static final Set<Character> WHITESPACE_CHARS = new HashSet<>(Arrays.asList(
            '\n', '\t', '\r', ' '
    ));



}
