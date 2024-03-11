import java.util.Map;

public class DoubleLit implements Expr {
    private double value;

    public DoubleLit(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public double eval(Map<String, Double> variables) {
        // Since DoubleLit represents a literal value, just return the value itself.
        return value;
    }


}
