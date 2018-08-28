package ee.sda.maven.tickets;

public class DiscountCalculator {

    public int calculate(Person person) {

        //Now not needed because of correct implementation of Mockin
        if (person == null) {
            throw new IllegalArgumentException("Given person is null");
        }

        if (person.getAge() < 0) {
            throw new IllegalArgumentException("Negative ages are not allowed: " + person.getAge());
        }

        if (person.getAge() <= 6) {
            return 100;
        }
        if (person.getStatus() == PersonStatus.DISABLED){
            return 90;
        }

        if (person.getAge() > 6 && person.getAge() < 18){
            return 75;
        }
        if (person.getStatus() == PersonStatus.STUDENT
                && person.getAge() >= 18 && person.getAge() <= 26){
            return 50;
        }
        if (person.getAge() >= 70){
            return 90;
        }

        /*
        Disabled checking, 8 tests required to be fool-proof
        We have 4 groups of ages
         */

        return 0;
    }
}
