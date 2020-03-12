(ns red-tetris.core
  (:require [red-tetris.tetroes :as tetroes]
            [cljs.core.async :refer [go go-loop chan take! onto-chan <! timeout]]))

(let [c (tetroes/random-tetroes)
        tetroes-chan (chan)]
    (onto-chan tetroes-chan c)
    (go
      (dotimes [i 10]
        (<! (timeout 1000))
        (take! tetroes-chan print))))
