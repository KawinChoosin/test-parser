import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class NearbyTest {
    @Test
    void nearbyTest() throws Exception {
        String[] direction= new String[]{"up","upleft","downleft","down","downright","upright"};
        Double[] ans={550.00,750.00,550.00,350.00,350.00,350.00};
        player playerMain=new player(4,6);
        player playerup=new player(0,6);
        player playerupleft=new player(1,0);
        player playerdownleft=new player(6,2);
        player playerdown=new player(6,6);
        player playerdownright=new player(5,8);
        player playerupright=new player(3,8);
        for(int i=0;i<6;i++){
            Nearby testNB=new Nearby(playerMain,direction[i]);
            Double result=testNB.eval(new HashMap<>());
            assertEquals(ans[i],result);
        }
    }
}