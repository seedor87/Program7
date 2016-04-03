import java.rmi.server.ExportException;

/**
 * Created by robertseedorf on 4/2/16.
 */
public class LessThan extends Exp{

    public LessThan(Exp l, Exp r) {
        left = l;
        right = r;
    }

    public Object accept(Visitor v) {
        return (Integer) v.visit(this);
    }

    public String toString() {
        return left.toString() + " < " + right.toString();
    }
}