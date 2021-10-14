package de.sollder1.sscript.parser.pojos;

import de.sollder1.sscript.parser.enums.DataType;

public record FunctionParameter(String identifier, DataType dataType) {

    @Override
    public String toString() {
        return identifier + ": " + dataType;
    }
}

