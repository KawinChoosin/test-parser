
import java.util.HashMap;
import java.util.Map;

import static com.sun.org.apache.xalan.internal.lib.ExsltMath.power;

public class BinaryArithExpr implements Expr {

    private Expr left;
    private String op;
    private Expr right;



     public BinaryArithExpr(Expr left,String op,Expr right) {
         this.left = left;
         this.op = op;
         this.right = right;
    }

    public double eval(Map<String, Double> bindings) throws Exception {
        double lv = left.eval(bindings);
        double rv = right.eval(bindings);
        if (op.equals("+")) return lv + rv;
        if (op.equals("*")) return lv * rv;
        if (op.equals("-")) return lv - rv;
        if (op.equals("/")){
            if(rv==0.00){
                throw new Exception("invalid case X/0");
            }else{
                return lv / rv;
            }
        }
        if (op.equals("%")) {
            if (rv == 0.00) {
                throw new Exception("invalid case X%0");
            } else {
                return lv % rv;
            }
        }
        if (op.equals("^")) return Math.pow(lv, rv);
        throw new Exception("you dont have any data");
    }
}
