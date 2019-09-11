(ns spootnik.clock
  "A clock that can be easily replaced in a component system")

(defprotocol Clock
  (epoch [this] "Yield epoch for this clock"))

(def wall-clock
  "System clock, gives wall clock time"
  (reify Clock
    (epoch [this]
      #?(:clj  (System/currentTimeMillis)
         :cljs (.getTime (js/Date.))))))
