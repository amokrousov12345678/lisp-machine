(def comment (macro (fn (&args) nil)))

(comment "short way to define macro (defmacro name (args) &body)")
(def defmacro (macro (fn (name args &body) (concat (list (quote def)) (list name) (list (list (quote macro) (concat (list (quote fn) args) body)))))))

(comment "short way to define function (defn name (args) &body)")
(defmacro defn (name args &body) (list (quote def) name (concat (list (quote fn) args) body)))

(comment "transform expression like (infix 1 + 1) into prefix notation")
(def infix (macro (fn (first op next) (list op first next))))

(defn gen-var-decls (seq) (if (= (count seq) 0) (list) (concat (list (list (quote def) (first seq) (first (rest seq)))) (gen-var-decls (rest (rest seq))))))

(comment "(let (var1 value1 var2 value2 ...) &body) i.e (let (x 5 x (+ x 5) y 3) (print x) (print y))")
(defmacro let (var-vals &body) (list (concat (list (quote fn) (list)) (gen-var-decls var-vals) body)))

