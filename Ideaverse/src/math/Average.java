package math;
import java.util.*;

public class Average {
	List<Double> numbersToAverage;
	private Scanner gui;
	public Average() {
		numbersToAverage = new ArrayList<>();
		gui = new Scanner(System.in);
	}
	
	public Average(ArrayList<Double> numbers){
		numbersToAverage = numbers;
	}
	
	public double average () {
		double sum = 0;
		if(numbersToAverage.size() == 0) return sum;
		for(double i : numbersToAverage){
			sum+=i;
		}
		return sum/numbersToAverage.size();
	}

	public void console(){
		while(true){
			if(gui.nextLine().trim() == " ") System.exit(0);
			int number = gui.nextInt();
			if(number == 0) System.exit(0);
			numbersToAverage.add((double) number);
			System.out.println(number);
			System.out.println(average());
		}
	}
	
	public static void main(String args[]){
		Average mean = new Average(); 
		mean.console();
	}
}