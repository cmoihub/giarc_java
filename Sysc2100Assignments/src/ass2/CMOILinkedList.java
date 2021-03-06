package ass2;

public class CMOILinkedList <T> {
	private Node<T> first = null;

    public void add (T data) {
    	if(first == null) {
    		first = new Node<T>(data);
    	}else{
    		Node <T> newNode = new Node<T>(data);
    		Node <T> curr = first;
    		if (curr != null) {
    			// iterate to the end of the list and then create element after last node
    			while (curr.next != null) {
    				curr = curr.next;
    			}
    			// the last node's "next" reference is set to our new node
    			curr.next = newNode;
    		}
    	}
    }
    
    public void add (T data, int index){
    	if(first == null) {
    		first = new Node<T>(data);
    	}else{
    		Node<T> newNode = new Node<T>(data);
        	Node <T> curr = first;
        	
        	if(curr!=null){
        		for(int i = 0; i<index && curr.next == null; i++){
    				curr = curr.next;
    			}
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
//  	 public T get(int position){
//  		//iteration
//  		Node <T> curr = first;
//  		if(first!=null){
//  			curr = first.next;
//  			for(int i = 0; i<position; i++){
//  				if(curr.next == null) 
//  					return null;
//  				curr = curr.next;
//  			}
//  			return curr.data;
//  		}
//  		return curr.data;
//  	}
//  	 
  	 public T get(int index)
  	 {
  		int count = 0;
  		Node<T> curr = first;
    	while(curr!=null){
    		if(count == index)
        		return curr.data;
    		count ++;
    		curr = curr.next;
    	}
		return null;
  	 }
    private int size(){
    	int count = 0;
    	for (Node<T> curr = first; curr != null; curr = curr.next){
    		count++;
    	}
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

    public boolean isEmpty() {
        return first == null;
    }
    
    public void displayList(){
    	System.out.println(this.size() + "items:  "+  this);
    	}
    
	public static void main (String [] args){
		CMOILinkedList <String> tldr = new CMOILinkedList<String>();
		
		System.out.println("test add");
		tldr.add("Greg");
		tldr.add("Franks");
		tldr.add("is");
		tldr.add("great");
		tldr.displayList();
		
		System.out.println("test remove");
		tldr.removeFirst();
		tldr.displayList();
		
		System.out.println("test remove all items");
		tldr.removeAll();
		if(tldr.isEmpty()) tldr.displayList();
		
		System.out.println("retesting add");
		tldr.add("Greg");
		tldr.add("Craig");
		tldr.displayList();
		
		System.out.println("test add at a given position");
		tldr.add("TA", 1);
		tldr.displayList();
		}

	private static class Node<T> {

        private final T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }
}