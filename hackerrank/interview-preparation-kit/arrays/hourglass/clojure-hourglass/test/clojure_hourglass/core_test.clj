(ns clojure-hourglass.core-test
  (:require [clojure.test :refer :all]
            [clojure-hourglass.core :refer :all]))

(deftest max-hourglass-sum-test
  (testing "max hourglass sum"
    (are [expected arr] (= expected (max-hourglass-sum arr))
      nil nil
      nil []
      19 [[1 1 1 0 0 0]
          [0 1 0 0 0 0]
          [1 1 1 0 0 0]
          [0 0 2 4 4 0]
          [0 0 0 2 0 0]
          [0 0 1 2 4 0]]
      13 [[1 1 1 0 0 0]
          [0 1 0 0 0 0]
          [1 1 1 0 0 0]
          [0 9 2 -4 -4 0]
          [0 0 0 -2 0 0]
          [0 0 -1 -2 -4 0]])))
