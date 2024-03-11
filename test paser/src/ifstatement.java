import java.util.Map;

public class ifstatement implements Expr {
    Expr condition;
    Expr statement1;
    Expr statement2;
    public ifstatement(Expr condition, Expr statement1, Expr statement2) {
        this.condition=condition;
        this.statement1=statement1;
        this.statement2=statement2;
    }

    @Override
    public double eval(Map<String, Double> bindings) throws Exception {
        if(condition.eval(bindings) >0){
            statement1.eval(bindings);
        }else{
            statement2.eval(bindings);
        }
        return 0;
    }
}
