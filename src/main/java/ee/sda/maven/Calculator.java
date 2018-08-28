package ee.sda.maven;

public class Calculator {

    //method overloading to call it without arguments
    // this allows for 2nd test case one to work
    public int sum() {
        return 0;
    }

    //4th test to allow the "100" input
    public int sum(String input) {

        if (input == null) {
            return 0;
        }
        //5th case: plus signs need to be escaped.
        //we have an array of numbers
        String[] numbers = input.split("\\+");

        int sum = 0;
        for (String number : numbers) {
            try {
                sum += Integer.valueOf(number);
            } catch (NumberFormatException ignore) {
                // ignored and empty catch exception on purpose
            }
        }
        return sum;
    }

}