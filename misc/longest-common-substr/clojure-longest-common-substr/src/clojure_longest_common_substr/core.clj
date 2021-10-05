(ns clojure-longest-common-substr.core
  (:gen-class))

(defn all-subs [s]
  (loop [acc #{""}
         i 0
         j (count s)]
    (if (= i (count s))
      acc
      (if (= i j)
        (recur acc (inc i) (count s))
        (recur (conj acc (subs s i j)) i (dec j))))))

(defn longest-common-substrs [xs ys]
  (let [subs-in-common (clojure.set/intersection (all-subs xs)
                                                 (all-subs ys))
        one-longest-common-subs (first (reverse (sort-by count subs-in-common)))]
    (set (filter #(= (count one-longest-common-subs) (count %)) subs-in-common))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
