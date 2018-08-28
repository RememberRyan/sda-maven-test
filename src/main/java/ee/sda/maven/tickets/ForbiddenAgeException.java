package ee.sda.maven.tickets;
import java.time.LocalDateTime;

public class ForbiddenAgeException extends RuntimeException {
    private LocalDateTime timestamp;
    public ForbiddenAgeException(int age, LocalDateTime timestamp) {
        super("Ticket sale is not allowed for this age: " + age);
        this.timestamp = timestamp;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}