(ns red-tetris.core
  (:require [red-tetris.tetroes :as tetroes]
            [cljs.core.async :refer [go-loop chan take! onto-chan <! timeout]]))

(let [c (tetroes/random-tetroes 42)
        tetroes-chan (chan)]
    (onto-chan tetroes-chan c)
    (go-loop [i 0]
             (when (< i 20)
               (take! tetroes-chan print)
               (<! (timeout 1000))
               (recur (inc i)))))
