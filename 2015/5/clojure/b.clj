(ns b
  (:require [clojure.java.io :as io]))

(defn read-file [filename]
  (with-open [rdr (io/reader filename)]
    (doall (line-seq rdr))))

(defn interposed-repeat [^String s]
  (some #(= (first %) (last %)) (partition 3 1 s)))

(defn has-non-overlapping-pairs [s]
  (seq (re-seq #"(..).*\1" s)))

(defn evaluate [filename]
    (->> (read-file filename)
         (filter has-non-overlapping-pairs)
         (filter interposed-repeat)
         (count)
         (println)))

(evaluate "../5.input")