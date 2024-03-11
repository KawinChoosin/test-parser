import java.util.Map;

public class Variable implements Expr {
    private String value;
    public Variable(String value){
        this.value=value;
    }


    @Override
    public double eval(Map<String, Double> bindings) throws Exception {
        if(bindings.containsKey(value)){
            return bindings.get(value);
        }else{
            bindings.put(value,0.00);
            return bindings.get(value);
        }
    }
}
