package lazy;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class PrimeNumber {

    public static List<Integer> getAllPrimeNumbers(int n) {
        return IntStream.range(0, n).boxed()
                .filter(PrimeNumber::isPrime)
                .collect(toList());
    }

    public static void printAllPrimeNumbers(int n) {
        IntStream.range(0, n).boxed()
                .filter(PrimeNumber::isPrime)
                .forEach(System.out::println);
    }

    public static boolean isPrime(int n) {
        if (n <= 1)
            return false;
        else if (n == 2)
            return true;
        else if (n % 2 == 0)
            return false;

        return !IntStream.range(3, roofRoot(n))
                .boxed()
                .anyMatch(i -> n % i == 0);
    }

    private static int roofRoot(double n) {
        return ((int) Math.sqrt(n)) + 1;
    }
}
