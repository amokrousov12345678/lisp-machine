(def comment (macro (fn (&args) nil)))

(comment "Not equals")
(def != (fn (a b) (! (= a b))))

(comment "Less or equals")
(def <= (fn (a b) (or (= a b) (< a b))))

(comment "Greater or equals")
(def >= (fn (a b) (! (< a b))))

(comment "Greater")
(def > (fn (a b) (! (<= a b))))
