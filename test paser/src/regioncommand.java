import java.util.Map;

public class regioncommand implements Expr {
    private String word;
    private player crew;
    private Expr expression;
    public regioncommand(String word, player crew, Expr expression) {
        this.word=word;
        this.crew=crew;
        this.expression=expression;
    }


    @Override
    public double eval(Map<String, Double> bindings) throws Exception {
        region nowregion=region.findregion(crew.currentcurcol,crew.currentcurrow);
        if(word.equals("collect")) {
            if(nowregion!=null&&!region.checkOpponent(crew)){
                double value=expression.eval(bindings);
                crew.increasebudget(nowregion,value);
                return 0;
            }
        }else if(word.equals("invest")){
            if(nowregion!=null&&!region.checkOpponent(crew)){
                double value=expression.eval(bindings);
                crew.investbudget(nowregion,value);
            }
        }
        return 0;
    }
}