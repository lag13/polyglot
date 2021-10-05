(ns clojure-repeated-string.core
  (:gen-class))

(defn count-letters [s l]
  (reduce (fn [acc x] (if (= l x) (inc acc) acc))
          0
          s))

(defn count-letter-a [s]
  (count-letters s \a))

(defn count-letter-as-fun
  "Counts the number of letter a's by making an infinite list of the
  string and then just counting."
  [s n]
  (->> s
       (repeat)
       (apply concat)
       (take n)
       (count-letter-a)))

(defn count-letter-as-in-repeated-str [s n]
  (if (= s "") 0
      (+ (* (quot n (count s)) (count-letter-a s))
         (count-letter-a (take (rem n (count s)) s)))))

(defn -main
  [& args]
  (let [s (read-line)
        n (Long/parseLong (read-line))]
    (println (count-letter-as-in-repeated-str s n))))
