import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class CollectionsLambdaExamplesTest {

    final List<String> friends =
            Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

    @Test
    public void shouldSumUp() throws Exception {

        final List<BigDecimal> prices = Arrays.asList(
                new BigDecimal("10"), new BigDecimal("30"), new BigDecimal("17"),
                new BigDecimal("20"), new BigDecimal("15"), new BigDecimal("18"),
                new BigDecimal("45"), new BigDecimal("12"));

        /*
        for(BigDecimal price : prices) {
            if(price.compareTo(BigDecimal.valueOf(20)) > 0)
                totalOfDiscountedPrices =
                    totalOfDiscountedPrices.add(price.multiply(BigDecimal.valueOf(0.9)));
        }
         */


        final BigDecimal totalOfDiscountedPrices =
                prices.parallelStream()
                        .filter(price -> price.compareTo(BigDecimal.valueOf(20)) > 0)
                        .map(price -> price.multiply(BigDecimal.valueOf(0.9)))
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total of discounted prices: " + totalOfDiscountedPrices);
    }

    @Test
    public void pickElementsTest() throws Exception {

       /*final List<String> startsWithN = new ArrayList<>();
        for(String name : friends) {
            if(name.startsWith("N")) {
                startsWithN.add(name);
            }
        }*/

        friends.stream().filter(n -> n.startsWith("N"))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    @Test
    public void repeatLamdas() throws Exception {


        final List<String> comrades =
                Arrays.asList("Kate", "Ken", "Nick", "Paula", "Zach");
        final List<String> editors =
                Arrays.asList("Brian", "Jackie", "John", "Mike");

        /*final long countFriendsStartN =
                friends.stream().filter(name -> name.startsWith("N")).count();
        final long countComradesStartN =
                comrades.stream().filter(name -> name.startsWith("N")).count();
        final long countEditorsStartN =
                editors.stream().filter(name -> name.startsWith("N")).count();*/

        //save lambda as predicate
        // In sentence there is predicate and subject -
        //he purpose of the predicate is to complete an idea about the subject,
        // such as what it does or what it is like
        // ALSO - Predicate Logic
        final Predicate<String> startsWithN = (String name) -> name.startsWith("N");

        final long countFriendsStartN =
                friends.stream().filter(startsWithN.and(n -> n.endsWith("e"))).count();
        final long countComradesStartN =
                comrades.stream().filter(startsWithN).count();
        final long countEditorsStartN =
                editors.stream().filter(startsWithN).count();

    }

    //static methods cache each variable in the future.
    public static Predicate<String> checkIfStartsWith(final String letter) {
        return name -> name.startsWith(letter);
    }

    @Test
    public void testLexicalScope() throws Exception {

        final Predicate<String> startsWithN = name -> name.startsWith("N");
        final Predicate<String> startsWithB = name -> name.startsWith("B");

        /*final long countFriendsStartN =
                friends.stream().filter(startsWithN).count();
        final long countFriendsStartB =
                friends.stream().filter(startsWithB).count();*/


        final long countFriendsStartN =
                friends.stream().filter(checkIfStartsWith("N")).count();
        final long countFriendsStartB =
                friends.stream().filter(checkIfStartsWith("B")).count();

    }

    /*
    In the preceding (smelly) example we used a static method, but we don’t want
    to pollute the class with static methods to cache each variable in the future.
    It would be nice to narrow the function’s scope to where it’s needed. We can
    do that using a Function class.
     */
    final Function<String, Predicate<String>> startsWithLetter =
            (String letter) -> {
                Predicate<String> checkStartsWith =
                        (String name) -> name.startsWith(letter);
                return checkStartsWith;
            };

    //NOTE this can also be written as
    final Function<String, Predicate<String>> startsWith2 =
            (String letter) -> {
                return (String name) -> name.startsWith(letter);
            };

    //Or even better
    final Function<String, Predicate<String>> startsWith3 =
            l -> n -> n.startsWith(l);

    @Test
    public void testNarrowScope() throws Exception {
        final long countFriendsStartN =
                friends.stream().filter(startsWithLetter.apply("N")).count();
        final long countFriendsStartB =
                friends.stream().filter(startsWithLetter.apply("B")).count();
    }

    //Picking an element of the collection

    public static void pickName(final List<String> names, final String startingLetter) {
        String foundName = null;
        for (String name : names) {
            if (name.startsWith(startingLetter)) {
                foundName = name;
                break;
            }
        }
        System.out.print(String.format("A name starting with %s: ", startingLetter));
        if (foundName != null) {
            System.out.println(foundName);
        } else {
            System.out.println("No name found");
        }
    }

    public static void pickNameLamda(final List<String> names, final String startingLetter) {
//
//        final Optional<String> foundName =
//                names.stream()
//                        .filter(name -> name.startsWith(startingLetter))
//                        .findFirst();
//
//        System.out.println(String.format("A name starting with %s: %s",
//                startingLetter, foundName.orElse("No name found")));

        names.stream()
                .filter(name -> name.startsWith(startingLetter))
                .findFirst().ifPresent(name -> System.out.println("Hello " + name));

    }

    @Test
    public void testPickNameLambda() throws Exception {
        pickNameLamda(friends, "N");
        pickNameLamda(friends, "Z");
    }

    //Reducing a Collection to a Single Value


    @Test
    public void pickLongest() throws Exception {
        System.out.println("Total number of characters in all names: " +
                friends.stream()
                        //or .mapToInt(String::length)
                        .mapToInt(name -> name.length())
                        .sum());
        //instead of sum() -> max(), sorted(), etc
    }

    @Test
    public void pickALongestReduceTest() throws Exception {
        final Optional<String> aLongName =
                friends.stream()
                        .reduce((name1, name2) ->
                                name1.length() >= name2.length() ? name1 : name2);
        aLongName.ifPresent(name ->
                System.out.println(String.format("A longest name: %s", name)));


        System.out.println(
                friends.stream()
                        .reduce("Steve", (name1, name2) ->
                                name1.length() >= name2.length() ? name1 : name2));
    }

    @Test
    public void testJoin() throws Exception {
        System.out.println(String.join(", ", friends));

        System.out.println(
                friends.stream().map(String::toUpperCase).collect(joining(", ")));
    }
}
