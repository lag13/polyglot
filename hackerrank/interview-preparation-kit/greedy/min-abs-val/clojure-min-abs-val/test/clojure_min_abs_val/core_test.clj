(ns clojure-min-abs-val.core-test
  (:require [clojure.test :refer :all]
            [clojure-min-abs-val.core :refer :all]))

(deftest min-abs-val-test
  (testing "minimum absolute value"
    (are [expected arr] (= expected (min-abs-val arr))
      0 [3 2 1 1]
      5 [0 10 5]
      3 [-1 -5 -8]
      10 (str-to-num-arr (slurp "test-case.txt")))))
