(ns clojure-counting-valleys.core
  (:gen-class))

(defn count-valleys
  ([s]
   (count-valleys s 0 0))
  ([[x & xs] elevation acc]
   (cond
     (nil? x) acc
     (= x \D) (recur xs (dec elevation) acc)
     (= x \U)
     (let [new-elevation (inc elevation)]
       (if (zero? new-elevation)
         (recur xs new-elevation (inc acc))
         (recur xs new-elevation acc)))
     :else (throw (Exception. (str "invalid movement " x " passed in"))))))

(defn -main
  [& args]
  (read-line)
  (println (count-valleys (read-line))))

