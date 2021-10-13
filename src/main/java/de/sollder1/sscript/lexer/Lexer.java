package de.sollder1.sscript.lexer;

import de.sollder1.sscript.Logger;
import de.sollder1.sscript.lexer.exceptions.UnknownTokenException;
import de.sollder1.sscript.lexer.tokens.Token;
import de.sollder1.sscript.lexer.tokens.TokenType;
import de.sollder1.sscript.lexer.tuple.LexerKeywordScanTuple;
import de.sollder1.sscript.lexer.tuple.LexerValueScanTuple;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Lexer {

    private final List<String> sourceLines;
    private List<Token> tokens = new LinkedList<>();

    public Lexer(List<String> sourceLines) {
        this.sourceLines = sourceLines;
    }

    public void tokenize() throws UnknownTokenException {

        for (int lineIndex = 0; lineIndex < sourceLines.size(); lineIndex++) {

            for (int posIndex = 0; posIndex < sourceLines.get(lineIndex).length(); posIndex++) {

                if (isWhiteSpace(lineIndex, posIndex)) {
                    Logger.debugf("Whitespace at %d:%d\n", lineIndex, posIndex);
                    continue;
                }

                var keywordResult = scanForKeyword(lineIndex, posIndex);
                if (keywordResult.isFound()) {
                    Logger.debugf("Token %s at line %d from %d to %d \n", keywordResult.getType().toString(), lineIndex, posIndex, keywordResult.getNewPosIndex());
                    posIndex = keywordResult.getNewPosIndex();
                    tokens.add(new Token(keywordResult.getType(), null, lineIndex));
                    continue;
                }

                var symbolResult = scanForSymbol(lineIndex, posIndex);
                if (symbolResult.isFound()) {
                    Logger.debugf("Token %s at line %d from %d to %d \n", symbolResult.getType().toString(), lineIndex, posIndex, symbolResult.getNewPosIndex());
                    posIndex = symbolResult.getNewPosIndex();
                    tokens.add(new Token(symbolResult.getType(), null, lineIndex));
                    continue;
                }

                var identifierResult = scanForIdentifier(lineIndex, posIndex);
                if (identifierResult.isFound()) {
                    Logger.debugf("Token %s at line %d from %d to %d \n", identifierResult.getToken().getType().toString(),
                            lineIndex, posIndex, identifierResult.getNewPosIndex());
                    posIndex = identifierResult.getNewPosIndex();
                    tokens.add(identifierResult.getToken());
                    continue;
                }


                var intResult = scanForIntLiteral(lineIndex, posIndex);
                if (intResult.isFound()) {
                    Logger.debugf("Token %s at line %d from %d to %d \n", intResult.getToken().getType().toString(),
                            lineIndex, posIndex, intResult.getNewPosIndex());
                    posIndex = intResult.getNewPosIndex();
                    tokens.add(intResult.getToken());
                    continue;
                }

                var stringResult = scanForStringLiteral(lineIndex, posIndex);
                if (stringResult.isFound()) {
                    Logger.debugf("Token %s at line %d from %d to %d \n", stringResult.getToken().getType().toString(),
                            lineIndex, posIndex, stringResult.getNewPosIndex());
                    posIndex = stringResult.getNewPosIndex();
                    tokens.add(stringResult.getToken());
                    continue;
                }


                throw new UnknownTokenException(lineIndex, posIndex, sourceLines.get(lineIndex).substring(0, posIndex + 1));

                //Todo...


            }


        }


    }

    private boolean isWhiteSpace(int lineIndex, int posIndex) {
        return LexerConstants.WHITESPACE_CHARS.contains(sourceLines.get(lineIndex).charAt(posIndex));
    }


    private LexerKeywordScanTuple scanForKeyword(int lineIndex, int posIndex) {
        return scanForConstantToken(lineIndex, posIndex, LexerConstants.KEYWORDS);
    }

    private LexerKeywordScanTuple scanForSymbol(int lineIndex, int posIndex) {
        return scanForConstantToken(lineIndex, posIndex, LexerConstants.SYMBOLS);
    }

    private LexerKeywordScanTuple scanForConstantToken(int lineIndex, int posIndex, Map<String, TokenType> targetSet) {
        String currentLine = sourceLines.get(lineIndex);
        for (int i = posIndex; i <= currentLine.length(); i++) {
            String valueToScan = currentLine.substring(posIndex, i);

            if (targetSet.containsKey(valueToScan)) {
                return new LexerKeywordScanTuple(targetSet.get(valueToScan), i);
            } else {
                long approximateMatches = targetSet.entrySet().stream()
                        .filter(stringTokenTypeEntry -> stringTokenTypeEntry.getKey().startsWith(valueToScan)).count();
                if (approximateMatches == 0) {
                    return new LexerKeywordScanTuple();
                }
            }
        }
        return new LexerKeywordScanTuple();
    }

    private LexerValueScanTuple scanForIdentifier(int lineIndex, int posIndex) {
        //
        String currentLine = sourceLines.get(lineIndex);
        if (!LexerConstants.VALID_IDENTIFIER_CHARS.contains(currentLine.charAt(posIndex))) {
            return new LexerValueScanTuple();
        }

        StringBuilder identifierName = new StringBuilder();
        int i;
        for (i = posIndex; i < currentLine.length(); i++) {
            var valueToScan = currentLine.charAt(i);
            if (!LexerConstants.VALID_IDENTIFIER_CHARS.contains(valueToScan)) {
                break;
            }
            identifierName.append(valueToScan);
        }

        return new LexerValueScanTuple(new Token(TokenType.IDENTIFIER, identifierName.toString(), lineIndex), i);
    }

    //TODO: We actually need a float scanner still
    private LexerValueScanTuple scanForIntLiteral(int lineIndex, int posIndex) {
        //
        String currentLine = sourceLines.get(lineIndex);
        if (!LexerConstants.VALID_NUMERIC_CHARS.contains(currentLine.charAt(posIndex))) {
            return new LexerValueScanTuple();
        }

        StringBuilder identifierName = new StringBuilder();
        int i;
        for (i = posIndex; i < currentLine.length(); i++) {
            var valueToScan = currentLine.charAt(i);
            if (!LexerConstants.VALID_NUMERIC_CHARS.contains(valueToScan)) {
                break;
            }
            identifierName.append(valueToScan);
        }

        return new LexerValueScanTuple(new Token(TokenType.INT_LITERAL, identifierName.toString(), lineIndex), i);
    }

    private LexerValueScanTuple scanForStringLiteral(int lineIndex, int posIndex) {
        //
        String currentLine = sourceLines.get(lineIndex);
        if (currentLine.charAt(posIndex) != '"') {
            return new LexerValueScanTuple();
        }

        StringBuilder identifierName = new StringBuilder();
        int i;
        //+1 because we don´t want the string literal start token...
        for (i = posIndex + 1; i < currentLine.length(); i++) {
            var valueToScan = currentLine.charAt(i);
            if (valueToScan == '"') {
                break;
            }
            identifierName.append(valueToScan);
        }

        return new LexerValueScanTuple(new Token(TokenType.STRING_LITERAL, identifierName.toString(), lineIndex), i);
    }

    public void printInternalState() {

        var byLine = this.tokens.stream().collect(Collectors.groupingBy(Token::getLineNumber));

        byLine.forEach((line, tokensAtLine) -> {
            System.out.print(line + ": ");
            System.out.println(tokensAtLine);
        });
    }


}
