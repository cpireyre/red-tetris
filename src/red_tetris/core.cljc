(ns red-tetris.core
  (:require [clojure.core.async :refer [chan take! onto-chan <!! timeout]]
            [red-tetris.tetris.tetrominos :refer [random-tetrominos!]]))

;; The whole game has to be inside a single go block lol help me Lord God

(def input-keys #{::flip ::drop ::left ::right})

(defn -main [& args]
  (let [c (random-tetrominos!)
          tetrominos-chan (chan)]
      (onto-chan tetrominos-chan c)
      (dotimes [i 10]
        (<!! (timeout 1000))
        (take! tetrominos-chan println))))
