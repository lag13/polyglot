(ns clojure-common-child.core
  (:gen-class))

(defn common-child
  "A naive implementation of this function which leans heavy on
  recursion. It's very slow but I like the implementation! Even if I
  use \"memoize\" on it, the stack overflows for large input it seems
  so it's definitely not so great."
  [s1 s2]
  (if (or (empty? s1) (empty? s2))
    0
    (if (= (first s1)
           (first s2))
      (inc (common-child (rest s1) (rest s2)))
      (max (common-child s1 (rest s2))
           (common-child (rest s1) s2)))))

(defn common-child2
  "Solving this problem with dynamic programming. As expected,
  it dramatically improves the runtime but it still wasn't fast enough
  for hackerrank."
  [s1 s2]
  (loop [curr (vec (repeat (inc (count s2)) 0))
         prev curr
         i 0
         j 0]
    (cond
      (= i (count s1))
      (peek prev)

      (= j (count s2))
      (recur prev curr (inc i) 0)

      (= (nth s1 i) (nth s2 j))
      (recur (assoc curr (inc j) (inc (nth prev j)))
             prev
             i
             (inc j))

      :else
      (recur (assoc curr (inc j) (max (nth curr j) (nth prev (inc j))))
             prev
             i
             (inc j)))))

(defn common-child3
  "Even just adding type hints to the previous iteration's
  implementation plus changing the function calls surrounding those
  type hinted variables helps. This was alllmooost good enough for
  hackerrank, passing all tests except for one."
  [^String s1 ^String s2]
  (loop [curr (vec (repeat (inc (count s2)) 0))
         prev curr
         i 0
         j 0]
    (cond
      (= i (.length s1))
      (peek prev)

      (= j (.length s2))
      (recur prev curr (inc i) 0)

      (= (.charAt s1 i) (.charAt s2 j))
      (recur (assoc curr (inc j) (inc (nth prev j)))
             prev
             i
             (inc j))

      :else
      (recur (assoc curr (inc j) (max (nth curr j) (nth prev (inc j))))
             prev
             i
             (inc j)))))

(defn common-child4
  "Going full mutation here for maximum speed. I kind of wish the aset
  function returned the array so I don't have those \"do\" calls.
  Maybe it's fine though, it probably helps indicate that there's some
  mutation going on which could be nice."
  [^String s1 ^String s2]
  (loop [curr (long-array (repeat (inc (count s2)) 0))
         prev (aclone curr)
         i 0
         j 0]
    (cond
      (= i (.length s1))
      (aget prev (dec (count prev)))

      (= j (.length s2))
      (recur prev curr (inc i) 0)

      (= (.charAt s1 i) (.charAt s2 j))
      (do (aset curr (inc j) (inc (aget prev j)))
          (recur curr
                 prev
                 i
                 (inc j)))

      :else
      (do (aset curr (inc j) (max (aget curr j) (aget prev (inc j))))
          (recur curr
                 prev
                 i
                 (inc j))))))

(defn common-child5
  "Solving this problem more efficiently with dynamic programming, type
  hints, AND transients (which I recently learned about in \"the joy
  of clojure\" and they feel like they're probably a better first step
  towards optimization over using things like \"aset\")."
  [^String s1 ^String s2]
  (loop [curr (transient (vec (repeat (inc (count s2)) 0)))
         prev (transient (vec (repeat (inc (count s2)) 0)))
         i 0
         j 0]
    (cond
      (= i (.length s1))
      (nth prev (dec (count prev)))

      (= j (.length s2))
      (recur prev curr (inc i) 0)

      (= (.charAt s1 i) (.charAt s2 j))
      (recur (assoc! curr (inc j) (inc (nth prev j)))
             prev
             i
             (inc j))

      :else
      (recur (assoc! curr (inc j) (max (nth curr j) (nth prev (inc j))))
             prev
             i
             (inc j)))))

(defn get-2d [coll i j]
  (get coll (+ (* i j) j)))

(defn assoc-2d! [coll i j v]
  (assoc! coll (+ (* i j) j) v))

(defn common-child6
  "Solving this problem more efficiently with dynamic programming, type
  hints, transients, AND I changed how the looping gets done because
  doing \"nested\" loops with a single \"loop\" construct is kind of
  annoying. Doing a true 2d transient vector was annoying (there is no
  assoc-in!) so I opted to just make a 1d vector and operate on it in
  a 2d fashion. Very C of me. "
  [^String s1 ^String s2]
  (let [memo (transient (vec (repeat (* (inc (.length s1))
                                        (inc (.length s2)))
                                     0)))]
    (doseq [[i j] (for [x (range (.length s1))
                        y (range (.length s2))]
                    [x y])]
      (if (= (.charAt s1 i) (.charAt s2 j))
        (assoc-2d! memo (inc i) (inc j) (inc (get-2d memo i j)))
        (assoc-2d! memo (inc i) (inc j) (max (get-2d memo (inc i) j)
                                             (get-2d memo i (inc j))))))
    (get-2d memo (.length s1) (.length s2))))

(defn -main
  "Prints the largest common child"
  [& args]
  (println (common-child6 (read-line)
                          (read-line))))
