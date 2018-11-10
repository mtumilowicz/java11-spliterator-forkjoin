# java11-spliterator-forkjoin
Fork-join using spliterator and parallel streams.

_Reference_: https://java-8-tips.readthedocs.io/en/stable/parallelization.html  

For more info about spliterators please refer my other github project:
* https://github.com/mtumilowicz/java11-spliterator

Note that this project is the exact copy of my other github project:
https://github.com/mtumilowicz/fork-join-find-minimum
but using spliterator + parallel streams instead of fork-join API.

# project description
Find minimum in the stream of ints in a parallel way with full
control over splitting.

1. We have `int[] arr`, so we will be using `Spliterator.OfInt`
(it removes overhead of autoboxing).
1. We implement `Spliterator.OfInt` in `FindMinimumSpliterator`
in the same way like fork-join, but note that:
    * splitting tear away part of `this` (so `this` spliterator
    has to be mutable)
    * `trySplit()` **returns** 
        * a Spliterator covering some portion of the elements
        * or null if this spliterator cannot be split
1. Used characteristics:
    * `IMMUTABLE` - Characteristic value signifying that the element 
    source cannot be structurally modified; that is, elements cannot 
    be added, replaced, or removed, so such changes cannot occur 
    during traversal.
    * `NONNULL` - Characteristic value signifying that the source 
    guarantees that encountered elements will not be null.
    * `ORDERED` - Characteristic value signifying that an encounter 
    order is defined for elements.
    * `SIZED` - Characteristic value signifying that the value 
    returned from `estimateSize()` prior to traversal or splitting 
    represents a finite size that, in the absence of structural 
    source modification, represents an exact count of the number 
    of elements that would be encountered by a complete traversal.
    * `SUBSIZED` - Characteristic value signifying that all 
    Spliterators resulting from trySplit() will be both `SIZED` and 
    `SUBSIZED` (This means that all child Spliterators, whether direct 
    or indirect, will `SIZED`).
1. Constructing parallel `IntStream` from `Spliterator.OfInt`:
    ```
    IntStream intStream = StreamSupport.intStream(new FindMinimumSpliterator(array, 0, array.length - 1), true);
    ```
1. On the `IntStream` we found minimum calling `min()` method
    ```
    OptionalInt minimum = intStream.min();
    ```

# tests
Tested in `FindMinimumTest` over array of `100_000` random elements.