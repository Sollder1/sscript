package de.sollder1.sscript.parser.statements;

import de.sollder1.sscript.lexer.tokens.Token;
import de.sollder1.sscript.lexer.tokens.TokenType;

import java.util.List;

public class BraceCloseStatement extends Statement {

    public BraceCloseStatement(List<Token> sourceTokens) {
        super(sourceTokens);
    }

    @Override
    protected void init() {
        simpleValidateAndParse();
    }

    @Override
    public void simpleValidateAndParse() {
        assertSize(1);
        assertThat(0, TokenType.BRACE_CLOSE);
    }

    @Override
    public String createJavaCode() {
        return null;
    }

    @Override
    public String toString() {
        return "BraceCloseStatement";
    }
}
