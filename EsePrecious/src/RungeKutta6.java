
public class RungeKutta6
{
  public static double differentiate (double x_i, double x_f, double y_0, double h, double tol, Function f)
  {

    double k1, k2, k3, k4, k5, k6;
    double del_y=0., y_new=0.;
    double x=x_i;
    double y=y_0;
    
    int n=(int)((x_f-x_i)/h);
    int counter=0;
    
    do{
     
      for (int i=0; i<n; i++)
      {
        
        k1=f.returnValue(x,y);
        k2=f.returnValue((x+(1/4)*h),(y+(1/4)*k1*h));
        k3=f.returnValue((x+(1/4)*h),(y+((1/8)*k1*h)+((1/8)*k2*h)));
        k4=f.returnValue((x+(1/2)*h),(y-((1/2)*k2*h)+(k3*h)));
        k5=f.returnValue((x+(3/4)*h),(y+((3/16)*k1*h)+((9/16)*k4*h)));
        k6=f.returnValue((x+h),(y-((3/7)*k1*h)+((2/7)*k2*h)+((12/7)*k3*h)-((12/7)*k4*h)+((8/7)*k5*h)));
        
        y_new=y+((1/90)*h*((7*k1)+(32*k3)+(12*k4)+(32*k5)+(7*k6)));
        x=x+h;
        del_y=y-y_new;
        y=y_new;
      }//end of for loop
      counter++;
      
    }while ((counter<=n)&&(del_y<tol));
      
    return y_new;
  }
}