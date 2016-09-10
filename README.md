commons: it's dangerous to go alone, take this.
===============================================

![zelda](https://upload.wikimedia.org/wikipedia/en/b/b2/It's_dangerous_to_go_alone!_Take_this..png)

A collection of small, dependency-free functions that you might find useful.

[![Build Status](https://secure.travis-ci.org/pyr/commons.png)](http://travis-ci.org/pyr/commons)


## Installing

```clojure
  [spootnik/commons "0.3.0"]
```

## Transducers

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

## License

Copyright © 2016 Pierre-Yves Ritschard. MIT/ISC License.

