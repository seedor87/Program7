import com.sun.tools.javac.tree.JCTree;

/**
 * Created by robertseedorf on 4/4/16.
 */
public class toString implements Visitor {

    public String visit (Sum n) {
        return "( " + n.left.accept(this) + " + " + n.right.accept (this) + " )";
    }

    public String visit (Difference n) {
        return "( " + n.left.accept(this) + " - "  +  n.right.accept (this) + " )";
    }

    public String visit (Product n) {
        return "( " + n.left.accept(this) + " * "  + n.right.accept (this) + " )";
    }

    public String visit (Quotient n) {
        return "( " + n.left.accept(this) + " / "  + n.right.accept (this) + " )";
    }

    public String visit (Mod n) {
        return "( " + n.left.accept(this) + " % "  + n.right.accept (this) + " )";
    }

    public String visit (And n) {
        return "( " + n.left.accept(this) + " & "  + n.right.accept (this) + " )";
    }

    public String visit (Or n) {
        return "( " + n.left.accept(this) + " | "  + n.right.accept (this) + " )";
    }

    public String visit (GreaterThan n) {
        return "( " + n.left.accept(this) + " > "  + n.right.accept (this) + " )";
    }

    public String visit (AltGreaterThan n) {
        return "( " + n.left.accept(this) + " ?> "  + n.right.accept (this) + " )";
    }

    public String visit (LessThan n) {
        return "( " + n.left.accept(this) + " < "  + n.right.accept (this) + " )";
    }

    public String visit (AltLessThan n) {
        return "( " + n.left.accept(this) + " ?< "  +  n.right.accept (this) + " )";
    }

    public String visit (Assign n) {
        return n.left.accept(this) + " = " + n.right.accept (this);
    }

    public String visit (AltPlus n) {
        return "( " + n.left.accept(this) + " ?+ "  + n.right.accept (this) + " )";
    }

    public String visit (AltMinus n) {
        return "( " + n.left.accept(this) + " ?- "  + n.right.accept (this) + " )";
    }

    public String visit (AltMult n) {
        return "( " + n.left.accept(this) + " ?* "  + n.right.accept (this) + " )";
    }

    public String visit (AltDiv n) {
        return "( " + n.left.accept(this) + " ?/ "  + n.right.accept (this) + " )";
    }

    public String visit (AltMod n) {
        return "( " + n.left.accept(this) + " ?% "  + n.right.accept (this) + " )";
    }

    public String visit (Identifier n) {
        return "" + n.name;
    }

    public String visit (Constant n) {
        return "" + n.value;
    }

    public String visit (BooleanConst n) { return "" + n.value; }

    public String visit (StringConst n) { return "" + n.value; }

    public String visit (Exp e1, Exp e2)
    {   return (String) e1.accept(this) + " , " + (String) e2.accept(this);  }

    public String visit (Exp e) {
        return (String) e.accept(this);
    }

    public String toString(Exp n) {
        return n.toString();
    }
}
