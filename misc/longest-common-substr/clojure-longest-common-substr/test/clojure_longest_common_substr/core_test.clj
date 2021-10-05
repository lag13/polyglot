(ns clojure-longest-common-substr.core-test
  (:require [clojure.test :refer :all]
            [clojure-longest-common-substr.core :refer :all]))

(deftest longest-common-substrs-test
  (testing "Finds all longest common substrings in a string"
    (are [expected s1 s2] (= expected (longest-common-substrs s1 s2))
      #{""} "abcd" "efgh"
      #{"hey"} "hey buddy" "hey, you come here often?"
      #{"hey" "you"} "heyyou" "youhey")))

