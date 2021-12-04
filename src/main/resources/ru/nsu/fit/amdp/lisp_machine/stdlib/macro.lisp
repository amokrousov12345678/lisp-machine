(def comment (macro (fn (&args) nil)))

(comment "short way to define macro (defmacro name (args) &body)")
(def defmacro (macro (fn (name args &body) (concat (list (quote def)) (list name) (list (list (quote macro) (concat (list (quote fn) args) body)))))))

(comment "short way to define function (defn name (args) &body)")
(defmacro defn (name args &body) (list (quote def) name (concat (list (quote fn) args) body)))

(comment "transform expression like (infix 1 + 1) into prefix notation")
(def infix (macro (fn (first op next) (list op first next))))

(comment "functions below DON'T work")
(comment "apply for single list of args")
(def apply-single (macro (fn (fun arglist) (concat (list fun) (eval arglist)))))

(comment "apply for multiple lists of arguments")
(def apply (macro (fn (fun &args) (concat (list fun) (apply-single concat args)))))
