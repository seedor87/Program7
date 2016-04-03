import sun.tools.tree.LessExpression;

/**
 * Task:  Evaluate an arithmetic and boolean expressions.
 * Implements Visitor.
 * Uses the Visitor pattern.
 * (This is what an interpreter would do, not a compiler)
 * 
 * @author (sdb) and Bob
 * @version (Mar 2016) and April 2016
 */
public class Eval implements Visitor {

     public Integer visit (Sum n) {
         return (Integer) n.left.accept(this) + 
                (Integer) n.right.accept (this);
     }
     
    public Integer visit (Difference n) {
        return (Integer) n.left.accept(this) - (Integer) n.right.accept (this);
    }
    
     public Integer visit (Product n) {
         return(Integer) n.left.accept (this) * (Integer) n.right.accept(this);
     }

     public Integer visit (Quotient n) {
         return(Integer) n.left.accept (this) / (Integer) n.right.accept(this);
     }

    public Integer visit (Mod n) {
        return(Integer) n.left.accept (this) % (Integer) n.right.accept(this);
    }

    public Boolean visit (And n) {
        return (Boolean) n.left.accept(this) && (Boolean) n.right.accept(this);
    }

    public Boolean visit (Or n) {
        return (Boolean) n.left.accept(this) || (Boolean) n.right.accept(this);
    }

    public Boolean visit (GreaterThan n) {
        return (Integer) n.left.accept(this) > (Integer) n.right.accept(this);
    }

    public Boolean visit (AltGreaterThan n) {
        return (Integer) n.left.accept(this) >= (Integer) n.right.accept(this);
    }

    public Boolean visit (LessThan n) {
        return (Integer) n.left.accept(this) < (Integer) n.right.accept(this);
    }

    public Boolean visit (AltLessThan n) {
        return (Integer) n.left.accept(this) <= (Integer) n.right.accept(this);
    }

     public Integer visit (Identifier n) {
         if(n.value != null) {
             return (Integer) n.value;
         }
         throw new IllegalArgumentException("Cannot Evaluate a Variable with No Value");
     }
    
    public Integer visit (Constant n) {
        return n.value;
    }

    public Boolean visit (BooleanConst n) { return n.value; }
    
    public Object visit (Assign n) {
         ((Identifier) (n.left)).setValue((Integer) n.right.accept(this));
         return n.right.accept(this);
     }

    public Object visit (AltPlus n) {
        Integer value = (Integer) n.left.accept(this) + (Integer) n.right.accept(this);
        ((Identifier) (n.left)).setValue(value);
        return (value);
    }

    public Object visit (AltMinus n) {
        Integer value = (Integer) n.left.accept(this) - (Integer) n.right.accept(this);
        ((Identifier) (n.left)).setValue(value);
        return (value);
    }

    public Object visit (AltMult n) {
        Integer value = (Integer) n.left.accept(this) * (Integer) n.right.accept(this);
        ((Identifier) (n.left)).setValue(value);
        return (value);
    }

    public Object visit (AltDiv n) {
        Integer value = (Integer) n.left.accept(this) / (Integer) n.right.accept(this);
        ((Identifier) (n.left)).setValue(value);
        return (value);
    }

    public Object visit (AltMod n) {
        Integer value = (Integer) n.left.accept(this) % (Integer) n.right.accept(this);
        ((Identifier) (n.left)).setValue(value);
        return (value);
    }

    // stub, should never be called
    public Exp visit (Exp e1, Exp e2) {
        return null;
    }

    public String toString(Exp n) {
        return n.toString();
    }

}