package Assignment2;

public class Node <T> {
	private T data;
    private Node<T> next;

    public Node(T data) {
        this.data = data;
    }
    
    public Node (T data, Node<T> next){
    	this.data = data;
    	this.next = next;
    }

    @Override
    public String toString() {
        return data.toString();
    }
    
    public T getData(){return data;}
	void setData(T newData){data = newData;}
	public Node <T> getNext(){return next;}
	void setNext(Node <T> next){this.next = next;}
}
