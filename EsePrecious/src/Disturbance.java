public class Disturbance
{

//  public static double disturbance(double time)
//  {
//    return Math.exp(time);
//  }//end of static method

  public static double disturbance (double old_process_input, double new_process_input)
  {
   return  new_process_input+old_process_input;
  }
  
}//end of disturbance class