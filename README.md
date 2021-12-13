
![Java Maven CI](https://github.com/amokrousov12345678/lisp-machine/actions/workflows/maven.yml/badge.svg)
# lisp-machine
Lisp machine for own lisp dialect inspired by CommonLisp and [Clojure](https://clojure.org/). You may find some examples [here](https://github.com/amokrousov12345678/lisp-machine/tree/main/src/main/resources/ru/nsu/fit/amdp/lisp_machine/stdlib).

Implemented as group project for [Advanced programming methods](http://ccfit.nsu.ru/~shadow/DT6/) course of the 1st master year of [FIT NSU](https://www.nsu.ru/n/information-technologies-department/).
## Build & run
Build
```mvn compile test-compile```

Run tests
```mvn test```

Run machine
```mvn exec:java```

Create JAR package
```mvn package -DskipTests && mkdir jar-package && cp target/*.jar jar-package```

Assemble javadoc
```mvn javadoc:javadoc```
## Usage
Current lisp machine implementation provides two options:
 - Source file execution. In this mode program is read from source file and then executed.
 - REPL mode. In this mode user inputs statements one by one to standard in. Result of each expression's evaluation is printed to standard out. BE CAREFUL WITH INFINITE LAZY SEQUENCES IN THIS MODE!!!
 
 In order to execute program from source file you should provide path to file with program as command line argument. For example: `machine my-lisp-program.lisp`.
 
 In order to run machine in REPL mode you should just run it without providing any command line arguments. You should just run `machine`.

  REPL mode example:
```lisp	
    amdp-lisp=> (defn sqr (x) (let (s (* x x)) s))
    ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispDefine@2db7a79b
    amdp-lisp=> (sqr 12)
    144
    amdp-lisp=> (defn sqr-normal (x) (* x x))
    ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispDefine@2db7a79b
    amdp-lisp=> (sqr-normal 65536)
    4294967296
    amdp-lisp=> (range 10)
    (0 1 2 3 4 5 6 7 8 9)
    amdp-lisp=> (static. java.lang.Math floor 1.7)
    1.0
```
## Authors
 - [Anton Mokrousov](https://github.com/amokrousov12345678) (a.mokrousov@g.nsu.ru)
 - [Denis Parfenov](https://github.com/tempoden) (d.parfenov@g.nsu.ru)
