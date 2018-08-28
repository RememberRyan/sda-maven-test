package ee.sda.maven.tickets;

import org.junit.Before;
import org.junit.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

// Alternative option to using line.25 'mock.....'
// @RunWith(MockitoJUnitRunner.class)
public class AdultTicketMachineTest {

    //  @Mock
    private DiscountCalculator discountCalculator;

    private Clock clock = Clock.fixed(Instant.parse("2018-08-27T10:00:00Z"), ZoneId.of("Europe/Tallinn"));

    @Before
    public void setUp() throws Exception {
        discountCalculator = mock(DiscountCalculator.class);
    }

    @Test
    public void buy_ThrowsNoPersonDataException_IfPersonIsNull() {
        // given
        Person person = null;
        AdultTicketMachine adultTicketMachine = new AdultTicketMachine(discountCalculator, 100, clock);

        // when
        try {
            adultTicketMachine.buy(person);
            fail("no exception was thrown");
        } catch (NoPersonDataException e) {
            // then
            assertEquals("Sorry, no person data", e.getMessage());
            assertNotNull(e.getTimestamp());
            assertEquals(LocalDateTime.now(clock), e.getTimestamp());

            // assure that null never happens(???) using the placeholder, any()
            verify(discountCalculator, never()).calculate(any());
        }
    }

    // ryan's attempt at underage selling tickets prevention
    @Test
    public void buy_ThrowsForbiddenAgeException_IfPersonAgeIsBelow18() throws NoPersonDataException {
        // given
        Person person = new Person(10);
        AdultTicketMachine adultTicketMachine = new AdultTicketMachine(discountCalculator, 100, clock);
        // when
        try {
            adultTicketMachine.buy(person);
            fail("no exception was thrown");
        } catch (ForbiddenAgeException e) {
            // then
            assertEquals("Ticket sale is not allowed for this age: 10", e.getMessage());
            assertEquals(LocalDateTime.now(clock), e.getTimestamp());
        }
    }

    @Test
    public void buy_ReturnsTicket_IfPersonAgeIs18() throws NoPersonDataException {
        // given
        Person person = new Person(18);
        AdultTicketMachine adultTicketMachine = new AdultTicketMachine(discountCalculator, 100, clock);
        // when
        Ticket result = adultTicketMachine.buy(person);
        // then
        assertEquals(100, result.getPrice());
        assertEquals(person, result.getPerson());

    }

    @Test
    public void buy_ReturnsFullPriceTicket_IfSubsidizedPersonAndNoDiscountCalculator() throws NoPersonDataException {
        // given
        Person person = new Person(50, PersonStatus.DISABLED);
        DiscountCalculator discountCalculator = null;
        AdultTicketMachine adultTicketMachine = new AdultTicketMachine(discountCalculator, 100, clock);

        // when
        Ticket result = adultTicketMachine.buy(person);

        // then
        assertEquals(100, result.getPrice());
        assertEquals(person, result.getPerson());
        // assertEquals(LocalDateTime.now(), result.getTimestamp());
        assertNotNull(result.getTimestamp());
    }

    @Test
    public void buy_ReturnsFullPriceTicket_IfSubsidizedPersonAndDiscountCalculator() throws NoPersonDataException {
        // given
        Person person = new Person(50, PersonStatus.DISABLED);
        when(discountCalculator.calculate(person)).thenReturn(90);
        AdultTicketMachine adultTicketMachine = new AdultTicketMachine(discountCalculator, 100, clock);

        // when
        Ticket result = adultTicketMachine.buy(person);

        // then
        assertEquals(10, result.getPrice());
        assertEquals(person, result.getPerson());
        // assertEquals(LocalDateTime.now(), result.getTimestamp());
        assertNotNull(result.getTimestamp());

        // verify can also be used
        verify(discountCalculator).calculate(person);
    }


    // Two tests for requesting a ticket history
    @Test
    public void getHistory_ReturnsEmptyList_IfNoTicketsWereSold() {
        // given
        Person person = new Person(20);
        AdultTicketMachine adultTicketMachine = new AdultTicketMachine(discountCalculator, 100, clock);
        // when
        List<Ticket> result = adultTicketMachine.getHistory();
        // then
        assertTrue(result.isEmpty());
    }
    @Test
    public void getHistory_ReturnsOneEntry_IfOneTicketWasSold() throws NoPersonDataException {
        // given
        Person person = new Person(20);
        AdultTicketMachine adultTicketMachine = new AdultTicketMachine(discountCalculator, 100, clock);
        adultTicketMachine.buy(person);
        // when
        List<Ticket> result = adultTicketMachine.getHistory();
        // then
        assertEquals(1, result.size());
        assertEquals(new Ticket(person, 100, LocalDateTime.now(clock)), result.get(0));
    }

}


// Somewhere in here I have mistakes that don't match above

//package ee.sda.maven.tickets;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import java.time.Clock;
//import java.time.Instant;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
////@RunWith(MockitoJUnitRunner.class)
//
//public class AdultTicketMachineTest {
//
//    //  @Mock
//    private DiscountCalculator discountCalculator;
//
//    private Clock clock = Clock.fixed(Instant.parse("2018-08-27T10:00:00Z"), ZoneId.of("Europe/Tallinn"));
//
//    @Before
//    public void setUp() throws Exception {
//        discountCalculator = mock(DiscountCalculator.class);
//    }
//
//
//    @Test
//    public void buy_ThrowsNoPersonDataException_IfPersonIsNull() {
//        // given
//
//
//        Person person = null;
//        // no longer needed because of Mock
//        // DiscountCalculator discountCalculator = new DiscountCalculator();
//        AdultTicketMachine adultTicketMachine = new AdultTicketMachine(discountCalculator, 100, clock);
//
//        // when
//        try {
//            adultTicketMachine.buy(person);
//            fail("no exception was thrown");
//        } catch (NoPersonDataException e) {
//            // then
//            assertEquals("Sorry, no person data", e.getMessage());
//            assertNotNull(e.getTimestamp());
//            assertEquals(LocalDateTime.now(clock), e.getTimestamp());
//        }
//
//    }
//
//    @Test
//    public void buy_ReturnsFullPriceTicket_IfSubsidizedPersonAndNoDiscountCalculator() throws NoPersonDataException {
//        // given
//        Person person = new Person(50, PersonStatus.DISABLED);
//        DiscountCalculator discountCalculator = new DiscountCalculator();
//        AdultTicketMachine adultTicketMachine = new AdultTicketMachine(discountCalculator, 100, clock);
//
//        // when
//        // good convention to name it 'result' every time to find it easily later
//        Ticket result = adultTicketMachine.buy(person);
//
//        // then
//        assertEquals(100, result.getPrice());
//        assertEquals(person, result.getPerson());
//        //assertEquals(LocalDateTime.now(clock), result.getTimestamp());
//        assertNotNull(result.getTimestamp());
//    }
//
//    @Test
//    public void buy_ReturnsFullPriceTicket_IfSubsidizedPersonAndDiscountCalculator() throws NoPersonDataException {
//        // given
//        Person person = new Person(50, PersonStatus.DISABLED);
//        // mock the behaviour for a discount
//        when(discountCalculator.calculate(person)).thenReturn(90);
//        AdultTicketMachine adultTicketMachine = new AdultTicketMachine(discountCalculator, 100, clock);
//
//        // when
//        // good convention to name it 'result' every time to find it easily later
//        Ticket result = adultTicketMachine.buy(person);
//
//        // then
//        assertEquals(10, result.getPrice());
//        assertEquals(person, result.getPerson());
//        //assertEquals(LocalDateTime.now(clock), result.getTimestamp());
//        assertNotNull(result.getTimestamp());
//    }
//
//}