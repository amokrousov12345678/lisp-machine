(def comment (macro (fn (&args) nil)))

(comment "Returns a new list where x is the first element and seq is the rest")
(def cons (fn (x seq) (concat (list x) seq)))

(comment "Returns a new list where x is the last element")
(def conj (fn (x seq) (concat seq (list x))))

(comment "Returns the nth element of given sequence")
(def nth (fn (n seq)
    (if (= n 0)
        (first seq)
        (recur (- n 1) (rest seq)))
))

(comment "Returns the second element of given sequence")
(def second (fn (seq) (nth 1 seq)))

(comment "Returns the third element of given sequence")
(def third (fn (seq) (nth 2 seq)))

(comment "Returns the last element of given sequence")
(def last (fn (seq) (nth (- (count seq) 1) seq)))

(comment "Base function for sequence generation")
(def __range__ (fn (COUNT STEP START)
	(if (< COUNT 0)
        (list)
        (if (= COUNT 1)
            (list START)
            (lazy-seq (lazy-cat (list START)
                                (__range__ (- COUNT 1) STEP (+ STEP START)))))
    )
))

(comment "(ssrange COUNT STEP START).Returns range of COUNT numbers with step STEP starting from START")
(def ssrange (fn (COUNT STEP START)
    (__range__ COUNT STEP START)
))

(comment "(srange COUNT STEP START).Returns range of COUNT numbers with step STEP starting from 0")
(def srange (fn (COUNT STEP)
    (ssrange COUNT STEP 0)
))

(comment "(srange COUNT STEP START).Returns range of COUNT sequential numbers starting from 0")
(def range (fn (COUNT)
    (srange COUNT 1)
))

(comment "Returns lazy sequence of the first COUNT elements of provided sequence SEQ")
(def take (fn (COUNT SEQ)
    (if (or (< COUNT 0)
            (empty? SEQ))
        (list)
        (if (= COUNT 1)
            (list (first SEQ))
            (lazy-seq (lazy-cat (list (first SEQ))
                                (take (- COUNT 1) (rest SEQ))))
        )
    )
))

(comment "Returns lazy sequence of elements of SEQ where first COUNT elements are removed")
(def drop (fn (COUNT SEQ)
    (if (or (< COUNT 1)
            (empty? SEQ))
            SEQ
            (recur (- COUNT 1) (rest SEQ))
        )
))

(comment "Infinite range with customizable START and STEP")
(def ssinfrange (fn (START STEP)
    (lazy-seq (lazy-cat (list START)
                        (ssinfrange (+ STEP START) STEP))))
)

(comment "Infinite range with customizable START")
(def sinfrange (fn (START)
    (ssinfrange START 1)))

(comment "Infinite range with STEP=1 and START=0")
(def infrange (fn () (sinfrange 0)))
