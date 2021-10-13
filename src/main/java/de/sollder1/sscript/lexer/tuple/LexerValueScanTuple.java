package de.sollder1.sscript.lexer.tuple;

import de.sollder1.sscript.lexer.tokens.Token;

public class LexerValueScanTuple {

    private final Token token;
    private final Integer newPosIndex;
    private final boolean found;

    public LexerValueScanTuple() {
        this.token = null;
        this.newPosIndex = null;
        found = false;
    }

    public LexerValueScanTuple(Token token, Integer newPosIndex) {
        this.token = token;
        this.newPosIndex = newPosIndex - 1;
        found = true;
    }

    public Token getToken() {
        return token;
    }

    public Integer getNewPosIndex() {
        return newPosIndex;
    }

    public boolean isFound() {
        return found;
    }
}
