import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class assignmentsStatementTest {

    @Test
    public void testEval_ExistingVariable() throws Exception {
        Map<String,Double> variable = new HashMap<>();
        Expr left = new ConstantExpr(5);
        Expr right = new ConstantExpr(10);
        BinaryArithExpr expr = new BinaryArithExpr(left, "+", right);
        assignmentsStatement test = new assignmentsStatement("x", expr);
        double result1 =test.eval(variable);
        System.out.println(result1);
        System.out.println(variable.get("x"));
    }




}
