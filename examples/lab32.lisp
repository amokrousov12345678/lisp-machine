(comment Lab3.2 by amokrousov, rewritten to amdp-lisp)
(defn chunkify (chunkSize seq) 
    (if (empty? seq) (list) (lazy-seq (lazy-cat (list (take chunkSize seq)) (chunkify chunkSize (drop chunkSize seq)))))
)

(defn joinChunks (seq)
	(if (empty? seq) (list) (lazy-seq (lazy-cat (first seq) (joinChunks (rest seq)))))
)
(defn parallelFilter (f chunkSize futureChunkSize seq)
    (->> (chunkify chunkSize seq)
         (map (fn (chunk) (future (fn () (doall (filter f chunk))))))
         (chunkify futureChunkSize)
		 (map (fn (futureChunk) (doall futureChunk) (joinChunks (map deref futureChunk))))
		 (joinChunks)
    )
)

(def naturals (iterate (fn (x) (inc x)) 1))
(def naturals2 (iterate (fn (x) (inc x)) 1))


(let (pred (fn (x) (comment static. java.lang.Thread sleep 100) (= (mod x 3) 0))
    pred2 (fn (x) (comment static. java.lang.Thread sleep 100) (= (mod x 10000) 0))
)
    (print "(take 5 (chunkify 10 naturals)):")
    (print (take 5 (chunkify 10 naturals)))
	(print "(chunkify 10 (take 46 naturals))")
    (print (chunkify 10 (take 46 naturals)))  
    (print "(parallelFilter pred 10 (take 46 naturals))")
    (print (parallelFilter pred 10 5 (take 46 naturals)))
    (print "(take 15 (parallelFilter pred 10 naturals))")
    (print (take 15 (parallelFilter pred 10 1 naturals)))
    (print "Now lazyness check")
    (def tmp (parallelFilter pred 100 100 (take 460000 naturals)))
    (print "Filter result created but not acquired")
    (doall tmp)
    (print "Now acquired")
    (doall tmp)
    (print "Acquired once more (faster)")
    (def tmp2 (map (fn (x) (print x)) (parallelFilter pred2 100 100 naturals2)))
    (doall tmp2)
)