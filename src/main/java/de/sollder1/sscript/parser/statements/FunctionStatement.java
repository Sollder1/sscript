package de.sollder1.sscript.parser.statements;

import de.sollder1.sscript.lexer.tokens.Token;
import de.sollder1.sscript.lexer.tokens.TokenType;
import de.sollder1.sscript.parser.exceptions.ParserPreParsingValidationException;
import de.sollder1.sscript.parser.enums.DataType;
import de.sollder1.sscript.parser.pojos.FunctionParameter;

import java.util.ArrayList;
import java.util.List;

public class FunctionStatement extends Statement {

    private String functionName;
    private DataType returnType;
    private List<FunctionParameter> functionParameters= new ArrayList<>();


    public FunctionStatement(List<Token> sourceTokens) {
        super(sourceTokens);
    }

    @Override
    public void init() {
        simpleValidateAndParse();
    }


    @Override
    public void simpleValidateAndParse() {

        assertThat(0, TokenType.FUNCTION);
        assertThat(1, TokenType.IDENTIFIER);
        functionName = sourceTokens.get(1).getValue();
        assertThat(2, TokenType.BRACKET_OPEN);

        var index = validateAndParseParams(3);
        validateAndParseReturnType(index);

    }

    private int validateAndParseParams(int index) {
        //We expect either a closing bracket OR a list of params (group of 4)
        while (index < sourceTokens.size()) {
            if (isTypeAt(index, TokenType.BRACKET_CLOSE)) {
                break;
            } else if (isTypeAt(index, TokenType.IDENTIFIER)) {
                assertThat(index, TokenType.IDENTIFIER);
                assertThat(index + 1, TokenType.COLON);
                assertThatDataType(index + 2);
                assertEither(index + 3, TokenType.COMMA, TokenType.BRACKET_CLOSE);
                functionParameters.add(new FunctionParameter(sourceTokens.get(index).getValue(), DataType.fromToken(sourceTokens.get(index + 2))));
                index += 3;

                if (isTypeAt(index, TokenType.COMMA)) {
                    index++;
                }
            } else {
                System.out.println(index);
                throw new ParserPreParsingValidationException("Expected either a closing bracket or a parameter");
            }
        }
        return ++index;
    }

    private void validateAndParseReturnType(int index) {
        if (isTypeAt(index, TokenType.BRACE_OPEN)) {
            returnType = DataType.VOID;
        } else if (isTypeAt(index, TokenType.COLON)) {
            assertThatDataType(++index);
            returnType = DataType.fromToken(sourceTokens.get(index));
            assertThat(++index, TokenType.BRACE_OPEN);
        } else {
            throw new ParserPreParsingValidationException("Expected a token of type BRACE_OPEN at the end of the function statement!");
        }
    }


    @Override
    public String createJavaCode() {
        return null;
    }

    @Override
    public String toString() {
        return "FunctionStatement{" +
                "functionName='" + functionName + '\'' +
                ", returnType=" + returnType +
                ", functionParameters=" + functionParameters +
                '}';
    }
}
