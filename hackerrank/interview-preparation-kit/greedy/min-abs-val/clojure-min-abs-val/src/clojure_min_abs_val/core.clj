(ns clojure-min-abs-val.core
  (:gen-class))

(defn min-abs-val [arr]
  (let [arr (vec (sort arr))
        min-abs-val (- (nth arr 1) (nth arr 0))]
    (loop [acc min-abs-val
           i 1]
      (cond
        (zero? acc)
        acc

        (= i (dec (count arr)))
        acc

        :else
        (recur (min acc (- (nth arr (inc i)) (nth arr i)))
               (inc i))))))

(defn str-to-num-arr [s]
  (mapv #(Integer/parseInt %) (remove empty? (clojure.string/split (clojure.string/trim s) #" |\n"))))

(defn -main []
  (read-line)
  (println (-> (read-line)
               (str-to-num-arr)
               (min-abs-val))))
