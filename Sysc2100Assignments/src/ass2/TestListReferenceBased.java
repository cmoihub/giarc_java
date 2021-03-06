package ass2;
import java.util.Random;

//import Assignment2.CMOILinkedList.Node;

public class TestListReferenceBased <T> {
	private int nodeCount;//size of linked list or number of nodes
	private Node <T> head;//first node
	//private Node curr;
	public static void main(String[] args) {
		TestListReferenceBased <Object> tldr = new TestListReferenceBased <> ();
		
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
		
//		System.out.println("retesting add");
//		tldr.generateRandomValues();
//		tldr.display();
		
		System.out.println("test add at a given position");
		tldr.add("TA", 1);
		tldr.display();
	}
	
	/*public TestListReferenceBased <T> (){
		removeAll();
		}*/
	int size(){return nodeCount;}
	//private void increaseSize(){nodeCount++;}
	//private void decreaseSize(){nodeCount--;} 
	//public boolean isEmpty(){return nodeCount==0;}
	public boolean isEmpty(){return head == null;}
	/*
	 * this method traverses to the end of the list 
	 * and adds a new node containing data
	 * In the case that there is no head, 
	 * head will contain the data
	 */
	void add(T data) {
		if (head == null) head = new Node <T> (data);
		
		Node <T> newNode = new Node <T> (data);
		Node <T> curr = head;
		if (curr != null) {
			// iterate to the end of the list and then create element after last node
			while (curr.next != null) {
				curr = curr.next;
			}
			// the last node's "next" reference is set to our new node
			curr.next = newNode;
		}
		//increaseSize();
	}
	
	//method for testing add
//	void generateRandomValues(){
//		Random rand = new Random ();
//		for (int i = 0; i < rand.nextInt(30); i++){
//			this.add(rand.nextInt(10));
//		}
//	}
	
	/*
	 * this traverses to the indicated position
	 * then it adds a node at that place
	 */
	public void add (T data, int position){
		if(head == null) {
    		head = new Node<T>(data);
    	}
		Node <T> newNode = new Node <T> (data);
		Node <T> curr = head;
		if(curr!=null)
			for(int i = 0; i<position && curr.next == null; i++){
				curr = curr.next;
			}
		newNode.next = curr.next;
		curr.next = newNode;
		//increaseSize();
	}
	
	//clear up the contents of the current linked list
	public void removeAll(){
		head = new Node <T> (null);
		nodeCount = 0;
	}
	
	//remove element at specified position of list
	public void remove(int position){
		//out of range case
		if(position<0||position>size())
			System.out.println("no such position, "
					+ "index is out of range");
		//iteration
		Node <T> curr = head;
		if(head!=null){
			for(int i = 0; i < position; i++){
				if(curr.next == null) 
					return;
				curr = curr.next;
			}
			curr.next = curr.next.next;
			//curr.setNextNode(curr.getNextNode().getNextNode());
			//decreaseSize();
		}
	}
	
	//return the element at the specified position of the list
	 public T get(int position){
		//iteration
		Node <T> curr = head;
		if(head!=null){
			curr = head.next;
			for(int i = 0; i<position; i++){
				if(curr.next == null) 
					return null;
				curr = curr.next;
			}
			return curr.data;
		}
		return curr.data;
	}
	 
	 public void displayList() {
		    Node <T> current = head;
		    while (current != null) {
		        //current.displayNode();
		        current = current.next;
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
	
	private static class Node<T> {

        final T data;
        public Node<T> next;

        public Node (T data) {this(data, null);}

		public Node(T data_, Node <T> next_) {
        	next = next_;
            data = data_;
        }

        @Override
        public String toString() {
            return data.toString();
        }
        }
}