(ns red-tetris.tetris.tetrominos
  (:require [clojure.core.matrix :as matrix]
            [red-tetris.lib.random-seq :refer [random-seq!]]))

;; This isn't cljc because of core.matrix
;; Technically there's a cljs implementation kind of sort of.

(def tetromino-keys #{::L ::J ::T ::I ::Z ::S ::O})

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
(def J1 (matrix/array [[0 0 2]
                       [0 0 2]
                       [0 2 2]]))

(def J2 (matrix/array [[2 2 2]
                       [0 0 2]
                       [0 0 0]]))

(def J3 (matrix/array [[2 2 0]
                       [2 0 0]
                       [2 0 0]]))

(def J4 (matrix/array [[0 0 0]
                       [2 0 0]
                       [2 2 2]]))

;; T
(def T1 (matrix/array [[3 3 3]
                       [0 3 0]
                       [0 0 0]]))

(def T2 (matrix/array [[3 0 0]
                       [3 3 0]
                       [3 0 0]]))

(def T3 (matrix/array [[0 0 0]
                       [0 3 0]
                       [3 3 3]]))

(def T4 (matrix/array [[0 0 3]
                       [0 3 3]
                       [0 0 3]]))

;; I
(def I1 (matrix/array [[4 4 4 4]
                       [0 0 0 0]
                       [0 0 0 0]
                       [0 0 0 0]]))

(def I2 (matrix/array [[4 0 0 0]
                       [4 0 0 0]
                       [4 0 0 0]
                       [4 0 0 0]]))

(def I3 (matrix/array [[0 0 0 0]
                       [0 0 0 0]
                       [0 0 0 0]
                       [4 4 4 4]]))

(def I4 (matrix/array [[0 0 0 4]
                       [0 0 0 4]
                       [0 0 0 4]
                       [0 0 0 4]]))

;; Z
(def Z1 (matrix/array [[5 5 0]
                       [0 5 5]
                       [0 0 0]]))

(def Z2 (matrix/array [[0 5 0]
                       [5 5 0]
                       [5 0 0]]))

(def Z3 (matrix/array [[0 0 0]
                       [5 5 0]
                       [0 5 5]]))

(def Z4 (matrix/array [[0 0 5]
                       [0 5 5]
                       [0 5 0]]))

;; S
(def S1 (matrix/array [[0 6 6]
                       [6 6 0]
                       [0 0 0]]))

(def S2 (matrix/array [[6 0 0]
                       [6 6 0]
                       [0 6 0]]))

(def S3 (matrix/array [[0 0 0]
                       [0 6 6]
                       [6 6 0]]))

(def S4 (matrix/array [[0 6 0]
                       [0 6 6]
                       [0 0 6]]))

;; O
(def O (cycle (matrix/array [[7 7] [7 7]])))

;; Putting it all together
(def L (cycle [L1 L2 L3 L4]))
(def J (cycle [J1 J2 J3 J4]))
(def T (cycle [T1 T2 T3 T4]))
(def I (cycle [I1 I2 I3 I4]))
(def Z (cycle [Z1 Z2 Z3 Z4]))
(def S (cycle [S1 S2 S3 S4]))

(def tetrominos {::L L ::J J ::T T ::I I ::S S ::O O})

(defn random-tetrominos! []
  (random-seq! (seq tetromino-keys)))
