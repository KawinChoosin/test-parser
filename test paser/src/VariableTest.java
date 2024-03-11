import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class VariableTest {

    @Test
    public void checkvariable() throws Exception {
        Map<String,Double> variable =new HashMap<>();
        variable.put("x",10.00);
        variable.put("y",100.00);
        variable.put("z",1000.00);
        String test="i";
        Variable testx=new Variable(test);
        double result=testx.eval(variable);
        System.out.println(result);
    }

}