package Streams;

import java.util.*;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class intermediate {
        public static void main(String[] args) {
                // 1. filter(Predicate<? super T> predicate)
                List<String> words = Arrays.asList("apple", "banana", "cherry", "date");
                List<String> str = words.stream().filter(i -> i.length() > 5).collect(toList());
                // System.out.println(str); // [banana, cherry]

                // 2. map(Function<? super T, ? extends R> mapper)
                List<String> words1 = Arrays.asList("hello", "world", "");
                List<Integer> len = words1.stream().map(i -> i.length()).collect(toList());
                // List<Integer> len = words1.stream().map(String::length).collect(toList());
                // System.out.println(len); // [5, 5, 0]

                // 3. mapToInt(ToIntFunction<? super T> mapper)
                List<String> words2 = Arrays.asList("one", "two", "three");
                IntStream length1 = words2.stream().mapToInt(i -> i.length());
                List<Integer> ans1 = length1.boxed().collect(toList());
                // System.out.println(ans1);
                // System.out.println(length1.sum()); // 11

                List<String> numbers = Arrays.asList("100", "200", "300");
                List<Integer> ans2 = numbers.stream().mapToInt(Integer::parseInt).boxed().collect(toList());
                String a = "100";
                int b = Integer.parseInt(a);
                // System.out.println(ans2);

                // 4. mapToLong(ToLongFunction<? super T> mapper)
                List<String> numbers1 = Arrays.asList("100", "200", "300");
                LongStream longStream = numbers1.stream().mapToLong(Long::parseLong);
                long total = longStream.sum();
                // Result: 600

                // 5. mapToDouble(ToDoubleFunction<? super T> mapper)
                List<String> prices = Arrays.asList("10.5", "20.3", "15.7");
                DoubleStream doubleStream = prices.stream().mapToDouble(Double::parseDouble);
                double average = doubleStream.average().orElse(0.0);
                // System.out.println(average);

                // 6. flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)
                List<List<String>> nestedList = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("c", "d", "e"),
                                Arrays.asList("f"));
                List<String> ans3 = nestedList.stream().flatMap(i -> i.stream()).collect(toList());
                // List<String> ans3=
                // nestedList.stream().flatMap(Collection::stream).collect(toList());
                // System.out.println(ans3);

                // 7. flatMapToInt(Function<? super T, ? extends IntStream> mapper)
                List<String> words3 = Arrays.asList("abc", "bcd");
                IntStream length2 = words3.stream().flatMapToInt(i -> i.chars());
                // List<String> ans4 = length2.mapToObj(c -> String.valueOf((char)
                // c)).collect(toList());
                // System.out.println(ans4);
                System.out.println(length2.boxed().collect(toList()));

                // 8. flatMapToLong(Function<? super T, ? extends LongStream> mapper)
                List<String> numbers2 = Arrays.asList("1,2,3", "4,5,6"); // "1 2 3" , "4 5 6"
                LongStream length3 = numbers2.stream()
                                .flatMapToLong(i -> Arrays.stream(i.split(",")).mapToLong(Long::parseLong));
                // System.out.println(length3.boxed().collect(toList()));
                // System.out.println(length3.sum());

                // 9. flatMapToDouble(Function<? super T, ? extends DoubleStream> mapper)
                List<String> data = Arrays.asList("1.1,2.2", "3.3,4.4");
                DoubleStream doubleStream1 = data.stream()
                                .flatMapToDouble(s -> Arrays.stream(s.split(",")).mapToDouble(Double::parseDouble));
                double sum2 = doubleStream1.sum();

                // 10. distinct()
                List<Integer> numbers3 = Arrays.asList(1, 2, 2, 3, 3, 3, 4);
                List<Integer> ans5 = numbers3.stream().distinct().collect(toList());
                // System.out.println(ans5);

                // 11. sorted()
                List<String> words4 = Arrays.asList("banana", "apple", "cherry");
                List<String> sorted = words4.stream().sorted().collect(Collectors.toList());
                // reverse
                List<String> sortedRev = words4.stream().sorted(Collections.reverseOrder())
                                .collect(Collectors.toList());
                // sort by length
                // List<String> sortedLen =
                // words4.stream().sorted((ar,br)->Integer.compare(ar.length(),br.length())).collect(Collectors.toList());
                List<String> sortedLen = words4.stream().sorted(Comparator.comparing(String::length))
                                .collect(Collectors.toList());
                // System.out.println(sortedLen);

                // 12. peek(Consumer<? super T> action)
                List<String> words6 = Arrays.asList("hello", "world");
                // List<String> result = words6.stream().peek(System.out::println)
                // .map(String::toUpperCase)
                // .peek(s->System.out.println("Uppercase : " + s))
                // .collect(toList());

                // List<String> result1 =
                // words6.stream().map(String::toUpperCase).collect(toList());
                // System.out.println(result1);
                // Prints:
                // hello
                // Uppercase: HELLO
                // world
                // Uppercase: WORLD
                // [HELLO, WORLD]

                // 13. limit(long maxSize)
                List<Integer> numbers4 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
                List<Integer> limited = numbers4.stream().limit(5).collect(Collectors.toList());
                // System.out.println(limited);

                // 14. skip(long n)
                List<Integer> numbers5 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
                List<Integer> skipped = numbers5.stream().skip(3).collect(Collectors.toList());

                // Example
                List<Integer> numbers6 = Arrays.asList(1, 2, 3); // i
                List<Integer> numbers7 = Arrays.asList(1, 2, 3, 4); // k => {i,k}
                List<int[]> ans = numbers6.stream().flatMap(i -> numbers7.stream().map(k -> new int[] { i, k }))
                                .collect(toList());
                // for(int []i:ans){
                // System.out.println(Arrays.toString(i));
                // }
                /*
                 * [1, 1]
                 * [1, 2]
                 * [1, 3]
                 * [1, 4]
                 * [2, 1]
                 * [2, 2]
                 * [2, 3]
                 * [2, 4]
                 * [3, 1]
                 * [3, 2]
                 * [3, 3]
                 * [3, 4]
                 */

                // List<Integer> l = new ArrayList<>(List.of(1,2,3));
                // int arr[] = l.stream().mapToInt(Integer::intValue).toArray();
                // System.out.println(Arrays.toString(arr));
                // Integer ar = 10;
                // System.out.println(ar);
                // System.out.println(ar.intValue());

        }
}
