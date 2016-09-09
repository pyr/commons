(ns spootnik.transducers-test
  (:require [clojure.test         :refer :all]
            [spootnik.transducers :refer :all]))

(deftest test-reductions-with
  (is (= (reductions-with + 10 [1 2 3 4 5])
         [10 11 13 16 20 25]))

  (is (= (sequence (reductions-with + 10) [1 2 3 4 5])
         [11 13 16 20 25]))

  (is (= [0 2 6 12 12]
         (reductions-with (fn [acc x]
                            (if (= x :stop)
                              (reduced acc)
                              (+ acc x)))
                          0
                          [2 4 6 :stop 8 10])))

  (is (= [2 6 12 12]
         (sequence
          (reductions-with (fn [acc x]
                            (if (= x :stop)
                              (reduced acc)
                              (+ acc x)))
                           0)
          [2 4 6 :stop 8 10]))))

(deftest test-distinct-by
  (is (= (sequence (distinct-by :time [{:time 0 :event :a} {:time 0 :event :b} {:time 1 :event :c}]))
         [{:time 0 :event :a} {:time 1 :event :c}])))
