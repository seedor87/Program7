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
         /*Exp e1 = new Constant (3);
         Exp e2 = new Constant (7);

         Eval intrp = new Eval();
         System.out.println (intrp.visit (new Product (e1,e2)));     // 3 * 7

         Exp x = new Identifier ("x");
         try {
         System.out.println (intrp.visit (new Product (x,e2)));      // x * 7
     }
         catch (Exception e)
             {  System.out.println ("Exception caught");  }

         Exp e3 = new Assign (x, e1);                            // x = 3
         System.out.println (intrp.visit (new Product (e3,e2)));      // (x=3) * 7*/
        
        Exp e1 = new Constant(3);
        Exp e2 = new Sum(e1,new Constant(2));
        Eval intrp = new Eval();
        // 3+2-2
//         Difference diff = new Difference (e2, new Constant (2));
        Difference diff = new Difference (e2, new Constant (2));
        System.out.println (intrp.visit (diff));
        
        Simplify simple = new Simplify();
        Exp e3 = simple.visit (diff);
        
        Exp e4 = new Sum (new Constant(2), new Constant(3));
        Equals eq = new Equals();
        System.out.println ("3+2 == 2+3: " + eq.visit (e2, e4));
        
        diff = new Difference (e2, e4);
        Exp e5 = simple.visit (diff);     
    }
}
        
