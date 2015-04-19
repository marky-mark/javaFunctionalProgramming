import org.junit.Test;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

public class StringsComparatorsFiltersTest {

    final List<BasicPerson> people = Arrays.asList(
            new BasicPerson("John", 20),
            new BasicPerson("Sara", 21),
            new BasicPerson("Jane", 21),
            new BasicPerson("Greg", 35));

    @Test
    public void testChars() throws Exception {
        final String str = "w00t";
        str.chars()
                .mapToObj(ch -> (char) ch)
                .forEach(System.out::println);

        str.chars()
                .filter(Character::isDigit)
                .mapToObj(ch -> (char) ch)
                .forEach(System.out::println);
    }

    Comparator<BasicPerson> compareAscending = BasicPerson::ageDifference;
    Comparator<BasicPerson> compareDescending = compareAscending.reversed();

    @Test
    public void testComparable() throws Exception {


        List<BasicPerson> ascendingAge =
                people.stream()
                        .sorted(BasicPerson::ageDifference)
                        .collect(toList());

        printPeople("Sorted in ascending order of age: ", ascendingAge);

        printPeople("Sorted in descending order of age: ", people.stream()
                .sorted((person1, person2) -> person2.ageDifference(person1))
                .collect(toList()));

        //alternatively
        printPeople("Sorted in descending order of age: ", people.stream()
                .sorted(compareDescending)
                .collect(toList()));

    }

    public static void printPeople(
            final String message, final List<BasicPerson> people) {
        System.out.println(message);
        people.forEach(System.out::println);
    }

    @Test
    public void testMin() throws Exception {
        people.stream().min(BasicPerson::ageDifference)
                .ifPresent(p -> System.out.println("Hey" + p));

        //simarly for max
    }

    @Test
    public void testCompare() throws Exception {
        people.stream()
                .sorted((person1, person2) ->
                        person1.getName().compareTo(person2.getName()));

        final Function<BasicPerson, String> byName = person -> person.getName();
        people.stream().sorted(comparing(byName));

        final Function<BasicPerson, Integer> byAge = person -> person.getAge();
        final Function<BasicPerson, String> byTheirName = person -> person.getName();
        printPeople("Sorted in ascending order of age and name: ",
                people.stream()
                        //comparing age and name
                        .sorted(comparing(byAge).thenComparing(byTheirName))
                        .collect(toList()));
    }

    @Test
    public void testCollectPeople() throws Exception {
        List<BasicPerson> olderThan20 =
                people.stream()
                        .filter(person -> person.getAge() > 20)
                                //same as toList()
                        .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println("People older than 20: " + olderThan20);
    }

    @Test
    public void testGroupingByAge() throws Exception {
        Map<Integer, List<BasicPerson>> peopleByAge =
                people.stream()
                        .collect(groupingBy(BasicPerson::getAge));
        System.out.println("People grouped by age: " + peopleByAge);
    }

    @Test
    public void testGetPeoplesNameOrderedByAge() throws Exception {
        Map<Integer, List<String>> nameOfPeopleByAge =
                people.stream()
                        .collect(
                                groupingBy(BasicPerson::getAge,
                                        mapping(BasicPerson::getName,
                                                toList())));
        System.out.println("People grouped by age: " + nameOfPeopleByAge);
    }

    @Test
    public void testGroupNamesByFirstCharacterThenByOldest() throws Exception {

        Comparator<BasicPerson> byAge = Comparator.comparing(BasicPerson::getAge);

        Map<Character, Optional<BasicPerson>> oldestPersonInEachAlphabet =
                people.stream()
                        .collect(groupingBy(person -> person.getName().charAt(0),
                                /*In each group, it reduces the elements to the
                                oldest person, as decided by the maxBy() method*/
                                reducing(BinaryOperator.maxBy(byAge))));

        System.out.println("Oldest person in each alphabet:");
        System.out.println(oldestPersonInEachAlphabet);

    }
}
