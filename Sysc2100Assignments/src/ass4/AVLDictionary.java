package ass4;

public class AVLDictionary <E,K extends Sortable> implements Dictionary<E, K>{
	AVLNode <String, SortableString> root;
	AVLNode <String, SortableString> curr;
	public AVLDictionary () {
		root = null;
	}
	
	@Override
	public E search(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(K key, E element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(K key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printTree() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int depth() {
		// TODO Auto-generated method stub
		return 0;
	}


	}
