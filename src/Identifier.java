/**
 * Created by robertseedorf on 3/31/16.
 */
public class Identifier extends Exp {

    public Object value;
    String name;

    public Identifier (String name) {
        this.name = name;
    }

    public Object accept (Visitor v) {
        return  v.visit (this);
    }

    public void setValue(Object i) {
        this.value = i;
    }

    public String toString() {
        if(value != null) {
            return "(" + name + "=" + value + ")";
        }
        return name;
    }
}
