(ns clojure-sales-by-match.core
  (:gen-class))

(defn count-pairs [socks]
  (reduce (fn [acc [_ v]] (+ acc (quot v 2)))
          0
          (frequencies socks)))

(defn str-to-nums [s]
  (->> (clojure.string/split s #" ")
       (filter #(not= "" %))
       ;; Java methods do not impement IFn so can't be passed around
       ;; like a function:
       ;; https://stackoverflow.com/questions/6196719/weird-error-when-trying-to-map-parseint-in-clojure/6197026
       (map #(Integer/parseInt %))))

;; (read-line)
;; (println (count-pairs (str-to-nums (read-line))))
