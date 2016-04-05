/**
 * Created by robertseedorf on 3/31/16.
 *
 * * this class represents assignment on all, string, integers, and booleans in regular java
 */
public class Assign extends Exp {

    public Assign (Exp l, Exp r) {
        left = l;
        right = r;
    }

    public Object accept (Visitor v) {
        return  v.visit (this);
    }

    public String toString() {
        return left.toString() + " = " + right.toString();
    }
}
