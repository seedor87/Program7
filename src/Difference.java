
/**
 * A Difference is an Expression which is the result
 * of adding two expressions.
 * 
 * @author Bill Clark and Bob Seedorf
 * @version (Mar 2016)
 */
public class Difference extends Exp {

   public Difference (Exp l, Exp r) {
       left = l;
        right = r;
    }

    public Object accept (Visitor v) {
        return (Integer)  v.visit (this);
    }

    public String toString() {
        return left.toString() + " - " + right.toString();
    }
    
}
