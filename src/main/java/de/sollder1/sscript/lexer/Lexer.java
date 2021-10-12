package de.sollder1.sscript.lexer;

import de.sollder1.sscript.Logger;
import de.sollder1.sscript.lexer.exceptions.UnknownTokenException;
import de.sollder1.sscript.lexer.tokens.Token;

import java.util.LinkedList;
import java.util.List;

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

                throw new UnknownTokenException(lineIndex, posIndex,  sourceLines.get(lineIndex).substring(0, posIndex + 1));

                //Todo...


            }


        }


    }

    private boolean isWhiteSpace(int lineIndex, int posIndex) {
        return LexerConstants.WHITESPACE_CHARS.contains(sourceLines.get(lineIndex).charAt(posIndex));
    }


    private LexerKeywordScanTuple scanForKeyword(int lineIndex, int posIndex) {

        String currentLine = sourceLines.get(lineIndex);
        for (int i = posIndex; i <= currentLine.length(); i++) {
            String valueToScan = currentLine.substring(posIndex, i);

            if (LexerConstants.KEYWORDS.containsKey(valueToScan)) {
                return new LexerKeywordScanTuple(LexerConstants.KEYWORDS.get(valueToScan), i - 1);
                //return new LexerKeywordScanTuple(true, LexerConstants.KEYWORDS.get(valueToScan));
            } else {
                long approximateMatches = LexerConstants.KEYWORDS.entrySet().stream()
                        .filter(stringTokenTypeEntry -> stringTokenTypeEntry.getKey().startsWith(valueToScan)).count();
                if (approximateMatches == 0) {
                    return new LexerKeywordScanTuple();
                }
            }
        }
        return new LexerKeywordScanTuple();

    }


    public void printInternalState() {
        System.out.println(tokens);
    }


}
