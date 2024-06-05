package live.ashish.cpjava.interview;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MatherTest {
    Mather mather=new Mather();

    @Test
    public void testSum() {
        assertEquals(30, mather.sum(10, 20));
    }

    @Test(expected = ArithmeticException.class)
    public void testDevideByZero() {
        mather.divide(10,0);
    }

    @Test()
    public void testDivide() {
        assertEquals(2.0d,mather.divide(10,5), 0.01d);
    }

    @Test
    public void multiple() {
        assertEquals(6, mather.multiple(2,3));
    }
}
