package strings;

import java.util.ArrayList;
import java.util.List;

public class Manipulation {
	
	/**
	 * this method takes recursively removes the first element of a string and prints it out
	 * @param string
	 */
	public void cutdown(String string){
		if(string.length()==0) return;
		
		int i = 0;
		System.out.println(string);
		cutdown(string.substring(++i));
	}
	
	public boolean checkIfStringIsEmpty(int length){
		if(length>0) return true;
		return false;
	}
	
	/**
	 * @param size - size/length of String s
	 */
	private void writeBackword (String s, int size){
		if(checkIfStringIsEmpty(size)){
			System.out.print(s.charAt(size-1));
      	writeBackword(s,size-1);
      	}
      }

	
	public static void main (String args[]){
		Manipulation wb = new Manipulation();
		String jas = "jasmine";
		List<String> strings = new ArrayList<>();
		wb.cutdown(jas);
		wb.writeBackword(jas,jas.length());
	}
}
