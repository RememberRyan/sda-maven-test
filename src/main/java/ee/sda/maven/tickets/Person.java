package ee.sda.maven.tickets;

public class Person {

    private int age;
    private PersonStatus status;

    // constructors
    public Person(int age) {
        this(age, PersonStatus.COMMON);
    }


    public Person(int age, PersonStatus status) {
       this.age = age;
        this.status = status;
    }

    // getters
    public int getAge() {
        return age;
    }

    public PersonStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (age != person.age) return false;
        return status == person.status;
    }

    @Override
    public int hashCode() {
        int result = age;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", status=" + status +
                '}';
    }
}

