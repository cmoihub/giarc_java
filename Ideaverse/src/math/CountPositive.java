package math;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CountPositive {

	private static void countPositive(List<Integer> numbersToCount){
		int x=0;
		for (int i:numbersToCount){
			if(i>0) x++;
		}
		System.out.println(x);
		System.exit(0);
	}
	
	
	private static void console(){
		Scanner scan = new Scanner(System.in);
		ArrayList<Integer> list = new ArrayList<>();
		int index = 0;
		while (index<9){
			list.add(scan.nextInt());
			index++;
		}
		countPositive(list);
	}
	
	public static void main(String[] args){	
		console();
	}
}