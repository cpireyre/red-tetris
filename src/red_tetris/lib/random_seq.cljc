(ns red-tetris.lib.random-seq)

(defn- seeded-random-seq-with-hash
  "Returns a pseudorandom INFINITE lazy seq of elements of coll, with seed s.
   Uses hash-fn as step function."
  [coll hash-fn s]
  (let [select #(nth coll (mod % (count coll)))]
    (map select (iterate hash-fn s))))

(defn seeded-random-seq
  "Returns a pseudorandom INFINITE lazy seq of elements of coll, with seed s."
  [coll s]
  (seeded-random-seq-with-hash coll
                    #?(:clj hash :cljs m3-hash-int)
                    s))
  
(defn random-seq
  "Returns a random INFINITE lazy seq of elements of coll."
  [coll]
  (seeded-random-seq coll (rand-int 2147483647)))
;; rand-int is cljc-compliant

(comment
    (random-seq [1 2 3 4])
    (seeded-random-seq [1 2 3 4] 42)
)
