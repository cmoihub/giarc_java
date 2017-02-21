public class TestFirstOrderProcess extends FirstOrderProcess implements Function
{
  
  //constructor method for TestOrderProcess
  public TestFirstOrderProcess (double process_time_constant, double process_gain, double process_input)
   {
     super(process_time_constant, process_gain, process_input);
   }//end of constructor method
  
    
  //general first order process
   public double returnValue(double x, double y)
   {
     return (-y/super.process_time_constant)+((super.process_gain*super.process_input)/super.process_time_constant);
   }
  
  
  //processOutput calculates the output of the process
  public double processOutput(double input, double output, double delta_time)
  {
    return NumericalMethod.rungeKutta6(input, output, delta_time, this);
  }//end of outputSignal method
  
  
}//end of TestFirstOrderProcess class














