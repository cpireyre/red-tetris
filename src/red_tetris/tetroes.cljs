(ns red-tetris.tetroes)

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
  (:require  [cljs.core.async :refer [go-loop chan take! onto-chan <! timeout]])
  (let [c (random-tetroes 42)
          tetroes-chan (chan)]
      (onto-chan tetroes-chan c)
      (go-loop [i 0]
               (when (< i 20)
                 (take! tetroes-chan print)
                 (<! (timeout 1000))
                 (recur (inc i)))))
  
  (def sss (chan))
  (def bbb (random-tetroes 5))
  (onto-chan sss bbb)
  (realized? (rest bbb)))
 
