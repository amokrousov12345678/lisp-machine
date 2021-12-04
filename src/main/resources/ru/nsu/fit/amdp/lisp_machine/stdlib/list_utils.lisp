(def comment (macro (fn (&args) nil)))

(comment "Returns a new list where x is the first element and seq is the rest")
(def cons (fn (x seq) (concat (list x) seq)))

(comment "Returns a new list where x is the last element")
(def conj (fn (x seq) (concat seq (list x))))

(comment "Returns the nth element of given sequence")
(def nth (fn (n seq)
    (if (= n 0)
        (first seq)
        (nth (- n 1) (rest seq)))
))

(comment "Returns the second element of given sequence")
(def second (fn (seq) (nth 1 seq)))

(comment "Returns the third element of given sequence")
(def third (fn (seq) (nth 2 seq)))

(comment "Returns the last element of given sequence")
(def last (fn (seq) (nth (- (count seq) 1) seq)))
