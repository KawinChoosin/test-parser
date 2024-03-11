

import java.util.List;

public class plan implements Parser {
    private List<String> banword = List.of("collect", "done", "down", "downleft", "downright", "else", "if", "invest", "move", "nearby", "opponent", "relocate", "shoot", "then", "up", "upleft", "upright", "while");
//    protected static Map<String, Double> DoubleVariables = new HashMap<>();
    private Tokenizer tkz;
    private player crew;
    public plan(Tokenizer tkz,player newbie) {
        this.tkz = tkz;
        crew=newbie;
    }

    public Expr parse() throws Exception {
        return Plan();
    }

    //Plan → Statement+
    public Expr Plan() throws Exception {
//        List<Tokenizer> statement =new ExprTokenizer();
//        while(tkz.hasNextToken()){
//            while(!tkz.peek("/n")){statement.add();}
//        }

        return Statement();
    }

    //Statement → Command | BlockStatement | IfStatement | WhileStatement
    private Expr Statement() throws Exception {
        if(tkz.peek("if")){
            tkz.consume("if");
            tkz.consume("(");
            Expr result = parsrexpression();
            tkz.consume(")");
            tkz.consume("then");
            Expr Statement1 = Statement();
            tkz.consume("else");
            Expr Statement2 = Statement();
            return new ifstatement(result,Statement1,Statement2);
        }else if(tkz.peek("while")){
            tkz.consume("while");
            tkz.consume("(");
            Expr result = parsrexpression();
            tkz.consume(")");
            Expr Statement1 = Statement();
            return new whilestatement(result,Statement1);
        }else if(tkz.peek("{")){
            tkz.consume("{");
            Expr result = Statement();
            tkz.consume("}");
            return result;
        }else{
            return command();
        }
    }

    //Command → AssignmentStatement | ActionCommand
    private Expr command() throws Exception {
        String check=tkz.peek();
        if(!checkbanword(check)) {
            return assignmentsStatement();
        }else {
            return actioncommand();
        }
    }
    //AssignmentStatement → <identifier> = Expression
    private Expr assignmentsStatement() throws Exception {
        String value=tkz.consume();
        tkz.consume("=");
        Expr result = parsrexpression();
        return new assignmentsStatement(value,result);
    }

    //ActionCommand → done | relocate | MoveCommand | RegionCommand | AttackCommand
    //MoveCommand → move Direction
    //RegionCommand → invest Expression | collect Expression
    //AttackCommand → shoot Direction Expression
    private Expr actioncommand() throws Exception {
        if(tkz.peek("done")) {
            tkz.consume("done");
            donecommand result=new donecommand(crew);
            return result;
        }else if(tkz.peek("relocate ")){
            tkz.consume("relocate");
            return new relocatecommand(crew);
        }else if(tkz.peek("move")){
            tkz.consume("move");
            String direct = tkz.consume();
            return new movecommand(crew,direct);
        }else if(tkz.peek("shoot")) {
            tkz.consume("shoot");
            String direct = tkz.consume();
            Expr expression=parsrexpression();
            return new attackcommand (direct,expression,crew);
        }else{
            if(tkz.peek("")){
                tkz.consume("invest");
                Expr expression=parsrexpression();
                String invest="invest";
                return new regioncommand(invest,crew,expression) ;
            //same problem about expression after
            }else{
                tkz.consume("collect");
                Expr expression=parsrexpression();
                String collect="collect";
                return new regioncommand(collect,crew,expression) ;
                //same problem about expression after
            }
        }

    }

    //parseexpression use to calculate about only math
    Expr parsrexpression() throws Exception {
        Expr result = parseterm();
        while(tkz.peek("+")||tkz.peek("-")) {
            if (tkz.peek("+")) {
                tkz.consume();
                result=new BinaryArithExpr(result, "+", parseterm());
            } else if (tkz.peek("-")) {
                tkz.consume();
                result=new BinaryArithExpr(result, "-", parseterm());
            }
        }
        return result;
    }

    //Term → Term * Factor | Term / Factor | Term % Factor | Factor
    Expr parseterm() throws Exception {
        Expr result = parsefactor();
        while(tkz.peek("*")||tkz.peek("/")||tkz.peek("%")) {
            if (tkz.peek("*")) {
                tkz.consume("*");
                result=new BinaryArithExpr(result, "*", parsefactor());
            } else if (tkz.peek("/")) {
                tkz.consume("/");
                result=new BinaryArithExpr(result, "/", parsefactor());
            } else if (tkz.peek("%")) {
                tkz.consume("%");
                result=new BinaryArithExpr(result, "%", parsefactor());
            }
        }
        return result;
    }

    //Factor → Power ^ Factor | Power
    Expr parsefactor() throws Exception {
        Expr result = parsepower();
        while(tkz.peek("^")) {
            tkz.consume("^");
            new BinaryArithExpr(parsepower(), "^", result);
        }
        return result;
    }

    //Power → <number> | <identifier> | ( Expression ) | InfoExpression
    private Expr parsepower() throws Exception {
        if (isNumber(tkz.peek())) {
            return new DoubleLit(Double.parseDouble(tkz.consume()));

        } else if(tkz.peek("nearby")) {
            tkz.consume("nearby");
            String direct = tkz.consume();
            return new Nearby(crew,direct);
            //not yet
        } else if(tkz.peek("opponent")) {
            tkz.consume("opponent");
            return new Opponent(crew);
            //not yet
        } else if (isVariable(tkz.peek()) && !checkbanword(tkz.peek())) {
            String value=tkz.consume();
            return new Variable(value);

        }else{
            tkz.consume("(");
            Expr result = parsrexpression();
            tkz.consume(")");
            return result;
        }
    }


    private boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private boolean isVariable(String str) {
        // Implement your logic to check if the string is a variable
        // For example, you might want to check if it starts with a letter
        return Character.isLetter(str.charAt(0));
    }

    private boolean checkbanword(String check) {
        for(String word:banword){
            if(check.equals(word)){
                return true;
            }
        }
        return false;
    }

}
