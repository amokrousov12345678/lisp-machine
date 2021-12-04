(def infix (macro (fn (first op next) (list op first next))))

(def comment (macro (fn (&args) nil)))

(comment "apply for single list of args")
(def apply-single (macro (fn (fun arglist) (concat (list fun) (eval arglist)))))

(comment "apply for multiple lists of arguments")
(def apply (macro (fn (fun &args) (concat (list fun) (apply-single concat args)))))
