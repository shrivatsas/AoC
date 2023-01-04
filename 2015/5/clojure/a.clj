(ns a
  (:require [clojure.java.io :as io]))

(defn read-file [filename]
  (with-open [rdr (io/reader filename)]
    (doall (line-seq rdr))))

(defn twice-in-a-row [^String s]
  (some #(= (first %) (second %)) (partition 2 1 s)))

(defn vowels-count [s]
    (count (re-seq #"[aeiouAEIOU]" s)))

(defn has-bad-strings [s]
    (re-find #"ab|cd|pq|xy" s))

(defn evaluate [filename]
    (->> (read-file filename)
         (filter #(>= (vowels-count %) 3))
         (filter twice-in-a-row)
         (filter #(not (has-bad-strings %)))
         (count)
         (println)))

(evaluate "../5.input")