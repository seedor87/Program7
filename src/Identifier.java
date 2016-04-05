/**
 * Created by robertseedorf on 3/31/16.
 *
 * this class represents all non-typed identifier in out language, this is all method calls, variable names and class names
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
