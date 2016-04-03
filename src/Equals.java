/**
 * Task:  Test arithmetic expressions for equality.
 * Implements Visitor.
 * Uses the Visitor pattern.
 * (This is what the optimization phase of a compiler would do)
 * 
 * @author (sdb)
 * @version (Mar 2016)
 */
public class Equals implements Visitor {
    
     public Boolean visit (Sum n) {
         return visit (n.left, n.right);
       // return  new Sum ((Boolean)n.left.accept(this), (Exp)n.right.accept(this));
     }
     
    public Boolean visit (Difference n)
    {  
         return visit (n.left, n.right);
    }
    
    public Boolean visit (Exp e1, Exp e2)
    {   if (! e1.getClass().equals (e2.getClass()))
            return false;
        
        if (e1 instanceof Sum)
            return ((Boolean) visit (e1.left, e2.left)) &&
                    ((Boolean) visit (e1.right, e2.right)) ||
                    ((Boolean) visit (e1.left, e2.right)) &&
                    ((Boolean) visit (e1.right, e2.left));
        
        if (e1 instanceof Difference)
            return ((Boolean) visit (e1.left, e2.left)) &&
                    ((Boolean) visit (e1.right, e2.right));

       if (e1 instanceof Product)
           return ((Boolean) visit (e1.left, e2.left)) &&
                   ((Boolean) visit (e1.right, e2.right)) ||
                   ((Boolean) visit (e1.left, e2.right)) &&
                           ((Boolean) visit (e1.right, e2.left));

        if (e1 instanceof Quotient)
            return ((Boolean) visit (e1.left, e2.left)) &&
                    ((Boolean) visit (e1.right, e2.right));

        //default case
        return ((Constant)e1).value.equals (((Constant)e2).value);
        }
    
     public Boolean visit (Product n) {
         return visit (n.left, n.right);
     }

     public Boolean visit (Quotient n) {
         return visit (n.left, n.right);
     }

    public Boolean visit (Mod n) {
        return visit (n.left, n.right);
    }

    public Boolean visit (And n) {
        return visit (n.left, n.right);
    }

    public Boolean visit (Or n) {
        return visit (n.left, n.right);
    }

    public Boolean visit (GreaterThan n) {
        return visit (n.left, n.right);
    }

    public Boolean visit (AltGreaterThan n) { return  visit (n.left, n.right); }

    public Boolean visit (LessThan n) {
        return visit (n.left, n.right);
    }

    public Boolean visit (AltLessThan n) {
        return visit (n.left, n.right);
    }

    public Boolean visit (Assign n) {
        return visit (n.left, n.right);
    }

    public Boolean visit (AltPlus n) { return visit (n.left, n.right); }

    public Boolean visit (AltMinus n) { return visit (n.left, n.right); }

    public Boolean visit (AltMult n) { return visit (n.left, n.right); }

    public Boolean visit (AltDiv n) { return visit (n.left, n.right); }

    public Boolean visit (AltMod n) { return visit (n.left, n.right); }

     public Boolean visit (Identifier n) {
         //return n.accept(this);
         return true;
     }
    
    public Integer visit (Constant n) {
        return n.value;
    }

    public Boolean visit (BooleanConst n) { return n.value; }
    
//     public int visit (Assign n)
//     {  ((Identifier) (n.left)).setValue (n.right.accept(this));
//         return n.right.accept(this);  
//     }

    public Object visit (Exp e)
    {   throw new IllegalArgumentException("Invalid Expression");  }

    public String toString(Exp n) {
        return n.toString();
    }
}