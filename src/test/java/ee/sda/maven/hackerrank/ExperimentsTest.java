package ee.sda.maven.hackerrank;

import org.junit.Assert;
import org.junit.Test;

public class ExperimentsTest {

//    @Rule
//    //forced to specify before the erraneous code, or it will not work (????)
//    public ExpectedException expectedException = ExpectedException.none();


    @Test (expected = ArithmeticException.class)
    public void divide_ThrowsArithmeticException_IfDivisionByZero(){
        // given is empty
        int a = 10;
        int b = 0;

        //for using the @Rule
//        expectedException.expectMessage("/ by zero");
//        expectedException.expect(ArithmeticException.class);

        //when
        // sometimes you may just have variables and it wont be so human readable that zero is the second operand
        int result = a / b;
        // int result = 10 / 0; //always check for if the second operand of a division is zero

        //then
        //it should throw an exception that we are expecting
    }


    //This is the recommended way to assert exceptions in TDD - Dmitri. You have full control of received exceptions
    @Test
    public void divide_ThrowsArithmeticException_IfDivisionByZero3() {
        // given is empty
        int a = 10;
        int b = 10;

        //when
        try {
            int result = a / b;
            //if exception is expected but never thrown - we want it to fail when line 41 "succeeds"
            Assert.fail("It is failing because: No ArithmeticException was thrown");
        } catch (ArithmeticException e) {

            //then
            Assert.assertEquals("/ by zero", e.getMessage());
        }
    }
}
