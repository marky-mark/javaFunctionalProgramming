package lazy;

import org.junit.Test;

import static lazy.PrimeNumber.printAllPrimeNumbers;
import static lazy.Primes.isPrime;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PrimeNumberTest {

    @Test
    public void test1rLess() throws Exception {
        assertFalse(isPrime(-1));
        assertFalse(isPrime(0));
        assertFalse(isPrime(1));
    }

    @Test
    public void test2() throws Exception {
        assertTrue(isPrime(2));
    }

    @Test
    public void testEven() throws Exception {
        assertFalse(isPrime(4));
        assertFalse(isPrime(6));
        assertFalse(isPrime(8));
    }

    @Test
    public void testPrime() throws Exception {
        assertTrue(isPrime(3));
        assertTrue(isPrime(5));
        assertTrue(isPrime(7));
    }

    @Test
    public void testOddNotPrime() throws Exception {
        assertFalse(isPrime(9));
        assertFalse(isPrime(15));
    }

    @Test
    public void testPrint() throws Exception {
        printAllPrimeNumbers(50);
    }
}