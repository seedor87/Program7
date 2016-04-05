/**
 * Created by robertseedorf on 4/2/16.
 *
 * this class represents > in regular java
 */
public class GreaterThan extends Exp {

    public  GreaterThan(Exp l, Exp r) {
        left = l;
        right = r;
    }

    public Object accept(Visitor v) {
        return (Integer) v.visit(this);
    }

    public String toString() {
        return left.toString() + " > " + right.toString();
    }
}