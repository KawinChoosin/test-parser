import java.util.ArrayList;
import java.util.List;

public class player {
    private double budget;
    private int turn;
    private boolean done = false;

    protected int currentcurcol;
    protected int currentcurrow;
    private region center;
    private List<region> own=new ArrayList<>();

    protected player(){
        int randomcol = (int) (Math.random() * territory.m);
        int randomrow = (int) (Math.random() * territory.n);
        currentcurcol=randomcol;
        currentcurrow=randomrow;
        budget=territory.init_budget;
        center=new region(this);
        center.forbegindep();
        this.own.add(center);

    }
    public player(int row,int col){
        int randomcol =col ;
        int randomrow =row ;
        currentcurcol=randomcol;
        currentcurrow=randomrow;
        budget=territory.init_budget;
        center=new region(this);
        center.forbegindep();
        this.own.add(center);
    }


    protected void increasebudget(region a,double result)
    {
        if(budget>=1){
            budget--;
            double collect=a.collectmoney(result);
            budget+=collect;
            if(a.getown().equals(null)){
                own.remove(a);
            }
        }else{
            endturn.endturn(this);
        }

    }
    protected void investbudget(region a,double result)
    {
        if(result+1<=budget){
            if(!region.checkOpponent(this)&&a.getown().equals(null)){
                own.add(a);
            }
            a.investmoney(result);
            budget-=result+1;
        }else{
            budget=0;
        }

    }

    protected void relocate(){
        int distance=Math.abs(currentcurcol- center.curcol)+Math.abs(currentcurrow- center.currow);
        int cost=distance*5+10;
        if(budget>=cost && !region.checkOpponent(this)){
            center.curcol=currentcurcol;
            center.currow=currentcurrow;
        }

    }

    protected void move(Direction direct,boolean status){
        if(status==true){
            budget--;
        }
        currentcurcol=direct.curcol;
        currentcurrow=direct.currow;
    }

    protected void printcoordinate(){
        System.out.print("row : "+currentcurrow);
        System.out.print(" ");
        System.out.print("col : "+currentcurcol);
        System.out.println();
    }
    protected void printcity(){
        System.out.print("city row : "+center.currow);
        System.out.print(" ");
        System.out.print("city col : "+center.curcol);
        System.out.println();
    }

    protected Double getBudget() {return budget;}
    protected void costattack(double cost) {budget-=cost;}
    protected region getCenter() {return center;}


}
