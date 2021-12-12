package ru.nsu.fit.amdp.lisp_machine.runtime.native_api;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.amdp.lisp_machine.grammar.ParseException;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispObject;
import ru.nsu.fit.amdp.lisp_machine.test_utils.LispTestRunner;

import java.io.IOException;

public class NativeCallsTest {
    @Test
    public void methodCallTest() throws IOException, ParseException {
        String actual = "(. toUpperCase \"fred\")";
        String expected = "\"FRED\"";
        LispTestRunner.checkStatementsForEquality(actual, expected);
    }

    @Test
    public void methodCallInMapTest() throws IOException, ParseException {
        String actual = "(doall (map (fn (e) (. toLowerCase e)) (list \"FRED\" \"xDD\" \"L0l\")))";
        String expected = "(list \"fred\" \"xdd\" \"l0l\")";
        LispTestRunner.checkStatementsForEquality(actual, expected);
    }

    @Test
    public void newCallTest() throws IOException, ParseException {
        String actual = "(. getYear (new java.util.Date))";
        String expected = String.valueOf(new java.util.Date().getYear());
        LispTestRunner.checkStatementsForEquality(actual, expected);
    }

    @Test
    public void staticMethodCallTest() throws IOException, ParseException {
        String actual = "(static. java.lang.Math floor 1.7)";
        String expected = "1.0";
        LispTestRunner.checkStatementsForEquality(actual, expected);
    }
}
