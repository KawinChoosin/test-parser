

import java.util.Map;

interface Expr{

    double eval(Map<String, Double> bindings) throws Exception;

}
