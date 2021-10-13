package de.sollder1.sscript.lexer.tokens;

public enum TokenType {


    //Keywords:
    FUNCTION, WHILE, IF, INT, FLOAT, BOOL, STRING,
    //Symbols:
    BRACKET_OPEN, BRACKET_CLOSE, BRACE_OPEN, BRACE_CLOSE, COLON, ASSIGN, COMMA,
    PLUS, MINUS, DIV, MULTI, GREATER_THAN, LESS_THAN, EQUAL,
    //Other:
    IDENTIFIER, INT_LITERAL, STRING_LITERAL, FALSE, TRUE;


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
