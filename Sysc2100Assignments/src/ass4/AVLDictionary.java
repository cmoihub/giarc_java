
package ass4;

//https://www.youtube.com/watch?v=FNeL18KsWPc
//http://www.algolist.net/Data_structures/Binary_search_tree
//https://appliedgo.net/balancedtree/
//http://www.mathcs.emory.edu/~cheung/Courses/323/Syllabus/Trees/
//http://stackoverflow.com/questions/28018362/java-AVL-deletion-how-to-implement-using-existing-rotation-code
//http://algorithms.tutorialhorizon.com/binary-search-tree-complete-implementation/
//http://stackoverflow.com/questions/5771827/implementing-an-AVL-tree-in-java
//https://github.com/phishman3579/java-algorithms-implementation/blob/master/src/com/jwetherell/algorithms/data_structures/BinarySearchTree.java

public class AVLDictionary<E,K extends Sortable> implements Dictionary<E,K> {

	AVLNode <E, K> root;
	AVLNode <E, K> curr;
	int left, balanced, right = 0;
	
	public AVLDictionary(){
		this(null);
	}//Default constructor
	
	public AVLDictionary(AVLNode <E, K> head){
		root = head;
	}// construct AVL  with given node as root
	
	private boolean add (K key, E element, AVLNode<E,K> curr){
		AVLNode<E,K> newNode = new AVLNode <> (key, element,null,null,AVLNode.EVEN);
		
		if (curr.getKey().compareTo(key) == 0)
		{//node already exists, nothing changes
			curr.setElement(element);
			return false;
		}
		
		//key < curr.getKey()
		else if(curr.getKey().compareTo(key) < 0){
			if (curr.getLeft() == null)
			{//create a new node if there's no left child
				curr.setLeft(newNode);
				updateBalanceAfterInsert(curr);
			}else{
				if(add(key, element, curr.getLeft())){
					if (unbalanced(curr.getLeft())){
						rebalance(curr.getLeft(), curr);
					}else{
						curr.setBalance(curr.getBalance() - 1);
					}
				}
			}
		}
		//mirrors above case
		//key > curr.getKey()
		else if(curr.getKey().compareTo(key) > 0){
			if (curr.getRight() == null){
				curr.setRight(newNode);
				updateBalanceAfterInsert(curr); 
			}else{
				if(add(key, element, curr.getRight())){
					if(unbalanced(curr.getRight())){
						rebalance(curr.getRight(), curr);
					} else {
						curr.setBalance(curr.getBalance() + 1);
					}
				}
				//return add(key, element, curr.getRight());
			}
		}
//		if(curr.getBalance() != 0) return true;
		return false;
		
	}
	//update balance after node is added
	private void updateBalanceAfterInsert(AVLNode <E, K> curr){
		if(curr.getRight() == null)//left node added
		{//no right child, the new node has increased the height of the subtree
			curr.setBalance(AVLNode.MORE_LEFT);//new left child's only child
			left++;
		}
		
		else if (curr.getLeft() == null)
		{//right node added
			curr.setBalance(AVLNode.MORE_RIGHT);//new right child's only child
			right++;
		}
		
		else//if there're 2 children,right child cannot have children
		{//curr would be unbalanced otherwise 
			curr.setBalance(AVLNode.EVEN);
			balanced++;
		}
	}
	
	//@Override
	public void insert(K key, E element) {
		if(root == null){
			root = new AVLNode<> (key, element,null,null,AVLNode.EVEN);
			//return true;
		}else add(key, element, root);
	}//insert a key-value pair into the binary search tree

	@Override
	public void delete (K key){
		AVLNode <E, K> parent = root;
		AVLNode <E, K> curr = root;
		boolean isLeftChild = false;
		while (!curr.getKey().equals(key)){
			parent = curr;
			if (key.compareTo(curr.getKey())>0){
				isLeftChild = true;
				curr = curr.getLeft();
			}else{
				isLeftChild = false;
				curr = curr.getRight();
			}
			
			if(curr == null)
				return;
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
			AVLNode <E, K> min = successor (curr);
			if(curr == root)
				root = min;
			else if (isLeftChild)
				parent.left = min;
			else
				parent.right = min;
			min.left = curr.getLeft();
		}
		return;
	}

	/**
	 * @param tempNode node that can be delete
	 * @return node that will replace tempNode
	 */
	private AVLNode <E, K> successor (AVLNode <E, K> tempNode){
		AVLNode <E, K> success = null;
		AVLNode <E, K> successParent = null;
		AVLNode <E, K> curr = tempNode.getRight();
		
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
	
	private AVLNode <E, K> getParent(AVLNode <E, K> root, AVLNode<E, K> node){
		AVLNode<E, K> lh = null, rh = null;
	    if (null == root)
	        return null;

	    if (root.getLeft() == node || root.getRight() == node)
	        return root;

	    lh = getParent(root.getLeft(), node);
	    rh = getParent(root.getRight(), node);

	    return lh != null ? lh : rh;
	}
	
	/*
	 * BALANCING
	 */
	private boolean unbalanced (AVLNode<E, K> curr){
		if (curr.getBalance() < AVLNode.MORE_LEFT
							|| curr.getBalance() > AVLNode.MORE_RIGHT)
			return true;
		return false;
	}
	
	private void rebalance(AVLNode <E,K> node, AVLNode <E,K> parent){
		//System.out.println("rotate" + node.getElement().toString());
		if(node.getBalance() < AVLNode.MORE_LEFT && node.getLeft().getBalance() == AVLNode.MORE_LEFT){
			rotateRight(node, parent);
		}//High left subtree and left child has a left child
		
		if(node.getBalance() > AVLNode.MORE_RIGHT && node.getRight().getBalance() == AVLNode.MORE_RIGHT){
			rotateLeft(node, parent);
		}//High right subtree and right child has a right child
		
		if(node.getBalance() < AVLNode.MORE_LEFT && node.getLeft().getBalance() == AVLNode.MORE_RIGHT){
			rotateLeftRight(node, parent);
		}//High left subtree and left child has a right child
		
		if(node.getBalance() > AVLNode.MORE_LEFT && node.getRight().getBalance() == AVLNode.MORE_LEFT){
			rotateRightLeft(node, parent);
		}//High right subtree and right child has a left child
	}
	
	private void rotateLeft(AVLNode <E,K> node, AVLNode <E,K> parent){
		//System.out.println("rotate left" + node.getElement().toString());
		AVLNode<E,K> rightChild = node.getRight();
		node.setRight(rightChild.getLeft());//node's right child’s left subtree gets reassigned to node.
		rightChild.setLeft(node);//node becomes left child of it's right child
		if (node.equals(parent.getLeft())){
			parent.setLeft(rightChild);
		}else{
			parent.setRight(rightChild);
		}
		//adjust balances
		rightChild.setBalance(AVLNode.EVEN);
		node.setBalance(AVLNode.EVEN);
	}
	
	private void rotateRight(AVLNode <E,K> node, AVLNode <E,K> parent){
		//System.out.println("rotate right" + node.getElement().toString());
		AVLNode<E,K> leftChild = node.getLeft();
		node.setLeft(leftChild.getRight());//node's left child’s right subtree gets reassigned to node.
		leftChild.setRight(node);//node becomes right child of it's left child
		if (node.equals(parent.getRight())){
			parent.setRight(leftChild);
		}else{
			parent.setLeft(leftChild);
		}
		//adjust balances
		leftChild.setBalance(AVLNode.EVEN);
		node.setBalance(AVLNode.EVEN);
	}//mirrors rotateLeft
	
	/*
	 * rotateRightLeft first rotates the right child of node to the right, then node to the left.
	 * rotateRight assumes that the left child has a left child, 
	 * but as part of the rotate-right-left process, the left child of node.Right is a leaf. 
	 * We therefore have to tweak the balance factors before and after calling rotateRight. 
	 * If we did not do that, we would not be able to reuse rotateRight and rotateLeft
	 */
	private void rotateRightLeft(AVLNode <E,K> node, AVLNode <E,K> parent){
		node.getRight().getLeft().setBalance(AVLNode.MORE_RIGHT);
		rotateRight(node.getRight(),node);
		node.getRight().setBalance(AVLNode.MORE_RIGHT);
		rotateLeft(node, parent);
	}

	/*
	 * mirrors rotateLeft
	 */
	private void rotateLeftRight(AVLNode <E,K> node, AVLNode <E,K> parent){
		node.getLeft().getRight().setBalance(AVLNode.MORE_LEFT);
		rotateLeft(node.getLeft(),node);
		node.getLeft().setBalance(AVLNode.MORE_LEFT);
		rotateRight(node, parent);
	}
	
	/**
	 * beginning from the root the method checks if the current node's key matches up to the specified key
	 * if it doesn't it traverses leftwise or rightwise
	 */
	@Override
	public E search(K key) {
		AVLNode <E, K> found = getNode(key);
		if (found == null)
			return null;
		else 
			return found.getElement();
	}
	
	private AVLNode <E, K> getNode(K key){
		AVLNode<E, K> curr = root;
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
	
	/*
	 * PRINTING
	 */
	@Override
	public void printTree() {
		System.out.println("Balanced Binary Search Tree");
		System.out.print("Inorder: ");
		display(root);
		System.out.println();
		System.out.println(left + " 1s\\left-heavy nodes " 
				+ balanced + " 2s\\balanced nodes " + right + " 3s\\right-heavy nodes");
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
	private String printLevels(AVLNode<E,K> node, int level) {
	    if (node == null) {
	        return "";
	    }//empty tree
	    if (level == 1) {//top most level case ie root
	        return node.getElement() + "|" + node.getBalance() + " ";
	    } else {
	        String leftStr = printLevels(node.left, level - 1);
	        String rightStr = printLevels(node.right, level - 1);
	        return leftStr + rightStr;
	    }
	}
	
	private void display(AVLNode <E, K> root){
		if(root!=null){
			display(root.left);
			System.out.print(" " + root.getElement());
			display(root.right);
		}
	}

	/*
	 * DEPTH
	 */
	@Override
	public int depth() {
		return depthRecursive(root);
	}//return the depth of the binary search tree
	
	private int depthRecursive(AVLNode<E,K> node){
		if (node == null) return 0;//finished traversing
		return 1 + Math.max(depthRecursive(node.getLeft()), depthRecursive(node.getRight()));
	}//recursively determine depth of tree by traversing comparing left and right nodes
	
	}