public abstract class FirstOrderProcess extends Process
{
   //protected instance variable to allow access to child classes
   protected double process_input;  //input signal
  
   
   //abstract method that calculates the output of the process based on an input value
   public abstract double processOutput(double input, double output, double delta_time);
  
   
   //FirstOrderProcess constructor and copy constructor methods
   public FirstOrderProcess (double process_time_constant, double process_gain, double process_input)
   {
     super(process_time_constant, process_gain);
     this.process_input=process_input;
   }//end of constructor method
   
   
  public double getProcessInput()
  {
    double process_inputCopy=this.process_input;
    return process_inputCopy;
  }//end of getProcessInput accessor method
  
  public void setProcessInput(double process_input)
  {
    this.process_input=process_input;
  }//end of setProcessInput mutator method
   
   //public abstract double returnValue(double x, double y);
  
}//end of FirstOrderProcess