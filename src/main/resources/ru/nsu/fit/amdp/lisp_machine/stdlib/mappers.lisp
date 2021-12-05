(def comment (macro (fn (&args) nil)))

(comment "Map. Usage (map [function elem->f(elem)] [sequence])")
(def map (fn (fun args)
    (reduce
        (fn (acc e) (conj (fun e) acc))
        args
        (list)
    )
))
