package ass4;

// The "DictionaryAdvancedTest" class.
// This class tests two implementations of the Dictionary 
// interface by inserting 676 different entries, removing 
// half of them, and inserting 156 more entries. It also
// prints the initial dictionaries (i.e., the ones after
// inserting the first 676 entries) and searches for 6 
// randomly chosen entries in both dictionaries. Obviously,
// the search result should be the same for both dictionaries.
public class DictionaryAdvancedTest {
	static int k = 3;
	protected static String[] entries = new String[k * k];

	protected static void fill() {
		// Insert k * k entries
		for (int i = 0; i < k; i++)
			for (int j = 0; j < k; j++) {
				StringBuffer s = new StringBuffer();
				s.append((char) ((int) 'A' + i));
				s.append((char) ((int) 'A' + j));
				entries[i * k + j] = s.toString();
			}
	} // fill method

	public static void main(String[] args) {
		BSTDictionary<String, SortableString> dict1 = new BSTDictionary<String, SortableString>();
		AVLDictionary<String, SortableString> dict2 = new AVLDictionary<String, SortableString>();
//		TestBrief<String, SortableString> dictk = new TestBrief<String, SortableString>();

		// Insert lots of entries
		fill();
		for (int i = 0; i < k * k; i++) {
			int e;
			do {
				e = (int) (Math.random() * (k * k));
			} while (entries[e] == null);

			dict1.insertSam(new SortableString(entries[e]), entries[e]);
			dict2.insert(new SortableString(entries[e]), entries[e]);
			entries[e] = null;
		}

		// print the two dictionaries
		dict1.printTree();
		dict2.printTree();
		// print the depth
		System.out.println("The initial BST tree has a maximum depth of "
				+ dict1.depth());
		System.out.println("The initial AVL tree has a maximum depth of "
				+ dict2.depth());

		// Delete half the entries
		fill();
		for (int i = 0; i < 1 * k; i++) {
			int e;
			do {
				e = (int) (Math.random() * (k * k));
			} while (entries[e] == null);

			dict1.delete(new SortableString(entries[e]));
			dict2.delete(new SortableString(entries[e]));
		}

		// print the two dictionaries
		dict1.printTree();
		dict2.printTree();
		System.out
				.println("After deletes, the BST tree has a maximum depth of "
						+ dict1.depth());
		System.out
				.println("After deletes, the AVL tree has a maximum depth of "
						+ dict2.depth());
		

		// Add a quarter the entries
		fill();
		for (int i = 0; i < 2 * k; i++) {
			int e;
			do {
				e = (int) (Math.random() * (k * k));
			} while (entries[e] == null);

			dict1.insertSam(new SortableString(entries[e]), entries[e]);
			dict2.insert(new SortableString(entries[e]), entries[e]);
		}

		// print the two dictionaries
		dict1.printTree();
		dict2.printTree();
		System.out
				.println("After insertions, the BST tree has a maximum depth of "
						+ dict1.depth());
		System.out
				.println("After insertions, the AVL tree has a maximum depth of "
						+ dict2.depth());

		// Search for a few random entries
		fill();
		for (int i = 0; i < k; i++) {
			int e;
			do {
				e = (int) (Math.random() * (k * k));
			} while (entries[e] == null);

			System.out.print("Searching for " + entries[e] + ": ");
			if (dict1.search(new SortableString(entries[e])) == null) {
				System.out.print("Not found in Dict1, ");
			} else {
				System.out.print("Found in Dict1, ");
			}
			if (dict2.search(new SortableString(entries[e])) == null) {
				System.out.println("not found in Dict2.");
			} else {
				System.out.println("found in Dict2.");
			}
		}
	} // main method
} /* DictionaryAdvancedTest class */
