package ru.nsu.fit.amdp.lisp_machine.test_utils;

import ru.nsu.fit.amdp.lisp_machine.grammar.ParseException;
import ru.nsu.fit.amdp.lisp_machine.runtime.Machine;

import java.io.IOException;
import java.util.Objects;

public class LispTestRunner {

    private static Machine commonMachine = Machine.getInstanceWithLoadedLibrary();

    public static boolean checkStatementsForEquality(String lispStatement1,
                                                     String lispStatement2,
                                                     String contextSetup,
                                                     Machine machine) throws IOException, ParseException {
        if (machine == null) {
            machine = new Machine();
        }

        if (contextSetup != null) {
            var setup = TestParser.parseLispStatement(contextSetup);
            machine.eval(setup);
        }

        var statement1 = TestParser.parseLispStatement(lispStatement1).get(0);
        var statement2 = TestParser.parseLispStatement(lispStatement2).get(0);

        return Objects.equals(machine.evaluate(statement1), machine.evaluate(statement2));
    }

    public static boolean checkStatementsForEquality(String lispStatement1,
                                                     String lispStatement2,
                                                     String contextSetup) throws IOException, ParseException {
        return checkStatementsForEquality(lispStatement1, lispStatement2, contextSetup, null);
    }

    public static boolean checkStatementsForEquality(String lispStatement1,
                                                     String lispStatement2) throws IOException, ParseException {
        return checkStatementsForEquality(lispStatement1, lispStatement2, null);
    }
}
