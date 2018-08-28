package ee.sda.maven;

/*
When writing unit tests add the suffix 'Test', making it easier to find this class by name.
 */


import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {

    //need to add dependency for tests

    // every test is a method
    @Test

    /*
    change the name of this from being 'test'
    1. Name of the tested method: sum_ (underscores allowed as convention in development)
    2. Expected behaviour: Returns0_
    3. Describes condition how the result should arrive: If called without arguments
     */
    public void sum_Returns0_IfCalledWithNoArgs() {
        // given
        Calculator calculator = new Calculator();

        //when
        int sum = calculator.sum();

        //then
        Assert.assertEquals(0, sum);
    }

    /* 2nd test
    implemented new test case but this also breaks previous test case unless we use method overloading
    in the main Calcualtor.java
     */
    @Test
    public void sum_Returns0_IfCalledWithNull() {
        // given
        Calculator calculator = new Calculator();
        String input = null;

        // when:
        int sum = calculator.sum(null);

        // then
        Assert.assertEquals(0, sum);
    }

    //3rd test case
    @Test
    public void sum_Returns0_IfInputHasNoNumbers() {
        Calculator calculator = new Calculator();

        int sum = calculator.sum("It's great to write tests at last!");
        Assert.assertEquals(0, sum);
    }

    //4th test case: expected value is 100
    @Test
    public void sum_ReturnsSameNumber_IfInputIsOneNumber() {
        Calculator calculator = new Calculator();

        int sum = calculator.sum("100");
        Assert.assertEquals(100, sum);
    }

    //5th test
    @Test
    public void sum_ReturnsCorrectSum_IfInputHasSumOfTwoNumbers() {
        Calculator calculator = new Calculator();

        int sum = calculator.sum("100+123");
        Assert.assertEquals(223, sum);
    }

    //6th test
    @Test
    public void sum_ReturnsCorrectSum_IfInputHasPartlyValidNumbers() {
        Calculator calculator = new Calculator();

        int sum = calculator.sum("100+12asd3");
        Assert.assertEquals(100, sum);
    }
    //7th test
    @Test
    public void sum_ReturnsCorrectSum_IfInputHasManyNumbers() {
        Calculator calculator = new Calculator();

        int sum = calculator.sum("100+1+2+3+4+5+6+7+8+9+10");
        Assert.assertEquals(155, sum);
    }
    //8th test
    @Test
    public void sum_ReturnsCorrectSum_IfInputHasNegativeNumbers() {
        Calculator calculator = new Calculator();

        int sum = calculator.sum("-100+-1");
        Assert.assertEquals(-101, sum);
    }
}