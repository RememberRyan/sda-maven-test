package ee.sda.maven.tickets;

import java.time.LocalDateTime;
import java.util.Objects;

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

    // implementation of requesting ticket history
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return price == ticket.price &&
                Objects.equals(person, ticket.person) &&
                Objects.equals(timestamp, ticket.timestamp);
    }
    @Override
    public int hashCode() {
        return Objects.hash(person, price, timestamp);
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
