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
	
	//curr = root is needed to compile
	@Override
	public E search(K key) {
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
	public void delete(K key) {
		//curr = root; doesn't work this way for some reason? scoping issue?
		BSTNode<E, K> parent = null/*useful for special cases*/, 
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
					//BSTNode<E,K> newNode = new BSTNode <> (key, element,null,null);
					 BSTNode<E,K> tempNode = findMin(target.getRight());//finds the minimum value in the right tree
					target.key = tempNode.getKey();//exchange target node for minimum value in right subtree
					target.element = tempNode.getElement();
					tempNode = null;//delete node at minimum value of right subtree
				}//2 children case
				else if(target.getLeft()!=null&&target.getRight()==null)
				{
					//sets the left node of the parentnode(the element to be deleted) to the only childnode of the node to be deleted
					parent.setLeft(target.getLeft());
					target=null;//deletes the node
				}//left child case
				else if(target.getLeft()==null&&target.getRight()!=null)
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

	public BSTNode<E,K> findMin (BSTNode<E,K> node){
		while(node.getLeft()!=null){//while there is still a left node keep going left
            node = node.getLeft();
        }
        return node;//leftmost node found
	}//used to find the left most node of the tree
	
	
	@Override
	public void printTree() {
		System.out.println("Binary Search Tree");
		int depth = depth();
		for (int level = 1; level <= depth; level++) {
	        System.out.print("Level " + (level-1) + ": ");
	        String levelNodes = printLevels(root, level);
	        System.out.print(levelNodes + "\n");
	    }
		System.out.println();
		//printRecursive(root); harder to read :p
	}//print the binary search tree

	public String printLevels(BSTNode<E,K> node, int level) {
	    if (node == null) {
	        return "";
	    }//empty tree
	    if (level == 1) {//top most level case ie root
	        return node.getElement() + " ";
	    } else /*if (level > 1) */{
	        String leftStr = printLevels(node.left, level - 1);
	        String rightStr = printLevels(node.right, level - 1);
	        return leftStr + rightStr;
	    }
	    //else // helps to compile
	      //return "";
	}//recursively parents then their children until you've reached the leaves
	
//	public void printRecursive(BSTNode <E,K> node){
//	if(node!=null){
//        printRecursive(node.getLeft());//go through nodes attached to current left node
//        System.out.println("key: " + node.getKey().toString() + 
//            " element: " + node.getElement().toString());
//        printRecursive(node.getRight());//go through nodes attached to current right node      
//    }
//}


	@Override
	public int depth() {
		return depthRecursive(root);
	}//return the depth of the binary search tree
	
	public int depthRecursive(BSTNode<E,K> node){
		if (node == null) return 0;//finished traversing
		return 1 + Math.max(depthRecursive(node.getLeft()), depthRecursive(node.getRight()));
	}//recursively determine depth of tree by traversing comparing left and right nodes
}