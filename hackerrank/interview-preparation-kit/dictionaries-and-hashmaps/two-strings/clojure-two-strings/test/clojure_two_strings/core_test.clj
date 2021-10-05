(ns clojure-two-strings.core-test
  (:require [clojure.test :refer :all]
            [clojure-two-strings.core :refer :all]))

(deftest share-common-substr-test
  (testing "Two strings share common substring"
    (are [expected s1 s2] (= expected (if (share-common-substr s1 s2) true false))
      false "abc" "def"
      true "hello" "there")))
