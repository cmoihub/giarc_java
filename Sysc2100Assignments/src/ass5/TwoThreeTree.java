package ass5;
//http://pages.cs.wisc.edu/~paton/readings/Old/fall01/2-3Tree.html
public class TwoThreeTree   {
	private static class Node {
		protected int key;
		protected int value;
		private Node left, middle, right;
		private Node first, second, parent;
		private Node temp;
		
		public Node(int key, int value){
			set(key, value);
		}
		
		private void set(int key, int value){
			this.key = key;
			this.value = value;
		}
		
		private boolean isLeaf(){
			if(this.left == null && this.right == null) return true;
			return false;
		}
		
		private boolean is2Node(){
			if(this.first!=null && this.second == null) return true;
			return false;
		}
		
		private boolean is3Node(){
			if(this.second != null) return true;
			return false;
		}
	}
	
	private Node root, curr;
	private void add(int value){
		if(root == null){
			root = new Node(value, value);
		}
		else add(root, value);
	}
	private void add(Node node, int value) {
		//leaf at which value can be put
		Node leaf = get(node, value);
		Node newNode = new Node(value, value);
		if(leaf.is2Node()){
			if (leaf.value < newNode.value) leaf.second = newNode;
			else {
				leaf.second = leaf.first;
				leaf.first.set(value, value);
			}
		}
		if(leaf.is3Node()){
			if (newNode.value <= leaf.first.value){
				leaf.temp = leaf.first;
				leaf.first = newNode;
				leaf.temp.left = leaf.first;
				leaf.temp.right = leaf.second;
				leaf = leaf.temp;
			}
			else if (newNode.value > leaf.second.value){
				leaf.temp = leaf.second;
				leaf.second = newNode;
				leaf.temp.left = leaf.first;
				leaf.temp.right = leaf.second;
				leaf = leaf.temp;
			}
			else{
				leaf.temp = newNode;
				leaf.temp.left = leaf.first;
				leaf.temp.right = leaf.second;
				leaf = leaf.temp;
			}
		}
	}
	private void traverse(Node node){
		if(node == null) return;//empty tree
		else if(node.isLeaf()) System.out.println(" " + node.value);//Leaf
		else if (node.is2Node()){
			traverse(node.left);
			System.out.print(" " + node.first.value);
			traverse(node.right);
		}//TwoNode
		else if(node.is3Node()){
			traverse(node.left);
			System.out.print(" " + node.first.value);
			traverse(node.middle);
			System.out.print(" " + node.second.value);
			traverse(node.right);
		}//ThreeNode
	}
	
	private Node get(Node node, int key){
		if(node == null) return null;
		if(node.isLeaf()){
			return node;
		}
		else if(node.is2Node()){
			if(node.key>key) return get(node.left, key);
			else return get(node.right, key);
		}
		else{
			if(node.first.key>key) return get (node.left, key);
			else if(node.second.key<key) return get (node.right, key);
			else return get(node.middle, key);
		}
	}
	
	private void cut(int key){
	}
	
	private void print(){ traverse(root); }
	
	public static void main (String args []){
		TwoThreeTree tree = new TwoThreeTree ();
		for (int i = 1; i<12; i++){
			tree.add(i);
		}
		tree.print();
		tree.cut(3);
		tree.cut(7);
		tree.print();
		tree.cut(13);
	}
}