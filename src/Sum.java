
/**
 * A Sum is an Expression which is the result
 * of adding two expressions.
 * 
 * @author (sdb)
 * @version (Mar 2016)
 */
public class Sum  extends Exp 
{
   public Sum (Exp l, Exp r)
   {    left = l;
        right = r;
    }
    
    public Object accept (Visitor v)
    {   return  v.visit (this);
       
    }
    
}
