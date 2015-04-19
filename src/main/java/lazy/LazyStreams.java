package lazy;

import java.util.Arrays;
import java.util.List;

public class LazyStreams {

    private static int length(final String name) {
        System.out.println("getting length for " + name);
        return name.length();
    }

    private static String toUpper(final String name) {
        System.out.println("converting to uppercase: " + name);
        return name.toUpperCase();
    }

    public static void main(final String[] args) {

        List<String> names = Arrays.asList("Brad", "Kate", "Kim", "Jack", "Joe",
                "Mike", "Susan", "George", "Robert", "Julia", "Parker", "Benson");

        //filter and map are lazy - evaluations only happen when findFirst is called
        final String firstNameWith3Letters =
                names.stream()
                        //intermediate
                        .filter(name -> length(name) == 3)
                        .map(name -> toUpper(name))
                        //terminal
                        .findFirst()
                        .get();
        System.out.println(firstNameWith3Letters);

        //so in other words 5 operations (3 filters, 1 map and 1 findFirst) rather than 15 (go through all the elements)
    }
}