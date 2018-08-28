package ee.sda.maven.tickets;

//inherit from abstract class


import java.time.Clock;
import java.time.LocalDateTime;

//concrete ticket machine we can now test
public class AdultTicketMachine extends TicketMachine{

    /*
     almost the same clock but specifying  predefined clock
     this is necessary because we provide the same system clock.
     in testing we can provide our created clock that will return the same moment in time.
     We're creating one object with another object that we can control: "Mocking"
      */
    private Clock clock;

    // saving inside our object
    private DiscountCalculator discountCalculator;
    private int price;

    // constructor for discount ticket machine
    public AdultTicketMachine(DiscountCalculator discountCalculator, int price, Clock clock) {
        this.discountCalculator = discountCalculator;
        this.price = price;
        this.clock = clock;
    }

    //we need a method we can now test


    // buying a ticket
    // we need to provide person data to calculate the discount
    public Ticket buy(Person person)  throws NoPersonDataException {
        if (person == null) {
            throw new NoPersonDataException("Sorry, no person data", LocalDateTime.now(clock));
        }
        if (discountCalculator == null) {
            return new Ticket(person, price, LocalDateTime.now(clock));
        }

        // will not sell tickets to under 18 / non-adults
        if (person.getAge() < 18) {
            throw new ForbiddenAgeException(person.getAge(), LocalDateTime.now(clock));
        }

        // something to calculate the discount
        int discountPercentage = discountCalculator.calculate(person);

        //taking the price, multiply it by discount percentage divided by 100
        double discount = price * (discountPercentage / 100d);
        double discountedPrice = price - discount;

        // (int) is safe until maximum possible integer. It's 'safe' for now
        return new Ticket(person, (int) Math.floor(discountedPrice), LocalDateTime.now(clock));
    }

}
