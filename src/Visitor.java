
/**
 * Abstract class for Expressions.
 * Subclasses must implement an accept method.
 * Uses the Visitor pattern
 * 
 * @author (sdb)
 * @version (Mar 2016)
 */
public interface Visitor
{
//     Object visit (Exp n);
    Object visit (Sum n);
    Object visit (Difference n);
    Object visit (Exp e1, Exp e2);            // For Equals
    
//     Object visit (Product n);
//     Object visit (Quotient n);
//     Object visit (Identifier n);
    Object visit (Constant n);
//     Object visit (Assign n);
}
