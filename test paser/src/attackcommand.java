import java.util.Map;

public class attackcommand implements Expr {
    private String direction;
    private Expr expression;
    private player crew;

    public attackcommand(String move, Expr expression, player crew) {
        this.direction=move;
        this.expression=expression;
        this.crew=crew;
    }

    public Direction directionmove() {
        int newRow = crew.currentcurrow;
        int newCol = crew.currentcurcol;

        if (direction.equals("up")) {
            newRow--;
        } else if (direction.equals("down")) {
            newRow++;
        } else if (direction.equals("upleft")) {
            if (crew.currentcurcol % 2 == 0) {
                newCol--;
            } else {
                newCol--;
                newRow--;
            }
        } else if (direction.equals("upright")) {
            if (crew.currentcurcol % 2 == 0) {
                newCol++;
            } else {
                newRow--;
                newCol++;
            }
        } else if (direction.equals("downleft")) {
            if (crew.currentcurcol % 2 == 0) {
                newCol--;
                newRow++;
            } else {
                newCol--;
            }
        } else if (direction.equals("downright")) {
            if (crew.currentcurcol % 2 == 0) {
                newCol++;
                newRow++;
            } else {
                newCol++;
            }
        } else {
            throw new IllegalArgumentException("Invalid direction: " + direction);
        }
//
//        if (newRow < 0 || newCol < 0 || newRow>=territory.m || newCol>=territory.n) {
//            throw new IllegalArgumentException("Invalid move: Negative row or column value");
//        }
        return new Direction(newRow, newCol);
    }

    @Override
    public double eval(Map<String, Double> bindings) throws Exception {
        double cost=expression.eval(bindings);
        Direction value=directionmove();
        double budget= crew.getBudget();
        if(budget>=cost+1){
            if(region.checkOpponent(value.currow,value.curcol,crew)){
                region a=region.findregion(value.currow,value.curcol);
                double DepIn_a=a.getdep();
                if(DepIn_a>cost){
                    a.losedep(cost);
                    crew.costattack(cost+1);
                }else{
                    a.losedep(DepIn_a);
                    a.loseown();
                    crew.costattack(cost+1);
                }
            }else{
                region a=region.findregion(value.currow,value.curcol);
                if(a.getown().equals(null)){
                    crew.costattack(cost+1);
                }else{
                    crew.costattack(cost+1);
                    a.loseown();
                }
            }
        }
        return 0;
    }
}
