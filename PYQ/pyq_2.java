package PYQ;

import java.*;

import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class pyq_2 {
    public static void main(String[] args) {

    }

    static void Q_2023() {
        /*
         * (a) From an array of words
         * 
         * Assume:
         */
        String[] words = { "apple", "orange", "apple", "pear", "mango", "berry" };
        /*
         * (i) Count and print the number of different words
         */ long distinctCount = Arrays.stream(words)
                .distinct()
                .count();

        System.out.println("Number of different words = " + distinctCount);

        /*
         * (ii) Print the sum of the lengths of all words having length > 4
         */
        int sum = Arrays.stream(words)
                .filter(w -> w.length() > 4)
                .mapToInt(String::length)
                .sum();

        System.out.println("Sum = " + sum);

        /*
         * (b) Multiply numbers in a list and add 25
         *
         * Given:
         *
         */ List<Integer> nums = List.of(2, 3, 4);

        // Using Streams
        int result = nums.stream()
                .reduce(1, (a, b) -> a * b) // product of all numbers
                + 25;

        System.out.println(result);

        /*
         * Does it work sequentially?
         * 
         * Yes. The above uses stream() which executes sequentially.
         * 
         * Can it execute with parallel streams?
         * 
         * Yes, simply replace:
         * 
         * nums.stream()
         * 
         * 
         * with:
         * 
         * nums.parallelStream()
         * 
         * Is multiplication reduction safe for parallel execution?
         * 
         * Yes.
         * Multiplication is associative, and the identity value 1 is correct, so the
         * reduction behaves correctly in parallel.
         */
        /*
         * (c) Create a collection of n Fibonacci numbers using Streams
         * Using Stream.iterate() and a tuple (a, b)
         */
        int n = 10;

        List<Integer> fib = Stream.iterate(new int[] { 0, 1 }, p -> new int[] { p[1], p[0] + p[1] })
                .limit(n)
                .map(p -> p[0])
                .toList();

        System.out.println(fib);

        /*
         * Did we use an infinite stream?
         * 
         * Yes.
         * 
         * Stream.iterate(...) produces an infinite stream.
         * 
         * We restrict it using .limit(n) to obtain only the first n Fibonacci numbers.
         */
    }

    static void Q_2024() {
        /*
         * (a) Count the number of times each word appears in a Stream
         * 
         * Given:
         * 
         */ Stream<String> stream = Stream.of("Das", "Sarkar", "Das", "Das", "Sarkar");
        /*
         * Counting occurrences
         */
        Map<String, Long> frequencies = stream.collect(Collectors.groupingBy(
                w -> w,
                Collectors.counting()));

        System.out.println(frequencies);

        /*
         * Example Output:
         * {Das=3, Sarkar=2}
         */
        /*
         * (b) Create a collection of n Tribonacci numbers using Streams
         * 
         * Tribonacci series:
         * 0, 0, 1, 1, 2, 4, 7, 13, 24, 44, 81, ...
         * 
         * Generate n Tribonacci numbers
         * int n = 20;
         */
        List<Long> trib = Stream.iterate(new long[] { 0, 0, 1 },
                t -> new long[] { t[1], t[2], t[0] + t[1] + t[2] })
                .limit(n)
                .map(t -> t[0])
                .toList();

        /*
         * Print sum of first 20 Tribonacci numbers
         * long sum = trib.stream().mapToLong(Long::longValue).sum();
         * System.out.println("Sum = " + sum);
         */
        /*
         * (c) Does reduce() implement a mutable accumulator pattern?
         * 
         * No. It does NOT.
         * 
         * Reasons
         * 
         * reduce() is defined for immutable reduction.
         * It repeatedly combines values without modifying any mutable structure.
         * 
         * The accumulator in reduce() must be pure and side-effect free.
         * It must not mutate objects such as lists, maps, or arrays.
         * 
         * Mutable accumulation violates reduce contract, especially for parallel
         * streams, where:
         * 
         * intermediate results must be safely combinable,
         * 
         * mutation breaks associativity and thread-safety.
         * 
         * For mutable accumulation, Java provides collect(), not reduce().
         * 
         * Conclusion
         * 
         * reduce() is designed for immutable, associative reductions, not mutable
         * accumulation.
         */
        /*
         * (d) Count the number of lowercase letters in a string
         */
        String s = "HelloWorld123abc";

        long count = s.chars()
                .filter(Character::isLowerCase)
                .count();

        System.out.println("Lowercase count = " + count);

    }

    static void Q_2025() {

        List<String> courses = List.of("OS", "DBMS", "OS", "AI");
        List<String> rooms = List.of("R1", "R2", "R1", "R3");
        /*
         * Pairs formed:
         * 
         * (OS,R1), (DBMS,R2), (OS,R1), (AI,R3)
         * Identify duplicate pairs using Streams
         * 
         * List<String> courses = ...;
         * List<String> rooms = ...;
         * 
         */
        List<SimpleEntry<String, String>> duplicates = IntStream.range(0, Math.min(courses.size(), rooms.size()))
                .mapToObj(i -> new AbstractMap.SimpleEntry<>(courses.get(i), rooms.get(i)))
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .toList();
        // Solution (uses only Strings for pairs)
        List<String> duplicates1 = IntStream.range(0, Math.min(courses.size(), rooms.size()))
                .mapToObj(i -> courses.get(i) + "#" + rooms.get(i)) // represent pair as a string
                .collect(Collectors.groupingBy(x -> x, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() > 1) // frequency > 1 means duplicate
                .map(Map.Entry::getKey)
                .toList();

        // To print in readable form
        duplicates1.forEach(p -> {
            String[] parts = p.split("#");
            System.out.println("(" + parts[0] + ", " + parts[1] + ")");
        });
        /*
         * Result:
         * All pairs that appear more than once.
         * 
         * (Any custom Pair class may be used; SimpleEntry is a convenient choice.)
         */
        /* (b) Count the number of times each word appears in a Stream */
        Stream<String> stream = Stream.of("Das", "Sarkar", "Das", "Das", "Sarkar");

        Map<String, Long> freq = stream.collect(Collectors.groupingBy(
                w -> w,
                Collectors.counting()));
        /*
         * Example Output:
         * {Das=3, Sarkar=2}
         */
        /*
         * (c) Mutable accumulator pattern and its effect on parallelization
         * Definition
         * The mutable accumulator pattern refers to a reduction operation where a
         * single mutable object (such as a List, Map, StringBuilder) is updated
         * repeatedly during accumulation.
         * 
         * Example of incorrect use:
         * 
         */
        List<Integer> result = new ArrayList<>();
        numbers.stream().reduce(result, (acc, x) -> {
            acc.add(x);
            return acc;
        },
                (a, b) -> {
                    a.addAll(b);
                    return a;
                });
        /*
         * Why is it problematic?
         * 1. Violates the contract of reduce()
         * reduce() requires:
         * 
         * associativity
         * 
         * immutability of intermediate results
         * 
         * no side effects
         * 
         * Mutating the same accumulator breaks these rules.
         * 
         * 2. Unsafe in parallel streams
         * Parallel streams split work across threads.
         * If threads mutate the same accumulator object, this leads to:
         * 
         * race conditions
         * 
         * lost updates
         * 
         * inconsistent state
         * 
         * non-deterministic results
         * 
         * 3. Prevents parallel optimization
         * Because mutable objects cannot be safely combined without synchronization,
         * the runtime cannot parallelize efficiently.
         * 
         * Correct approach
         * Use collect(), which is designed for mutable containers:
         * 
         */
        List<Integer> list = numbers.stream()
                .collect(ArrayList::new, List::add, List::addAll);
        /*
         * Conclusion
         * Mutable accumulator patterns break correctness and parallel performance.
         * Therefore, reduce() must avoid mutable state; collect() should be used
         * instead.
         */
        /*
         * (d) Which lambda expressions are valid Function<Double, Double>
         * implementations?
         * A Function<Double, Double> has the form:
         * 
         * Double apply(Double x)
         * It accepts one Double argument and returns a Double.
         * 
         * (i) x → x;
         * ✔ Valid
         * 
         * One argument → OK
         * 
         * Returns the same argument (a Double) → OK
         * 
         * Conforms to Function<Double,Double>
         * 
         * This is simply the identity function.
         * 
         * (ii) (x,y) → x + y + 1;
         * ✘ Invalid
         * 
         * It takes two parameters, but Function<T,R> takes exactly one.
         * 
         * This lambda matches BiFunction<Double, Double, Double> instead.
         */
    }
}
