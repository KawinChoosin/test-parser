import java.util.HashMap;
import java.util.Map;

public class Nearby implements Expr {
    private player crew;
    private String direction;
    public Nearby(player crew, String direct) {
        this.crew=crew;
        this.direction=direct;
    }

    @Override
    public double eval(Map<String, Double> bindings) throws Exception {
        int newRow = crew.currentcurrow;
        int newCol = crew.currentcurcol;
        int distance=0;
        while(!region.checkOpponent(newRow,newCol,crew)){
            if (direction.equals("up")) {
                newRow--;
                distance++;
            } else if (direction.equals("down")) {
                newRow++;
                distance++;
            } else if (direction.equals("upleft")) {
                if (newCol % 2 == 0) {
                    newCol--;
                    distance++;
                } else {
                    newRow--;
                    newCol--;
                    distance++;
                }
            } else if (direction.equals("upright")) {
                if (newCol % 2 == 0) {
                    newCol++;
                    distance++;
                } else {
                    newRow--;
                    newCol++;
                    distance++;
                }
            } else if (direction.equals("downleft")) {
                if (newCol % 2 == 0) {
                    newCol--;
                    newRow++;
                    distance++;
                } else {
                    newCol--;
                    distance++;
                }
            } else if (direction.equals("downright")) {
                if (newCol % 2 == 0) {
                    newCol++;
                    newRow++;
                    distance++;
                } else {
                    newCol++;
                    distance++;
                }
            }
            if (newRow < 0 || newCol < 0 || newRow>=territory.m || newCol>=territory.n) {
                break;
            }

        }
        if(!region.checkOpponent(newRow,newCol,crew)){
            return 0;
        }else{
            double depofopponent=region.findregion(newRow,newCol).getdep();
            double result=100*distance+depofopponent;
            return result;
        }


    }

}
