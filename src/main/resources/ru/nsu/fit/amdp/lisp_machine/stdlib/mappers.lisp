(def comment (macro (fn (&args) nil)))

(comment "Returns a new list where x is the last element")
(def conj (fn (x seq) (concat seq (list x))))

(comment "Map. Usage (map [function elem->f(elem)] [sequence])")
(def map (fn (fun args)
    (reduce
        (fn (acc e) (conj (fun e) acc))
        args
        (list)
    )
))

(comment "Filter. Usage (filter [predicate elem->bool] [sequence])")
(def filter (fn (pred args)
    (reduce
        (fn (acc e)
            (if (pred e)
                (conj e acc)
                acc
            )
        )
        args
        (list)
    )
))
