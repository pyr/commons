(ns spootnik.transducers
  "A couple of useful transducers")

(defn reductions-with
  "Returns a lazy seq of the intermediate values of the reduction (as
  per reduce) of coll by f, starting with init.  Returns a stateful
  transducer when no collection is provided."
  ([f init]
   (fn [rf]
     (let [state (volatile! init)]
       (fn
         ([] (rf))
         ([result] (rf result))
         ([result input]
          (if (reduced? @state)
            @state
            (rf result (unreduced (vswap! state f input)))))))))
  ([f init coll]
   (reductions f init coll)))

(defn distinct-by
  "Returns a lazy sequence of distinct elements of coll. Element
  identity is determined by `ident-fn`
  Returns a stateful transducer when no collection is provided."
  ([ident-fn]
   (fn [rf]
     (let [seen (volatile! #{})]
       (fn
         ([] (rf))
         ([result] (rf result))
         ([result input]
          (let [ident (ident-fn input)]
            (if (contains? @seen ident)
              result
              (do (vswap! seen conj ident)
                  (rf result input)))))))))
  ([ident-fn coll]
   (let [step (fn step [xs seen]
                (lazy-seq
                 ((fn [[f :as xs] seen]
                    (when-let [s (seq xs)]
                      (let [ident (ident-fn f)]
                        (if (contains? seen ident)
                          (recur (rest s) seen)
                          (cons f (step (rest s) (conj seen ident)))))))
                  xs seen)))]
     (step coll #{}))))
