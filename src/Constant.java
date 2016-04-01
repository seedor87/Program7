
/**
 * A Constant is an Expression which has a value.
 * 
 * @author (sdb)
 * @version (Mar 2016)
 */
public class Constant extends Exp
{
    Integer value;
    
   public Constant (Integer n)
   {    value = n;   }
    
    /** @return the value of this Number,
       by calling the appropriate Visitor
    */
    public Object accept (Visitor v)
    {    return v.visit (this);  }
    
}
