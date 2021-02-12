(ns red-tetris.tetroes)

(def tetroes [::L ::J ::T ::I ::Z ::S ::O])

(defn- seeded-random-seq
  "Returns a pseudorandom lazy seq of elements of coll, with seed s.
   Uses hash-fn as step function."
  [coll hash-fn s]
  (let [select #(nth coll (mod % (count coll)))]
    (map select (iterate hash-fn s))))

(defn random-tetroes
  "Returns a pseudorandom lazy seq of tetrominoes, with seed s."
  [s]
  (seeded-random-seq tetroes
                    #?(:clj hash :cljs m3-hash-int)
                    s))
  
(defn random-tetroes!
  "Returns a random lazy seq of tetrominoes."
  []
  (random-tetroes (rand-int 2147483647)))

(comment
  (for [x (range 10)]
    (print (hash x) \newline))
  (def sss (chan))
  (def bbb (random-tetroes 5))
  (def ccc (random-tetroes 5))
  (take 9 (random-tetroes!))
  (onto-chan sss bbb)
  (realized? (rest bbb))
  (realized? bbb) ; false
  (take 10 bbb) ; now bbb is realized
  (realized? ccc)) ; but ccc is not

(comment
  (def random-tetroes-memo (memoize random-tetroes))
  (def a (random-tetroes-memo 10))
  (realized? a) ; false
  (def b (random-tetroes-memo 10))
  (take 10 a)
  (realized? a) ; true
  (realized? b)) ; also true!
