import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static com.sun.org.apache.xalan.internal.lib.ExsltMath.power;

public class BinaryArithExprTest {
    Map<String,Double> variable = new HashMap<>();
    @Test
    void testwithvariableExprleft() throws Exception {
        Variable result=new Variable("t");
        Expr left = result;
        Expr right = new ConstantExpr(10);
        BinaryArithExpr expr = new BinaryArithExpr(left, "+", right);
        Double ans=expr.eval(variable);
        assertEquals(10.00, ans);
    }
    @Test
    void testwithvariableExprright() throws Exception {
        Variable result=new Variable("t");
        Expr right = result;
        Expr left = new ConstantExpr(11);
        BinaryArithExpr expr = new BinaryArithExpr(left, "+", right);
        Double ans=expr.eval(variable);
        assertEquals(11.00, ans);
    }
    @Test
    void testwithvariableExprrightandleft() throws Exception {
        Variable result=new Variable("t");
        Variable result2=new Variable("y");
        Expr right = result;
        Expr left =result2;
        BinaryArithExpr expr = new BinaryArithExpr(left, "+", right);
        Double ans=expr.eval(variable);
        assertEquals(0.00, ans);
    }
    @Test
    void testEval_Addition() throws Exception {
        Expr left = new ConstantExpr(5);
        Expr right = new ConstantExpr(10);
        BinaryArithExpr expr = new BinaryArithExpr(left, "+", right);
        Double ans=expr.eval(variable);
        assertEquals(15, ans);
    }

    @Test
    void testEval_DivisionByZero() throws Exception {
        Expr left = new ConstantExpr(10);
        Expr right = new ConstantExpr(0);
        BinaryArithExpr expr = new BinaryArithExpr(left, "/", right);
        assertThrows(Exception.class, () -> {
            expr.eval(variable);
        });
    }

    @Test
    void testEval_ModByZero() throws Exception {
        Expr left = new ConstantExpr(10);
        Expr right = new ConstantExpr(0);
        BinaryArithExpr expr = new BinaryArithExpr(left, "%", right);
        assertThrows(Exception.class, () -> {
            expr.eval(variable);
        });
    }
    @Test
    void testEval_power() throws Exception {
        Expr left = new ConstantExpr(4);
        Expr right = new ConstantExpr(2);
        BinaryArithExpr expr = new BinaryArithExpr(left, "^", right);
        Double ans=expr.eval(variable);
        assertEquals(16.0, ans);
    }

    @Test
    void testEval_powerwithvariable() throws Exception {
        variable.put("t",5.00);
        Variable result2=new Variable("t");
        Expr right = result2;
        Expr left = new ConstantExpr(4);
        BinaryArithExpr expr = new BinaryArithExpr(left, "^", right);
        Double ans=expr.eval(variable);
        assertEquals(1024.0, ans);
    }

}
