import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SummationTest {
    private static Summation programTest;

    @BeforeAll
    public static void setup(){
        programTest = new Summation();
    }

    @Test
    public void SummTestZeroOfThen_1(){
        // set data test
        int numberFirst = 0;
        int numberSecond = 3;

        // get actual program
        int result = programTest.sum(numberFirst, numberSecond);
        assertEquals(3, result);
    }

    @Test
    public void SummTestZeroOfThen_2(){
        // set data test
        int numberFirst = 5;
        int numberSecond = 0;

        // get actual program
        int result = programTest.sum(numberFirst, numberSecond);
        assertEquals(5, result);
    }

    @Test
    public void SummTestZeroBothOfThen(){
        // set data test
        int numberFirst = 0;
        int numberSecond = 0;

        // get actual program
        int result = programTest.sum(numberFirst, numberSecond);
        assertEquals(0, result);
    }

    @Test
    public void SummTestPositive(){
        // set data test
        int numberFirst = 10;
        int numberSecond = 8;

        // get actual program
        int result = programTest.sum(numberFirst, numberSecond);
        assertEquals(18, result);
    }

    @Test
    public void SummTestNegative(){
        // set data test
        int numberFirst = -10;
        int numberSecond = -8;

        // get actual program
        int result = programTest.sum(numberFirst, numberSecond);
        assertEquals(-18, result);
    }

    @Test
    public void SummTestNegativeOneOfThem_1(){
        // set data test
        int numberFirst = -10;
        int numberSecond = 8;

        // get actual program
        int result = programTest.sum(numberFirst, numberSecond);
        assertEquals(-2, result);
    }

    @Test
    public void SummTestNegativeOneOfThem_2(){
        // set data test
        int numberFirst = 10;
        int numberSecond = -8;

        // get actual program
        int result = programTest.sum(numberFirst, numberSecond);
        assertEquals(2, result);
    }

}
