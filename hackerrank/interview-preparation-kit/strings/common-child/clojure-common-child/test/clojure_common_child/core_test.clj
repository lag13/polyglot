(ns clojure-common-child.core-test
  (:require [clojure.test :refer :all]
            [clojure-common-child.core :refer :all]
            [criterium.core]))

(deftest common-child-test
  (testing "common child"
    (are [expected s1 s2] (= expected (common-child6 s1 s2))
      0 "" ""
      0 "ABC" "DEF"
      2 "HARRY" "SALLY"
      3 "ABCD" "ABDC"
      4 "SHINCHAN" "NOHARAAAN"
      27 "ELGGYJWKTDHLXJRBJLRYEJWVSUFZKYHOIKBGTVUTTOCGMLEXWDSXEBKRZTQUVCJNGKKRMUUBACVOEQKBFFYBUQEMYNENKYYGUZSP" "FRVIFOVJYQLVZMFBNRUTIYFBMFFFRZVBYINXLDDSVMPWSQGJZYTKMZIPEGMVOUQBKYEWEYVOLSHCMHPAZYTENRNONTJWDANAMFRX")))

(defn benchmarks
  "Compares the different implementations of the common-child algorithm
  except for the naive recursive solution which we know is slow, no
  need to test it. I was reading
  https://tech.redplanetlabs.com/2020/09/02/clojure-faster/ for how to
  do benchmarks and a bit about how to optimize clojure code."
  []
  (let [s1 "ELGGYJWKTDHLXJRBJLRYEJWVSUFZKYHOIKBGTVUTTOCGMLEXWDSXEBKRZTQUVCJNGKKRMUUBACVOEQKBFFYBUQEMYNENKYYGUZSP"
        s2 "FRVIFOVJYQLVZMFBNRUTIYFBMFFFRZVBYINXLDDSVMPWSQGJZYTKMZIPEGMVOUQBKYEWEYVOLSHCMHPAZYTENRNONTJWDANAMFRX"
        fns [#'common-child2 #'common-child3 #'common-child4 #'common-child5 #'common-child6]]
    (doseq [f fns]
      (println "####################3")
      (println f)
      (println "####################3")
      (criterium.core/report-result
       (criterium.core/quick-benchmark (f s1 s2) {})))))
