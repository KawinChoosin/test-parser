import java.util.Map;

public class relocatecommand implements Expr {
    player crew;

    protected relocatecommand(player crew){
        this.crew=crew;
    }

    @Override
    public double eval(Map<String, Double> bindings) throws Exception {
        crew.relocate();
        return 0;
    }
}