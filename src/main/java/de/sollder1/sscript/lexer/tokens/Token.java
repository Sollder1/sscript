package de.sollder1.sscript.lexer.tokens;

public class Token {

    private final TokenType type;
    //Might be empty keyword
    private final String value;
    private final int lineNumber;

    public Token(TokenType type, String value, int lineNumber) {
        this.type = type;
        this.value = value;
        this.lineNumber = lineNumber;
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public String toString() {
        return type.toString() + (value != null ?  "->" + value : "");
    }
}