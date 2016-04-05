/**
 * Created by robertseedorf on 3/31/16.
 *
 * * this class represents * in regular java
 */
public class Product extends Exp {

    public Product (Exp l, Exp r) {
        left = l;
        right = r;
    }

    public Object accept (Visitor v) {
        return  v.visit (this);

    }

    public String toString() {
        return left.toString() + " * " + right.toString();
    }
}
