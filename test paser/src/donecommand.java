import java.util.Map;

public class donecommand implements Expr {
    private player crew;
    protected donecommand(player crew){
        this.crew=crew;

    }

    @Override
    public double eval(Map<String, Double> bindings) throws Exception {
       endturn last=new endturn();
       return 0;
    }
}


