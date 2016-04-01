/**
 * Created by robertseedorf on 3/31/16.
 */
public class Mod extends Exp {

    public Mod (Exp l, Exp r) {
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
