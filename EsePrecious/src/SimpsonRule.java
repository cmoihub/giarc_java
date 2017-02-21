
public class SimpsonRule
{
  public static double integrate (double t_f, double e, Function f)
  {
    double v1=f.returnValue(0.0, e);
    double v2=4*f.returnValue(t_f/2.0, e);
    double v3=f.returnValue(t_f, e);
    return ((t_f/6)*(v1+v2+v3));
  }
                       
}//end of SimpsonRule class