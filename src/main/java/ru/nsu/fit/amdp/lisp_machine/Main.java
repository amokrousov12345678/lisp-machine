package ru.nsu.fit.amdp.lisp_machine;

import ru.nsu.fit.amdp.lisp_machine.grammar.ParseException;
import ru.nsu.fit.amdp.lisp_machine.parser.LispParser;
import ru.nsu.fit.amdp.lisp_machine.runtime.Machine;

import java.io.*;

public class Main {
    public static void main(String[] args) throws ParseException, FileNotFoundException {
        System.out.println("It's lisp machine");
        try {
            Machine machine = new Machine();
            machine.loadStandardLibrary();

            if (args.length>=1) {
                InputStream in = new FileInputStream(args[0]);
                var program = LispParser.parseLispProgram(in);
                machine.eval(program);
            } else {
                BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
                while (true) {
                    System.out.print("amdp-lisp=> ");
                    String inputString = buffer.readLine();
                    if (inputString == null) break;
                    var program = LispParser.parseLispProgram(inputString);
                    machine.eval(program, true);
                }
            }
        } catch (ParseException e) {
            System.out.println("Error while parsing " + (args.length>=1 ? "file '" + args[0] + "'" : "stdin"));
            throw e;
        } catch (FileNotFoundException e) {
            System.out.println("File '" + args[0] + "' not found");
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
