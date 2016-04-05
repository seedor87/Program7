
/**
 * Abstract class for Expressions.
 * Subclasses must implement an accept method.
 * Uses the Visitor pattern
 * 
 * @author Bill Clark and Bob Seedorf
 * @version (Mar 2016)
 */
public interface Visitor {

    Object visit(Sum n);
    Object visit(Difference n);
    Object visit(Product n);
    Object visit(Quotient n);
    Object visit(Mod n);
    Object visit(And n);
    Object visit(Or n);
    Object visit(GreaterThan n);
    Object visit(AltGreaterThan n);
    Object visit(LessThan n);
    Object visit(AltLessThan n);
    Object visit(BooleanEquals n);
    Object visit(Assign n);
    Object visit(AltPlus n);
    Object visit(AltMinus n);
    Object visit(AltMult n);
    Object visit(AltDiv n);
    Object visit(AltMod n);
    Object visit(Identifier n);
    Object visit(Constant n);
    Object visit(BooleanConst n);
    Object visit(StringConst N);
    Object visit(Exp e1, Exp e2);            // For Equals

    String toString(Exp n);

}
