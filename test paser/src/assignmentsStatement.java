import java.util.Map;

public class assignmentsStatement implements Expr {
    private String value;
    private Expr results;
    public assignmentsStatement(String value, Expr result) {
        this.value=value;
        this.results=result;
    }
    @Override
    public double eval(Map<String, Double> bindings) throws Exception {
        Double realresult =results.eval(bindings);
        bindings.put(value,realresult);
        return 0.00;
    }

}
