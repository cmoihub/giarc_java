public class NumericalMethod //this class contains an integration and differentiation method
{
  
  //this method evaluates an integral using Simpson's Rule
  public static double simpsonRule (double x1, double x2, Function f)
  {
    double y=0.0;
    double v1=f.returnValue(x1, y);
    double v2=4*f.returnValue(((x1+x2)/2.0), y);
    double v3=f.returnValue(x2, y);
    return (((x1+x2)/6.0)*(v1+v2+v3));
  }//end of simpson rule method
  
  
  //this method evaluates a differential using the RK6 formula
  public static double rungeKutta6 (double x, double y_0, double h, Function f) //page 735 of numerical methods textbook
  {

    double k1, k2, k3, k4, k5, k6;
    double z=y_0;
    
    k1=f.returnValue(x,z);
    k2=f.returnValue((x+(1./4.)*h),(z+(1./4.)*k1*h));
    k3=f.returnValue((x+(1./4.)*h),(z+((1./8.)*k1*h)+((1./8.)*k2*h)));
    k4=f.returnValue((x+(1./2.)*h),(z-((1./2.)*k2*h)+(k3*h)));
    k5=f.returnValue((x+(3./4.)*h),(z+((3./16.)*k1*h)+((9./16.)*k4*h)));
    k6=f.returnValue((x+h),(z-((3./7.)*k1*h)+((2./7.)*k2*h)+((12./7.)*k3*h)-((12./7.)*k4*h)+((8./7.)*k5*h)));
    
    z+=((1./90.)*h*((7*k1)+(32*k3)+(12*k4)+(32*k5)+(7*k6)));
    //x+=h; 
    //double y=y_0+z*h;
    
    System.out.println("y="+z);

    return z;
  }//end of RungeKutta6 method
  
}