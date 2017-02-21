public class Integral extends Controller
{
  //private instance variables
  private double k_integral;  //gain constant of the integral controller
  
  
  //Integral constructor and copy constructor methods
  public Integral (double old_error, double new_error, double k_integral)
  {
   super(old_error, new_error);
   this.k_integral=k_integral;
  }//end of constructor
  
  public Integral (Integral copy)
  {
    super(copy);
    this.k_integral=copy.k_integral;
  }//end of copy constructor
  
  
  //integral gain accessor and mutator methods
  public double getKIntegral()
  {
    double k_integralCopy=this.k_integral;
    return k_integralCopy;
  }//end of getKIntegral accessor method
  
  public void setKIntegral(double k_integral)
  {
    this.k_integral=k_integral;
  }//end of setKIntr mutator method
  

  //calculateError method calculates the value of the error for the integral controller
  public double calculateError(double delta_time, double old_error, double new_error)
  {
    double integralError=new_error*delta_time;
    return integralError;
  }//end of calculateError method
    
}//end of Integral class