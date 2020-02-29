(ns red-tetris.tetroes
  (:require [core.async :as ]))

(def tetroes [::L ::J ::T ::I ::Z ::S ::O])

(defn seeded-random-seq
  "Returns a pseudorandom lazy seq of elements of coll, with seed s.
   Uses hash-fn as step function."
  [coll hash-fn s]
  (let [select #(nth coll (mod % (count coll)))]
    (map select (iterate hash-fn s))))

(defn random-tetroes
  "Returns a pseudorandom lazy seq of tetrominoes, with seed s."
  [s]
  (seeded-random-seq tetroes m3-hash-int s))

(comment
  (take 10 (random-tetroes 4)))
