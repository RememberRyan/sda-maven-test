package ee.sda.maven.tickets;

import java.time.LocalDateTime;

public class Ticket {

    //saving person data
    private Person person;
    private int price;
    private LocalDateTime timestamp;


    // constructors
    // timestamp can be set automatically so no constructor for it
    public Ticket(Person person, int price, LocalDateTime timestamp) {
        this.person = person;
        this.price = price;
        //update when ticket is bought
        this.timestamp = timestamp;
    }


    // Getters
    public int getPrice() {
        return price;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Person getPerson() {
        return person;
    }
    // ToString

    @Override
    public String toString() {
        return "Ticket{" +
                "person=" + person +
                ", price=" + price +
                ", timestamp=" + timestamp +
                '}';
    }
}
