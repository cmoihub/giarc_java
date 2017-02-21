import java.util.*;

public class MainClass
{
  public static void main(String[]args)
  {
    //creating Scanner object
    @SuppressWarnings("resource")
	Scanner read=new Scanner(System.in);
 
    
    //variable initialization      
    int maximumIterations=50, iterations=0;
    double tolerance=0.001, old_error=0., new_error=0.;
    //double process_output=0.;       //this is the initial process output at steady state (time = -1). 
    double controller_output=0.;    //this is the initial controller output at steady state (time = -1). 
    
    
    //input variables for the controllers
    double valueP=0., k_proportional=10., p_O=0., proportional_output=0.;
    double valueI=0., k_integral=4., integral_output=0.;
    double valueD=0., k_derivative=0.4, derivative_output=0.;
    
    
    //creating objects for the controllers
    Proportional proportionalController=new Proportional(old_error, new_error, k_proportional, p_O);
    Integral integralController=new Integral(old_error, new_error, k_integral);
    Derivative derivativeController= new Derivative(old_error, new_error, k_derivative);
    
    
    //creating a TestFirstOrderProcess of a tank which receives a flowrate and controls the height of the liquid
    //initilializing the variables
    double area=4.;                                           //m^2 cross-sectional area of the tank
    double resistance=0.25;                                     //m/(m^3/min)
    double steady_state_flowrate=2.;                           //m^3/min 
    double height_setPoint=2.;   //deviation variables
    
    
    //initializing process values
    double process_time_constant=area*resistance;
    double process_gain=resistance;
   
    
    //initializing variables - Number seconds passed before a new reading is taken
    double delta_time=0.1;   //s
    
    
    //user enters controller type
    String[] controllerName= {"P-controller", "I-controller", "D-controller" };
    int[] controllerType=new int[3];
    System.out.println("Enter 1 for a controller and 0 for no controller");
    for(int i=0; i<3; i++)
    {
      System.out.println(controllerName[i]);
      controllerType[i]=read.nextInt();
    }//end of for loop   
    
    
    //creating a disturbance array that takes int he disturbance and time of disturbance
    double[] disturbance=new double[2];  //m^3/min
    double new_process_input=.25;
    disturbance[0]=Disturbance.disturbance(steady_state_flowrate,new_process_input);
    disturbance[1]=1.;
    
    
    //creating a tank object
    TestFirstOrderProcess tank=new TestFirstOrderProcess (process_time_constant, process_gain, controller_output);
    double measuredVariable=tank.processOutput(disturbance[0], height_setPoint, delta_time);
      
    
    //calculates the output signal
    do      
    {
      new_error=height_setPoint-measuredVariable;
      System.out.println("error="+new_error);
    
      valueP=proportionalController.calculateError(delta_time, old_error, new_error);
      valueI+=integralController.calculateError(delta_time, old_error, new_error);
      valueD=derivativeController.calculateError(delta_time, old_error, new_error);  
      System.out.println("valueP="+valueP+"; valueI="+valueI+"; valueD="+valueD);
      
      proportional_output=((double)controllerType[0])*(p_O+(k_proportional*valueP));
      integral_output=((double)controllerType[1])*(k_integral*valueI);
      derivative_output=((double)controllerType[2])*(k_derivative*valueD);
      //System.out.println("proportional_output="+proportional_output+"; integral_output="
                          // +integral_output+"; derivative_output="+derivative_output);
      
      controller_output=proportional_output + integral_output + derivative_output;
      //System.out.println("controller output = "+controller_output);
      
      new_process_input=controller_output;
      old_error=new_error;
      
      measuredVariable=tank.processOutput(new_process_input, measuredVariable, delta_time);
      System.out.println("The output signal is "+measuredVariable);
      
      iterations++;
     }while((Math.abs(new_error)>tolerance)&&(iterations<maximumIterations));

     //System.out.println("error="+height_setPoint-measuredVariable);
   System.out.println("The output signal is "+measuredVariable);
   System.out.println("The number of iterations is "+iterations);
    
  }//end of main method
  //read.close();
  
}//end of main class method