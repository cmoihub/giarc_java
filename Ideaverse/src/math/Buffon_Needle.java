package math;

import java.util.Random;

public class Buffon_Needle {
	public static void main(String[] args){
		Random r = new Random();
		int b = 0;
		double o = r.nextDouble();
		double a = b*Math.sin(o);
		double y = 0;
		for (int i = 0; i < 15; i++){
			System.out.print("Y = " + y + "|||");
			System.out.println("Angle = ");
		}
		
		for (int i = 0; i < 100; i++){
			System.out.print("Y = " + y + "|||");
			System.out.println("Angle = " + o);
		}
	}

}