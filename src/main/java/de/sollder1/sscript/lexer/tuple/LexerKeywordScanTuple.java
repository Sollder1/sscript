package de.sollder1.sscript.lexer.tuple;

import de.sollder1.sscript.lexer.tokens.TokenType;

public class LexerKeywordScanTuple {

    private final TokenType type;
    private final Integer newPosIndex;
    private final boolean found;

    public LexerKeywordScanTuple() {
        this.type = null;
        this.newPosIndex = null;
        found = false;
    }

    public LexerKeywordScanTuple(TokenType type, Integer newPosIndex) {
        this.type = type;
        this.newPosIndex = newPosIndex - 1;
        found = true;
    }

    public TokenType getType() {
        return type;
    }

    public Integer getNewPosIndex() {
        return newPosIndex;
    }

    public boolean isFound() {
        return found;
    }
}
