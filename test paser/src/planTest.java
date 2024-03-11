import org.junit.jupiter.api.Test;

import java.lang.invoke.SwitchPoint;
import java.net.SocketOption;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class planTest {
    @Test
    void testtoken () throws Exception {

        Map<String,Double> bind=new HashMap<>();
        String test = "s = 1+8 s=3  " +
                "z =s move up ";
//                "if(z) then move up else move down " +
//                "while(s){s = s-1} " +
//                "invest 90000 " +
//                "done";


        System.out.println(test);
        Tokenizer a=new ExprTokenizer(test);
        player crew=new player();
        plan newplan=new plan(a,crew);
        Expr newExpr=newplan.parse();
        newExpr.eval(bind);
        System.out.println();
        System.out.println(bind.get("s"));
        System.out.println(bind.get("z"));

    }
    @Test
    void moveCommandTest1() throws Exception {
        Map<String,Double> bind=new HashMap<>();
        String test = "move up";
        Tokenizer a=new ExprTokenizer(test);
        player crew=new player();
        crew.printcity();
        crew.printcoordinate();
        plan newplan=new plan(a,crew);
        Expr newExpr=newplan.parse();
        newExpr.eval(bind);
        crew.printcoordinate();
        crew.relocate();
        crew.printcity();
    }

    @Test
    void moveCommandTest2() throws Exception {
        Map<String,Double> bind=new HashMap<>();
        String test = "move upright";
        Tokenizer a=new ExprTokenizer(test);
        player crew=new player();
        crew.printcoordinate();
        plan newplan=new plan(a,crew);
        Expr newExpr=newplan.parse();
        newExpr.eval(bind);
        crew.printcoordinate();

    }

    @Test
    void IfTest() throws Exception {
        Map<String,Double> bind=new HashMap<>();
        String test = "if(2) then move up else {move down} done";
        Tokenizer a=new ExprTokenizer(test);
        player crew=new player();
        plan newplan=new plan(a,crew);
        crew.printcoordinate();
        Expr newExpr=newplan.parse();
        newExpr.eval(bind);
        crew.printcoordinate();
    }
@Test
    void WhileTest() throws Exception {
        Map<String,Double> bind=new HashMap<>();
        bind.put("t",5.00);
        String test = "x=3 while(t) t = t-1";
        Tokenizer a=new ExprTokenizer(test);
        player crew=new player();
        plan newplan=new plan(a,crew);
        Expr newExpr=newplan.parse();
        newExpr.eval(bind);
    System.out.println(bind.get("t"));
    System.out.println(bind.get("x"));


}
}