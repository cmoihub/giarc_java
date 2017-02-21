public class Proportional extends Controller
{
  //private instance variables
  private double p_0; 
  private double k_proportional;  //gain constant of proportional controller
  
  
  //Proportional constructor and copy constructor methods
  public Proportional (double old_error, double new_error, double k_proportional, double p_0)
  {
   super(old_error, new_error);
   this.p_0=p_0;
   this.k_proportional=k_proportional;
  }//end of constructor method
  
  public Proportional (Proportional copy)
  {
   super(copy);
   this.p_0=copy.p_0;
   this.k_proportional=copy.k_proportional;
  }//end of copy constructor
  
  
  //p_O accessor and mutator methods
  public double getP0()
  {
    double p_0Copy=this.p_0;
    return p_0Copy;
  }//end of getPO accessor method
  
  public void setP0(double p_0)
  {
    this.p_0=p_0;
  }//end of setPO mutator method
  
  
  //proportional gain accessor and mutator methods
  public double getKProportional()
  {
    double k_proportionalCopy=this.k_proportional;
    return k_proportionalCopy;
  }//end of getKP accessor method
  
  public void setKProportional(double k_proportional)
  {
    this.k_proportional=k_proportional;
  }//end of setKP mutator method
  
 
  //calculateError method calculates the value of the error for the proportional controller
  public double calculateError(double delta_time, double old_error, double new_error)
  {
    return new_error;
  }//end of calculateError methods
  
}//end of Proportional class