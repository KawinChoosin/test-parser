import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class regioncommandTest {
    @Test
    public void testCollectCommand() throws Exception {
        // Create a player for testing
        player playerTest = new player(0, 0);
        region testRegion =new region(playerTest);
        System.out.println(testRegion.getown());
        testRegion.forbegindep();
        System.out.println(testRegion.getdep());
        System.out.println(playerTest.getBudget());
        Map<String, Double> bindings = new HashMap<>();
        Expr collectExpr = new ConstantExpr(60);
        regioncommand collectCommand = new regioncommand("collect", playerTest, collectExpr);
        System.out.println("budget before collect"+playerTest.getBudget());
        System.out.println("deposit before collect"+testRegion.getdep());
        System.out.println("collect : "+collectExpr.eval(bindings));
        collectCommand.eval(bindings);
        System.out.println("budget after collect"+playerTest.getBudget());
        System.out.println("deposit after collect"+testRegion.getdep());

    }//result deposit is fault


    @Test
    public void testinvest() throws Exception {
        player playerTest = new player(0, 0);
        region testRegion =new region(playerTest);
        System.out.println(testRegion.getown());
        testRegion.forbegindep();
        System.out.println(testRegion.getdep());
        System.out.println(playerTest.getBudget());
        Map<String, Double> bindings = new HashMap<>();
        Expr collectExpr = new ConstantExpr(60);
        regioncommand investCommand = new regioncommand("invest", playerTest, collectExpr);
        System.out.println("budget before invest"+playerTest.getBudget());
        System.out.println("deposit before invest"+testRegion.getdep());
        System.out.println("invest : "+collectExpr.eval(bindings));
        investCommand.eval(bindings);
        System.out.println("budget after invest"+playerTest.getBudget());
        System.out.println("deposit after invest"+testRegion.getdep());

    }//result deposit is fault

    @Test
    public void testfindregion() {
        player playerTest = new player(0, 0);
        region findreg=playerTest.getCenter();
        System.out.print(findreg.curcol);
        System.out.print(findreg.currow);
        region testfindregion=region.findregion(0,0);
        System.out.println(testfindregion);
    }
}