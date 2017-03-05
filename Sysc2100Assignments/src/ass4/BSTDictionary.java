package ass4;

public class BSTDictionary<E,K extends Sortable> implements Dictionary<E,K> {

	BSTNode <E, K> root;
	BSTNode <E, K> curr;
	
	public BSTDictionary(){
		root = null;
	}//Default constructor
	
	@Override
	public E search(K key) {
		if(curr == null)
		{
			curr = root;
			return null;
		}// key not found
		
		if(curr.getKey().compareTo(key) == 0){
			return curr.getElement();
		}//if key is the one at the current node
		
		else if(curr.getKey().compareTo(key) < 0){
			return search(curr.getLeft().getKey());
		}//left child check
		
		else/* if(curr.getKey().compareTo(key) > 0)*/{
			return search(curr.getRight().getKey());
		}//right child check
	}//return the value corresponding to a specified Key k

	@Override
	public void insert(K key, E element) {
		BSTNode<E,K> temp =new BSTNode<E, K>(key, element,null,null);
		if(root == null){
			root = temp;
			//curr = root;
		}//empty tree
		search(key);//overwrite duplicates
		
	}//insert a key-value pair into the binary search tree

	@Override
	public void delete(K key) {
		
	}//remove the entry in the binary search tree corresponding to key K

	@Override
	public void printTree() {
		
	}//print the binary search tree

	@Override
	public int depth() {

		return 0;
	}//return the depth of the binary search tree
}
