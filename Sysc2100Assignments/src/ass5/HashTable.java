package ass5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class HashTable{
	private static final int INIT_CAPACITY = 3;
	private int size;
	private ArrayList<HashNode> bucket;
	private static int tableSize;
	
	public HashTable(){
		bucket = new ArrayList<>();
		size = 0;
		tableSize = 7;
		for (int i = 0; i < tableSize; i++){
			bucket.add(null);//create empty chains
		}
	}
	
	public int size(){return size;}
	
	public void retrieve (String item){
		HashNode head = bucket.get(hash(item));
		while (head!=null)//search for item in chain
		{
			if(head.item.equals(item))
				System.out.println("Item found");
			head = head.next;
		}
		insert(item);
	}
	
	public void insert(String item){
		int index = hash(item);
		HashNode head = bucket.get(index);
		while(head!=null)//check if item is present
		{
			if(head.item.equals(item))return;
			head = head.next;
		}
		//Add item
		System.out.println("Item added");
		size++;
		head = bucket.get(index);
		HashNode newNode = new HashNode(item);
		newNode.next = head;
		bucket.set(index, newNode);
		if((1.0*size)/tableSize>=0.7) increase();
	}
	
	public void increase(){
		ArrayList <HashNode> temp = bucket;
		bucket = new ArrayList<>();
		tableSize *= 2;
		size = 0;
		for (int i = 0; i < tableSize; i++){
			bucket.add(null);
		}for(HashNode node : temp){
			while (node!=null){
				insert (node.item);
				node = node.next;
			}
		}
	}
	
	/**
	 * Hash function suggested by Professor Franks
	 * It utilizes modulo arithmetic
	 * @param word search item specified by user
	 * @return
	 */
	private int hash (String word){
		return horner(word) % tableSize;
	}
	
	/**
	 * Given a word convert it's letters to numbers
	 * The numbers act as coeffecients for a polynomial equation based on their position
	 * Given ax^3 + bx^2 + cx + d
	 * It would be simplified to ((((0)x+a)x+b))x+c)x+d
	 * Given ax^2 + bx + c
	 * It would be simplified to (((0)x+a)x+b))x+c
	 * The max power of x is the size of the list
	 * @param word specified by user
	 * @return string converted to a number
	 */
	private int horner(String word){
		List<Integer> letters = generateAscii(word);
		int x = 2;
		int sum = 0;
		for (int i = 0; i < letters.size(); i++){
			sum = sum*x + letters.get(i);
		}
		return sum;
	}
	
	/**
	 * Converts letters in a given word to their ASCII equivalents
	 * @param word specified by user
	 * @return individual letters converted to numbers 
	 */
	private List<Integer> generateAscii(String word){
		List<Integer> asciiList = new ArrayList<>();
		for (char ascii: word.toCharArray()){
			asciiList.add((int)ascii);
		}
		return asciiList;
	}

	public List<String> generateStrings(){
		List<String> randomStrings = null;
		return randomStrings;
	}
	
	public static void main (String [] args){
		HashTable table = new HashTable();
		table.insert("foo");
		table.insert("bar");
		table.insert("baz");
		System.out.println(table.size());
		table.retrieve("XXX");
		table.retrieve("bar");
		//System.out.println(table.retrieve("XXX"));
		//System.out.println(table.retrieve("bar"));
		System.out.println(table.size());
	}
	
	private String getInput(){
		Scanner scan = new Scanner(System.in);
		System.out.println("What word do you wanna make a number");
		String input = scan.nextLine();
		scan.close();
		return input;
	}
	
	private static class HashNode{
		String item;
		HashNode next;//references next node
		public HashNode(String key){
			item = key;
			this.next = next;
		}
	}
}