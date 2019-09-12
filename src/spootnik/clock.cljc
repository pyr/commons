(ns spootnik.clock
  "A clock that can be easily replaced in a component system"
  (:require [clojure.spec.alpha :as s]))

(defprotocol Clock
  (epoch [this] "Yield epoch for this clock"))

(def wall-clock
  "System clock, gives wall clock time"
  (reify Clock
    (epoch [this]
      #?(:clj  (System/currentTimeMillis)
         :cljs (.getTime (js/Date.))))))

(defn clock?
  "Predicate to test against a valid clock"
  [x]
  (satisfies? Clock x))

(s/def ::clock clock?)
