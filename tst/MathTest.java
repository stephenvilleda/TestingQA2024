import org.junit.Assert;
import org.junit.Test;
public class MathTest {

    @Test
    public void testAddtion() {
        int x = 10;
        int y = 100;
        Assert.assertEquals(110, x + y); // expected vs actual

        // DO NEGATIVE NUMBERS WORK??
        //int a = -10;
        //int b = -20;
        //Assert.assertEquals(-30, a + b);

        // CAN YOU ADD A VALUE MORE THAN THE MAXIMUM VALUE OF AN INTEGER?
        //int i = Integer.MAX_VALUE;
        //int j = 1;
        //Assert.assertEquals(0, i+j);
    }

    @Test
    public void testFoatingPointNumber() {
        double x = 6.12;
        double y = 3.08;
        Assert.assertEquals(3.04, x - y, 0.001);

    }
}
