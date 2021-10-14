package de.sollder1.sscript.parser.statements;

import de.sollder1.sscript.lexer.tokens.Token;
import de.sollder1.sscript.lexer.tokens.TokenType;
import de.sollder1.sscript.parser.exceptions.ParserPreParsingValidationException;

import java.util.List;

/**
 * As we only allow one Statement per line a Statement effectively represents one line of code...!
 */
public abstract class Statement {

    protected final List<Token> sourceTokens;

    public Statement(List<Token> sourceTokens) {
        this.sourceTokens = sourceTokens;
    }

    protected abstract void init();

    /**
     * Validates the given Tokens And parses the Statement.
     * Validate means a purely syntactical analysis of the current line, any further validation,
     * for example regarding scope and context are NOT Part of this Method, as for that we shall parse the whole program into statements first.
     */
    public abstract void simpleValidateAndParse();

    //Do we really want that here...?
    public abstract String createJavaCode();


    protected void assertThat(int tokenIndex, TokenType type) {
        if (sourceTokens.get(tokenIndex).getType() != type) {
            throw new ParserPreParsingValidationException(String.format("Expected type %s at index %d yet got %s",
                    type.toString(), tokenIndex, sourceTokens.get(tokenIndex).getType().toString()));
        }

    }

    protected void assertThatDataType(int index) {

        var typeAtIndex = sourceTokens.get(index).getType();
        boolean isDataType = typeAtIndex == TokenType.BOOL || typeAtIndex == TokenType.FLOAT ||
                typeAtIndex == TokenType.INT || typeAtIndex == TokenType.STRING;

        if (!isDataType) {
            throw new ParserPreParsingValidationException(String.format("Expected a datatype at index %d yet got %s", index, typeAtIndex.toString()));
        }

    }

    protected void assertEither(int tokenIndex, TokenType a, TokenType b) {

        var typeAtIndex = sourceTokens.get(tokenIndex).getType();

        if (a != typeAtIndex && b != typeAtIndex) {
            throw new ParserPreParsingValidationException(String.format("Expected type %s or %s at index %d yet got %s",
                    a, b, tokenIndex, sourceTokens.get(tokenIndex).getType()));
        }

    }

    protected boolean isTypeAt(int tokenIndex, TokenType type) {
        return sourceTokens.get(tokenIndex).getType() == type;
    }

    protected void assertSize(int size) {
        if(sourceTokens.size() != size) {
            throw new ParserPreParsingValidationException(String.format("Expected amount of tokens is %d, yet got %d", size, sourceTokens.size()));
        }
    }
}
