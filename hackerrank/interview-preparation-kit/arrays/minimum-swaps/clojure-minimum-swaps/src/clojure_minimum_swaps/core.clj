(ns clojure-minimum-swaps.core
  (:gen-class))

(defn swap [arr i j]
  (assoc arr i (get arr j) j (get arr i)))

(defn min-swaps
  ([arr]
   (min-swaps arr 0 0))
  ([arr idx num-swaps]
   (cond
     (= (count arr) idx) num-swaps
     (= idx (dec (get arr idx))) (recur arr (inc idx) num-swaps)
     :else (recur (swap arr idx (.indexOf arr (inc idx)))
                      (inc idx)
                      (inc num-swaps)))))

(defn min-swaps2 [arr]
  (loop [idx 0
         num-swaps 0]
    (cond
      (= idx (count arr))
      num-swaps

      (= (inc idx) (get arr idx))
      (recur (inc idx) num-swaps)

      (> (get arr idx) (inc idx))
      (recur (inc idx) (inc num-swaps))

      :else
      (recur (inc idx) num-swaps))))

(defn str->numv [s]
  (mapv #(Integer/parseInt %) (filter not-empty (clojure.string/split (clojure.string/trim s) #" "))))

(defn -main
  [& args]
  (read-line)
  (println (min-swaps (str->numv (read-line)))))
