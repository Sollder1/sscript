package de.sollder1.sscript.lexer.tokens;

public enum TokenType {


    FUNCTION, WHILE, IF, INT, FLOAT, BOOL, STRING;

    private final boolean isLiteral;
    private final boolean isKeyword;


    TokenType() {
        this.isLiteral = false;
        this.isKeyword = false;
    }

    TokenType(boolean isLiteral, boolean isKeyword) {
        this.isLiteral = isLiteral;
        this.isKeyword = isKeyword;
    }

    public boolean isLiteral() {
        return isLiteral;
    }

    public boolean isKeyword() {
        return isKeyword;
    }

}
