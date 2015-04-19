package recursion;

import static recursion.TailCalls.call;
import static recursion.TailCalls.done;

public class Factorial {
    public static int factorialRec(final int number) {
        if (number == 1)
            return number;
        else
            return number * factorialRec(number - 1);
    }

    //large number causes stack overflow due to eager loading
    /*public static void main(String[] args) {
        try {
            System.out.println(factorialRec(20000));
        } catch(StackOverflowError ex) {
            System.out.println(ex);
        }
    }*/
    /////////////////////////////////////LAMBDA////////////////////////////////

    public static int factorial(final int number) {
        return factorialTailRec(1, number).invoke();
    }

    public static TailCall<Integer> factorialTailRec(final int factorial, final int number) {
        if (number == 1)
            return done(factorial);
        else
            return call(() -> factorialTailRec(factorial * number, number - 1));
    }

    public static void main(String[] args) {
//        System.out.print(factorialTailRec(1, 2).invoke());
        System.out.print(factorialTailRec(1, 8).invoke());

        //aritmetic overflow results in 0!
//        System.out.print(factorial(2000));
    }
}