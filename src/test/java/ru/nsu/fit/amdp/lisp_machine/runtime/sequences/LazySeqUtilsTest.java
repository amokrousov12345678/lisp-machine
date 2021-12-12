package ru.nsu.fit.amdp.lisp_machine.runtime.sequences;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.amdp.lisp_machine.grammar.ParseException;
import ru.nsu.fit.amdp.lisp_machine.test_utils.LispTestRunner;

import java.io.IOException;

public class LazySeqUtilsTest {
    @Test
    public void zipTest() throws IOException, ParseException {
        String actual = "(doall (take 4 (zip (drop 20 (infrange)) (drop 40 (infrange)))))";
        String expected = "(list (list 20 40) (list 21 41) (list 22 42) (list 23 43))";
        LispTestRunner.checkStatementsForEquality(actual, expected);
    }

    @Test
    public void zipDifferentLengthTest() throws IOException, ParseException {
        String actual = "(doall (take 40 (zip (drop 20 (infrange)) (range 3))))";
        String expected = "(list (list 20 0) (list 21 1) (list 22 2))";
        LispTestRunner.checkStatementsForEquality(actual, expected);
    }

    @Test
    public void zipEmptyTest() throws IOException, ParseException {
        String actual = "(doall (take 40 (zip (drop 20 (infrange)) (list))))";
        String expected = "(list)";
        LispTestRunner.checkStatementsForEquality(actual, expected);
    }

    @Test
    public void enumerateTest() throws IOException, ParseException {
        String actual = "(doall (enumerate (take 3 (filter (fn (x) (= (mod x 10) 1)) (map sqr (range 1000))))))";
        String expected = "(list (list 0 1) (list 1 81) (list 2 121))";
        LispTestRunner.checkStatementsForEquality(actual, expected);
    }
}
