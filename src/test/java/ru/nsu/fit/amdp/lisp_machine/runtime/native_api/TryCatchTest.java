package ru.nsu.fit.amdp.lisp_machine.runtime.native_api;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.amdp.lisp_machine.grammar.ParseException;
import ru.nsu.fit.amdp.lisp_machine.test_utils.LispTestRunner;

import java.io.IOException;

public class TryCatchTest {
    @Test
    public void tryTest_Ok() throws IOException, ParseException {
        String actual = "(try (fn () (/ 1 1)) (catch java.lang.ArithmeticException (fn (e) (. getMessage e))))";
        String expected = "1";
        LispTestRunner.checkStatementsForEquality(actual, expected);
    }

    @Test
    public void tryTest_Exception() throws IOException, ParseException {
        String actual = "(try (fn () (/ 1 0)) (catch java.lang.ArithmeticException (fn (e) (. getMessage e))))";
        String expected = "\"/ by zero\"";
        LispTestRunner.checkStatementsForEquality(actual, expected);
    }

    @Test
    public void throwTest() throws IOException, ParseException {
        String actual = "(try (fn () (throw (new java.io.IOException))) (catch java.io.IOException (fn (e) 7)))";
        String expected = "7";
        LispTestRunner.checkStatementsForEquality(actual, expected);
    }

    @Test
    public void tryCatchResultTest() throws IOException, ParseException {
        String throwerDefinition = "(defn thrower (x) (if (= x 0) x (if (= x 1) (throw (new java.io.IOException))" +
                " (throw (new java.lang.StackOverflowError)))))";
        String testerDefinition = "(defn tester (x) (try (fn () (thrower x)) (catch java.io.IOException (fn (e) 7))" +
                " (catch java.lang.StackOverflowError (fn (e) \"XDD\"))))";

        {
            String actual = "(tester 0)";
            String expected = "0";
            LispTestRunner.checkStatementsForEquality(actual, expected, throwerDefinition + testerDefinition);
        }

        {
            String actual = "(tester 1)";
            String expected = "7";
            LispTestRunner.checkStatementsForEquality(actual, expected);
        }

        {
            String actual = "(tester 3)";
            String expected = "\"XDD\"";
            LispTestRunner.checkStatementsForEquality(actual, expected);
        }
    }

}
