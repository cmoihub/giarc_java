package Assignment2;
import java.io.*;
import java.util.*;
//Assignment 2b
public class CountSubstrings {
	public static void main(String[] args) { CountSubstrings.loop(); }
	
	private static void loop(){
		while(true){
			Scanner input = new Scanner(System.in);
			System.out.println("Type any number to begin, Type 0 to end");
			if(input.nextInt()==0)	System.exit(0);
			else run();
		}
	}
	
	private static void run(){
			ArrayList<Character> pattern1 = new ArrayList<Character>();
			LinkedList<Character> pattern2 = new LinkedList<Character>();

			ArrayList<Character> input_string1 = new ArrayList<Character>();
			LinkedList<Character> input_string2 = new LinkedList<Character>();

			String file_name = getFile();
			String input = getPattern();

			convertStringToList(input, pattern1);
			convertStringToList(input, pattern2);
			List<Object> output = timer(file_name,pattern1, input_string1);
			System.out.println("Using ArrayLists: " + output.get(0) + 
	            " matches, derived in "+ output.get(1) +
	            " milliseconds.");
			
			output = timer(file_name,pattern2, input_string2);
			System.out.println("Using LinkedLists: " + output.get(0) + 
	            " matches, derived in "+ output.get(1) +
	            " milliseconds.");
	}

	/*
	 * Repeatedly prompt user for filename until a file with such a name exists
	 * and can be opened.
	 */
	private static String getFile() 
	{
		BufferedReader keyboardReader = new BufferedReader(
				new InputStreamReader(System.in));

		String inFilePath = "";
		BufferedReader inFileReader;
		boolean pathsOK = false;

		while (!pathsOK) 
		{
			try 
			{
				System.out.print("Please enter the path for the input file: ");
				//Example: src/Les-Miserables.txt
				inFilePath = keyboardReader.readLine();
				inFileReader = new BufferedReader(new FileReader(inFilePath));
				pathsOK = true;
				inFileReader.close();
			} // try
			catch (IOException e) {System.out.println(e);} // catch I/O exception
		} // while
		return inFilePath;
	} // method openFiles

	// read in substring pattern, catching any exceptions
	private static String getPattern(){
		BufferedReader keyboardReader = new BufferedReader(
				new InputStreamReader(System.in));
		String input = new String();
		try {
			while (true) {
				System.out.print("Enter the pattern to look for: ");
				//Example:Javert
				input = keyboardReader.readLine();
				break;
			}
		} catch (IOException e) {System.out.println(e);}
		return input;
	}

	/*
	 * Helper method to convert a string to a List. Loops over all characters in
	 * the string and may not be all that efficient - may be better to read in
	 * the file character by character until we hit whitespace.
	 */
	private static void convertStringToList(String in, List<Character> out) 
	{
		char[] input_chars = in.toCharArray();
		out.clear();
		for (int i = 0; i < input_chars.length; i++) 
		{
			out.add(input_chars[i]);
		}
	}

	/*
	 * Iterate over all strings in input file to determine whether the input
	 * string is a substring in any of these strings. Returns the number of
	 * times such a match exists.
	 */
	public static int readAndMatchDocument(String filename,
			List<Character> pattern, List<Character> Input)
					throws FileNotFoundException {
		StringTokenizer tokens;
		String line, textword;
		int count = 0;
		// open file anew to ensure we start at the first character
		BufferedReader inFileReader = new BufferedReader(new FileReader(
				filename));

		try 
		{
			while (true) 
			{
				line = inFileReader.readLine();
				//move on if invalid line
				if (line == null) break;
				tokens = new StringTokenizer(line);
				// for all the words in the line
				while (tokens.hasMoreTokens()) 
				{
					textword = tokens.nextToken();
					convertStringToList(textword, Input);
					if (findBrute(Input, pattern) != -1) count = count + 1;
				} // end while tokens
			} // end while true
		} 
		catch (IOException e) { System.out.println(e);}
		
		try {inFileReader.close();} 
		catch (IOException e) { e.printStackTrace(); }
		
		return count;
	}
	
	/*
	 * Supplied by Professor
	 * Returns the lowest index at which substring pattern begins in text (or
	 * else -1).
	 */
	private static int findBrute(List<Character> text, List<Character> pattern) {
		int n = text.size();
		int m = pattern.size();
		for (int i = 0; i <= n - m; i++) { // try every starting index within    
			// text
			int k = 0; // k is index into pattern
			while (k < m && text.get(i + k) == pattern.get(k))
				// kth character of pattern matches
				k++;
			if (k == m) // if we reach the end of the pattern,
				return i; // substring text[i..i+m-1] is a match
		}
		return -1; // search failed
	}
	
   //measure execution time
   private static List<Object> timer(String file_name, 
  		 List<Character> pattern1, List<Character> input_string1)
   {
   	ArrayList<Object> objects = new ArrayList<>();
  	 try 
  	 {
         double startTime = System.currentTimeMillis();
         int final_count1 = readAndMatchDocument(file_name, pattern1,
                 input_string1);
         double endTime = System.currentTimeMillis();
         objects.add(final_count1);
         objects.add(endTime-startTime);
         } 
  	 catch (IOException e) {System.out.println(e);}
  	 
  	 return objects;
   }
}