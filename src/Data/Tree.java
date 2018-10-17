package Data;
/**
 * This class contains a tree with rudimentary functions for usage by WordCount.
 * It contains a root and stores the size of the tree as an integer.
 * 
 * @author Aaron Rothemich
 */

public class Tree {
	
	/** Root node of Tree */
	Node root;
	/** Size of Tree */
	int size;
	
	/**
	 * Empty constructor of Tree
	 */
	public Tree() {
		// Empty Constructor
	}
	
	/**
	 * Inserts a new word into the Tree, by calling the recursive insert() method of
	 * Node.
	 * 
	 * @param newWord String of word to be added to the Tree
	 */
	public void insert(String newWord) {
		if (this.root == null) {
			this.root = new Node(newWord);
			size++;
		} else {
			size += root.insert(newWord);
		}
	}
	
	/**
	 * Creates an Array of the Nodes comprising the tree using recursive inOrder traversal.
	 * 
	 * @return Node[] Array of Nodes in alphabetical order by word
	 */
	public Node[] toArray() {
		if (root == null) {
			return null;
		} else {
			Node[] arr = new Node[size];
			root.toArray(arr, 0);
			return arr;
		}
	}
	
}

