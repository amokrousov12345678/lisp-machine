(def comment (macro (fn (&args) nil)))

(comment "Check whether provided number is even")
(def even? (fn (n) (= (mod n 2) 0)))

(comment "Check whether provided number is odd")
(def odd? (fn (n) (= (mod n 2) 1)))
