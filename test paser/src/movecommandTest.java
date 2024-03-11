import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class movecommandTest {


    @Test
    void testdirection(){
        String direct[]={"up","down","upleft","upright","downleft","downright"};
        player newplayer=new player();
        newplayer.printcoordinate();
        for(int i=0;i<6;i++){
            movecommand newmove=new movecommand(newplayer,direct[i]);
            newmove.directionmove().print();
        }
    }
}