public abstract class Process //implements Function
{
  //the instance variables are protected to allow the child classes access to them
  protected double process_time_constant; 
  protected double process_gain;
    
  
  //abstract methods
  //calculates the value of a process based on an input value
  public abstract double processOutput(double input, double output, double delta_time);
  
  //inherited method from the function interface
  //public abstract double returnValue(double x, double y);
  
  
  //Process constructor and copy constructor methods
  public Process(double process_time_constant, double process_gain)
  {
    this.process_time_constant=process_time_constant;
    this.process_gain=process_gain;
  }//end of constructor
  
  public Process (Process copy)
  {
    this.process_time_constant=copy.process_time_constant;
    this.process_gain=copy.process_gain;
  }//end of copy constructor
    
  
  //process time constant accessor and mutator methods
  public double getProcessTimeConstant()
  {
    double process_time_constantCopy=this.process_time_constant;
    return process_time_constantCopy;
  }//end of getProcessTimeConstant accessor method
  
  public void setProcessTimeConstant(double process_time_constant)
  {
    this.process_time_constant=process_time_constant;
  }//end of setProcessTimeConstant mutator method
  
  
  //process gain accessor and mutator methods
  public double getProcessGain()
  {
    double process_gainCopy=this.process_gain;
    return process_gainCopy;
  }//end of getProcessGain accessor method
  
  public void setProcessGain(double process_gain)
  {
    this.process_gain=process_gain;
  }//end of setProcessGain mutator method
  
}//end of Process class