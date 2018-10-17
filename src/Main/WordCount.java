package Main;

import java.io.IOException;
import java.util.Arrays;
import org.jsoup.Jsoup;

import Data.Node;
import Data.Tree;

/**
 * This program builds a dictionary of all words used on a webpage, then prints the top 25
 * words and their counts to the console. 
 *
 * @author Aaron Rothemich
 * 
 */
public class WordCount {

	/** Number of sorted words to return */
	public static final int NUMBER_OF_WORDS = 25;
	
	/**
	 * Initializes the program, builds dictionary in a binary search tree, and prints the top 25 most 
	 * used words to the console. It accepts one command line argument with the URL to be
	 * visited.
	 * 
	 * @param args Array of command line arguments, should contain 1 argument with a URL
	 */
	public static void main(String[] args) {
		
		if (args.length != 1) {
			throw new IllegalArgumentException("Usage: java WordCount <URL>");
		}
		String site = args[0];
		
		// If efficiency becomes important, a BufferedInputStream could be used in place of Jsoup.
		// This would prevent multiple passes over the text, but would introduce the challenge of
		// reading between HTML tags.
		String text;
		try {
			text = Jsoup.connect(site).get().text();
		} catch (IllegalArgumentException e) {
			// Attempt to connect with https:// protocol prepended
			try {
				site = "https://" + site;
				text = Jsoup.connect(site).get().text();
			} catch (IllegalArgumentException a) {
				throw new IllegalArgumentException("Page cannot be loaded. "
						+ "Usage: java WordCount <URL>");
			} catch (IOException m) {
				throw new IllegalArgumentException("Page cannot be loaded. "
						+ "Usage: java WordCount <URL>"); 
			}
		} catch (IOException e) {
			throw new IllegalArgumentException("Page cannot be loaded."
					+ " Usage: java WordCount <URL>");
		} 
		
		// Split text into array using alphanumeric and apostrophe characters
		String[] textArr = text.split("[^\\p{L}'’]+");
		Tree dictionary = new Tree();
		for (int i = 0; i < textArr.length; i++) {
			dictionary.insert(textArr[i].toLowerCase());
		}
		
		// Build array using the dictionary. A more efficient solution would involve using a 
		// sorted LinkedList of size 25, and only adding Nodes to the list if they are greater
		// than the current least Node, adding the new lowest value to the tail with 
		// each insertion.
		Node[] arr = dictionary.toArray();
		Arrays.sort(arr);
		for (int i = 0; i < NUMBER_OF_WORDS; i++) {
			if (i < arr.length) {
				System.out.printf("%-12s%10d\n", arr[i].getWord(), arr[i].getCount());
			}
		}
		
	}
	
	
	

	
}