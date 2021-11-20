package ru.nsu.fit.amdp.lisp_machine;

import ru.nsu.fit.amdp.lisp_machine.grammar.LispStatement;
import ru.nsu.fit.amdp.lisp_machine.grammar.ParseException;
import ru.nsu.fit.amdp.lisp_machine.grammar.SimpleNode;

public class Main {
    public static void main(String[] args) {
        System.out.println("It's lisp machine");
        LispStatement parser = new LispStatement(System.in);
        try {
            SimpleNode root = parser.LispExpressions();
            root.dump("");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}