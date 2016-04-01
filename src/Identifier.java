/**
 * Created by robertseedorf on 3/31/16.
 */
public class Identifier extends Exp {

    Object value;

    public Identifier (Exp l, Exp r) {
        left = l;
        right = r;
    }

    public Object accept (Visitor v) {
        return  v.visit (this);
    }

    public void setValue(Integer i) {
        this.value = i;
    }
}
