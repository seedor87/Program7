/**
 * Created by robertseedorf on 4/4/16.
 */
public class StringConst extends Exp {
    String value;

    public StringConst (String n) {
        value = n;
    }

    /** @return the value of this Number,
    by calling the appropriate Visitor
     */
    public Object accept (Visitor v)
    {    return v.visit (this);  }

    public String toString() {
        return "\"" + value + "\"";
    }
}