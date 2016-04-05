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

    public Exp visit (And n) {
        Exp result = new And((Exp) n.left.accept(this), (Exp) n.right.accept(this));
        return result;
    }

    public Exp visit (Or n) {
        Exp result = new Or((Exp) n.left.accept(this), (Exp) n.right.accept(this));
        return result;
    }

    public Exp visit (GreaterThan n) {
        Exp result = new GreaterThan((Exp) n.left.accept(this), (Exp) n.right.accept(this));
        return result;
    }

    public Exp visit (AltGreaterThan n) {
        Exp result = new AltGreaterThan((Exp) n.left.accept(this), (Exp) n.right.accept(this));
        return result;
    }

    public Exp visit (LessThan n) {
        Exp result = new LessThan((Exp) n.left.accept(this), (Exp) n.right.accept(this));
        return result;
    }

    public Exp visit (AltLessThan n) {
        Exp result = new AltLessThan((Exp) n.left.accept(this), (Exp) n.right.accept(this));
        return result;
    }

    public Exp visit (BooleanEquals n) {
        Exp result = new BooleanEquals((Exp) n.left.accept(this), (Exp) n.right.accept(this));
        return result;
    }

    public Exp visit (Assign n) {
        Exp result = new Assign((Exp) n.left.accept(this), (Exp) n.right.accept(this));
        return result;
    }

    public Exp visit (AltPlus n) {
        Exp result = new AltPlus((Exp) n.left.accept(this), (Exp) n.right.accept(this));
        return result;
    }

    public Exp visit (AltMinus n) {
        Exp result = new AltMinus((Exp) n.left.accept(this), (Exp) n.right.accept(this));
        return result;
    }

    public Exp visit (AltMult n) {
        Exp result = new AltMult((Exp) n.left.accept(this), (Exp) n.right.accept(this));
        return result;
    }

    public Exp visit (AltDiv n) {
        Exp result = new AltDiv((Exp) n.left.accept(this), (Exp) n.right.accept(this));
        return result;
    }

    public Exp visit (AltMod n) {
        Exp result = new AltMod((Exp) n.left.accept(this), (Exp) n.right.accept(this));
        return result;
    }

     public Exp visit (Identifier n) {
        return n;
    }
    
    public Exp visit (Constant n)
    { 
        return n;  
    }

    public Exp visit (BooleanConst n) { return n; }

    public Exp visit (StringConst n) { return n; }

    public Exp visit (Exp e1, Exp e2)
    {   return null;  }
    
    public Exp visit (Exp e)
    {   return null;  }

    public String toString(Exp n) {
        return n.toString();
    }
}