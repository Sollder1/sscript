package de.sollder1.sscript.parser;

import de.sollder1.sscript.Logger;
import de.sollder1.sscript.lexer.Lexer;
import de.sollder1.sscript.parser.statements.FunctionStatement;
import de.sollder1.sscript.parser.statements.Statement;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

public class Parser {

    private final Lexer lexer;

    private final List<Statement> statements = new LinkedList<>();

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }


    public void parse() {
        var tokensByLine = lexer.getTokensByLine();

        for (var entry : tokensByLine.entrySet()) {
            var firstTokenType = entry.getValue().get(0).getType();
            var statementClass = ParserConstants.START_TOKEN_TO_STATEMENT_CLASS.get(firstTokenType);

            if(statementClass == null) {
                //TODO... Errorhandling
                throw new IllegalArgumentException();
            }

            try {
                var statement = statementClass.getDeclaredConstructor(List.class).newInstance(entry.getValue());
                statement.simpleValidateAndParse();
                statements.add(statement);
                //TODO... Errorhandling
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    public void printInternalState() {
        statements.stream().map(Statement::toString).forEach(Logger::debug);
    }

}
