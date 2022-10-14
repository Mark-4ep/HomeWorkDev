import org.example.SumCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SumCalculatorTest {

    private SumCalculator sum;

   @BeforeEach
    public void beforeEach() {
      sum = new SumCalculator();
    }

    @Test
    public void testThatSumCalculatorMethodWorksOkForOne() {

            //When
            int actual = sum.sumCalculator(1);

            //Then
            int expected = 1;
            Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testThatSumCalculatorMethodWorksOkForThree() {
        //When
        int actual = sum.sumCalculator(3);

        //Then
        int expected = 6;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testThatSumCalculatorMethodWorksOkForZero() {

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                sum.sumCalculator(0);
            });
            assertEquals("The number must be greater than 0", exception.getMessage());
    }
}
