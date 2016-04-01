/**
 * Created by robertseedorf on 3/31/16.
 */
public class Quotient extends Exp {

    public Quotient (Exp l, Exp r) {
        left = l;
        right = r;
    }

    public Object accept (Visitor v) {
        return  v.visit (this);

    }
}
