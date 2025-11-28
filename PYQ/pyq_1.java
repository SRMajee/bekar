package PYQ;

import java.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
// import static java.util.stream.Collectors.toSet;

public class pyq_1 {
        public static void main(String[] args) {
                List<Artist> artists = new ArrayList<>();
                /*
                 * ----------------------2023--------------------
                 * /*
                 * (a) Find the bands with most members using lambda expressions and/or streams
                 * API.
                 */
                Optional<Artist> result = artists.stream()
                                .max(Comparator.comparingInt(a -> a.getMembers().size()));
                // To get all bands tied for max:
                int maxSize = artists.stream()
                                .mapToInt(a -> a.getMembers().size())
                                .max()
                                .orElse(0);

                List<Artist> bands = artists.stream()
                                .filter(a -> a.getMembers().size() == maxSize)
                                .toList();

                // Set<Artist> bands = artists.stream()
                // .filter(a -> a.getMembers().size() == maxSize)
                // .collect(Collectors.toSet());

                // HashSet<Artist> bands = artists.stream()
                // .filter(a -> a.getMembers().size() == maxSize)
                // .collect(Collectors.toCollection(HashSet::new));

                /*
                 * (b) min() and count() using only reduce and lambda expressions. Can it handle
                 * empty list?
                 */
                List<Integer> list = new ArrayList<>();
                // min()
                Optional<Integer> min = list.stream()
                                .reduce((a, b) -> a < b ? a : b);

                // count()
                int count = list.stream()
                                .reduce(0,
                                                (acc, x) -> acc + 1,
                                                (a, b) -> a + b);

                // int count = list.stream()
                // .reduce(0, (acc, x) -> acc + 1, Integer::sum);

                /*
                 * Can it handle an empty list?
                 * 
                 * min() returns Optional.empty() → safe.
                 * 
                 * count() returns 0 because identity is 0 → safe.
                 */
                /*
                 * (c) Is this lambda expression side-effect free or does it mutate state? x →
                 * x==1
                 * Is x → x == 1 side-effect free?
                 * 
                 * Yes.
                 * It is pure because:
                 * 
                 * It computes a boolean value only from its input.
                 * 
                 * It does not mutate external state, does not write to variables, collections,
                 * or I/O.
                 */
                /*
                 * (d) Given 2 list of numbers, repeat the second list as many times as the
                 * numbers in the first list.
                 * 
                 * // Example: L1 = [2, 3], L2 = [5, 6] → Output: [5,6,5,6,5,6,5,6,5,6]
                 * 
                 * List<Integer> result = list1.stream()
                 * .flatMap(n -> Collections.nCopies(n, list2)
                 * .stream()
                 * .flatMap(List::stream))
                 * .toList();
                 */
                // ----------------------2024---------------------
                /*
                 * (a) Find the bands with most members using lambda expressions and/or streams
                 * API.
                 */

                /*
                 * (b) Find the artist with the longest name. You should implement this using a
                 * Collector and the reduce higher-order function, Compare the two approaches.
                 */
                // Using a Collector
                Artist longest = artists.stream()
                                .collect(Collectors.maxBy(
                                                Comparator.comparingInt(a -> a.getName().length())))
                                .orElse(null);

                // Using reduce
                Optional<Artist> longest2 = artists.stream()
                                .reduce((a, b) -> a.getName().length() >= b.getName().length() ? a : b);
                /*
                 * Comparison
                 * 
                 * Collector: clearer, declarative, optimized by JDK.
                 * 
                 * reduce: more manual, risk of accidental misuse when identity is required.
                 * Collectors are preferred for mutating reductions.
                 */

                /*
                 * (c) Print the artists' names city-wise. This should be done parallelly.
                 */
                Map<String, List<String>> cityToNames = artists.parallelStream()
                                .collect(Collectors.groupingBy(
                                                Artist::getOrigin,
                                                Collectors.mapping(Artist::getName, Collectors.toList())));
                cityToNames.forEach((city, names) -> System.out.println(city + ": " + names));

                HashMap<String, List<String>> cityToNames2 = artists.parallelStream()
                                .collect(Collectors.groupingBy(
                                                Artist::getOrigin,
                                                HashMap::new,
                                                Collectors.mapping(Artist::getName, Collectors.toList())));
                cityToNames2.forEach((city, names) -> System.out.println(city + ": " + names));
                /*
                 * (d) Is this lambda expression side-effect free? Give reasons. y→y*5
                 *
                 * Yes.
                 * 
                 * Computes a new value strictly from input.
                 * 
                 * Does not modify variables, shared objects, or global state.
                 * 
                 * Pure mathematical function.
                 * 
                 */
                /*
                 * (e) Write an implementation of max() using reduce and lambda expressions.
                 */
                Optional<Integer> max = list.stream()
                                .reduce((a, b) -> a >= b ? a : b);

                // ---------------------2025----------------------
                /*
                 * (a) Find the bands with least members using lambda expressions and/or streams
                 * API.
                 */
                /*
                 * (b) Print the artists' names city-wise. This should be done parallelly.
                 * 
                 * (c) Form a list of numbers that represents pairwise summations of numbers
                 * taking each number from two lists of numbers. Each number should appear
                 * exactly once in the list.
                 */

                /*
                 * Given:
                 * A = [a1, a2, ...], B = [b1, b2, ...]
                 * Output: [a1+b1, a2+b2, ...]
                 */

                List<Integer> result2 = IntStream.range(0, Math.min(list1.size(), list2.size()))
                                .mapToObj(i -> list1.get(i) + list2.get(i))
                                .toList();
                /*
                 * (d) Partition the list of natural numbers into prime and non-prime numbers
                 * using Java Streams.
                 */
                Map<Boolean, List<Integer>> result1 = list.stream()
                                .collect(Collectors.partitioningBy(n -> isPrime(n)));

        }

        static boolean isPrime(int n) {
                if (n < 2)
                        return false;
                return IntStream.rangeClosed(2, (int) Math.sqrt(n))
                                .noneMatch(i -> n % i == 0);
        }

}

class Artist {
        String name;
        Set<Artist> members;
        String origin;

        public Artist(String name, Set<Artist> members, String origin) {
                this.name = name;
                this.members = members;
                this.origin = origin;
        }

        public String getName() {
                return name;
        }

        public Set<Artist> getMembers() {
                return members;
        }

        public String getOrigin() {
                return origin;
        }
}