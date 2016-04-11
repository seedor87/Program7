//import sun.tools.tree.LessExpression;

/**
 * Task:  Evaluate an arithmetic and boolean expressions.
 * Implements Visitor.
 * Uses the Visitor pattern.
 * (This is what an interpreter would do, not a compiler)
 * 
 * @author Bill C and Bob S
 * @version (Mar 2016) and April 2016
 */
public class Eval implements Visitor {

     public Object visit (Sum n) {
         if(n.left instanceof BooleanConst || n.right instanceof BooleanConst) {
             throw new IllegalArgumentException("Cannot perform " + n.getClass() + " on boolean, at " + n.toString());
         }
         if(n.left instanceof StringConst && n.right instanceof StringConst) {
            return (String) n.left.accept(this) + (String) n.right.accept (this);
         }
         if(n.left instanceof StringConst && !(n.right instanceof StringConst)) {
             return (String) n.left.accept(this) + " + " + (Integer) n.right.accept (this);
         }
         return (Integer) n.left.accept(this) + (Integer) n.right.accept (this);
     }
     
    public Integer visit (Difference n) {
        if(n.left instanceof BooleanConst || n.right instanceof BooleanConst) {
            throw new IllegalArgumentException("Cannot perform " + n.getClass() + " on boolean, at " + n.toString());
        }
        return (Integer) n.left.accept(this) - (Integer) n.right.accept (this);
    }
    
     public Integer visit (Product n) {
         if(n.left instanceof BooleanConst || n.right instanceof BooleanConst) {
             throw new IllegalArgumentException("Cannot perform " + n.getClass() + " on boolean, at " + n.toString());
         }
         return(Integer) n.left.accept (this) * (Integer) n.right.accept(this);
     }

     public Integer visit (Quotient n) {
         if(n.left instanceof BooleanConst || n.right instanceof BooleanConst) {
             throw new IllegalArgumentException("Cannot perform " + n.getClass() + " on boolean, at " + n.toString());
         }
         return(Integer) n.left.accept (this) / (Integer) n.right.accept(this);
     }

    public Integer visit (Mod n) {
        if(n.left instanceof BooleanConst || n.right instanceof BooleanConst) {
            throw new IllegalArgumentException("Cannot perform " + n.getClass() + " on boolean, at " + n.toString());
        }
        return(Integer) n.left.accept (this) % (Integer) n.right.accept(this);
    }

    public Boolean visit (And n) {
        if(n.left instanceof Constant || n.right instanceof Constant) {
            throw new IllegalArgumentException("Cannot perform " + n.getClass() + " on Integer, at " + n.toString());
        }
        if(n.left instanceof StringConst || n.right instanceof StringConst) {
            throw new IllegalArgumentException("Cannot perform " + n.getClass() + " on String, at " + n.toString());
        }
        return (Boolean) n.left.accept(this) && (Boolean) n.right.accept(this);
    }

    public Boolean visit (Or n) {
        if(n.left instanceof Constant || n.right instanceof Constant) {
            throw new IllegalArgumentException("Cannot perform " + n.getClass() + " on Integer, at " + n.toString());
        }
        if(n.left instanceof StringConst || n.right instanceof StringConst) {
            throw new IllegalArgumentException("Cannot perform " + n.getClass() + " on String, at " + n.toString());
        }
        return (Boolean) n.left.accept(this) || (Boolean) n.right.accept(this);
    }

    public Boolean visit (GreaterThan n) {
        if(n.left instanceof BooleanConst || n.right instanceof BooleanConst) {
            throw new IllegalArgumentException("Cannot perform " + n.getClass() + " on boolean, at " + n.toString());
        }if(n.left instanceof StringConst || n.right instanceof StringConst) {
            throw new IllegalArgumentException("Cannot perform " + n.getClass() + " on String, at " + n.toString());
        }
        return (Integer) n.left.accept(this) > (Integer) n.right.accept(this);
    }

    public Boolean visit (AltGreaterThan n) {
        if(n.left instanceof BooleanConst || n.right instanceof BooleanConst) {
            throw new IllegalArgumentException("Cannot perform " + n.getClass() + " on boolean, at " + n.toString());
        }
        if(n.left instanceof StringConst || n.right instanceof StringConst) {
            throw new IllegalArgumentException("Cannot perform " + n.getClass() + " on String, at " + n.toString());
        }
        return (Integer) n.left.accept(this) >= (Integer) n.right.accept(this);
    }

    public Boolean visit (LessThan n) {
        if(n.left instanceof BooleanConst || n.right instanceof BooleanConst) {
            throw new IllegalArgumentException("Cannot perform " + n.getClass() + " on boolean, at " + n.toString());
        }
        if(n.left instanceof StringConst || n.right instanceof StringConst) {
            throw new IllegalArgumentException("Cannot perform " + n.getClass() + " on String, at " + n.toString());
        }
        return (Integer) n.left.accept(this) < (Integer) n.right.accept(this);
    }

    public Boolean visit (AltLessThan n) {
        if(n.left instanceof BooleanConst || n.right instanceof BooleanConst) {
            throw new IllegalArgumentException("Cannot perform " + n.getClass() + " on boolean, at " + n.toString());
        }
        if(n.left instanceof StringConst || n.right instanceof StringConst) {
            throw new IllegalArgumentException("Cannot perform " + n.getClass() + " on String, at " + n.toString());
        }
        return (Integer) n.left.accept(this) <= (Integer) n.right.accept(this);
    }

    public Boolean visit (BooleanEquals n) {
        if(n.left instanceof StringConst && n.right instanceof StringConst) {
            return ((String) n.left.accept(this)).equals((String) n.right.accept(this));
        }
        if(n.left instanceof BooleanConst && n.right instanceof BooleanConst) {
            return (Boolean) n.left.accept(this) == (Boolean) n.right.accept(this);
        }
        if(n.left instanceof Constant && n.right instanceof Constant) {
            return (Integer) n.left.accept(this) == (Integer) n.right.accept(this);
        }
        throw new IllegalArgumentException("Cannot check equivalence of unlike terms at: " + n.toString());
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

    public String visit (StringConst n) { return n.value; }
    
    public Object visit (Assign n) {
        if(n.right instanceof StringConst) {
            ((Identifier) (n.left)).setValue((String) n.right.accept(this));
            return n.right.accept(this);
        }
        if(n.right instanceof Sum  && n.right.right instanceof StringConst) {
            ((Identifier) (n.left)).setValue((String) n.right.accept(this));
            return n.right.accept(this);
        }
        if(n.right instanceof Sum && n.right.left instanceof StringConst  && !(n.right.right instanceof StringConst)) {
            ((Identifier) (n.left)).setValue((String) n.right.accept(this));
            return n.right.accept(this);
        }
        ((Identifier) (n.left)).setValue((Integer) n.right.accept(this));
        return n.right.accept(this);
    }

    public Object visit (AltPlus n) {
        if(n.left instanceof BooleanConst || n.right instanceof BooleanConst) {
            throw new IllegalArgumentException("Cannot perform " + n.getClass() + " on boolean, at " + n.toString());
        }
        if(n.right instanceof StringConst) {
            String value = (String) n.left.accept(this) + (String) n.right.accept(this);
            return (value);
        }
        Integer value = (Integer) n.left.accept(this) + (Integer) n.right.accept(this);
        ((Identifier) (n.left)).setValue(value);
        return (value);
    }

    public Object visit (AltMinus n) {
        if(n.left instanceof BooleanConst || n.right instanceof BooleanConst) {
            throw new IllegalArgumentException("Cannot perform " + n.getClass() + " on boolean, at " + n.toString());
        }
        Integer value = (Integer) n.left.accept(this) - (Integer) n.right.accept(this);
        ((Identifier) (n.left)).setValue(value);
        return (value);
    }

    public Object visit (AltMult n) {
        if(n.left instanceof BooleanConst || n.right instanceof BooleanConst) {
            throw new IllegalArgumentException("Cannot perform " + n.getClass() + " on boolean, at " + n.toString());
        }
        Integer value = (Integer) n.left.accept(this) * (Integer) n.right.accept(this);
        ((Identifier) (n.left)).setValue(value);
        return (value);
    }

    public Object visit (AltDiv n) {
        if(n.left instanceof BooleanConst || n.right instanceof BooleanConst) {
            throw new IllegalArgumentException("Cannot perform " + n.getClass() + " on boolean, at " + n.toString());
        }
        Integer value = (Integer) n.left.accept(this) / (Integer) n.right.accept(this);
        ((Identifier) (n.left)).setValue(value);
        return (value);
    }

    public Object visit (AltMod n) {
        if(n.left instanceof BooleanConst || n.right instanceof BooleanConst) {
            throw new IllegalArgumentException("Cannot perform " + n.getClass() + " on boolean, at " + n.toString());
        }
        Integer value = (Integer) n.left.accept(this) % (Integer) n.right.accept(this);
        ((Identifier) (n.left)).setValue(value);
        return (value);
    }

    // stub, should never be called
    public Exp visit (Exp e1, Exp e2) {
        return null;
    }

    public Exp visit(Exp e) { return null; }

    public String toString(Exp n) {
        return n.toString();
    }

}