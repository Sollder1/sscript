package de.sollder1.sscript.parser.enums;

import de.sollder1.sscript.lexer.tokens.Token;

public enum DataType {
    INT, FLOAT, STRING, BOOL, VOID;

    public static DataType fromToken(Token token) {
        return switch (token.getType()) {
            case INT -> DataType.INT;
            case FLOAT -> DataType.FLOAT;
            case BOOL -> DataType.BOOL;
            case STRING -> DataType.STRING;
            default -> throw new IllegalStateException("Unexpected value: " + token.getType());
        };
    }
}
