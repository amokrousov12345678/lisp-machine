package ru.nsu.fit.amdp.lisp_machine.runtime;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import ru.nsu.fit.amdp.lisp_machine.grammar.ParseException;
import ru.nsu.fit.amdp.lisp_machine.parser.LispParser;
import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.context.LispContext;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.*;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.PrintOperation;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.comparators.LispEquals;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.comparators.LispLess;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.list.*;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.logic.*;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.math.*;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional.*;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Machine {

    private final Context context;

    public Machine(){
        context = new LispContext();

        // TODO: move context initialization elsewhere

        context.define(new LispIdentifier("+"), new Add());
        context.define(new LispIdentifier("-"), new Sub());
        context.define(new LispIdentifier("*"), new Mult());
        context.define(new LispIdentifier("/"), new Div());
        context.define(new LispIdentifier("mod"), new Mod());
        context.define(new LispIdentifier("print"), new PrintOperation());
        context.define(new LispIdentifier("def"), new LispDefine());
        context.define(new LispIdentifier("fn"), new LispFn());
        context.define(new LispIdentifier("list"), new CreateList());
        context.define(new LispIdentifier("first"), new ListFirst());
        context.define(new LispIdentifier("rest"), new ListRest());
        context.define(new LispIdentifier("count"), new ListCount());
        context.define(new LispIdentifier("concat"), new ListConcat());
        context.define(new LispIdentifier("quote"), new LispQuote());
        context.define(new LispIdentifier("eval"), new LispEval());
        context.define(new LispIdentifier("if"), new LispIf());
        context.define(new LispIdentifier("macro"), new LispMacro());
        context.define(new LispIdentifier("macroexpand-1"), new LispMacroExpandOnce());
        context.define(new LispIdentifier("apply"), new LispApply());
        context.define(new LispIdentifier("reduce"), new LispReduce());
        context.define(new LispIdentifier("recur"), new LispRecur());

        var eq = new LispEquals();
        context.define(new LispIdentifier("=="), eq);
        context.define(new LispIdentifier("="), eq);
        context.define(new LispIdentifier("eq"), eq);

        context.define(new LispIdentifier("<"), new LispLess());
        context.define(new LispIdentifier("!"), new LispNot());
        context.define(new LispIdentifier("and"), new LispAnd());
        context.define(new LispIdentifier("or"), new LispOr());

        context.define(new LispIdentifier("true"), new LispObject(true));
        context.define(new LispIdentifier("false"), new LispObject(false));
        context.define(new LispIdentifier("nil"), new LispObject(null));

        context.define(new LispIdentifier("new"), new LispNativeNew());
        context.define(new LispIdentifier("."), new LispNativeCall());
        context.define(new LispIdentifier("static."), new LispNativeStaticCall());

        context.define(new LispIdentifier("future"), new LispFuture());
        context.define(new LispIdentifier("deref"), new LispDeref());

        context.define(new LispIdentifier("atom"), new LispAtomCreate());
        context.define(new LispIdentifier("reset!"), new LispAtomAssign());
        context.define(new LispIdentifier("swap!"), new LispAtomModify());
    }

    public void eval(List<LispExecutableList> program) {
        for (var instruction : program) {
            instruction.evaluate(context);
        }
    }

    public Expression eval(Expression statement) {
        return statement.evaluate(context);
    }

    public void loadStandardLibrary() throws ParseException, IOException {
        Reflections reflections = new Reflections("ru.nsu.fit.amdp.lisp_machine.stdlib", Scanners.values());
        Set<String> fileNamesSet = reflections.getResources(".*\\.lisp");
        List<String> fileNames = fileNamesSet.stream().sorted().collect(Collectors.toList());
        for (var fileName : fileNames) {
            var inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
            assert(inputStream != null);
            eval(LispParser.parseLispProgram(inputStream));
            System.out.println("Loaded library file " + fileName);
        }
    }
}
