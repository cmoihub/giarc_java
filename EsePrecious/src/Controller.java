public abstract class Controller 
{
  //protected instance variables; private instance variables could not be accessed by the child classes
  protected double new_error;
  protected double old_error;
  protected double[] error;

  
  //Constructor method for Controller
  public Controller(double old_error, double new_error)
  {
    this.new_error=new_error;
    this.old_error=old_error;
  }//end of constructor method
  
  
  //copy constructor for Controller
  public Controller(Controller copy)
  {
    this.new_error=copy.new_error;
    this.old_error=copy.old_error;
  }//end of copy constructor
 
  
  //abstract method that calculates the error for each controller type
  public abstract double calculateError(double delta_time, double old_error, double new_error);

  
  //new_error Accessor and Mutator methods
  public double getNewError()
  {
    double new_errorCopy=this.new_error;
    return new_errorCopy;
  }//end of getNewError accessor method
  
  public void setNewError(double new_error)
  {
    this.new_error=new_error;
  }//end of setNewError mutator method
  
  
  //old_error Accessor and Mutator methods
  public double getOldError()
  {
    double old_errorCopy=this.old_error;
    return old_errorCopy;
  }//end of getOldError accessor method
  
  public void setOldError(double old_error)
  {
    this.old_error=old_error;
  }//end of setOldError mutator method
  
 
}//end of Controller parent class