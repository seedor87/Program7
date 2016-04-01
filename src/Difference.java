
/**
 * A Difference is an Expression which is the result
 * of adding two expressions.
 * 
 * @author (sdb)
 * @version (Mar 2016)
 */
public class Difference extends Exp {

   public Difference (Exp l, Exp r) {
       left = l;
        right = r;
    }
    
    /** @return the difference of the two given expressions,
       by calling the appropriate Visitor
    */
    public Object accept (Visitor v) {
        return (Integer)  v.visit (this);
    }

    
}
