import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class region {
    private double dep=0;
    protected int curcol;
    protected int currow;
    private double interest;
    private player own=null;

    private static List<region> allregion=new ArrayList<region>();


    public region(player crew) {
        curcol=crew.currentcurcol;
        currow=crew.currentcurrow;
        allregion.add(this);
        own=crew;
    }
    public region(int row,int col,player crew) {
        curcol=col;
        currow=row;
        allregion.add(this);
        own=crew;
    }

    public double collectmoney(double collect){
            if(dep<collect){
                allregion.remove(this);
                dep=0;
                return 0;
            }else{
                double result=collect;
                losedep(collect);

                if(dep==0){
                    allregion.remove(this);
                }
                return result;
            }
    }
    public void investmoney(double invest){

        if(dep+invest>territory.max_dep){
            dep=territory.max_dep;
        }else{
            dep+=invest;
        }

    }
    //invest function prepare in case max dep

    public static boolean checkOpponent(int newRow, int newCol, player crew) {
        for(region reg : allregion) {
            if (reg.curcol == newCol && reg.currow == newRow) {
                // Found the region at specified coordinates
                if (reg.own != null && reg.own != crew) {
                    // Region is owned by an opponent
                    return true;
                } else {
                    // Region is either unowned or owned by the crew
                    return false;
                }
            }
        }
        // No region found at specified coordinates
        return false;
    }

    public static boolean checkOpponent(player crew) {
        return checkOpponent(crew.currentcurrow,crew.currentcurcol,crew);
    }



    public static region findregion(int row, int col){
        for (region reg : allregion) {
            if (reg.curcol == col && reg.currow == row) {
                return reg;
            }
        }
        return null;
    }
    public player getown(){
        return own;
    }
    public void forbegindep(){
        dep=territory.init_center_dep;
    }

    public void calinterest(){
        if(dep+((territory.interest_pct/100)*dep)>territory.max_dep){
            dep=territory.interest_pct;
        }else{
            dep+=(territory.interest_pct/100)*dep;
        }
    }
    public double getdep(){
        return dep;
    }
    protected void loseown(){
        own = null;
    }
    protected void losedep(double cost){
        dep=dep-cost;
    }

}
