package live.ashish.codejava.libs;

import org.junit.Assert;
import org.junit.Test;

import javax.script.ScriptException;

public class UtilsTest {

    @Test
    public void evaluate() throws ScriptException {
        String expression = "2*3+4";
        Assert.assertEquals(10, Integer.parseInt(Utils.evaluateExpression(expression)));

        expression="3+(8-7.5)*10/5-(2+5*7)";
        Assert.assertEquals(-33.0, Double.parseDouble(Utils.evaluateExpression(expression)),0.0000);

    }

    @Test
    public void evaluateInvalidScript() throws ScriptException {
        //invalid expression
        String expression = "2+-3";
        Assert.assertEquals(-1,Integer.parseInt(Utils.evaluateExpression(expression)), 0.0000);
    }
}
