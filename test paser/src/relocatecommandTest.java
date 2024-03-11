import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class relocatecommandTest {
    @Test
    public void testrelocate() throws Exception {
        player test=new player();
        test.printcoordinate();
        test.printcity();
        movecommand move=new movecommand(test,"up");
        move.eval(new HashMap<>());
        test.printcoordinate();
        movecommand move2=new movecommand(test,"upleft");
        move2.eval(new HashMap<>());
        test.printcoordinate();
        test.printcity();
        test.relocate();
        test.printcity();
    }

}