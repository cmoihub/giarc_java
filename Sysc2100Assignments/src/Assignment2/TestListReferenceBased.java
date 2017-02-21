package Assignment2;
import java.util.Random;

public class TestListReferenceBased {
	private int nodeCount;//size of linked list or number of nodes
	private Node head;//first node
	private Node curr;
	public static void main(String[] args) {
		TestListReferenceBased tldr = new TestListReferenceBased();
		
		System.out.println("test add");
		tldr.add("Greg");
		tldr.add("Franks");
		tldr.add("is");
		tldr.add("great");
		tldr.display();
		
		System.out.println("test remove");
		tldr.remove(1);
		tldr.display();
		
		System.out.println("test remove all items");
		tldr.removeAll();
		if(tldr.isEmpty()) tldr.display();
		
		System.out.println("retesting add");
		tldr.generateRandomValues();
		tldr.display();
		
		System.out.println("test add at a given position");
		tldr.add("TA", 1);
		tldr.display();
	}
	
	public TestListReferenceBased(){
		removeAll();
		}
	int size(){return nodeCount;}
	private void increaseSize(){nodeCount++;}
	private void decreaseSize(){nodeCount--;} 
	public boolean isEmpty(){return nodeCount==0;}
	
	/*
	 * this method traverses to the end of the list 
	 * and adds a new node containing data
	 * In the case that there is no head, 
	 * head will contain the data
	 */
	void add(Object data) {
		if (head == null) head = new Node(data);
		
		Node newNode = new Node(data);
		Node curr = head;
		if (curr != null) {
			// iterate to the end of the list and then create element after last node
			while (curr.getNextNode() != null) {
				curr = curr.getNextNode();
			}
			// the last node's "next" reference is set to our new node
			curr.setNextNode(newNode);
		}
		increaseSize();
	}
	
	//method for testing add
	void generateRandomValues(){
		Random rand = new Random ();
		for (int i = 0; i < rand.nextInt(30); i++){
			this.add(rand.nextInt(10));
		}
	}
	
	/*
	 * this traverses to the indicated position
	 * then it adds a node at that place
	 */
	void add (Object data, int position){
		Node newNode = new Node(data);
		Node curr = head;
		if(curr!=null)
			for(int i = 0; i<position && curr.getNextNode() == null; i++){
				curr = curr.getNextNode();
			}
		newNode.setNextNode(curr.getNextNode());
		curr.setNextNode(newNode);
		increaseSize();
	}
	
	//clear up the contents of the current linked list
	public void removeAll(){
		head = new Node(null);
		nodeCount = 0;
	}
	
	//remove element at specified position of list
	void remove(int position){
		//out of range case
		if(position<0||position>size())
			System.out.println("no such position, "
					+ "index is out of range");
		//iteration
		Node curr = head;
		if(head!=null){
			for(int i = 0; i < position; i++){
				if(curr.getNextNode() == null) 
					return;
				curr = curr.getNextNode();
			}
			curr.setNextNode(curr.getNextNode().getNextNode());
			decreaseSize();
		}
	}
	
	//return the element at the specified position of the list
	 public Object get(int position){
		//invalid position case
		if(position<0||position>size()) {
			System.out.println("no such position, "
					+ "index is out of range");
			return null;
		}
		//iteration
		Node curr = null;
		if(head!=null){
			curr = head.getNextNode();
			for(int i = 0; i<position; i++){
				if(curr.getNextNode() == null) 
					return null;
				curr = curr.getNextNode();
			}
			return curr.getNodeContent();
		}
		return curr;
	}
	 
	 public void displayList() {
		    Node current = head;
		    while (current != null) {
		        //current.displayNode();
		        current = current.getNextNode();
		    }
		}
	
	public String toString(){
		String output = "";
		output+=this.size() + " items in the linked list:";
		for (int i = 0; i<this.size(); i++){
			output += " " + this.get(i);
		}
		return output;
	}
	
	public void display(){System.out.println(this);}
	
	//inner class, bad idea maybe?
	private static class Node {
		Object nodeContent;
		Node nextNode;
		//most likely head node
		Node(Object data) {this(data, null);}
		//given you know which node to point to
		Node (Object data, Node nextNode){
			setNodeContents(data);
			setNextNode(nextNode);
		}
		
		Object displayNode(){
			return nextNode;
		}
		
		/*
		 * The following methods are getters and setters for the Class' variables
		 */
		Object getNodeContent(){return nodeContent;}
		void setNodeContents(Object newData){nodeContent = newData;}
		Node getNextNode(){return nextNode;}
		void setNextNode(Node next){nextNode = next;}
	}
}