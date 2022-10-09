import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Arrays;

public class CalculatorTest {
    @Test
    public void testInfixToPostFix() {
        Calculator j = new Calculator();
        assertEquals("[1234, 567, 8, *, +, 90, -]", Arrays.toString(j.toPostFix("1234+567*8-90")));
    }
    @Test
    public void testCalculate() {
        Calculator j = new Calculator();
        assertEquals(5680, j.calculate("1234+567*8-90"));
    }
    @Test
    public void testIsValid(){
        Calculator j = new Calculator();

        String testCase ="1+1";
        assertTrue(j.isValid(testCase));

        testCase ="(2-1)+1";
        assertTrue(j.isValid(testCase));

        testCase ="+2-1";
        assertFalse(j.isValid(testCase));

        testCase ="(2+1";
        assertFalse(j.isValid(testCase));

        testCase ="2+1)";
        assertFalse(j.isValid(testCase));

//        testCase = new String[] {"(","2","-","1",")","+","1"};//(2-1)+1
//        assertTrue(j.isValid(testCase));
//
//        testCase = new String[] {"+","2","-","1"};
//        assertFalse(j.isValid(testCase));
//
//        testCase = new String[] {"(","2","+","1"};
//        assertFalse(j.isValid(testCase));
//
//        testCase = new String[] {"2","+","1",")"};
//        assertFalse(j.isValid(testCase));
    }
}
