import static org.junit.Assert.assertEquals;
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
}
