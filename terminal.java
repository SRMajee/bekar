package Streams;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class terminal {
    public static void main(String[] args) {
        // 1. forEach(Consumer<? super T> action)
//        List<String> words = Arrays.asList("hello", "world", "a", "b", "dfsd", "rg");
//        words.parallelStream().forEach(System.out::println);
//        List<String> lst =
//                words.parallelStream().peek(System.out::println).collect(toList());
//        System.out.println();
//        System.out.println(lst);
//        System.out.print("|");
//        System.out.println(lst.get(1));
        // Prints: hello, world, (order not guaranteed in parallel)

        // 2. forEachOrdered(Consumer<? super T> action)
//        List<String> words1 = Arrays.asList("hello", "world", "a", "b", "dfsd", "rg");
//        words1.parallelStream().forEachOrdered(System.out::println);
        // Prints: hello, world, (order guaranteed)

        // 3. toArray()
//        List<String> words3 = Arrays.asList("hello", "world");
//        Object[] arr1 = words3.toArray();
//        System.out.println(Arrays.toString(arr1));
//
//        List<Integer> wet = new ArrayList<>();
//        Integer a = 10;
//        Integer b =20;
//        wet.add(a);
//        wet.add(b);
//        Object[] arr2 = wet.toArray();
//        System.out.println(Arrays.toString(arr2));

        // 4. toArray(IntFunction<A[]> generator)
//        List<String> words3 = Arrays.asList("hello", "world");
//        String[] arr1 = words3.toArray(String[]::new);
//        System.out.println(Arrays.toString(arr1));

        // 5. reduce(T identity, BinaryOperator<T> accumulator)
//        List<Integer> numbers = Arrays.asList(1,2,3,4);
//        Optional<Integer> sum = numbers.stream().reduce((a, b)->a+b);
//        System.out.println(sum);
//        Integer sum1 = numbers.stream().reduce(0,(a, b)->a+b);
//        Integer sum2 = numbers.stream().reduce(0,Integer::sum);
//        System.out.println(sum1);
//        System.out.println(sum2);

//         if list empty
//        Optional.empty
//        0

//        List<String> words5 = Arrays.asList("hello", "world");
        // hello world
//        String concat = words5.stream().reduce("", (a, b) -> a + " " + b);
//        System.out.print("|");
//        System.out.println(concat);
        // h e l l o w o r l d 🔀

//        String a = "123";
//        String a = new StringBuilder("123").toString();
//        StringBuilder a = new StringBuilder("123");
//        StringBuilder b = new StringBuilder("45");
//        a.append(b);
//        a.toString();
//        a+="45";


        // 7. reduce(U identity, BiFunction<U, ? super T, U> accumulator,BinaryOperator<U> combiner)
//        List<String> words6 = Arrays.asList("hello", "world", "");
//        Integer totalLength = words6.stream().reduce(0, (length, word) -> length + word.length(),Integer::sum);
        // reduce ser upor - kaj korbe na
//        words6.stream().reduce(ArrayList::new, ArrayList::add,ArrayList::addAll);
//        ArrayList<Integer> ls1 = new ArrayList<>(); // [1]
//        ArrayList<Integer> ls2 = new ArrayList<>(); // [3]
//        ArrayList<ArrayList<Integer>> ls3 = new ArrayList<>(); // [ [1],[3] ]
//        ls1.add(1);
//        ls1.add(2);
//        ls2.add(3);
//        ls2.add(4);
//        ls3.add(ls1);
//        ls3.add(ls2);
//        System.out.println(ls1);
//        System.out.println(ls2);
//        System.out.println(ls3);
////        [1,2,3,4]
//        ArrayList<Integer> ls4 = new ArrayList<>();
//        ls4.addAll(ls1);
//        ls4.addAll(ls2);
//        System.out.println(ls4);
//        System.out.println(totalLength);

        // 8. collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner)
        List<String> words7 = Arrays.asList("hello", "world","");
        ArrayList<String> ans = words7.stream().collect(ArrayList::new, ArrayList::add,ArrayList::addAll);
        System.out.println(ans);
        // Result: ArrayList containing [hello, world]

        // joining
//        List<String> words7 = Arrays.asList("hello", "world","");
//        String joined = words7.stream().collect(Collectors.joining(", "));
//        System.out.println(joined);
        // Result: hello, world

        // groupingBy
//        List<String> words7 = Arrays.asList("hello", "world","");
//        Map<Integer,List<String>> map = words7.stream().
//                collect(Collectors.groupingBy(i->i.length()));
//        for(Map.Entry<Integer,List<String>> e:map.entrySet()){
//            System.out.print(e.getKey());
//            System.out.println(e.getValue());
//        }
        //  Result: {0=[], 5=[hello, world]}

        // 10. min(Comparator<? super T> comparator)
        List<String> words9 = Arrays.asList("hello", "world", "");
//        Optional<String> shortest = words9.stream().min(Comparator.comparing(String::length));
        List<Integer> ls = new ArrayList<>(List.of(2,-3,54,56,6,77,6));
        Optional<Integer> shortest = ls.stream().min((a,b)->a-b);
        System.out.println(shortest);
//        System.out.println(Collections.min(ls));

        // 11. max(Comparator<? super T> comparator)
        List<String> words10 = Arrays.asList("hello", "world", "");
        Optional<String> longest = words10.stream().max(Comparator.comparing(String::length));
        // Result: Optional["hello"] or Optional["world"]

        List<Integer> numbers6 = Arrays.asList(3, 1, 4, 1, 5);
        Optional<Integer> maximum = numbers6.stream().max(Integer::compareTo);
        // Result: Optional[5]

        // 12. count() // how many whose len >4
        List<String> words = Arrays.asList("hello", "world", "");
        long a = words.stream().filter(i -> i.length() > 4).count();

        // 13. anyMatch(Predicate<? super T> predicate)
        List<String> words13 = Arrays.asList("hello", "world", "");
        boolean hasLongWord = words13.stream().anyMatch(word -> word.length() > 4);
        // Result: true

        boolean hasShortWord = words.stream().anyMatch(word -> word.length() < 3);
        // Result: false

        // 14. allMatch(Predicate<? super T> predicate)
        List<String> words14 = Arrays.asList("hello", "world", "");
        boolean allLongWords = words14.stream().allMatch(word -> word.length() > 3);
        // Result: true

        boolean allVeryLongWords = words.stream().allMatch(word -> word.length() > 5);
        // Result: false

        // 15. noneMatch(Predicate<? super T> predicate)
        List<String> words15 = Arrays.asList("hello", "world", "");
        boolean noShortWords = words15.stream().noneMatch(word -> word.length() < 3);
        // Result: true

        // 16. findFirst()
        List<String> words16 = Arrays.asList("hello", "world", "");
        Optional<String> first = words16.stream().filter(word -> word.length() > 4).findFirst();
        // Result: Optional["hello"]

        Optional<String> notFound = words.stream().filter(word -> word.startsWith("x")).findFirst();
        // Result: Optional.empty()

        // 17. findAny()
        List<String> words17 = Arrays.asList("hello", "world", "");
        Optional<String> any = words17.parallelStream().filter(word -> word.length() > 4).findAny();
        // Result: Optional["hello"] or Optional["world"] (non-deterministic)


    }
}
