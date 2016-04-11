import javax.swing.text.StyledEditorKit;
import java.util.HashMap;
import java.util.Objects;

import java.util.*;
/**
 * Test the Expression interpreter.
 *  
 * Uses the Visitor pattern
 *
 * This class test all of the features of our expression verification methods.
 * Each test is denoted by a cooresponding example header and can be found in the source code under the print header.
 * These tests include all of the integer expression evalutions, string variable and string assignment operations, boolean cosntant expressions and the two problems required by the assignemnt as posted.
 *
 * We have cohsen to implement the recursive operations (+=) using ?+. These have been accomplished and are recorded in examples 8.1 and 8.2
 *
 * Note this class can be used on both the in-class mini-java tool, the out of class required  homework problems, and our own personal language
 * 
 * @author Bob S. and Bill C.
 * @version (April 2016)
 */

public class Driver {

    public static void main(String[] args) {


        Exp question;   // used for visual evaluation
        Eval intrp;

        print("---- Ex 1 ---");
        Exp e1 = new Constant (3);
        Exp e2 = new Constant (7);
        intrp = new Eval();
        print (intrp.visit (new Sum (e1,e2)));     // 3 + 7

        print("---- Ex 2 ---");
        Exp x = new Identifier ("x");
        try {
            print (intrp.visit (new Product (x,e2)));      // x * 7
        }
        catch (Exception e) {
            print ("Exception caught");
        }
        Exp e3 = new Assign (x, e1);                    // x = 3
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
        print ("3+2 == 2+3: " + eq.visit (e2, e4));         // 3 + 2 == 3 + 2

        print("---- Ex 4 ---");
        diff = new Difference (e2, e4);
        Exp e5 = simple.visit (diff);
        print (diff.toString());
        print (e5.toString());                              //simplify : 0

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
        print (question + ": " + intrp.visit((And) question));  // true and false yields false

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

        print("---- Ex 9 ----");
        e1 = new Identifier("b");
        e3 = new Assign(e1, new StringConst("ABSCDEFGHIJKLMNOPQRSTUVWXYZ"));
        print(intrp.visit((Assign) e3));

        print("---- Ex 10 ----");
        e1 = new Identifier("b");
        e2 = new Assign(e1, new StringConst("ABSCDEFGHIJ"));
        print(intrp.visit((Assign) e2));
        question = new AltPlus(e2, new StringConst("KLMNOPQRSTUVWXYZ"));
        print(question + ": " + intrp.visit((AltPlus) question));

        print("---- Ex 11 ----");
        e1 = new Identifier("c");
        e2 = new Assign(e1, new Sum(new StringConst("He"), new Sum(new StringConst("llo "), new StringConst("World!"))));
        print(e2 + ": " + intrp.visit((Assign) e2));

        print("---- Problem 1 ----");
        Identifier a = new Identifier ("a");
        Assign assign = new Assign (a, new Constant(17));       // a = 17
        intrp = new Eval();                                     // A Visitor which evaluates an expression tree
        try  {
            print(intrp.visit (a));
        }          // An Exception is thrown
        catch (Exception e) {
            print( e);
        }
        print(intrp.visit (assign));
        print(intrp.visit (a));                      // No Exception is thrown

        print("---- Problem 2 ----");
        toString toStr = new toString();
        a = new Identifier("a");
        print(toStr.visit(a));
        Exp b = new Identifier("b");
        print(toStr.visit(b));
        Exp e = new Identifier("e");
        Exp sum = new Sum(b, new Constant(2));  // b + 2
        print(toStr.visit(sum));
        Exp quo = new Quotient(a, sum);     // a / b + 2
        print(toStr.visit(quo));
        question = new Assign(e, quo);     // e =  a / (b+2)
        print(toStr.visit(question));

        print("---- Ex 12 ----");
        Sum p = new Sum(new Constant(5), new BooleanConst(false));
        intrp = new Eval();                                     // A Visitor which evaluates an expression tree
        try  {
            print(intrp.visit (p));
        }          // An Exception is thrown
        catch (Exception ex) {
            print(ex);
        }

        print("---- Ex 13 ----");
        Or q = new Or(new Constant(5), new BooleanConst(false));
        intrp = new Eval();                                     // A Visitor which evaluates an expression tree
        try  {
            print(intrp.visit (q));
        }          // An Exception is thrown
        catch (Exception ex) {
            print(ex);
        }

        print("---- Ex 14 ----");
        AltLessThan r = new AltLessThan(new Constant(5), new StringConst("hello"));
        intrp = new Eval();                                     // A Visitor which evaluates an expression tree
        try  {
            print(intrp.visit (r));
        }          // An Exception is thrown
        catch (Exception ex) {
            print(ex);
        }

        print("---- Ex 15 ----");
        Constant five = new Constant(5);
        BooleanConst True = new BooleanConst(true);
        StringConst me = new StringConst("me");
        BooleanEquals equivalence1 =new BooleanEquals(five, True);
        try  {
            print(intrp.visit (equivalence1));
        }          // An Exception is thrown
        catch (Exception ex) {
            print(ex);
        }
        BooleanEquals equivalence2 = new BooleanEquals(True, me);
        try {
            print(intrp.visit (equivalence2));
        }           // An Exception is thrown
        catch (Exception ex) {
            print(ex);
        }
        print(new BooleanEquals(me, me) + ": " + intrp.visit(new BooleanEquals(me, me)));
    }

    public static void print(Object o) {
          System.out.println(o.toString());
    }
}
        
