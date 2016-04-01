/**
 * Task:  Simplify an arithmetic expression if possible.
 * Implements Visitor.
 * Uses the Visitor pattern.
 * (This is what the optimization phase of a compiler would do)
 * 
 * @author (sdb)
 * @version (Mar 2016)
 */
public class Simplify implements Visitor {
    
    public Exp visit (Sum n) {
         return  new Sum ((Exp)n.left.accept(this), (Exp)n.right.accept(this));
     }
     
    public Exp visit (Difference n) {
        Equals eq = new Equals();
         Exp result =  new Difference((Exp)n.left.accept(this), (Exp)n.right.accept(this));
         if ((Boolean) eq.visit (result.left, result.right))        // x-x = 0
                return new Constant(0);
         return result;
    }
    
     public Exp visit (Product n) {
         Exp result = new Product ((Exp)n.left.accept(this), (Exp)n.right.accept(this));
         return result;
     }

     public Exp visit (Quotient n) {
         Exp result = new Quotient((Exp) n.left.accept(this), (Exp) n.right.accept(this));
         return result;
     }

    public Exp visit (Mod n) {
        Exp result = new Mod((Exp) n.left.accept(this), (Exp) n.right.accept(this));
        return result;
    }

    public Exp visit (Assign n) {
        Exp result = new Assign((Exp) n.left.accept(this), (Exp) n.right.accept(this));
        return result;
    }

     public Exp visit (Identifier n) {
        Exp result = new Identifier ((Exp)n.left.accept(this), (Exp)n.right.accept(this));
        return result;
    }
    
    public Exp visit (Constant n)
    { 
        return n;  
    }
    
//     public int visit (Assign n)
//     {  ((Identifier) (n.left)).setValue (n.right.accept(this));
//         return n.right.accept(this);  
//     }

    // stub, should never be called
    public Exp visit (Exp e1, Exp e2)
    {   return null;  }
    
    public Exp visit (Exp e)
    {   return null;  }
}