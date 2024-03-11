public class Direction {
    protected int curcol;
    protected int currow;
    protected Direction(int currow, int curcol){
        this.curcol=curcol;
        this.currow=currow;
    }

    public void print(){
        System.out.print(currow);
        System.out.print(",");
        System.out.print(curcol);
        System.out.println();
    }
}
