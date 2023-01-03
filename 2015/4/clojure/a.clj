(ns a)

(import 'java.security.MessageDigest)

(defn md5 [^String secret ^Integer zeros] 
  (let [md (MessageDigest/getInstance "MD5")
        zstr (apply str (repeat zeros "0"))]
    (loop [i 1]
      (let [s (str secret i)
            raw (BigInteger. 1 (.digest md (.getBytes s)))]
        (if (= zstr (.substring (format "%032x" raw) 0 zeros))
          i
          (recur (inc i)))))))

(print (md5 "iwrupvqb" 5))