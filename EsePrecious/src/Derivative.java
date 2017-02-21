public class Derivative extends Controller
{
  //private instance variables
  private double k_derivative;  //gain constant of the derivative controller

  
  //Derivative constructor and copy constructor methods
  public Derivative (double old_error, double new_error, double k_derivative)
  {
    super(old_error, new_error);
    this.k_derivative=k_derivative;
  }//end of constructor
  
  public Derivative (Derivative copy)
  {
    super(copy);
    this.k_derivative=copy.k_derivative;
  }//end of copy constructor
  
  
  //derivative gain accessor and mutator methods
  public double getKDerivative()
  {
    double k_derivativeCopy=this.k_derivative;
    return k_derivativeCopy;
  }//end of getKDerivative accessor method
  
  public void setKDerivative(double k_derivative)
  {
    this.k_derivative=k_derivative;
  }//end of setKI mutator method
  
  
  //calculateError method calculates the value of the error for the derivative controller
  public double calculateError(double delta_time, double old_error, double new_error) 
  {
    double derivative_error=(new_error-old_error)/delta_time;
    return derivative_error;
  }//end of calculateError method
   
  
}//end of derivative class