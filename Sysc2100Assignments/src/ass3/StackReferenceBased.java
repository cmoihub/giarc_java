package ass3;

import ass2.CMOILinkedList;

public class StackReferenceBased<T> {

	//private Node <T> top;
	//private Object items;
	//private TestListReferenceBased <T> tldr;
	private CMOILinkedList <T> cll;
	
	public StackReferenceBased (){
		createStack();
	}
	
	//create new linkedlist implemented stack
	void createStack(){
		cll = new CMOILinkedList<>();
		//tldr = new TestListReferenceBased <> ();
		//top = cll.getFirst();
	}
	
	//remove all items in the stack
	void popAll(){
		cll.removeAll();
		//tldr.removeAll();
	}
	
	//check if stack is empty
	boolean isEmpty(){
		return cll.isEmpty();
		//return tldr.isEmpty();
//		return top == null;
	}
	
	//put item on top of stack
	void push(T item){
		//tldr.add(item, 0);
		cll.addFirst(item);
//		if(top==null) top = new Node <T> (item);
//		else top = new Node <T> (item,top);
	}
	
	//remove most recently added item
	T pop(){
		return cll.removeFirst();
//		T item = tldr.get(0);
//		tldr.remove(0);
//		return item;
//		T oldTop = top.getData();
//		top = top.getNext();
//		return oldTop;
	}
	
	//check most recently added item
	T peek(){
		return cll.get(0);
//		//System.out.println(temp);
//		return top.getData();
	}
	
	public String toString(){
		return cll.toString();
	}
	
	public static void main (String [] args){
		StackReferenceBased <String> srb = new StackReferenceBased <>(); 
		srb.push("1");
		System.out.println(srb);
	}
}