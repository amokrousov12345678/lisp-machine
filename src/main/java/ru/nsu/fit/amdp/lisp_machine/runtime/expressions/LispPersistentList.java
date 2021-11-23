package ru.nsu.fit.amdp.lisp_machine.runtime.expressions;

import java.util.List;

public class LispPersistentList{

    private final List<Expression> list;

    public LispPersistentList(List<Expression> list) {
        this.list = list;
    }

    public LispPersistentList (LispExecutableList list) {
        this.list = list.asList();
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
