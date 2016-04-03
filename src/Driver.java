import java.util.HashMap;
import java.util.Objects;

import java.util.*;
/**
 * Test the Expression interpreter.
 *  
 * Uses the Visitor pattern
 * 
 *    UNDER CONSTRUCTION
 *    Mar. 26:  Attempt using Generics... won't compile.
 * 
 * @author (sdb)
 * @version (Mar 2016)
 */

public class Driver {

    /*
    Table of variables and values
     */
    public static HashMap<Object, Object> table = new HashMap<Object, Object>();

    public static void main(String[] args) {


        Exp question;   // used for visual evaluation
        Eval intrp;

        print("---- Ex 1 ---");
        Exp e1 = new Constant (3);
        Exp e2 = new Constant (7);
        intrp = new Eval();
        print (intrp.visit (new Product (e1,e2)));     // 3 * 7

        print("---- Ex 2 ---");
        Exp x = new Identifier ("x");
        try {
            print (intrp.visit (new Product (x,e2)));      // x * 7
        }
        catch (Exception e) {
            print ("Exception caught");  }


        Exp e3 = new Assign (x, e1);                            // x = 3
        print (intrp.visit (new Product (e3,e2)));      // (x=3) * 7

        print("---- Ex 3 ---");
        e1 = new Constant(3);
        e2 = new Sum(e1,new Constant(2));
        intrp = new Eval();
        Difference diff = new Difference (e2, new Constant (2));
        print (intrp.visit (diff));
        
        Simplify simple = new Simplify();
        e3 = simple.visit (diff);
        print(e3);
        
        Exp e4 = new Sum (new Constant(2), new Constant(3));
        Equals eq = new Equals();
        print ("3+2 == 2+3: " + eq.visit (e2, e4));

        print("---- Ex 4 ---");
        diff = new Difference (e2, e4);
        Exp e5 = simple.visit (diff);
        print (diff.toString());
        print (e5.toString());

        print("---- Ex 5 ---");
        e1 = new Quotient(new Constant(5), new Constant(4));
        e2 = new Quotient(new Constant(5), new Constant(4));
        print ("5/4 == 5/4: " + eq.visit (e1, e2));
        e2 = new Quotient(new Constant(4), new Constant(5));
        print ("5/4 == 4/5: " + eq.visit (e1, e2));

        print("---- Ex 6 ---");
        e1 = new BooleanConst(true);
        e2 = new BooleanConst(false);
        question = new And(e1, e2);
        print (question + ": " + intrp.visit((And) question));

        print("---- Ex 7 ---");
        e2 = new Sum(new Constant(6), new Constant(2));
        e3 = new Product(new Constant(3), new Constant(3));
        question = new GreaterThan(e3, e2);
        print (question + ": " + intrp.visit((GreaterThan) question));

        print("---- Ex 8.1 ---");
        e2 = new Identifier("Y");
        e3 = new Assign(e2, new Constant(10));
        print(intrp.visit((Assign) e3));
        question = new AltMod(e2, new Constant(3));
        print(question + ": " + intrp.visit((AltMod) question));
        question = new AltMod(e2, new Constant(3));
        print(question + ": " + intrp.visit((AltMod) question));


        print("---- Ex 8.2 ---");
        e2 = new Identifier("Z");
        e3 = new Assign(e2, new Constant(2));
        print(intrp.visit((Assign) e3));
        for(int i = 0; i < 10; i++) {
            question = new AltMult(e2, new Constant(2));
            print(question + ": " + intrp.visit((AltMult) question));
        }


    }

    public static void print(Object o) {
          System.out.println(o.toString());
    }
}
        
