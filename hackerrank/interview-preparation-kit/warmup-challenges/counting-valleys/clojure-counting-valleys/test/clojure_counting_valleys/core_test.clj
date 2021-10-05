(ns clojure-counting-valleys.core-test
  (:require [clojure.test :refer :all]
            [clojure-counting-valleys.core :refer :all]))

(deftest count-valleys-test
  (testing "Count valleys"
    (are [expected input] (= expected (count-valleys input))
      0 ""
      1 "UDDDUDUU"
      2 "DUDDDUUU"
      3 "UUDDDUDDDUUUDDUUUU")))
