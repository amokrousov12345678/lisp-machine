(def comment (macro (fn (&args) nil)))

(comment "Returns a new list where x is the last element")
(def conj (fn (x seq) (concat seq (list x))))

(comment "Map. Usage (map [function elem->f(elem)] [sequence])")
(def map (fn (fun seq)
    (if (empty? seq)
        (list)
        (lazy-seq (lazy-cat (list (fun (first seq)))
                            (map fun (rest seq))))
    )
))

(comment "Filter. Usage (filter [predicate elem->bool] [sequence])")
(def filter (fn (pred seq)
    (if (empty? seq)
            (list)
            (if (pred (first seq))
                (lazy-seq (lazy-cat (list (first seq))
                                    (filter pred (rest seq))))
                (lazy-seq (filter pred (rest seq)))
            )
        )
))

(comment "Zip. Returns lazy sequence of pairs of elements from provided sequences.
Length of zip(seq1, seq2) is min(seq1.len, seq2.len)")
(def zip (fn (seq1 seq2)
    (if (or (empty? seq1)
            (empty? seq2))
        (list)
        (lazy-seq (lazy-cat (list (list (first seq1) (first seq2)))
                            (zip (rest seq1) (rest seq2)))))
))

(comment "Enumerate. Returns lazy sequence of enumerated elements of provided sequence
in format ((0 seq[0]) (1 seq[1]) ...)")
(def enumerate (fn (seq)
    (zip (infrange) seq)
))

(comment "generate infinite sequence (x (f x) (f (f x)) ...)")
(defn iterate (f x) (lazy-seq (lazy-cat (list x) (iterate f (f x)))))

(comment "Identity transform")
(def identity (fn (x) x))
