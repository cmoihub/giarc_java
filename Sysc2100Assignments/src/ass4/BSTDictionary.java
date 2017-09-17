package ass4;


//https://www.youtube.com/watch?v=FNeL18KsWPc
//http://www.algolist.net/Data_structures/Binary_search_tree

public class BSTDictionary<E,K extends Sortable> implements Dictionary<E,K> {

	BSTNode <E, K> root;
	BSTNode <E, K> curr;
	
	public BSTDictionary(){
		root = null;
	}//Default constructor
	
	public BSTDictionary(BSTNode <E, K> head){
		root = head;
	}// construct BST  with given node as root
	
	@Override
	public void insert(K key, E element) {
		if(root == null) //empty tree
			root = new BSTNode<> (key, element,null,null);
		else //otherwise find somewhere on the tree to insert it
			add(key, element, root);
	}//insert a key-value pair into the binary search tree

	public boolean add (K key, E element, BSTNode<E,K> curr){
		BSTNode<E,K> newNode = new BSTNode <> (key, element,null,null);
		
		if (curr.getKey().compareTo(key) == 0)
		{//node already exists; update current node
			curr.setElement(element);
			return false;
		}
		
		//key < curr.getKey() e.g. 5 < 6
		//so if inserting in a tree like 6-4(L)-7(R) ->6-4(L) (5(R))-7(R)
		//5 goes on left node of 6 which is 4, as it's larger than 4 it then goes on right of 4
		else if(curr.getKey().compareTo(key) < 0){
			if (curr.getLeft() == null)//if current node has no left child
			{
				curr.setLeft(newNode);
				return true;
			}else//recursively attempt to insert node on left of current node 
				return add(key, element, curr.getLeft());
		}
		
		//key > curr.getKey(); mirrors previous case
		//so given inserting 8 in the previous tree
		//6-4(L) (5(R))-7(R) (8(R))
		else if(curr.getKey().compareTo(key) > 0){
			if (curr.getRight() == null){
				curr.setRight(newNode);
				return true;
			}else 
				return add(key, element, curr.getRight());
		}
		return false;
	}//helper method for insert
	
	public void insertSam(K key, E element) {
		BSTNode<E,K> newNode = new BSTNode <> (key, element,null,null);
		if(root == null){
			root = newNode;
			curr = root;
		}//empty tree
		else if (curr.getKey().compareTo(newNode.getKey()) != 0){
			if (curr.getKey().compareTo(newNode.getKey()) < 0){
				if (curr.getLeft() == null) {
					curr.setLeft(newNode);
					curr = root;
				}//insert if current node has no left child
				else/*compare key with left child's key*/{
					curr = curr.getLeft();//traverse leftwise
					insertSam (key,element);//handle left child
				}
			}//inserting on the left
			
			else if (curr.getKey().compareTo(newNode.getKey()) > 0){
				if (curr.getRight() == null){
					curr.setRight(newNode);
					curr = root;
				}//insert if current node has no right child
				else/*compare key with right child's key*/{
					curr = curr.getRight();//traverse rightwise
					insertSam (key,element);//handle right child
				}
			}//inserting on the right
		}//find a place in the tree to insert it
		return;
	}//insert a key-value pair into the binary search tree
	
	@Override
	public E search(K key) {
		BSTNode <E, K> found = getNode(key);
		if (found == null)
			return null;
		else 
			return found.getElement();
	}
	
	public BSTNode <E, K> getNode(K key){
		BSTNode<E, K> curr = root;
		while(curr != null){
			if(curr.getKey().compareTo(key) == 0)
				{//if key is the one at the current node
					return curr;
					}
			else if(curr.getKey().compareTo(key) < 0){
				curr = curr.getLeft();
			}
			else{
				curr = curr.getRight();
			}
		}
		//key not found
		return null;
	}
	
	
	//curr = root is needed to compile
//	@Override
	public E search_(K key) {
		if(curr == null)
		{//unavailable key-value pair
			curr = root;
			return null;//need to compile
		} 
		if(curr.getKey().compareTo(key) == 0)
		{//if key is the one at the current node
			BSTNode<E,K> copy = new BSTNode <> (curr.getKey(), curr.getElement(), curr.getLeft(), curr.getRight());
			curr = root;
			return copy.getElement();
			}
		else if(curr.getKey().compareTo(key) < 0)
		{//left child check
			curr = curr.getLeft();
			return search(key);
			}

		else 
		{//right child check
			curr = curr.getRight();
			return search(key);
			}
	}//return the entry corresponding to a specified Key k
	
	@Override
	public void delete (K key){
		BSTNode <E, K> parent = root;
		BSTNode <E, K> curr = root;
		boolean isLeftChild = false;
		while (!curr.getKey().equals(key)){
			parent = curr;
			if (key.compareTo(curr.getKey())>0){
				isLeftChild = true;
				curr = curr.getLeft();
			}else{
				isLeftChild = true;
				curr = curr.getLeft();
			}
			if(curr == null){
				return;
			}
		}
		
		//No children case
		if(curr.getLeft() == null && curr.getRight() == null){
			if(curr == root)
				root = null;
			if(isLeftChild)
				parent.setLeft(null);
			else
				parent.setRight(null);
		}
		
		//No right child case
		else if (curr.getRight() == null){
			if (curr == root)
				root = curr.left;
			else if (isLeftChild)
				parent.left = curr.getLeft();
			else
				parent.right = curr.getLeft();
		} 
		
		//No left child case
		else if (curr.getLeft() == null){
			if (curr == root)
				root = curr.right;
			else if (isLeftChild)
				parent.left = curr.getRight();
			else
				parent.right = curr.getRight();
		}
		
		//Two children case
		else if(curr.getLeft() != null && curr.getRight() != null){
			//work with minimum element in right sub tree
			BSTNode <E, K> successor = successor (curr);
			if(curr == root)
				root = successor;
			else if (isLeftChild)
				parent.left = successor;
			else
				parent.right = successor;
			successor.left = curr.getLeft();
		}
		return;
	}

	/**
	 * @param tempNode node that can be delete
	 * @return node that will replace tempNode
	 */
	private BSTNode <E, K> successor (BSTNode <E, K> tempNode){
		BSTNode <E, K> success = null;
		BSTNode <E, K> successParent = null;
		BSTNode <E, K> curr = tempNode.getRight();
		
		while (curr!= null){
			successParent = success;
			success = curr;
			curr = curr.getLeft();
		}
		
		//successor cannot have a left child as it's assumed to be the minimum element
		if(success != tempNode.getRight()){
			successParent.left = success.getRight();
			success.right = tempNode.getRight();
		}
		
		return success;
	}
	
	public BSTNode<E,K> findMin (BSTNode<E,K> node){
		while(node.getLeft()!=null){//while there is still a left node keep going left
            node = node.getLeft();
        }
        return node;//leftmost node found
	}//used to find the left most node of the tree
	
	
	/*
	 * PRINTING
	 */
	@Override
	public void printTree() {
		System.out.println("Normal Binary Search Tree");
		System.out.print("Inorder: ");
		display(root);
		System.out.println();
		int depth = depth();
		for (int level = 1; level <= depth; level++) {
	        System.out.print("Level " + (level-1) + ": ");
	        String levelNodes = printLevels(root, level);
	        System.out.print(levelNodes + "\n");
	    }
		System.out.println();
	}//print the binary search tree

	/**
	 * 
	 * @param node starting node that function uses to traverse through binary search tree
	 * @param level
	 * @return recursively traverse the binary search tree using a breadth-first approach
	 */
	public String printLevels(BSTNode<E,K> node, int level) {
	    if (node == null) {
	        return "";
	    }//empty tree
	    if (level == 1) {//top most level case ie root
	        return node.getElement() + "|"/* + node.getBalance() + " "*/;
	    } else {
	        String leftStr = printLevels(node.left, level - 1);
	        String rightStr = printLevels(node.right, level - 1);
	        return leftStr + rightStr;
	    }
	}
	
	public void display(BSTNode <E, K> root){
		if(root!=null){
			display(root.left);
			System.out.print(" " + root.getElement());
			display(root.right);
		}
	}



	@Override
	public int depth() {
		return depthRecursive(root);
	}//return the depth of the binary search tree
	
	public int depthRecursive(BSTNode<E,K> node){
		if (node == null) return 0;//finished traversing
		return 1 + Math.max(depthRecursive(node.getLeft()), depthRecursive(node.getRight()));
	}//recursively determine depth of tree by traversing comparing left and right nodes
}
