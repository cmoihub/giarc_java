package ass1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class ConfigurationCounting {

	private static int numberOfChannels;
	private static int numberOfNodes;
	private static int index = 0;
	private static ArrayList <int[]> availableCombos;
	private static int[] setOfChannels;
	
	//infinite loop to ease testing
	private void start(){
		while(true){
			getInput();//refreshes variables on each iteration
			generate(numberOfNodes, setOfChannels, index, numberOfNodes);
			totalAssignments(availableCombos, numberOfNodes);			
		}
	}
	
	/*
	 * set up variables using user input or end program
	 */
	private void getInput(){
		Scanner input = new Scanner(System.in);
		System.out.println("Type any number to begin, Type 0 to end");
		if(input.nextInt()==0)	System.exit(0);
		else{
			//Nodes
			System.out.println("How many Nodes?");
			numberOfNodes = input.nextInt();
			//Channels
			System.out.println("How many Channels?");
			numberOfChannels = input.nextInt();
			setOfChannels = new int[numberOfChannels];
			System.out.println(numberOfNodes + " Nodes, " + numberOfChannels + " Channels: ");
			availableCombos = new ArrayList<>();
		}
	}
	
	/*
	 * The base case is the last channel
	 * Set the channel to have the value of nodes
	 * currentChannel should be set to 0 as this is the base that lists in java work with
	 *  it is incremented with every call so as to fill up next channel
	 * remaining nodes helps to keep track of nodes to put into channels based on nodes in previous channels
	 * As arrays are filled they are copied into the list of available combinations
	 */
	private void generate(int nodes, int[] combo, int currentChannel, int remainingNodes){
		if(currentChannel<combo.length-1){
			if(currentChannel!=0) remainingNodes-=combo[currentChannel-1];
			for(int i = 0; i<=remainingNodes;i++){
				combo[currentChannel] = i;
				generate(nodes-i,combo,currentChannel+1,remainingNodes);
			}
		}
		//base case
		if(currentChannel==combo.length-1){
			combo[currentChannel] = nodes;
			int[] channelSet = new int[combo.length];
			for (int i = 0;i<combo.length;i++){
				channelSet[i] = combo[i];
			}
			availableCombos.add(channelSet);
		}
		//value of current channel should never be greater than the number of channels
		assert false; 
	}
	
	/*
	 * computes the total number of combos and displays sets of channels that were generated
	 */
	private void totalAssignments(List<int[] > combos, int nodes){
		long totalCombos = 0;
		for (int[]combo: combos){
			totalCombos+=countCombos(combo,nodes,0);
			System.out.println(countCombos(combo,nodes,0) + " set(s) with occupancies: " + Arrays.toString(combo));
		}
		System.out.println("Total number of assignments: " + totalCombos);
	}
	
	/*
	 * uses the binomial coefficient to determine how many times a combo should appear
	 */
	private static long countCombos(int[] combo, int nodes, int currentChannel){
		if(currentChannel<combo.length) 
			return binomial (nodes,combo[currentChannel])*countCombos(combo, nodes-combo[currentChannel],currentChannel+1);
		return 1;
	}
	
	//continually gets the binomial of a decremented n & k plus the binomial of only a decremented n
	private static long binomial(int n, int k){
		if(k==n||k==0) return 1;
		return binomial(n-1,k-1)+binomial(n-1,k);
	}
	
	public static void main(String args[]){ 
		ConfigurationCounting config = new ConfigurationCounting();
		config.start();
		}
}