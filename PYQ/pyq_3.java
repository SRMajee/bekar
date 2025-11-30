package PYQ;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.GroupLayout.Group;

public class pyq_3 {
    public static void main(String[] args) {

    }

    static void Q_2025() {
        // (b)
        Map<String, Double> result = BCSEIII.stream()
                .collect(Collectors.groupingBy(
                        s -> {
                            int att = s.getAttendance(); // numeric percentage value
                            if (att >= 75)
                                return "regular";
                            else if (att >= 50)
                                return "moderately regular";
                            else
                                return "irregular";
                        },
                        Collectors.averagingDouble(Student::getMarks)));

        result.forEach((group, avg) -> System.out.println(group + " → " + avg));

        // (c) Given a text file, find any repeated character

        Character repeated = Files.lines(Path.of("input.txt")) // Stream<String> -> each line is a string
                .flatMapToInt(String::chars) // IntStream -> sequence of character code-points
                .mapToObj(c -> (char) c) // Stream<Character> -> characters extracted from file
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                // Map<Character, Long> -> character ->frequency
                .entrySet() // Set<Map.Entry<Character, Long>>
                .stream() // Stream<Map.Entry<Character, Long>>
                .filter(e -> e.getValue() > 1) // Stream<Map.Entry<Character, Long>> (only repeated)
                .map(Map.Entry::getKey) // Stream<Character> -> only the character keys
                .findAny() // Optional<Character>
                .orElse(null); // Character (final result)

        System.out.println("Repeated character: " + repeated);

        /*
         * This groups and counts occurrences of each character, then returns the first
         * one that appears more than once.
         */
    }

    static void Q_2024() {
        /*
         * (b) Categorize words by length and print total count of each subgroup
         * 
         * Steps: read file → split into words → classify by length → count.
         */

        Map<String, Long> grouped = Files.lines(Path.of("input.txt"))
                .flatMap(line -> Arrays.stream(line.split("\\s+")))
                .collect(Collectors.groupingBy(
                        word -> {
                            if (word.length() == 2)
                                return "2-letter words";
                            else if (word.length() == 3)
                                return "3-letter words";
                            else
                                return ">3-letter words";
                        },
                        Collectors.counting() // count words in each category
                ));

        grouped.forEach((k, v) -> System.out.println(k + " : " + v));

        /*
         * Output example:
         * 
         * 2-letter words : 14
         * 3-letter words : 21
         * >3-letter words : 48
         */
        /*
         * (c) Find duplicate pairs of (courseName, roomNo)
         * 
         * Given two lists:
         * 
         * courses → list of course names
         * 
         * rooms → list of classroom numbers
         * 
         * We pair them index-wise, detect duplicates, and list them.
         */

        List<String> duplicates = IntStream.range(0, Math.min(courses.size(), rooms.size()))
                .mapToObj(i -> courses.get(i) + "#" + rooms.get(i)) // represent pair as string
                .collect(Collectors.groupingBy(x -> x, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() > 1) // frequency > 1 = duplicate pair
                .map(Map.Entry::getKey)
                .toList();

        System.out.println("Duplicate course-room pairs: " + duplicates);

        /*
         * Example output:
         * 
         * Duplicate course-room pairs: [DBMS#A203, AI#B106]
         */
    }

    static void Q_2023() {
        /*
         * (a) Group dishes by type → then by calorie level (low/high)
         * 
         * (Assume low calorie < 400, high calorie ≥ 400)
         */
        Map<Dish.Type, Map<String, List<Dish>>> result = menu.stream()
                .collect(Collectors.groupingBy(
                        Dish::getType, // MEAT / FISH / OTHER
                        Collectors.groupingBy(
                                d -> d.getCalories() < 400 ? "Low Calorie" : "High Calorie")));

        System.out.println(result);

        /*
         * (b) Lowest calorie dish for each type
         */
        Map<Dish.Type, Dish> lowestCal = menu.stream()
                .collect(Collectors.groupingBy(
                        Dish::getType,
                        Collectors.collectingAndThen(
                                Collectors.minBy(Comparator.comparingInt(Dish::getCalories)),
                                opt -> opt.orElse(null))));

        Map<Dish.Type, Optional<Dish>> lowestCal = menu.stream()
                .collect(Collectors.groupingBy(
                        Dish::getType,
                        Collectors.minBy(Comparator.comparingInt(Dish::getCalories))));

        System.out.println(lowestCal);

        /*
         * This returns one minimum-calorie dish per category (Meat, Fish, Other).
         * 
         * (c) Count total number of vegetarian dishes
         */
        long vegCount = menu.stream()
                .filter(Dish::IsVegetarian)
                .count();

        System.out.println("No. of vegetarian dishes = " + vegCount);

        /*
         * (d) Difference between Streams and Collections
         * Collections Streams
         * Store data in memory Process data without storing
         * Eager evaluation Lazy evaluation (executes only on terminal operations)
         * Can be modified (add/remove) Cannot be modified; data flows through
         * External iteration (for, iterator) Internal iteration (map, filter, reduce)
         * Traversable multiple times Consumable once
         * Parallelism is manual Parallel execution is simple via .parallel()
         * (e) Return list of all unique characters from a list of words
         */
        List<Character> uniqueChars = words.stream()
                .flatMap(word -> word.chars().mapToObj(c -> (char) c))
                .distinct()
                .toList();
        List<Character> uniqueChars1 = words.stream()
                .flatMapToInt(word -> word.chars()) // IntStream
                .mapToObj(c -> (char) c) // IntStream → Stream<Character>
                .distinct()
                .toList();

        System.out.println(uniqueChars);

        /*
         * This extracts characters, flattens them, removes duplicates, then returns a
         * list.
         */

        // Sort unique words by decreasing length, return list.

        List<String> longestUniqueWords = words.stream()
                .distinct()
                .sorted((a, b) -> Integer.compare(b.length(), a.length()))
                .toList();

        List<String> longestUniqueWords = words.stream()
                .distinct()
                .sorted(Comparator.comparing(String::length).reversed())
                .toList();

        System.out.println("Longest Unique Words = " + longestUniqueWords);
        // 2️⃣ Alphabetical Ordering (Case-insensitive)
        List<String> alphabeticalUnique = words.stream()
                .map(String::toLowerCase)
                .sorted()
                .toList();

        List<String> alphabeticalUnique2 = words.stream()
                .map(w -> w.toLowerCase()) // normalize case
                .distinct()
                .sorted((a, b) -> b.compareTo(a)) // reverse lexicographical
                .toList();
        List<String> alphabeticalUnique = words.stream()
                .map(w -> w.toLowerCase())
                .distinct()
                .sorted(Comparator.reverseOrder())
                .toList();
        System.out.println(alphabeticalUnique);
        System.out.println("Alphabetical Unique Words = " + alphabeticalUnique);
        // 3️⃣ Top N Frequent Words

        // Set any value of N.

        // int N = 3;

        List<String> topN = words.stream()
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue())) // frequency descending
                .limit(N)
                .map(i -> i.getKey())
                .toList();

        System.out.println("Top " + N + " Frequent Words = " + topN);

        // 4️⃣ Clean input → Remove punctuation + normalize + get unique words
        List<String> cleanedUniqueWords = words.stream()
                .map(w -> w.replaceAll("[^a-zA-Z]", "")) // remove punctuation
                .map(String::toLowerCase) // case normalize
                .filter(w -> !w.isBlank()) // remove empty results
                .distinct()
                .toList();

        System.out.println("Clean & Unique Words = " + cleanedUniqueWords);
    }
}
