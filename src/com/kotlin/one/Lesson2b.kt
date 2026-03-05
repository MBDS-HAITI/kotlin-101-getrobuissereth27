package com.android.com.kotlin.one


// ============================================================
//  Lesson 2 – Kotlin List Processing
//  All exercises in one file, with a runTest helper.
// ============================================================

// ---------------------------------------------------------------
// Exercise 0 – Test helper
// ---------------------------------------------------------------

/**
 * Runs a named test, prints the result, and checks it against
 * an expected value (if provided).
 *
 * @param name     label shown in the console
 * @param expected optional expected value; prints PASS / FAIL
 * @param block    lambda that returns the actual result
 */
fun <T> runTest(name: String, expected: T? = null, block: () -> T) {
    val result = block()
    val status = when {
        expected == null        -> ""
        result == expected      -> " PASS"
        else                    -> " FAIL (expected $expected)"
    }
    println("[$name]$status → $result")
}

// ---------------------------------------------------------------
// Exercise 1 – Immutable List
// ---------------------------------------------------------------

/**
 * Creates and returns an immutable list of 5 integers.
 */
fun ex1CreateImmutableList(): List<Int> {
    return listOf(10, 20, 30, 40, 50)
}

// ---------------------------------------------------------------
// Exercise 2 – Mutable List
// ---------------------------------------------------------------

/**
 * Creates a mutable list of 3 strings, adds one more element, and returns it.
 */
fun ex2CreateMutableList(): MutableList<String> {
    val list = mutableListOf("Kotlin", "is", "awesome")
    list.add("!")          // adding the 4th element
    return list
}

// ---------------------------------------------------------------
// Exercise 3 – Filter Even Numbers
// ---------------------------------------------------------------

/**
 * Returns only the even numbers from 1 to 10.
 * 'filter' keeps elements for which the predicate returns true.
 */
fun ex3FilterEvenNumbers(): List<Int> {
    return (1..10).filter { it % 2 == 0 }
}

// ---------------------------------------------------------------
// Exercise 4 – Filter and Map Ages
// ---------------------------------------------------------------

/**
 * Given a list of ages:
 *  1. Keeps only those >= 18
 *  2. Transforms each into a string like "Adult: 25"
 *
 * 'filter' then 'map' are chained – each returns a new list.
 */
fun ex4FilterAndMapAges(): List<String> {
    val ages = listOf(14, 25, 17, 32, 18, 9, 45)
    return ages
        .filter { it >= 18 }
        .map    { "Adult: $it" }
}

// ---------------------------------------------------------------
// Exercise 5 – Flatten Nested Lists
// ---------------------------------------------------------------

/**
 * Creates a nested list and flattens it into a single list.
 * 'flatten' merges a List<List<T>> into a List<T>.
 */
fun ex5FlattenList(): List<Int> {
    val nested = listOf(listOf(1, 2), listOf(3, 4), listOf(5))
    return nested.flatten()
}

// ---------------------------------------------------------------
// Exercise 6 – FlatMap Words
// ---------------------------------------------------------------

/**
 * Splits each phrase into words and returns all words in one flat list.
 * 'flatMap' applies a transformation that returns a list per element,
 * then merges all those sub-lists into one.
 */
fun ex6FlatMapWords(): List<String> {
    val phrases = listOf("Kotlin is fun", "I love lists")
    return phrases.flatMap { it.split(" ") }
}

// ---------------------------------------------------------------
// Exercise 7 – Eager Processing
// ---------------------------------------------------------------

/**
 * Processes 1..1_000_000 eagerly (regular list operations).
 *
 * IMPORTANT: each intermediate operation (filter, map) creates a full
 * new list in memory before the next step runs.
 * Even though we only need 5 results, ALL matching elements are
 * processed first.
 *
 * @return Pair(results, elapsedMs)
 */
fun ex7EagerProcessing(): Pair<List<Long>, Long> {
    val start = System.currentTimeMillis()

    val results = (1..1_000_000)
        .filter { it % 3 == 0 }   // builds a full filtered list (~333 333 elements)
        .map    { (it * it).toLong() }    // builds a full mapped  list
        .take(5)                   // only then picks the first 5

    val elapsed = System.currentTimeMillis() - start
    return Pair(results, elapsed)
}

// ---------------------------------------------------------------
// Exercise 8 – Lazy Processing (Sequence)
// ---------------------------------------------------------------

/**
 * Same logic as Exercise 7, but using .asSequence() for lazy evaluation.
 *
 * IMPORTANT: with a Sequence, elements are processed ONE AT A TIME through
 * the whole pipeline. As soon as 5 results are collected, processing STOPS.
 * No intermediate lists are created → much faster and memory-efficient.
 *
 * @return Pair(results, elapsedMs)
 */
fun ex8LazyProcessing(): Pair<List<Long>, Long> {
    val start = System.currentTimeMillis()

    val results = (1..1_000_000)
        .asSequence()              // switches to lazy mode
        .filter { it % 3 == 0 }   // lazily filters, one element at a time
        .map    { (it * it).toLong() }    // lazily maps
        .take(5)                   // stops after 5 elements are produced
        .toList()                  // terminal operation: triggers evaluation

    val elapsed = System.currentTimeMillis() - start
    return Pair(results, elapsed)
}

// ---------------------------------------------------------------
// Exercise 9 – Chain Multiple Operations on Names
// ---------------------------------------------------------------

/**
 * Given a list of names:
 *  1. Keeps only those starting with 'A'
 *  2. Converts them to uppercase
 *  3. Sorts them alphabetically
 *
 * Shows how multiple operations can be chained cleanly in Kotlin.
 */
fun ex9FilterAndSortNames(): List<String> {
    val names = listOf("Alice", "Bob", "Anna", "Charlie", "Arthur", "Diana", "Aaron")
    return names
        .filter    { it.startsWith("A") }
        .map       { it.uppercase() }
        .sorted()
}

// ---------------------------------------------------------------
// Main – run all tests
// ---------------------------------------------------------------

    fun main() {
        // ---------------------------------------------------------------
        // Main – run all tests
        // ---------------------------------------------------------------
        println("=== Lesson 2 – Kotlin List Processing ===\n")

        runTest("Ex1 – Immutable list",   expected = listOf(10, 20, 30, 40, 50)) {
            ex1CreateImmutableList()
        }

        runTest("Ex2 – Mutable list",     expected = mutableListOf("Kotlin", "is", "awesome", "!")) {
            ex2CreateMutableList()
        }

        runTest("Ex3 – Filter even",      expected = listOf(2, 4, 6, 8, 10)) {
            ex3FilterEvenNumbers()
        }

        runTest("Ex4 – Filter & map ages") {
            ex4FilterAndMapAges()          // no fixed expected value, just print
        }

        runTest("Ex5 – Flatten",          expected = listOf(1, 2, 3, 4, 5)) {
            ex5FlattenList()
        }

        runTest("Ex6 – FlatMap words") {
            ex6FlatMapWords()
        }

        // Exercises 7 & 8: print results + compare timings
        println()
        val (eagerResults, eagerMs) = ex7EagerProcessing()
        println("[Ex7 – Eager]  results=$eagerResults  time=${eagerMs}ms")

        val (lazyResults, lazyMs) = ex8LazyProcessing()
        println("[Ex8 – Lazy]   results=$lazyResults  time=${lazyMs}ms")

        println()
        println("⚡ Lazy is ${if (lazyMs < eagerMs) "faster" else "comparable"} " +
                "(eager: ${eagerMs}ms, lazy: ${lazyMs}ms)")

        println()
        runTest("Ex9 – Filter & sort names",
            expected = listOf("AARON", "ALICE", "ANNA", "ARTHUR")) {
            ex9FilterAndSortNames()
        }

        println("\n=== All tests complete ===")
    }
