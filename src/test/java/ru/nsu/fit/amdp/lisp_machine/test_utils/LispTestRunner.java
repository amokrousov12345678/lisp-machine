package ru.nsu.fit.amdp.lisp_machine.test_utils;

import org.junit.jupiter.api.Assertions;
import ru.nsu.fit.amdp.lisp_machine.grammar.ParseException;
import ru.nsu.fit.amdp.lisp_machine.runtime.Machine;

/**
 * Lisp test runner for tests written in lisp.
 */
public class LispTestRunner {

    /**
     * Common instance of machine for unit tests for better unit-test performance
     */
    private static final Machine commonMachine = Machine.getInstanceWithLoadedLibrary();

    /**
     * @param lispStatement1 actual statement
     * @param lispStatement2 expected result of lispStatement1 evaluation
     * @param contextSetup program to be loaded before test execution, ignored if null
     * @param machine machine to run tests one, if null uses commonMachine for test run
     * @throws ParseException on syntax error
     */
    public static void checkStatementsForEquality(String lispStatement1,
                                                  String lispStatement2,
                                                  String contextSetup,
                                                  Machine machine) throws ParseException {
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

    /**
     * @param lispStatement1 actual statement
     * @param lispStatement2 expected result of lispStatement1 evaluation
     * @param contextSetup program to be loaded before test execution, ignored if null
     * @throws ParseException on syntax error
     */
    public static void checkStatementsForEquality(String lispStatement1,
                                                     String lispStatement2,
                                                     String contextSetup) throws ParseException {
        checkStatementsForEquality(lispStatement1, lispStatement2, contextSetup, null);
    }

    /**
     * @param lispStatement1 actual statement
     * @param lispStatement2 expected result of lispStatement1 evaluation
     * @throws ParseException on syntax error
     */
    public static void checkStatementsForEquality(String lispStatement1,
                                                     String lispStatement2) throws ParseException {
        checkStatementsForEquality(lispStatement1, lispStatement2, null);
    }
}
