package lazy;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Primes {

    public static boolean isPrime(final int number) {
        return number > 1 &&
                IntStream.rangeClosed(2, (int) Math.sqrt(number))
                        .noneMatch(divisor -> number % divisor == 0);
    }

    private static int primeAfter(final int number) {
        if (isPrime(number + 1))
            return number + 1;
        else
            return primeAfter(number + 1);
    }

    public static List<Integer> primes(final int fromNumber, final int count) {

        return Stream.iterate(primeAfter(fromNumber - 1), Primes::primeAfter)
                .limit(count)
                .collect(Collectors.<Integer>toList());
    }

    /*
    The code produced two series of primes, one starting at 1 and the other
    starting at 100. These were extracted from the infinite series we created so
    succinctly within the primes() method, thanks to the laziness of Streams and
    the power of lambda expressions/method references.
     */
    public static void main(final String[] args) {
        System.out.println("10 primes from 1: " + primes(1, 10));
        System.out.println("5 primes from 100: " + primes(100, 5));
    }

}
