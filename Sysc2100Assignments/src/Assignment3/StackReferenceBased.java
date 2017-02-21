package Assignment3;

import Assignment2.CMOILinkedList;
import Assignment2.Node;

public class StackReferenceBased<T> {

	//private Node <T> top;
	//private Object items;
	private CMOILinkedList <T> cll;
	
	public StackReferenceBased (){
		createStack();
	}
	
	//create new linkedlist implemented stack
	void createStack(){
		cll = new CMOILinkedList<>();
		//top = cll.getFirst();
	}
	
	//remove all items in the stack
	void popAll(){
		cll.removeAll();
	}
	
	//check if stack is empty
	boolean isEmpty(){
		return cll.isEmpty();
//		return top == null;
	}
	
	//put item on top of stack
	void push(T item){
		cll.addFirst(item);
//		if(top==null) top = new Node <T> (item);
//		else top = new Node <T> (item,top);
	}
	
	//remove most recently added item
	T pop(){
		return cll.removeFirst();
//		T oldTop = top.getData();
//		top = top.getNext();
//		return oldTop;
	}
	
	//check most recently added item
	T peek(){
		T temp = pop();
		push(temp);
		return temp;
//		//System.out.println(temp);
//		return top.getData();
	}
	
	public String toString(){
		return cll.toString();
	}
	
	public static void main (String [] args){
		StackReferenceBased <String> srb = new StackReferenceBased <>(); 
		srb.push("1");
		srb.peek();
	}
}