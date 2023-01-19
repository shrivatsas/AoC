(ns a
  (:require [clojure.data.json :as json]))

(def input (slurp "../12.input"))

(defn sum-of-numbers-json [json]
  (cond
    (number? json) json
    (vector? json) (apply + (map sum-of-numbers-json json))
    (coll? json) (apply + (map sum-of-numbers-json json))
    :else 0))

(defn sum-of-numbers [json-string]
  (let [json (json/read-str json-string)]
    (sum-of-numbers-json json)))

(print (sum-of-numbers input))