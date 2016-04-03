/**
 * Created by robertseedorf on 4/2/16.
 */
public class BooleanConst extends Exp {
    Boolean value;

    public BooleanConst (Boolean n)
    {    value = n;   }

    /** @return the value of this Number,
    by calling the appropriate Visitor
     */
    public Object accept (Visitor v)
    {    return v.visit (this);  }

    public String toString() {
        return "" + value;
    }
}
