package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {

        // Atlikta ne visai vadovaujantis pagal užduotį, bet manau kad idėja ta.

        List<String> testArray = Arrays.asList("hi", "hello", "bye", "goodbye");
        List<Integer> testIntArray = Arrays.asList(100, 2, 55, 8490);

        firstTrue(testArray, s -> s.contains("z"), s -> s.contains("o"), s -> s.length() > 3);
        firsTallTrue(testArray, s -> s.contains("z"), s -> s.contains("o"), s -> s.length() > 3);
        firsTallTrue(testArray, s -> s.contains("g"), s -> s.contains("o"), s -> s.length() > 3);
        System.out.println();

        firstTrue(testIntArray,s -> s % 2 == 0, s -> s > 50);
        firsTallTrue(testIntArray, s -> s % 2 != 0, s -> s > 50);

    }

    @SafeVarargs
    public static <T> void firstTrue(List<T> list, Predicate<T>... predicate) {
        System.out.println(list.stream().filter(anyTrue(predicate)).findFirst().get());
    }

    @SafeVarargs
    public static <T> void firsTallTrue(List<T> list, Predicate<T>... predicate) {
        try {
            System.out.println(list.stream().filter(allTrue(predicate)).findAny().get());
        } catch (NoSuchElementException e) {
            System.out.println("No applicable entries found.");
        }
    }

    @SafeVarargs
    public static <T> Predicate<T> anyTrue(Predicate<T>... predicates) {
        return t -> {
            for (Predicate<T> predicate : predicates)
                if (predicate.test(t))
                    return true;
            return false;
        };
    }

    @SafeVarargs
    public static <T> Predicate<T> allTrue(Predicate<T>... predicates) {
        return t -> {
            for (Predicate<T> predicate : predicates)
                if (!predicate.test(t))
                    return false;
            return true;
        };
    }
}
