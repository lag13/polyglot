(ns clojure-bubble-sort.core-test
  (:require [clojure.test :refer :all]
            [clojure-bubble-sort.core :refer :all]))

(deftest bubble-sort-stats-test
  (testing "bubble sort"
    (are [expected input] (= expected (bubble-sort-stats2 input))
      {:num-swaps 0
       :first-elem 1
       :last-elem 3}
      [1 2 3]

      {:num-swaps 3
       :first-elem 7
       :last-elem 9}
      [9 8 7])))
