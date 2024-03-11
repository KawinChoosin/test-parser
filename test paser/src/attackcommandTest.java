import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class attackcommandTest {
    @Test
    void testattack() throws Exception {
        ConstantExpr result=new ConstantExpr(500);
        player mainplayer=new player(4,4);
        player opponent=new player(3,4);

        System.out.println("player budget "+mainplayer.getBudget());
        System.out.println("opponent region budget "+opponent.getCenter().getdep());
        System.out.println("region own "+opponent.getCenter().getown());
        attackcommand testattack=new attackcommand("up",result,mainplayer);
        testattack.eval(new HashMap<>());
        System.out.println("player budget "+mainplayer.getBudget());
        System.out.println("opponent region budget "+opponent.getCenter().getdep());
        System.out.println("region own "+opponent.getCenter().getown());
    }



}