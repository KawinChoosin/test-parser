import java.util.*;

public class Opponent implements Expr {

    private player crew;

    public Opponent(player crew) {
        this.crew = crew;
    }


    @Override
    public double eval(Map<String, Double> bindings) throws Exception {
        int newRow = crew.currentcurrow;
        int newCol = crew.currentcurcol;
        Direction up = new Direction(newRow, newCol);
        Direction down = new Direction(newRow, newCol);
        Direction upleft = new Direction(newRow, newCol);
        Direction upright = new Direction(newRow, newCol);
        Direction downleft = new Direction(newRow, newCol);
        Direction downright = new Direction(newRow, newCol);
        int distance = 0;
        String direction[] = {"1", "2", "3", "4", "5", "6"};
        while (isValidPosition(up.currow, up.curcol) || isValidPosition(down.currow, down.curcol) ||
                isValidPosition(upleft.currow, upleft.curcol) || isValidPosition(upright.currow, upright.curcol) ||
                isValidPosition(downleft.currow, downleft.curcol) || isValidPosition(downright.currow, downright.curcol)) {
            distance++;
            for (int j = 0; j < 6; j++) {
                if (direction[j].equals("1")) {
                    if (!region.checkOpponent(up.currow, up.curcol, crew)) {
                        up.currow--;
                    } else {
                        double resultDouble = (distance-1)*10+j+1;
                        return resultDouble;
                    }
                } else if (direction[j].equals("4")) {
                    if (!region.checkOpponent(down.currow, down.curcol, crew)) {
                        down.currow++;
                    } else {
                        double resultDouble = (distance-1)*10+j+1;
                        return resultDouble;
                    }

                } else if (direction[j].equals("6")) {
                    if (!region.checkOpponent(upleft.currow, upleft.curcol, crew)) {
                        if (upleft.curcol % 2 == 0) {
                            upleft.curcol--;
                        } else {
                            upleft.currow--;
                            upleft.currow--;
                        }
                    } else {
                        double resultDouble = (distance-1)*10+j+1;
                        return resultDouble;
                    }

                } else if (direction[j].equals("2")) {
                    if (!region.checkOpponent(upright.currow, upright.curcol, crew)) {
                        if (upright.curcol % 2 == 0) {
                            upright.curcol++;
                        } else {
                            upright.currow--;
                            upright.curcol++;
                        }
                    } else {
                        double resultDouble = (distance-1)*10+j+1;
                        return resultDouble;
                    }

                } else if (direction[j].equals("5")) {
                    if (!region.checkOpponent(downleft.currow, downleft.curcol, crew)) {
                        if (crew.currentcurcol % 2 == 0) {
                            downleft.curcol--;
                            downleft.currow++;
                        } else {
                            downleft.curcol--;
                        }
                    } else {
                        double resultDouble = (distance-1)*10+j+1;
                        return resultDouble;
                    }

                } else if (direction[j].equals("3")) {
                    if (!region.checkOpponent(downright.currow, downright.curcol, crew)) {
                        if (downright.curcol % 2 == 0) {
                            downright.curcol++;
                            downright.currow++;
                        } else {
                            downright.curcol++;
                        }
                    } else {
                        double resultDouble = (distance-1)*10+j+1;
                        return resultDouble;
                    }

                }
            }

        }
        return 0;
    }


    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < territory.m && col >= 0 && col < territory.n;
    }
}









