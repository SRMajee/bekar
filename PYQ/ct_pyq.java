
package PYQ;

import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

public class ct_pyq {
    public static void main(String[] args) throws IOException {
        // 1
        List<Integer> dfgdfg = List.of(1, 2, 3, 4, 5);
        // List<Integer> ans = list
        // .stream()
        // .map(i -> i * i)
        // .toList();
        // System.out.println(ans);

        // 2.
        // List<Integer> L1 = List.of(1, 2, 3);
        // List<Integer> L2 = List.of(3, 4);
        // List<int[]> ans = L1
        // .stream()
        // .flatMap(i -> L2.stream().map(j -> new int[] { i, j }))
        // .toList();
        // ans.forEach(p -> System.out.println(p[0] +" "+ p[1]));

        // 3.
        // List<Integer> L1 = List.of(1, 2, 3);
        // List<Integer> L2 = List.of(3, 4);
        // List<int[]> ans = L1
        // .stream()
        // .flatMap(i -> L2.stream().map(j -> new int[] { i, j }))
        // .filter(i -> (i[0] + i[1]) % 3 == 0)
        // .toList();
        // ans.forEach(p -> System.out.println(p[0] + " " + p[1]));

        // Text File
        // List<Character> ans = Files.lines(Path.of("C:\\DRIVE D\\College\\Sem
        // 5\\PPL\\bekar\\PYQ\\input.txt"))
        // .flatMap(i -> Arrays.stream(i.split("\\s+")))
        // .flatMap(i -> i.chars().mapToObj(j -> (char) j))
        // .distinct()
        // .toList();

        // Map<String,List<String>> ans =
        // Files.lines(Path.of("C:\\\\DRIVE D\\\\College\\\\Sem
        // 5\\\\PPL\\\\bekar\\\\PYQ\\\\input.txt"))
        // .flatMap(i->Arrays.stream(i.split("\\s+")))
        // .collect(Collectors.groupingBy(i->{
        // int a = i.length();
        // if(a==2) return "2-letter";
        // else if(a==3) return "3-letter";
        // else return ">3-letter";
        // }));

        int n = 45;
        List<Integer> ans = Stream
                .iterate(new int[] { 0, 0, 1 }, p -> new int[] { p[1], p[2], p[0] + p[1] + p[2] })
                .limit(n)
                .map(i -> i[0])
                .toList();
        System.out.println(ans);
    }

    static void Trader() {
        // Transaction and Trader
        List<Transaction> list;
        // List<Transaction> ans = list
        // .stream()
        // .filter(i -> i.getYear() == 2011)
        // .sorted(Comparator.comparingInt(i -> i.getValue()))
        // .toList();

        // List<String> ans = list
        // .stream()
        // .map(i -> i.getTrader().getCity())
        // .distinct()
        // .toList();

        // List<Trader> ans = list
        // .stream()
        // .map(i -> i.getTrader())
        // .distinct()
        // .filter(t -> t.getCity().equals("Cambridge"))
        // .sorted(Comparator.comparing(i -> i.getName()))
        // .toList();

        // String ans = list
        // .stream()
        // .map(i -> i.getTrader().getName())
        // .distinct()
        // .sorted()
        // .collect(Collectors.joining(",","(",")"));

        // bool ans =
        // list
        // .stream()
        // .anyMatch(i->i.getTrader().getCity().equals("Milan"));

        // list
        // .stream()
        // .filter(i -> i.getTrader().getCity().equals("Cambridge"))
        // .map(i -> i.getValue())
        // .forEach(i -> System.out.println(i));

        // int ans = list
        // .stream()
        // .max(Comparator.comparingInt(i -> i.getValue()))
        // .map(i -> i.getValue())
        // .orElse(0);

        // int ans = list
        // .stream()
        // .mapToInt(i -> i.getValue())
        // .max()
        // .orElse(0);

        // let high value be 10000
        // Map<String, List<Trader>> result = list
        // .stream()
        // .filter(t -> t.getValue() > 10000)
        // .collect(Collectors.groupingBy(
        // t -> t.getTrader().getCity(),
        // Collectors.collectingAndThen(
        // Collectors.mapping(t -> t.getTrader(), Collectors.toSet()),
        // set -> new ArrayList<>(set))));

        // Map<Integer, Transaction> ans = list
        // .stream()
        // .collect(Collectors.groupingBy(
        // i -> i.getYear(),
        // Collectors.collectingAndThen(
        // Collectors.maxBy(Comparator.comparingInt(i -> i.getValue())), i ->
        // i.orElse(0))));

        // Map<Integer, Double> ans = list.stream()
        // .collect(Collectors.groupingBy(
        // i -> i.getYear(),
        // Collectors.averagingDouble(i -> i.getValue())));

        // double ans = list
        // .stream()
        // .filter(i -> i.getTrader().getCity().equals("Mumbai"))
        // .min(Comparator.comparingInt(i -> i.getValue()))
        // .map(i -> i.getValue())
        // .orElse(0);

        // List<String> words;
        // List<String> ans = words
        // .stream()
        // .filter(i -> Character.isDigit(i.charAt(i.length() - 1)))
        // .collect(Collectors.groupingBy(i -> i, Collectors.counting()))
        // .entrySet()
        // .stream()
        // .filter(i -> i.getValue() > 1)
        // .map(i -> i.getKey())
        // .toList();
    }

    static void Artists() {
        // Artists
        List<Artists> list;
        // int ans = list
        // .stream()
        // .filter(i -> i.getOrigin().equals("Kolkata"))
        // // .collect(Collectors.summingInt(a -> a.getMembers().size()));
        // .mapToInt(i -> i.getMembers().size())
        // .sum();

        // List<Artists> ans =

        // list
        // .stream()
        // .collect(Collectors.groupingBy(i -> i.getMembers().size()))
        // .entrySet()
        // .stream()
        // .max(Comparator.comparingInt(i -> i.getKey()))
        // .map(i -> i.getValue())
        // .orElse(List.of());

        // int max = list.stream().reduce((a, b) -> a > b ? a : b).orElse(0);

        // int count = list.stream(0, (a, b) -> a + 1, (a, b) -> a + b);
        // int avg = list.stream().reduce(0, (a, b) -> a + b, (a, b) -> a + b) / count;
    }
}

class Transaction {

}

class Artists {

}