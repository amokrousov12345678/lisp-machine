package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.datatypes;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.LispExecutableList;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LispPersistentList{

    private final List<Expression> list;

    public LispPersistentList(List<Expression> list) {
        this.list = list;
    }

    public LispPersistentList (LispExecutableList list) {
        this.list = list.asList();
    }

    @Override
    public String toString(){
        return list.stream()
                .map(Objects::toString)
                .collect(Collectors.joining(" ", "(", ")"));
    }

    /* For unit testing purposes (fix next time) */

    public int size() {
        return list.size();
    }

    public Expression get(int index) {
        return list.get(index);
    }

    public List<Expression> asList() {
        return list;
    }
}
