package Streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class collectors {
    public static void main(String[] args) {
        // List, Set, and Collection
        // 1. toList()
        List<String> names = Stream.of("Alice", "Bob", "Carol")
                .collect(Collectors.toList());
//        System.out.println(names.getClass());
        // Result: ["Alice", "Bob", "Carol"]


        // 3. toSet()
        Set<Integer> set = Stream.of(1, 2, 2, 3).collect(Collectors.toCollection(TreeSet::new));
//        System.out.println(set.getClass());
        // Result: [1, 2, 3]

        // 5. toCollection(Supplier<C> collectionFactory)
        Queue<String> queue = Stream.of("A", "B")
                .collect(Collectors.toCollection(LinkedList::new));
//        System.out.println(queue.getClass());
        // Result: LinkedList with "A", "B"

        // String joining

        // 6. joining()
        String result = Stream.of("a", "b", "c")
                .collect(Collectors.joining());
        // Result: "abc"

        // 7. joining(CharSequence delimiter)
        String result1 = Stream.of("Java", "Python", "C++")
                .collect(Collectors.joining(", "));
        // Result: "Java, Python, C++"

        // 8. joining(CharSequence delimiter, CharSequence prefix, CharSequence suffix)
        String result2 = Stream.of("red", "green", "blue")
                .collect(Collectors.joining(" | ", "[", "]"));
        // Result: "[red | green | blue]"

        // 10. flatMapping(Function, Collector)
        List<List<String>> groups = Arrays.asList(Arrays.asList("A", "B"),
                Arrays.asList("C"));
        List<String> flat = groups.stream()
                .collect(Collectors.flatMapping(Collection::stream, Collectors.toList()));
        // Result: ["A", "B", "C"]

        // Counting

        // 12. counting()
        long count = Stream.of("A", "B", "C").collect(Collectors.counting());
        // Result: 3

        // Reducing and Summarizing

        // 13. reducing(BinaryOperator)
        Optional<Integer> max = Stream.of(3, 1, 4)
                .collect(Collectors.reducing((a,b)->Math.max(a,b)));
//        Optional<Integer> max = Stream.of(3, 1, 4).collect(Collectors.reducing(Integer::max));

        // Result: Optional[4]

        // 14. reducing(T identity, BinaryOperator)
        int product = Stream.of(2, 3, 4)
                .collect(Collectors.reducing(1, (a, b) -> a * b));
        // Result: 24

        // 15. reducing(U identity, Function, BinaryOperator)
        int lengthSum = Stream.of("aa", "bbb", "c")
                .collect(Collectors.reducing(0, String::length, Integer::sum));
        // Result: 6 (2+3+1)

        // 15. summingInt(ToIntFunction)
        int sum = Stream.of("aa", "bbbb")
                .collect(Collectors.summingInt(String::length));
        // Result: 6

        // 16. summingLong(ToLongFunction)
        long sum1 = Stream.of("1", "2", "30")
                .collect(Collectors.summingLong(Long::parseLong));
        // Result: 33

        // 17. summingDouble(ToDoubleFunction)
        double sum2 = Stream.of("1.5", "2.5")
                .collect(Collectors.summingDouble(Double::parseDouble));
        // Result: 4.0

        // 18. averagingInt(ToIntFunction)
        double avg = Stream.of("a", "bb", "ccc")
                .collect(Collectors.averagingInt(String::length));
        // Result: 2.0

        // 19. averagingLong(ToLongFunction)
        double avg1 = Stream.of("1", "3")
                .collect(Collectors.averagingLong(Long::parseLong));
        // Result: 2.0

        // 20. averagingDouble(ToDoubleFunction)
        double avg2 = Stream.of("1.5", "2.5")
                .collect(Collectors.averagingDouble(Double::parseDouble));
        // Result: 2.0

        // 21. minBy(Comparator)
        Optional<String> minWord = Stream.of("a", "abc", "ab")
                .collect(Collectors.minBy(Comparator.comparing(String::length)));
        // Result: Optional["a"]

        // 22. maxBy(Comparator)
        Optional<String> maxWord = Stream.of("a", "abc", "ab")
                .collect(Collectors.maxBy(Comparator.comparing(String::length)));
        // Result: Optional["abc"]

        // 23. summarizingInt(ToIntFunction)
        IntSummaryStatistics stats = Stream.of(1, 2, 3, 4)
                .collect(Collectors.summarizingInt(Integer::intValue));
        System.out.println(stats);
        // Result: count=4, sum=10, min=1, max=4, average=2.5

        // 24. summarizingLong(ToLongFunction)
        LongSummaryStatistics stats1 = Stream.of("1", "2")
                .collect(Collectors.summarizingLong(Long::parseLong));
        // Result: count=2, sum=3, min=1, max=2, average=1.5

        // 25. summarizingDouble(ToDoubleFunction)
        DoubleSummaryStatistics stats2 = Stream.of("1.5", "2.5")
                .collect(Collectors.summarizingDouble(Double::parseDouble));
        // Result: count=2, sum=4.0, min=1.5, max=2.5, average=2.0

        // 26. groupingBy(Function)
        List<String> words = Arrays.asList("apple", "bat", "car", "arc");
        Map<Integer, List<String>> byLength = words.stream()
                .collect(Collectors.groupingBy(String::length));
        // {3=[bat, car, arc], 5=[apple]}

        // 27. groupingBy(Function, Collector)
        List<String> items = Arrays.asList("apple", "bat", "arc");
        Map<Integer, Set<String>> byLength1 = items.stream()
                .collect(Collectors.groupingBy(String::length, Collectors.toSet()));
        // {3=[bat, arc], 5=[apple]}

        // 28. groupingBy(Function, Supplier, Collector)
        List<String> items2 = Arrays.asList("apple", "bat", "arc");
        TreeMap<Integer, Set<String>> byLength2 = items.stream()
                .collect(Collectors
                        .groupingBy(String::length, TreeMap::new, Collectors.toSet()));

        // Partitioning

        // 32. partitioningBy(Predicate)
        Map<Boolean, List<Integer>> partitioned = Stream.of(1, 2,2, 3, 4)
                .collect(Collectors.partitioningBy(i -> i % 2 == 0));
        // {false=[1, 3], true=[2,2, 4]}

        // 33. partitioningBy(Predicate, Collector)
        Map<Boolean, Set<Integer>> partitioned1 = Stream.of(1, 2, 2,3, 4)
                .collect(Collectors
                        .partitioningBy(i -> i % 2 == 0, Collectors.toSet()));
        // {false=[1, 3], true=[2, 4]}

        // collectingAndThen

        // 42. collectingAndThen(Collector, Function finisher)
        List<String> list = Stream.of("a", "b")
                .collect(Collectors
                        .collectingAndThen(Collectors
                                .toList(), Collections::unmodifiableList));
        // Result: unmodifiableList ["a", "b"]

    }
}
