import org.junit.Assert;
import org.junit.Test;

import javax.script.ScriptException;

import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void evaluate() throws ScriptException {
        String expression = "2*3+4";
        Assert.assertEquals(10, (long)Utils.evaluateExpression(expression));
    }
}