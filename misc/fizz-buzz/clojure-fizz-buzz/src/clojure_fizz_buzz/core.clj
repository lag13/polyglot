(ns clojure-fizz-buzz.core
  (:gen-class))

(defn generate-fizzbuzz [num]
  (map (fn [n]
         (cond
           (and (zero? (mod n 3))
                (zero? (mod n 5)))
           "FizzBuzz"

           (zero? (mod n 3))
           "Fizz"

           (zero? (mod n 5))
           "Buzz"

           :else
           (str n)))
       (range (inc num))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (doseq [fizzbuzznum (generate-fizzbuzz 100)]
    (println fizzbuzznum)))
