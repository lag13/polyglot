(ns clojure-two-strings.core
  (:gen-class))

(defn share-common-substr [s1 s2]
  (not-empty
   (clojure.set/intersection (set s1)
                             (set s2))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [num-test-cases (Integer/parseInt (read-line))]
    (dotimes [_ num-test-cases]
      (if (share-common-substr (read-line) (read-line))
        (println "YES")
        (println "NO")))))
