public class BasicPerson {
    private final String name;
    private final int age;

    public BasicPerson(final String theName, final int theAge) {
        name = theName;
        age = theAge;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int ageDifference(final BasicPerson other) {
        return age - other.getAge();
    }

    public String toString() {
        return String.format("%s - %d", name, age);
    }
}