(ns red-tetris.lib.elongate)

;; I found out there is a matrix/reshape function, but
;; it doesn't seem to do exactly what I want, so this is fine.

(defn elongate
  "Takes a collection coll and length l and repeatedly conjs a filler
  value until there are l elements in the collection. Returns the
  original collection as is if the specified length is shorter."
  [coll length filler]
  (if (> (count coll) length)
    coll
    (let [new-length (- length (count coll))]
      (apply conj coll (take new-length (repeat filler))))))

(defn zero-elongate
  "See elongate. Fills new spaces with 0s."
  [coll length]
  (elongate coll length 0))
