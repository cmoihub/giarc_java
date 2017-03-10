
package ass4;

//https://www.youtube.com/watch?v=FNeL18KsWPc
//http://www.algolist.net/Data_structures/Binary_search_tree
//https://appliedgo.net/balancedtree/

public class AVLDictionary<E,K extends Sortable> implements Dictionary<E,K> {

	AVLNode <E, K> root;
	AVLNode <E, K> curr;
	int left, balanced, right = 0;
	
	public AVLDictionary(){
		root = null;
	}//Default constructor
	
	public AVLDictionary(AVLNode <E, K> head){
		root = head;
	}// construct AVL  with given node as root
	
	public boolean add (K key, E element, AVLNode<E,K> curr){
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
				if(curr.getRight() == null)
				{//no right child, the new node has increased the height of the subtree
					curr.setBalance(AVLNode.MORE_LEFT);//new left child's only child
					left++;
				}else
				{//if there're 2 children,right child cannot have children
				//curr would be unbalanced otherwise 
					curr.setBalance(AVLNode.EVEN);
					balanced++;
				}
				//return true;
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
				if(curr.getLeft() == null) {
					curr.setBalance(AVLNode.MORE_RIGHT);//new left child's only child
					right++;
				}else{
					curr.setBalance(AVLNode.EVEN);
					balanced++;
				} 
					
				//return true;
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
	
	//@Override
	public void insert(K key, E element) {
		if(root == null){
			root = new AVLNode<> (key, element,null,null,AVLNode.EVEN);
			//return true;
		}else add(key, element, root);
	}//insert a key-value pair into the binary search tree
	
	/*
	 * this method works based on the fact that the leftmost node has the least significant key
	 */
	public AVLNode<E, K> minValue(AVLNode<E, K> node){
		if (node.getLeft() == null){
			return node;
		}//
		else 
			return minValue (node.getLeft());
//		return 0;
	}
	
	public void delete(K key) {
		//curr = root; doesn't work this way for some reason? scoping issue?
		AVLNode<E, K> parent = null/*useful for special cases*/, 
				target = null/*node to delete*/,
				curr = root;
		//if(key!=null){
			while(curr!=null){
				if(key.compareTo(curr.getKey())==0)	{
					target = curr;
					break;
				}//target is current node
				
				else if(key.compareTo(curr.getKey())>0) {
					parent = curr;
					curr=curr.getLeft();
				}//check left child
				
				else
				{
					parent=curr;
					curr = curr.getRight();
				}//check right child
			}//search for element to delete
			if(target!=null) {
				if(target.getLeft()!=null&&target.getRight()!=null)
				{
					//AVLNode<E,K> newNode = new AVLNode <> (key, element,null,null);
					 AVLNode<E,K> tempNode = findMin(target.getRight());//finds the minimum value in the right tree
					target.key = tempNode.getKey();//exchange target node for minimum value in right subtree
					target.element = tempNode.getElement();
					tempNode = null;//delete node at minimum value of right subtree
				}//2 children case
				else if(target.getLeft()!=null && target.getRight()==null)
				{
					//sets the left node of the parentnode(the element to be deleted) to the only childnode of the node to be deleted
					parent.setLeft(target.getLeft());
					target=null;//deletes the node
				}//left child case
				else if(target.getLeft()==null && target.getRight()!=null)
				{
					//sets the rightnode of the parentnode(the element to be deleted)to the only childnode of the node to be deleted
					parent.setRight(target.getRight());
					target=null;//deletes the node
				}//right child case
				else//the node does not have any children
				{
					target = null;//deletes node
				}
			}//assert that target has been found	
		//}	
	}
	
	public AVLNode<E,K> findMax (AVLNode<E,K> node){
		while(node.getRight()!=null){//while there is still a left node keep going left
            node = node.getRight();
        }
        return node;//leftmost node found
	}//used to find the left most node of the tree
	
	public AVLNode<E,K> findMin (AVLNode<E,K> node){
		while(node.getLeft()!=null){//while there is still a left node keep going left
            node = node.getLeft();
        }
        return node;//leftmost node found
	}//used to find the left most node of the tree
	
	/*
	 * BALANCING
	 */
	
	public boolean unbalanced (AVLNode<E, K> curr){
		if (curr.getBalance() < AVLNode.MORE_LEFT
							|| curr.getBalance() > AVLNode.MORE_RIGHT)
			return true;
		return false;
	}
	public void rebalance(AVLNode <E,K> node, AVLNode <E,K> parent){
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
	
	public void rotateLeft(AVLNode <E,K> node, AVLNode <E,K> parent){
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
	
	public void rotateRight(AVLNode <E,K> node, AVLNode <E,K> parent){
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
	public void rotateRightLeft(AVLNode <E,K> node, AVLNode <E,K> parent){
		node.getRight().getLeft().setBalance(AVLNode.MORE_RIGHT);
		rotateRight(node.getRight(),node);
		node.getRight().setBalance(AVLNode.MORE_RIGHT);
		rotateLeft(node, parent);
	}

	/*
	 * mirrors rotateLeft
	 */
	public void rotateLeftRight(AVLNode <E,K> node, AVLNode <E,K> parent){
		node.getLeft().getRight().setBalance(AVLNode.MORE_LEFT);
		rotateLeft(node.getLeft(),node);
		node.getLeft().setBalance(AVLNode.MORE_LEFT);
		rotateRight(node, parent);
	}
	
	@Override
	public E search(K key) {
		//System.out.println(curr.getKey());
		if(curr == null){
			curr = root;
			return null;
		}//unavailable key-value pair 
		if(curr.getKey().compareTo(key) == 0){
			AVLNode<E,K> copy = new AVLNode <> (curr.getKey(), curr.getElement(), curr.getLeft(), curr.getRight(),0);
			curr = root;
			return copy.getElement();
			}//if key is the one at the current node
		else if(curr.getKey().compareTo(key) < 0){
			curr = curr.getLeft();
			return search(key);
			}//left child check

		else if(curr.getKey().compareTo(key) > 0){
			curr = curr.getRight();
			return search(key);
			}//right child check
		return null;
	}//return the entry corresponding to a specified Key k
	
	/*
	 * PRINTING
	 */
	@Override
	public void printTree() {
		System.out.println("Balanced Binary Search Tree");
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
	 * @param node
	 * @param level
	 * @return recursively traverse the binary search tree using a breadth-first approach
	 */
	public String printLevels(AVLNode<E,K> node, int level) {
	    if (node == null) {
	        return "";
	    }//empty tree
	    if (level == 1) {//top most level case ie root
	        return node.getElement() + "|" + node.getBalance() + " ";
	    } else /*if (level > 1) */{
	        String leftStr = printLevels(node.left, level - 1);
	        String rightStr = printLevels(node.right, level - 1);
	        return leftStr + rightStr;
	    }
	    //else // helps to compile
	      //return "";
	}
	
	public String inOrder(AVLNode <E,K> node){
		String traversed = "";
		while(node!=null){
            inOrder(node.getLeft());//go through nodes attached to current left node
            traversed += node.getElement();
//            System.out.println("key: " + node.getKey().toString() + 
//                " element: " + node.getElement().toString());
            inOrder(node.getRight());//go through nodes attached to current right node      
        }
		return traversed;
	}

	/*
	 * DEPTH
	 */
	@Override
	public int depth() {
		return depthRecursive(root);
	}//return the depth of the binary search tree
	
	public int depthRecursive(AVLNode<E,K> node){
		if (node == null) return 0;//finished traversing
		return 1 + Math.max(depthRecursive(node.getLeft()), depthRecursive(node.getRight()));
	}//recursively determine depth of tree by traversing comparing left and right nodes
	
	/*
	 * Recreated functions
	 */
	public void insertSam(K key, E element) {
		//nodes are balanced until proven to be unbalanced
		AVLNode<E,K> newNode = new AVLNode <> (key, element,null,null,AVLNode.EVEN);
		
		if(root == null)//empty tree
		{
			root = newNode;
			curr = root;
			balanced++;
		}
		//inserting on the left
		else if (curr.getKey().compareTo(newNode.getKey()) < 0){
			//insert if current node has no left child
			if (curr.getLeft() == null) {
				curr.setLeft(newNode);
				if (curr.getRight() == null){
					curr.setBalance(AVLNode.MORE_LEFT);
					left++;
				}
				else {
					curr.setBalance(AVLNode.EVEN);
					balanced++;
				}
				curr = root;
			}
			else/*compare key with left child's key*/{
				curr = curr.getLeft();//traverse leftwise
				insertSam (key,element);//handle left child
			}
		}
		//mirrors previous case
		//find a place in the tree to insert it
		else /*if (curr.getKey().compareTo(newNode.getKey()) > 0)*/{
			//insert if current node has no right child
			if (curr.getRight() == null){
				curr.setRight(newNode);
				if (curr.getLeft() == null){
					curr.setBalance(AVLNode.MORE_RIGHT);
					right++;
				}
				else {
					curr.setBalance(AVLNode.EVEN);
					balanced++;
				}
				curr = root;
			}
			//inserting on the right
			else/*compare key with right child's key*/{
				curr = curr.getRight();//traverse rightwise
				insertSam (key,element);//handle right child
				}
			}
		//return;
	}//insert a key-value pair into the binary search tree
}