package Streams;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class extra {
    public static void main(String[] args) {
        // Static Factory Methods

        // 1. empty()
        Stream<String> emptyStream = Stream.empty();
        long count = emptyStream.count();
        // Result: 0

        // 2. of(T t)
        Stream<String> singleElement = Stream.of("hello");
        List<String> result = singleElement.collect(Collectors.toList());
        // Result: ["hello"]

        // 3. of(T... values)
        Stream<String> multipleElements = Stream.of("hello", "world", "");
        List<String> result1 = multipleElements.collect(Collectors.toList());
        // Result: ["hello", "world", ""]

        // 5. iterate(T seed, UnaryOperator<T> f)

        Stream<Integer> infinite = Stream.iterate(1, n -> n * 2);
        List<Integer> powers = infinite.limit(5)
                .collect(Collectors.toList());
        // Result: [1, 2, 4, 8, 16]

        Stream<String> stringStream = Stream.iterate("a", s -> s + "a");
        List<String> repeated = stringStream.limit(4)
                .collect(Collectors.toList());

        // 7. generate(Supplier<? extends T> s)
        Stream<Double> randomNumbers = Stream.generate(Math::random);
        List<Double> fiveRandoms = randomNumbers.limit(5)
                .collect(Collectors.toList());
         System.out.println(fiveRandoms);
        // Result: 5 random doubles

        Stream<String> constants = Stream.generate(() -> "constant");
        List<String> repeated1 = constants.limit(3)
                .collect(Collectors.toList());
        // Result: ["constant", "constant", "constant"]

        // 11. Statistical Operations

        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        IntSummaryStatistics stats = numbers1.stream()
                .mapToInt(Integer::intValue).summaryStatistics();

        // System.out.println("Count: " + stats.getCount()); // 10
        // System.out.println("Sum: " + stats.getSum()); // 55
        // System.out.println("Average: " + stats.getAverage()); // 5.5
        // System.out.println("Min: " + stats.getMin()); // 1
        // System.out.println("Max: " + stats.getMax()); // 10

    }
}
