import org.junit.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ChangeCalculatorTestv2 {

    @Test
    public void testChangeThatCanBeGivenInASingleCoin() {
        ChangeCalculatorv2 ChangeCalculatorv2 = new ChangeCalculatorv2(asList(1, 5, 10, 25, 100));

        assertEquals(
                singletonList(25),
                ChangeCalculatorv2.computeMostEfficientChange(25));
    }

    
    @Test
    public void testChangeThatMustBeGivenInMultipleCoins() {
        ChangeCalculatorv2 ChangeCalculatorv2 = new ChangeCalculatorv2(asList(1, 5, 10, 25, 100));

        assertEquals(
                asList(5, 10),
                ChangeCalculatorv2.computeMostEfficientChange(15));
    }

    
    @Test
    // https://en.wikipedia.org/wiki/Change-making_problem#Greedy_method
    public void testLilliputianCurrency() {
        ChangeCalculatorv2 ChangeCalculatorv2 = new ChangeCalculatorv2(asList(1, 4, 15, 20, 50));

        assertEquals(
                asList(4, 4, 15),
                ChangeCalculatorv2.computeMostEfficientChange(23));
    }

    
    @Test
    // https://en.wikipedia.org/wiki/Change-making_problem#Greedy_method
    public void testLowerElbonianCurrency() {
        ChangeCalculatorv2 ChangeCalculatorv2 = new ChangeCalculatorv2(asList(1, 5, 10, 21, 25));

        assertEquals(
                asList(21, 21, 21),
                ChangeCalculatorv2.computeMostEfficientChange(63));
    }

     @Test
    public void testADAMTEST() {
        ChangeCalculatorv2 ChangeCalculatorv2 = new ChangeCalculatorv2(asList(1,3,4));

        assertEquals(
                asList(3,3),
                ChangeCalculatorv2.computeMostEfficientChange(6));
    }
    @Test
    public void testLargeAmountOfChange() {
        ChangeCalculatorv2 ChangeCalculatorv2 = new ChangeCalculatorv2(asList(1, 2, 5, 10, 20, 50, 100));

        assertEquals(
                asList(2, 2, 5, 20, 20, 50, 100, 100, 100, 100, 100, 100, 100, 100, 100),
                ChangeCalculatorv2.computeMostEfficientChange(999));
    }

    
    @Test
    // https://en.wikipedia.org/wiki/Change-making_problem#Greedy_method
    public void testPossibleChangeWithoutUnitCoinAvailable() {
        ChangeCalculatorv2 ChangeCalculatorv2 = new ChangeCalculatorv2(asList(2, 5, 10, 20, 50));

        assertEquals(
                asList(2, 2, 2, 5, 10),
                ChangeCalculatorv2.computeMostEfficientChange(21));
    }

    
    @Test
    // https://en.wikipedia.org/wiki/Change-making_problem#Greedy_method
    public void testAnotherPossibleChangeWithoutUnitCoinAvailable() {
        ChangeCalculatorv2 ChangeCalculatorv2 = new ChangeCalculatorv2(asList(4, 5));

        assertEquals(
                asList(4, 4, 4, 5, 5, 5),
                ChangeCalculatorv2.computeMostEfficientChange(27));
    }

    
    @Test
    public void testZeroChange() {
        ChangeCalculatorv2 ChangeCalculatorv2 = new ChangeCalculatorv2(asList(1, 5, 10, 21, 25));

        assertEquals(
                emptyList(),
                ChangeCalculatorv2.computeMostEfficientChange(0));
    }

    
    @Test
    public void testChangeLessThanSmallestCoinInCurrencyCannotBeRepresented() {
        ChangeCalculatorv2 ChangeCalculatorv2 = new ChangeCalculatorv2(asList(5, 10));

        IllegalArgumentException expected =
            assertThrows(
                IllegalArgumentException.class,
                () -> ChangeCalculatorv2.computeMostEfficientChange(3));

        assertThat(expected)
            .hasMessage("The total 3 cannot be represented in the given currency.");
    }

    
    @Test
    public void testChangeLargerThanAllCoinsInCurrencyThatCannotBeRepresented() {
        ChangeCalculatorv2 ChangeCalculatorv2 = new ChangeCalculatorv2(asList(5, 10));

        IllegalArgumentException expected =
            assertThrows(
                IllegalArgumentException.class,
                () -> ChangeCalculatorv2.computeMostEfficientChange(94));

        assertThat(expected)
            .hasMessage("The total 94 cannot be represented in the given currency.");
    }

    
    @Test
    public void testNegativeChangeIsRejected() {
        ChangeCalculatorv2 ChangeCalculatorv2 = new ChangeCalculatorv2(asList(1, 2, 5));

        IllegalArgumentException expected =
            assertThrows(
                IllegalArgumentException.class,
                () -> ChangeCalculatorv2.computeMostEfficientChange(-5));

        assertThat(expected)
            .hasMessage("Negative totals are not allowed.");
    }

}
