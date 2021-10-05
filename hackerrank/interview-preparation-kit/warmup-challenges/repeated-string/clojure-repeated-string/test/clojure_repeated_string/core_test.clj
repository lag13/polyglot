(ns clojure-repeated-string.core-test
  (:require [clojure.test :refer :all]
            [clojure-repeated-string.core :refer :all]))

(deftest count-letters-as-test
  (testing "counting letter a's"
    (are [expected s n] (= expected (count-letter-as-in-repeated-str s n))
      0 "" 100
      0 "b" 100
      1 "a" 1
      4 "abcac" 10
      7 "aba" 10
      1000000000000 "a" 1000000000000)))
