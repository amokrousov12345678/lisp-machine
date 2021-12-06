(def comment (macro (fn (&args) nil)))

(comment "Check whether provided number is even")
(def even? (fn (n) (= (mod n 2) 0)))

(comment "Check whether provided number is odd")
(def odd? (fn (n) (= (mod n 2) 1)))

(comment "Returns value increased by 1")
(def inc (fn (n) (+ n 1)))

(comment "Returns value decreased by 1")
(def dec (fn (n) (- n 1)))

(comment "Returns square of n")
(def sqr (fn (n) (* n n)))

(comment "Check whether a is divisor of b")
(def divides? (fn (a b) (= (mod b a) 0)))

(comment "GCD of a b")
(def gcd (fn (a b)
    (if (> b a)
        (recur b a)
        (if (= b 0)
            a
            (recur b (mod a b))
        )
    )
))
