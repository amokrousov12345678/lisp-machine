package ru.nsu.fit.amdp.lisp_machine.runtime.sequences;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.amdp.lisp_machine.grammar.ParseException;
import ru.nsu.fit.amdp.lisp_machine.test_utils.LispTestRunner;

import java.io.IOException;

public class RangesTest {
    @Test
    public void simpleRangeTest() throws IOException, ParseException {
        String actual = "(doall (range 10))";
        String expected = "(list 0 1 2 3 4 5 6 7 8 9)";
        LispTestRunner.checkStatementsForEquality(actual, expected);
    }

    @Test
    public void rangeWithStepTest() throws IOException, ParseException {
        String actual = "(doall (srange 10 2))";
        String expected = "(list 0 2 4 6 8 10 12 14 16 18)";
        LispTestRunner.checkStatementsForEquality(actual, expected);
    }

    @Test
    public void rangeWithStepAndStartTest() throws IOException, ParseException {
        String actual = "(doall (ssrange 10 2 11))";
        String expected = "(list 11 13 15 17 19 21 23 25 27 29)";
        LispTestRunner.checkStatementsForEquality(actual, expected);
    }

    @Test
    public void emptyRangeTest() throws IOException, ParseException {
        String actual = "(doall (range 0))";
        String expected = "(list)";
        LispTestRunner.checkStatementsForEquality(actual, expected);
    }

    @Test
    public void takeFromRangeTest() throws IOException, ParseException {
        String actual = "(doall (take 4 (range 10)))";
        String expected = "(list 0 1 2 3)";
        LispTestRunner.checkStatementsForEquality(actual, expected);
    }

    @Test
    public void dropFromRangeTest() throws IOException, ParseException {
        String actual = "(doall (drop 6 (range 10)))";
        String expected = "(list 6 7 8 9)";
        LispTestRunner.checkStatementsForEquality(actual, expected);
    }

    @Test
    public void firstFromRangeTest() throws IOException, ParseException {
        String actual = "(first (range 10))";
        String expected = "(first (list 0))";
        LispTestRunner.checkStatementsForEquality(actual, expected);
    }

    @Test
    public void restFromRangeTest() throws IOException, ParseException {
        String actual = "(doall (rest (range 10)))";
        String expected = "(list 1 2 3 4 5 6 7 8 9)";
        LispTestRunner.checkStatementsForEquality(actual, expected);
    }

    @Test
    public void rangeLazinessTest() throws IOException, ParseException {
        String actual = "(first (range 9223372036854775599))";
        String expected = "(first (list 0))";
        LispTestRunner.checkStatementsForEquality(actual, expected);
    }

    @Test
    public void rangeTakeDropLazinessTest() throws IOException, ParseException {
        String actual = "(doall (take 3 (drop 72036 (range 9223372036854775599))))";
        String expected = "(list 72036 72037 72038)";
        LispTestRunner.checkStatementsForEquality(actual, expected);
    }

    @Test
    public void performanceRangeTest() throws IOException, ParseException {
        String actual = "(doall (take 10 (drop 192837 (range 12783682768))))";
        String expected = "(list 192837 192838 192839 192840 192841 192842 192843 192844 192845 192846)";
        LispTestRunner.checkStatementsForEquality(actual, expected);
    }

    @Test
    public void infiniteRangeTest() throws IOException, ParseException {
        String actual = "(doall (take 1000 (infrange)))";
        String expected = "(doall (take 1000 (range 123424)))";
        LispTestRunner.checkStatementsForEquality(actual, expected);
    }
}
