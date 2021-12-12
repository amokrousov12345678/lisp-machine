package ru.nsu.fit.amdp.lisp_machine.runtime.sequences;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.amdp.lisp_machine.grammar.ParseException;
import ru.nsu.fit.amdp.lisp_machine.test_utils.LispTestRunner;

import java.io.IOException;

public class SequencesOperationTest {

    @Test
    public void takeFromSequence() throws IOException, ParseException {
        String actual = "(doall (take 3 (list 1 2 3 4 5)))";
        String expected = "(list 1 2 3)";
        LispTestRunner.checkStatementsForEquality(actual, expected);
    }

    @Test
    public void takeFromShortSequence() throws IOException, ParseException {
        String actual = "(doall (take 3 (list 1 2)))";
        String expected = "(list 1 2)";
        LispTestRunner.checkStatementsForEquality(actual, expected);
    }

    @Test
    public void takeFromEmptySequence() throws IOException, ParseException {
        String actual = "(doall (take 3 (list)))";
        String expected = "(list)";
        LispTestRunner.checkStatementsForEquality(actual, expected);
    }

    @Test
    public void dropFromSequence() throws IOException, ParseException {
        String actual = "(doall (drop 3 (list 1 2 3 4 5)))";
        String expected = "(list 4 5)";
        LispTestRunner.checkStatementsForEquality(actual, expected);
    }

    @Test
    public void dropFromShortSequence() throws IOException, ParseException {
        String actual = "(doall (drop 3 (list 1 2)))";
        String expected = "(list)";
        LispTestRunner.checkStatementsForEquality(actual, expected);
    }

    @Test
    public void dropFromEmptySequence() throws IOException, ParseException {
        String actual = "(doall (drop 3 (list)))";
        String expected = "(list)";
        LispTestRunner.checkStatementsForEquality(actual, expected);
    }

    @Test
    public void lazycatTest() throws IOException, ParseException {
        String actual = "(doall (lazy-cat (list 2 3) (list) (list 2) (list 7 8)))";
        String expected1 = "(concat (list 2 3) (list) (list 2) (list 7 8))";
        String expected2 = "(list 2 3 2 7 8)";
        LispTestRunner.checkStatementsForEquality(actual, expected1);
        LispTestRunner.checkStatementsForEquality(actual, expected2);
    }

    @Test
    public void lazycatEmptySequencesTest() throws IOException, ParseException {
        String actual = "(doall (lazy-cat (list) (list) (list) (list)))";
        String expected = "(list)";
        LispTestRunner.checkStatementsForEquality(actual, expected);
    }
}
