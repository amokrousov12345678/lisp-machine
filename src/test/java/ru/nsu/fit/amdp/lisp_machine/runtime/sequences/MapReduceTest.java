package ru.nsu.fit.amdp.lisp_machine.runtime.sequences;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.amdp.lisp_machine.grammar.ParseException;
import ru.nsu.fit.amdp.lisp_machine.test_utils.LispTestRunner;

import java.io.IOException;

public class MapReduceTest {
    @Test
    public void reduceTest() throws IOException, ParseException {
        String actual = "(reduce + (range 101) 0)";
        String expected = "(first (list 5050))";
        LispTestRunner.checkStatementsForEquality(actual, expected);
    }

    @Test
    public void reduceLambdaTest() throws IOException, ParseException {
        String actual = "(reduce (fn (a b) b) (range 101) 0)";
        String expected = "(first (list 100))";
        LispTestRunner.checkStatementsForEquality(actual, expected);
    }

    @Test
    public void mapTest() throws IOException, ParseException {
        String actual = "(doall (map sqr (range 10)))";
        String expected = "(list 0 1 4 9 16 25 36 49 64 81)";
        LispTestRunner.checkStatementsForEquality(actual, expected);
    }

    @Test
    public void mapLambdaTest() throws IOException, ParseException {
        String actual = "(doall (map (fn (x) (- x)) (range 10)))";
        String expected = "(list 0 (- 1) (- 2) (- 3) (- 4) (- 5) (- 6) (- 7) (- 8) (- 9))";
        LispTestRunner.checkStatementsForEquality(actual, expected);
    }

    @Test
    public void filterTest() throws IOException, ParseException {
        String actual = "(doall (filter even? (range 20)))";
        String expected = "(list 0 2 4 6 8 10 12 14 16 18)";
        LispTestRunner.checkStatementsForEquality(actual, expected);
    }

    @Test
    public void filterLambdaTest() throws IOException, ParseException {
        String actual = "(doall (filter (fn (n) (= (mod n 2) 0)) (range 20)))";
        String expected = "(list 0 2 4 6 8 10 12 14 16 18)";
        LispTestRunner.checkStatementsForEquality(actual, expected);
    }

    @Test
    public void filterMapReduceTest() throws IOException, ParseException {
        String actual = "(reduce + (map sqr (filter even? (range 200))) 0)";
        String expected = "(first (list 1313400))";
        LispTestRunner.checkStatementsForEquality(actual, expected);
    }

    @Test
    public void mapFilterLaziness() throws IOException, ParseException {
        String actual = "(doall (take 10 (drop 21901 (filter even? (map sqr (infrange))))))";
        String expected = "(list 1918615204 1918790416 1918965636 1919140864 1919316100 1919491344" +
                          " 1919666596 1919841856 1920017124 1920192400)";
        LispTestRunner.checkStatementsForEquality(actual, expected);
    }
}
