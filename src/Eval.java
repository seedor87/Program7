/**
 * Task:  Evaluate an arithmetic expressions.
 * Implements Visitor.
 * Uses the Visitor pattern.
 * (This is what an interpreter would do, not a compiler)
 * 
 * @author (sdb)
 * @version (Mar 2016)
 */
public class Eval
    implements Visitor
    
{
     public Integer visit (Sum n)
     {  
         return (Integer) n.left.accept(this) + 
                (Integer) n.right.accept (this);
     }
     
    public Integer visit (Difference n)
    {  return (Integer) n.left.accept(this) - 
                (Integer) n.right.accept (this); 
    }
    
//     public int visit (Product n)
//     {  return(Integer) n.left.accept (this) *(Integer) n.right.accept(this);  }
//     
//     public int visit (Quotient n)
//     {  return(Integer) n.left.accept (this) /(Integer) n.right.accept(this);  }
//     
//     public int visit (Identifier n)
//     {  return n.accept(this);  }
    
    public Integer visit (Constant n)
    { 
        return n.value;  
    }
    
//     public int visit (Assign n)
//     {  ((Identifier) (n.left)).setValue (n.right.accept(this));
//         return n.right.accept(this);  
//     }

    // stub, should never be called
    public Exp visit (Exp e1, Exp e2)
    {   return null;  }
    
//     public Exp visit (Exp e)
//     {   return null; }
}