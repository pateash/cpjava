package live.ashish.codejava.libs;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Utils {

    private static final String SCRIPT_ENGINE="javascript";

    /* this function evaluates a string using javascript engine, syntax of expression shoule be  must be javascript*/
    public static String evaluateExpression(String expression) throws ScriptException {
        ScriptEngine engine=new ScriptEngineManager().getEngineByName(SCRIPT_ENGINE);
          return engine.eval(expression).toString();
    }
}
