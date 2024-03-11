import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OpponentTest {

    @Test
    void opponentTest() throws Exception {
        player playerMain=new player(4,6);
        player playerup=new player(0,6);
        player playerupleft=new player(1,0);
        player playerdownleft=new player(6,2);
        player playerdown=new player(6,6);
        player playerdownright=new player(5,8);
        player playerupright=new player(3,8);
        Opponent testoppoent=new Opponent(playerMain);
        double result=testoppoent.eval(new HashMap<>());
        assertEquals(22,result);
    }

    @Test
    void opponentTestforonedirection() throws Exception {
        player playerMain=new player(4,6);
        player playerup=new player(0,6);
        player playerunknow=new player(3,5);
        Opponent testoppoent=new Opponent(playerMain);
        double result=testoppoent.eval(new HashMap<>());
        assertEquals(41,result);
    }
    @Test
    void opponentTestfornoone() throws Exception {
        player playerMain=new player(4,6);
        player playerunknow=new player(3,5);
        Opponent testoppoent=new Opponent(playerMain);
        double result=testoppoent.eval(new HashMap<>());
        assertEquals(0,result);
    }
}