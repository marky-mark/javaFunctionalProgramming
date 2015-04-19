import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonTest {

    public void add(Object o) {
        System.out.println("actually printing this..." + o);
    }

    @Test
    public void shouldPrintLambda() throws Exception {

        List<Person> roster = new ArrayList<>();
        roster.add(new Person(LocalDate.of(1990, 4, 6), Person.Sex.FEMALE));
        roster.add(new Person(LocalDate.of(1986, 3, 12), Person.Sex.MALE));

//        Person.printPersons(roster, new CheckPerson() {
//            @Override
//            public boolean test(Person p) {
//                return p.getGender() == Person.Sex.MALE
//                        && p.getAge() >= 18
//                        && p.getAge() <= 29;
//            }
//        });
//

        Person.printPersons(
                roster,
                (Person p) -> p.getGender() == Person.Sex.MALE
                                && p.getAge() >= 18
                                && p.getAge() <= 29
        );

        roster.forEach(p -> {
            System.out.println("printing out person");
            p.printPerson();
        });
        roster.forEach(System.out::println);
        roster.forEach(this::add);
    }


}