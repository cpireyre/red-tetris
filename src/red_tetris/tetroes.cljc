(ns red-tetris.tetroes
  (:require [clojure.core.matrix :as matrix]))

(def tetroes [::L ::J ::T ::I ::Z ::S ::O])

;; L
(def L1 (matrix/array [[1 0 0]
                       [1 0 0]
                       [1 1 0]]))

(def L2 (matrix/array [[0 0 0]
                       [0 0 1]
                       [1 1 1]]))

(def L3 (matrix/array [[0 1 1]
                       [0 0 1]
                       [0 0 1]]))

(def L4 (matrix/array [[1 1 1]
                       [1 0 0]
                       [0 0 0]]))

;; J
(def J1 (matrix/array [[0 0 1]
                       [0 0 1]
                       [0 1 1]]))

(def J2 (matrix/array [[1 1 1]
                       [0 0 1]
                       [0 0 0]]))

(def J3 (matrix/array [[1 1 0]
                       [1 0 0]
                       [1 0 0]]))

(def J4 (matrix/array [[0 0 0]
                       [1 0 0]
                       [1 1 1]]))

;; T
(def T1 (matrix/array [[1 1 1]
                       [0 1 0]
                       [0 0 0]]))

(def T2 (matrix/array [[1 0 0]
                       [1 1 0]
                       [1 0 0]]))

(def T3 (matrix/array [[0 0 0]
                       [0 1 0]
                       [1 1 1]]))

(def T4 (matrix/array [[0 0 1]
                       [0 1 1]
                       [0 0 1]]))

;; I
(def I1 (matrix/array [[1 1 1 1]
                       [0 0 0 0]
                       [0 0 0 0]
                       [0 0 0 0]]

(def I2 (matrix/array [[1 0 0 0]
                       [1 0 0 0]
                       [1 0 0 0]
                       [1 0 0 0]])

(def I3 (matrix/array [[0 0 0 0]
                       [0 0 0 0]
                       [0 0 0 0]
                       [1 1 1 1]])

(def I4 (matrix/array [[0 0 0 1]
                       [0 0 0 1]
                       [0 0 0 1]
                       [0 0 0 1]])

;; Z
(def Z1 (matrix/array [[1 1 0]
                       [0 1 1]
                       [0 0 0]]))

(def Z2 (matrix/array [[0 1 0]
                       [1 1 0]
                       [1 0 0]]))

(def Z3 (matrix/array [[0 0 0]
                       [1 1 0]
                       [0 1 1]]))

(def Z4 (matrix/array [[0 0 1]
                       [0 1 1]
                       [0 1 0]]))

;; S
(def S1 (matrix/array [[0 1 1]
                       [1 1 0]
                       [0 0 0]]))

(def S2 (matrix/array [[1 0 0]
                       [1 1 0]
                       [0 1 0]]))

(def S3 (matrix/array [[0 0 0]
                       [0 1 1]
                       [1 1 0]]))

(def S4 (matrix/array [[0 1 0]
                       [0 1 1]
                       [0 0 1]]))

;; Putting it all together
(def L (cycle [L1 L2 L3 L4]))
(def J (cycle [J1 J2 J3 J4]))
(def T (cycle [T1 T2 T3 T4])
(def I (cycle [I1 I2 I3 I4]))
(def Z (cycle [Z1 Z2 Z3 Z4]))
(def S (cycle [S1 S2 S3 S4]))

;; O
(def O (cycle (matrix/array [[1 1] [1 1]])))

(def tetro-encodings {::L L ::J J ::T T ::I I ::S S ::O O})

(defn print-grid [ar] (map println ar))

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
  L1
  tetro-encodings
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
