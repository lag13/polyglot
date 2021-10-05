(ns clojure-bubble-sort.core
  (:gen-class))

(defn bubble-sort-stats [arr]
  (loop [res arr
         i 0
         j 0
         num-swaps 0]
    (cond
      (= i (count res))
      {:num-swaps num-swaps
       :first-elem (first res)
       :last-elem (last res)}

      (= j (dec (count res)))
      (recur res (inc i) 0 num-swaps)

      (> (nth res j) (nth res (inc j)))
      (recur (assoc res
                    j (nth res (inc j))
                    (inc j) (nth res j))
             i
             (inc j)
             (inc num-swaps))

      :else
      (recur res i (inc j) num-swaps))))

(defn bubble-sort-stats2 [arr]
  (loop [arr arr
         num-swaps 0
         [j & js] (mapcat identity (repeat (count arr) (range 0 (dec (count arr)))))]
    (cond
      (nil? j)
      {:num-swaps num-swaps
       :first-elem (first arr)
       :last-elem (last arr)}

      (> (nth arr j) (nth arr (inc j)))
      (recur (assoc arr
                    j (nth arr (inc j))
                    (inc j) (nth arr j))
             (inc num-swaps)
             js)

      :else
      (recur arr num-swaps js))))

(defn tokenize [s]
  (remove empty? (clojure.string/split (clojure.string/trim s) #" ")))

(defn -main [& args]
  (read-line)
  (let [{:keys [num-swaps first-elem last-elem]}
        (bubble-sort-stats2 (mapv #(Integer/parseInt %) (tokenize (read-line))))]
    	(println "Array is sorted in" num-swaps "swaps")
	(println "First Element:" first-elem)
	(println "Last Element:" last-elem)))
