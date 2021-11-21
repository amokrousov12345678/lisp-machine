package ru.nsu.fit.amdp.lisp_machine;

import ru.nsu.fit.amdp.lisp_machine.grammar.ParseException;
import ru.nsu.fit.amdp.lisp_machine.parser.LispParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws ParseException, FileNotFoundException {
        System.out.println("It's lisp machine");
        try {
            InputStream in = args.length>=1 ? new FileInputStream("input.txt") : System.in;
            LispParser.parseLispProgram(in);
        } catch (ParseException e) {
            System.out.println("Error while parsing " + (args.length>=1 ? "file '" + args[0] + "'" : "stdin"));
            throw e;
        } catch (FileNotFoundException e) {
            System.out.println("File '" + args[0] + "' not found");
            throw e;
        }
    }
}