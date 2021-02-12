(ns red-tetris.core
  (:require [red-tetris.tetroes :as tetroes]
            [clojure.core.async :refer [go chan take! onto-chan <!! timeout]]))

(defn -main []
  (let [c (tetroes/random-tetroes!)
          tetroes-chan (chan)]
      (onto-chan tetroes-chan c)
      (dotimes [i 10]
        (<!! (timeout 1000))
        (take! tetroes-chan println))))
