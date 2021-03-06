package Submissions;

//import Assignment2.CMOILinkedList;

//import Submissions.CMOILinkedList.Node;

class StackReferenceBased<T> {
	
	private CMOILinkedList <T> cll;

	public StackReferenceBased (){
		createStack();
	}
	
	//create new linkedlist implemented stack
	void createStack(){
		cll = new CMOILinkedList<>();
	}
	
	//remove all items in the stack
	void popAll(){
		cll.removeAll();
	}
	
	//check if stack is empty
	boolean isEmpty(){
		return cll.isEmpty();
	}
	
	//put item on top of stack
	void push(T item){
		cll.addFirst(item);
	}
	
	//remove most recently added item
	T pop(){
		return cll.removeFirst();
	}
	
	//check most recently added item
	T peek(){
		return cll.get(0);
	}
	
	public String toString(){
		return cll.toString();
	}
	
	public static void main (String [] args){
		StackReferenceBased <String> srb = new StackReferenceBased<>(); 
		srb.push("1");
		System.out.println(srb);
	}
	
	private static class CMOILinkedList <T> {
		private Node<T> first = null;

	    @SuppressWarnings("unused")
		public void add (T data) {
	    	if(first == null) {
	    		first = new Node<T>(data);
	    	}else{
	    		Node <T> newNode = new Node<T>(data);
	    		Node <T> curr = first;
	    		if (curr != null) 
	    			// iterate to the end of the list and then create element after last node
	    			while (curr.next != null) {
	    				curr = curr.next;
	    			// the last node's "next" reference is set to our new node
	    			curr.next = newNode;
	    		}
	    	}
	    }
	    
	    @SuppressWarnings("unused")
		public void add (T data, int index){
	    	if(first == null) {
	    		first = new Node<T>(data);
	    	}else{
	    		Node<T> newNode = new Node<T>(data);
	        	Node <T> curr = first;
	        	
	        	if(curr!=null)
	        		for(int i = 0; i<index && curr.next == null; i++){
	    				curr = curr.next;
	    			}
	        	
	        	
	        	newNode.next = curr.next;
	        	curr.next = newNode;
	    	}
	    }
	    
	    public void addFirst(T data){
	    	Node<T> newFirst = new Node<T>(data);
	        newFirst.next = first;
	        first = newFirst;
	    }

	    public T removeFirst() {
	        Node<T> oldFirst = first;
	        first = first.next;
	        return oldFirst.data;
	    }
	    
	    public void removeAll(){
	    	first = null;
	    }
	    
	  //return the element at the specified position of the list 
	  	 public T get(int index)
	  	 {
	  		int count = 0;
	  		Node<T> curr = first;
	    	while(curr!=null){
	    		if(count == index) return curr.data;
	    		count ++;
	    		curr = curr.next;
	    	}
			return null;
	  	 }
	  	 
	    private int size(){
	    	int count = 0;
	    	for (Node<T> curr = first; curr != null; curr = curr.next){ count++; }
	    	return count;
	    }

	    @Override
	    public String toString() {
	        StringBuilder builder = new StringBuilder();
	        //builder.append(this.size() + " items: ");
	        Node<T> current = first;
	        while (current != null) {
	            builder.append(current);
	            current = current.next;
	        }
	        return builder.toString();
	    }

	    public boolean isEmpty() { return first == null; }
	    
	    @SuppressWarnings("unused")
		public void displayList(){ System.out.println(this.size() + "items:  "+  this); }
	    
		private static class Node<T> {

	        private final T data;
	        private Node<T> next;

	        public Node(T data) {
	            this.data = data;
	        }

	        @Override
	        public String toString() {
	            return toString();
	        }
	    }
	}
}

