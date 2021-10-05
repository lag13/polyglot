(ns clojure-sales-by-match.core-test
  (:require [clojure.test :refer :all]
            [clojure-sales-by-match.core :refer :all]))

(deftest count-pairs-test
  (testing "Count pairs test"
    (are [expected input] (= expected (count-pairs input))
      0 nil
      0 []
      0 [1 2 3 4 5]
      1 [1 1]
      4 [1 1 2 2 3 4 5 5 5 5])))
