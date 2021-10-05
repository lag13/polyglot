(ns clojure-jumping-on-clouds.core-test
  (:require [clojure.test :refer :all]
            [clojure-jumping-on-clouds.core :refer :all]))

(deftest jumping-on-clouds-test
  (testing "jumping on clouds returns fewest jumps"
    (are [expected input] (= expected (jumping-on-clouds input))
      0 nil
      0 []
      0 [0]
      1 [0 1 0]
      2 [0 1 0 0]
      3 [0 1 0 0 0 1 0]
      4 [0 0 1 0 0 1 0])))
