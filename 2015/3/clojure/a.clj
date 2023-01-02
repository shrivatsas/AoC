(ns a)

(def walk (slurp "../3.input"))

(defn stringify [pos]
  (str (first pos) (second pos)))

(defn move [pos c]
  (case c
    \> [(inc (first pos)) (second pos)]
    \^ [(first pos) (inc (second pos))]
    \< [(dec (first pos)) (second pos)]
    \v [(first pos) (dec (second pos))]))

(defn travel [pos walk visited]
  (if (empty? walk)
    visited
    (let [pos (move pos (first walk))]
      (recur pos (rest walk) (conj visited (stringify pos))))))

(def visited (travel [0 0] walk #{(stringify [0 0])}))

(println (count visited))