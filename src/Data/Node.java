package Data;

/**
 * Node class which contains a String word, a count of the number of attempts to insert this word,
 * and a pointer to its left and right children.
 * 
 * @author Aaron Rothemich
 *
 */
public class Node implements Comparable<Node> {
		/** String word stored by this Node */
		private String word;
		/** Integer counter of number of calls to insert this word */
		private int count;
		/** Left child of Node */
		public Node left;
		/** Right child of Node */
		public Node right;
		
		/** 
		 * Constructor of a new Node containing a new word. Sets count to 1.
		 * 
		 * @param word Word to be added
		 */
		public Node(String word) {
			this.setWord(word);
			setCount(1);
		}
		
		/**
		 * Increments the value of count by one.
		 */
		public void increment() {
			setCount(getCount() + 1);
		}

		/**
		 * Returns the value of the word.
		 * 
		 * @return String word to be returned.
		 */
		public String getWord() {
			return word;
		}

		/**
		 * Sets the value of the word.
		 * 
		 * @param word Word to be stored.
		 */
		private void setWord(String word) {
			this.word = word;
		}

		/**
		 * Returns the number of insertions of the word.
		 * 
		 * @return int number of insertions of the word
		 */
		public int getCount() {
			return count;
		}
		
		/**
		 * Sets the count to the passed integer value.
		 * 
		 * @param count Value to be assigned to count
		 */
		private void setCount(int count) {
			this.count = count;
		}
		
		/**
		 * Recursive function to insert a word, or to increment its count if it already exists. 
		 * 
		 * @param newWord Word to be inserted or incremented
		 * @return integer 1 if a word has been added to the Tree, 0 if it already exists
		 */
		public int insert(String newWord) {
			if (newWord.compareTo(this.word) > 0) {
				if (this.right == null) {
					this.right = new Node(newWord);
					return 1;
				} else {
					return this.right.insert(newWord);
				}
			} else if (newWord.compareTo(this.word) < 0) {
				if (this.left == null) {
					this.left = new Node(newWord);
					return 1;
				} else {
					return this.left.insert(newWord);
				}
				
			} else {
				increment();
				return 0;
			}
		}
		
		/**
		 * Compare to another Node. Return positive if this is bigger, negative if this is smaller,
		 * 0 if equal.
		 * 
		 * @param n Node to be compared against
		 * @return int positive if this is bigger, negative if this is smaller, 0 if equal.
		 */
		@Override
		public int compareTo(Node n) {
			if (this.count < n.count) {
				return 1;
			} else if (this.count > n.count) {
				return -1;
			} else {
				return 0;
			}
		}
		
		/**
		 * Recursive function to build an Array. Inserts the Node at the passed index after 
		 * calling to add both of its children to the array.
		 * 
		 * @param arr Array to be added to
		 * @param index Index at which to insert the Node
		 * @return int Index to insert the Node above at
		 */
		public int toArray(Node[] arr, int index) {
			
			if (this.left != null) {
				index = this.left.toArray(arr, index);
			}
			if (this.right != null) {
				index = this.right.toArray(arr, index);
			}
			arr[index] = this;
			return index + 1;
			
		}

		
	}