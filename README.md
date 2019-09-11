commons: it's dangerous to go alone, take this.
===============================================

![zelda](https://upload.wikimedia.org/wikipedia/en/b/b2/It's_dangerous_to_go_alone!_Take_this..png)

A collection of small, dependency-free functions that you might find useful.

[![Build Status](https://secure.travis-ci.org/pyr/commons.png)](http://travis-ci.org/pyr/commons)


## Installing

```clojure
  [spootnik/commons "0.3.1"]
```

## Transducers (namespace: `spootnik.transducers`)

### `reductions-with`

See http://dev.clojure.org/jira/browse/CLJ-1903 for rationale and details. 

```clojure
(is (= (sequence (reductions-with + 10 [1 2 3 4 5]) [11 13 16 20 25])))
```

## `distinct-by`

A version of distinct which determines identity through an identity function.

```clojure
(is (= (sequence (distinct-by :time [{:time 0 :event :a} {:time 0 :event :b} {:time 1 :event :c}]))
       [{:time 0 :event :a} {:time 1 :event :c}]))
```

## Clock (namespace: `spootnik.clock`)

Useful in component systems where you might want to replace a wall
clock by a controllable one. Wall clock implementation provided.

Consists of the `spootnik.clock.Clock` protocol and a wall clock
implementation.

```
(epoch wall-clock) ;; => 1568196351534
```

## Changelog

## 0.3.1

- Introduce `spootnik.clock`


## License

Copyright © 2019 Pierre-Yves Ritschard. MIT/ISC License.

