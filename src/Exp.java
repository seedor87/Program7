
/**
 * Abstract class for Expressions.
 * Subclasses must implement an accept method.
 * Uses the Visitor pattern
 * 
 * @author Bill Clark and Bob Seedorf
 * @version (Mar 2016)
 */
public abstract class Exp {

    Exp left, right;
    
    public abstract Object accept (Visitor v);
    public abstract String toString();
}


