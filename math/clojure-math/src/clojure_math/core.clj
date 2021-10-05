(ns clojure-math.core
  (:gen-class))

(defn cartesian-product
  ([] [])
  ([xs] xs)
  ([xs ys & more]
   (reduce (fn [product zs]
             (mapcat (fn [tuple] (map (fn [z] (conj tuple z)) zs)) product))
           (for [x xs
                 y ys]
             [x y])
           more)))

(defn cartesian-product2
  ([] [])
  ([xs] xs)
  ([xs ys] (mapcat (fn [x]
                     (map (fn [y] (list x y)) ys))
                   xs))
  ([xs ys & more]
   (mapcat (fn [x]
             (map (fn [tuple] (cons x tuple))
                  (apply cartesian-product2 (cons ys more))))
           xs)))


(defn power-set [col]
  (if (empty? col)
    #{#{}}
    (let [s (power-set (rest col))]
      (into (map #(conj % (first col)) s)
            s))))

(defn power-set2
  ([col] (power-set2 #{#{}} col))
  ([acc [x & xs]]
   (if (nil? x)
     acc
     (recur (into acc
                  (map #(conj % x) acc))
            xs))))

(comment (defn combinations [col k]
           (cond
             (zero? k)
             [[]]

             (empty? col)
             []

             :else
             (conj (combinations (rest col) k) [(first col)])))

         (defn combinations2 [col k]
           (filter #(= k (count %)) (power-set2 col))))

(comment  (cond
            (zero? k)
            [[]]

            (= k 1)
            (map vector col)

            :else
            (map #(cons (first col) %) (combinations (rest col) (dec k)))))

