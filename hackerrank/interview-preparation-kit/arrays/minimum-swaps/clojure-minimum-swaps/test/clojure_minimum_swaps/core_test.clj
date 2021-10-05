(ns clojure-minimum-swaps.core-test
  (:require [clojure.test :refer :all]
            [clojure-minimum-swaps.core :refer :all]))

(deftest minimum-swaps-test
  (testing "reports the minimum number of swaps to make a sorted array"
    (are [expected input] (= expected (min-swaps input))
      0 []
      0 [1]
      1 [2 1]
      3 [1 3 5 2 4 6 7]
      5 [7 1 3 2 4 5 6]
      1 [4 2 3 1])))
