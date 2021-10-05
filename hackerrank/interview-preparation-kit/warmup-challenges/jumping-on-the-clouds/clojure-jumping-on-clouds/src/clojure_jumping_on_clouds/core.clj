(ns clojure-jumping-on-clouds.core
  (:gen-class))

(defn jumping-on-clouds-path [clouds]
  (loop [[c1 c2 & cs] (rest clouds)
         idx 0
         path [0]]
    (cond
      (nil? c1) path
      (= c2 0) (let [new-idx (+ 2 idx)]
                 (recur cs new-idx (conj path new-idx)))
      :else (let [new-idx (+ 1 idx)]
              (recur (cons c2 cs) new-idx (conj path new-idx))))))

(defn jumping-on-clouds [clouds]
  (loop [[c1 c2 & cs] (rest clouds)
         acc 0]
    (cond
      (nil? c1) acc
      (= c2 0) (recur cs (inc acc))
      :else (recur (cons c2 cs) (inc acc)))))

(defn parse-ints [s]
  (map #(Integer/parseInt %) (clojure.string/split s #" ")))

(defn -main
  [& args]
  (read-line)
  (println (jumping-on-clouds (parse-ints (read-line)))))
