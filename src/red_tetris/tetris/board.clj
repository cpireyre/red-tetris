(ns red-tetris.tetris.board
  (:require [clojure.core.matrix :as matrix]
            [red-tetris.lib.elongate :refer [zero-elongate elongate]] 
            [red-tetris.tetris.tetrominos :as tetrominos]))

(def dimensions [20 10])
(def board (matrix/zero-array dimensions))

(defn- broadcast-tetromino
  "Coerces the shape of a tetromino matrix to the dimensions
  of the game board.
  Note that the tetromino will sit at the top left of the
  new matrix.
  Probably idempotent."
  [tetromino]
  (let [coerce-slice #(zero-elongate % 10)
        slices (map coerce-slice tetromino)]
    (elongate (vec slices) 20 (matrix/zero-array [10]))))

(defn offset-tetromino
  "Broadcasts a tetromino with specified offset from the top left
  of the grid."
  [tetromino [x y]]
  (-> tetromino
      (broadcast-tetromino) ;; broadcast is idempotent probably so we can be flexible here
      (matrix/shift 0 (- y))
      (matrix/shift 1 (- x))))

(defn tetromino-offset-oob?
  "Returns true if shifting a tetromino by the given offset would
  take it outside of the board."
  [tetromino offset]
  (let [o (offset-tetromino tetromino offset)]
    (not= 4 (matrix/non-zero-count o))))

(defn matrices-collide?
  "Takes two matrices; returns true if adding them would overwrite nonzero values."
  [a b]
  (let [s (matrix/add a b)
        a-nonzeros (matrix/non-zero-count a)
        b-nonzeros (matrix/non-zero-count b)
        s-nonzeros (matrix/non-zero-count s)]
    (not= s-nonzeros (+ a-nonzeros b-nonzeros))))

(comment
  (def L1 (first (:red-tetris.tetris.tetrominos/L tetrominos/matrices)))
  (def L (second (:red-tetris.tetris.tetrominos/L tetrominos/matrices)))
  (tetromino-offset-oob? L [9 1]) ; true
  (tetromino-offset-oob? L [7 1]) ; false
  (tetromino-offset-oob? L [-1 0]) ; true
  (tetromino-offset-oob? L [0 -1])  ; false
  (tetromino-offset-oob? L [0 -2]) ; true
  (matrix/non-zero-count L)
  (offset-tetromino L [7 1])
  (offset-tetromino L [0 -1])
  (offset-tetromino L [0 -2])
  (def a (broadcast-tetromino L))
  (def b board)
  (matrices-collide? board board)
  (matrices-collide? (broadcast-tetromino L) board)
  (matrices-collide? (broadcast-tetromino L) (broadcast-tetromino L))
  (broadcast-tetromino (broadcast-tetromino L))
  (matrix/eseq L)
  (def new-board (matrix/add board (broadcast-tetromino L)))
  (broadcast-tetromino (offset-tetromino L1 [4 1]))
  (offset-tetromino L1 [2 1])
  (offset-tetromino L1 [0 4])
  (matrix/gt new-board 0)
  (matrix/eseq new-board)
  (matrix/esum (offset-tetromino L1 [2 1]))
  (matrix/shift new-board 0 -5)
  (matrix/add new-board (broadcast-tetromino L1))
  (broadcast-tetromino L)
  (map #(zero-elongate % 10) L)
  (zero-elongate [1 23] 10)
  (apply conj [1 23] [143423 234 23 423 42342])
  (matrix/array (first L))
  (matrix/add (first L) (matrix/zero-array [10]))
  (matrix/add board L)
  (matrix/broadcast-like board L))
