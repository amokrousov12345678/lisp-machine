package ru.nsu.fit.amdp.lisp_machine.test_utils;

import org.junit.jupiter.api.Assertions;
import ru.nsu.fit.amdp.lisp_machine.grammar.ParseException;
import ru.nsu.fit.amdp.lisp_machine.runtime.Machine;

import java.io.IOException;
import java.util.Objects;

public class LispTestRunner {

    private static final Machine commonMachine = Machine.getInstanceWithLoadedLibrary();

    public static void checkStatementsForEquality(String lispStatement1,
                                                     String lispStatement2,
                                                     String contextSetup,
                                                     Machine machine) throws IOException, ParseException {
        if (machine == null) {
            machine = commonMachine;
        }

        if (contextSetup != null) {
            var setup = TestParser.parseLispStatement(contextSetup);
            machine.eval(setup);
        }

        var statement1 = TestParser.parseLispStatement(lispStatement1).get(0);
        var statement2 = TestParser.parseLispStatement(lispStatement2).get(0);

        Assertions.assertEquals(machine.evaluate(statement2), machine.evaluate(statement1));
    }

    public static void checkStatementsForEquality(String lispStatement1,
                                                     String lispStatement2,
                                                     String contextSetup) throws IOException, ParseException {
        checkStatementsForEquality(lispStatement1, lispStatement2, contextSetup, null);
    }

    public static void checkStatementsForEquality(String lispStatement1,
                                                     String lispStatement2) throws IOException, ParseException {
        checkStatementsForEquality(lispStatement1, lispStatement2, null);
    }
}
