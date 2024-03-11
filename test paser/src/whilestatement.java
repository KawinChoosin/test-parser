import java.util.Map;

public class whilestatement implements Expr {
    private Expr result;
    private Expr statement;
    public whilestatement(Expr result, Expr statement) {
        this.result=result;
        this.statement=statement;

    }


    @Override
    public double eval(Map<String, Double> bindings) throws Exception {
        while (result.eval(bindings) >0){
            statement.eval(bindings);
        }
        return 0;
    }

}
