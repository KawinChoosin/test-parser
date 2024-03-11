import java.util.HashMap;
import java.util.Map;

public class ConstantExpr implements Expr {
    private int value;

    public ConstantExpr(int value) {
        this.value = value;
    }


    @Override
    public double eval(Map<String, Double> bindings) throws Exception {
        return value;
    }


}
