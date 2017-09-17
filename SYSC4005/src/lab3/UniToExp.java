package lab3;

import java.io.*;
import java.util.*;

public class UniToExp {
	private static double lambda = 5.0;
	private static int max = 100000;
	
	private static List<Double> importTimes(String path) {
		//File Opening
		String fileName = "/100K_uniform.txt";
		FileReader exp = null;
		try {
			exp = new FileReader(path + fileName); 
			}	catch(FileNotFoundException e){
				System.out.println("error opening file" + e); 
				}
		
		//Inverse-Transform Technique
		Scanner input = new Scanner(exp);
		ArrayList<Double> converted = new ArrayList<>();
		for (int i = 0; i<max; i++){
			Double num = input.nextDouble(); 
			converted.add(converter(num));
		}
		input.close();
		return converted;
	}
	
	private static Double converter(Double x){
		return Math.log(1-x)/-lambda;
	}
	
	private static void generateFile(String path, List<Double> data){
		File logFile = new File(path,"100K_exp.txt");//create a temporary file
		try(BufferedWriter writer = new BufferedWriter (new FileWriter(logFile))){
			//add values to the file
            for(int i = 0; i<max; i++){
            	writer.write(data.get(i).toString());
            	writer.newLine();
            }
		} catch(IOException e){
			System.out.println("error making file" + e);
		}
	}
	
	public static void main(String [] args){
		String path = "/Users/CraigBook/OneDrive/Documents/Carleton/"
				+ "YEAR4/Winter2017/SYSC4005A-L1/Labs/lab3";
		List<Double> data = importTimes(path);
		generateFile(path, data);
	}
}