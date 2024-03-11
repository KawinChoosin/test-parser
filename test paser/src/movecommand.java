import java.util.Map;

public class movecommand implements Expr {
    boolean status=false;
    private player crew;
    private String direction;
    protected movecommand(player crew, String direct){
        this.crew=crew;
        this.direction=direct;
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

        if (newRow < 0 || newCol < 0 || newRow>=territory.m || newCol>=territory.n) {
//            throw new IllegalArgumentException("Invalid move: Negative row or column value");
            return new Direction(crew.currentcurrow, crew.currentcurcol);
        }

        if(!region.checkOpponent(newRow,newCol,crew)){
            status=true;
            return new Direction(newRow, newCol);
        }else{
            return new Direction(crew.currentcurrow, crew.currentcurcol);
        }
    }


    @Override
    public double eval(Map<String, Double> bindings) throws Exception {
        crew.move(directionmove(),status);
        return 0;
    }

}