package de.sollder1.sscript.lexer;

import de.sollder1.sscript.lexer.tokens.TokenType;

import java.util.*;

public class LexerConstants {

    public static final Map<String, TokenType> KEYWORDS = new HashMap<>();

    static {
        KEYWORDS.put("function", TokenType.FUNCTION);
        KEYWORDS.put("while", TokenType.WHILE);
        KEYWORDS.put("if", TokenType.IF);
        KEYWORDS.put("int", TokenType.INT);
        KEYWORDS.put("float", TokenType.FLOAT);
        KEYWORDS.put("bool", TokenType.BOOL);
        KEYWORDS.put("string", TokenType.STRING);

        KEYWORDS.put("true", TokenType.TRUE);
        KEYWORDS.put("false", TokenType.FALSE);

    }


    public static final Set<Character> WHITESPACE_CHARS = new HashSet<>(Arrays.asList(
            '\n', '\t', '\r', ' '
    ));


    public static final Map<String, TokenType> SYMBOLS = new HashMap<>();

    static {
        SYMBOLS.put("{", TokenType.BRACE_OPEN);
        SYMBOLS.put("}", TokenType.BRACE_CLOSE);
        SYMBOLS.put("(", TokenType.BRACKET_OPEN);
        SYMBOLS.put(")", TokenType.BRACKET_CLOSE);
        SYMBOLS.put(":", TokenType.COLON);
        SYMBOLS.put("=", TokenType.ASSIGN);
        SYMBOLS.put(",", TokenType.COMMA);


        SYMBOLS.put("+", TokenType.PLUS);
        SYMBOLS.put("-", TokenType.MINUS);
        SYMBOLS.put("/", TokenType.DIV);
        SYMBOLS.put("*", TokenType.MULTI);
        SYMBOLS.put(">", TokenType.GREATER_THAN);
        SYMBOLS.put("<", TokenType.LESS_THAN);
        SYMBOLS.put("==", TokenType.EQUAL);
    }


    public static final Set<Character> VALID_IDENTIFIER_CHARS = new HashSet<>(Arrays.asList(
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '_'
    ));

    public static final Set<Character> VALID_NUMERIC_CHARS = new HashSet<>(Arrays.asList(
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    ));


}
