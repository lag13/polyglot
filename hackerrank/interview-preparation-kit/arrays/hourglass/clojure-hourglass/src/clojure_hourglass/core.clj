(ns clojure-hourglass.core
  (:gen-class))

(defn sum-shape [arr offsets origin]
  (reduce (fn [acc offset]
            (+ acc (get-in arr (map + offset origin))))
          0
          offsets))

(defn shape
  "Function name inspired by:
  https://numpy.org/doc/stable/reference/generated/numpy.shape.html"
  [arr]
  (loop [dimension-lens []
         arr arr]
    (if (coll? arr)
      (recur (conj dimension-lens (count arr))
             (first arr))
      dimension-lens)))

(defn cartesian-product
  ([] [])
  ([xs] xs)
  ([xs ys & colls]
   (let [seed (for [x xs
                    y ys]
                [x y])]
     (reduce (fn [product zs]
               (mapcat (fn [tuple] (map (fn [z] (conj tuple z)) zs)) product))
             seed
             colls))))

;; Nothing like a little overengineering in the morning. This must
;; mean I'm a very senior developer lol.
(defn max-hourglass-sum
  "The largest hourglass sum in a 2d array"
  [arr]
  (let [hourglass-shape [[0 0] [0 1] [0 2]
                         [1 1]
                         [2 0] [2 1] [2 2]]
        valid-origin-coords (apply cartesian-product
                                   (map range (map -
                                                   (shape arr)
                                                   (apply map max hourglass-shape))))
        hourglass-sums (map (partial sum-shape arr hourglass-shape) valid-origin-coords)]
    (if (empty? hourglass-sums)
      nil
      (apply max hourglass-sums))))

(defn max-hourglass-sum2
  "A much simpler much more brittle version of the hourglass sum
  function. It gets the job done! Probably should just stick with this
  solution."
  [arr]
  (let [hourglass-sums (map (fn [origin]
                              (+ (get-in arr (map + origin [0 0]))
                                 (get-in arr (map + origin [0 1]))
                                 (get-in arr (map + origin [0 2]))
                                 (get-in arr (map + origin [1 1]))
                                 (get-in arr (map + origin [2 0]))
                                 (get-in arr (map + origin [2 1]))
                                 (get-in arr (map + origin [2 2]))))
                            (for [x (range 4)
                                  y (range 4)]
                              [x y]))]
    (apply max hourglass-sums)))

(defn parse-nums [s]
  (mapv #(Integer/parseInt %)
        (filter not-empty (clojure.string/split (clojure.string/trim s) #" "))))

(defn parse-2d-arr [ss]
  (mapv parse-nums ss))

(defn read-lines [n]
  (loop [input-lines []
         n n]
    (if (zero? n)
      input-lines
      (recur (conj input-lines (read-line))
             (dec n)))))

(defn -main
  [& args]
  (println
   (-> (read-lines 6)
       (parse-2d-arr)
       (max-hourglass-sum))))
