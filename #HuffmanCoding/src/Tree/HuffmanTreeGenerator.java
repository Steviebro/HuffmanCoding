package Tree;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import DataStructures.*;

/**
 * Class with methods for generating a HuffmanTree.
 * @author Steven Gingras (40098045)
 *
 */
public class HuffmanTreeGenerator {
	/**
	 * Reads contents of a file to a String
	 * @param fileName a String of the name of the file
	 * @return a String of the contents of the file
	 */
	public static String readFile(String fileName) {
		String input = "";
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(fileName));
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			while (sc.hasNextLine())
				input = input.concat(sc.nextLine()+"\r\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		input = input.trim();
		sc.close();
		
		return input;
	}
	/**
	 * Creates a HashMap containing the frequencies of each character in a String
	 * @param input a String to count character occurrences of
	 * @return a HashMap with character keys and frequency values
	 */
	public static HashMap<Integer> countSymbols(String input) {
		
		HashMap<Integer> frequencies = new HashMap<>();
		char[] symbols = input.toLowerCase().toCharArray();
		
		for (char c : symbols) {
			frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
		}
		return frequencies;
	}
	/**
	 * Sorts a SymbolNode array in ASCENDING VALUE and ASCENDING ASCII CODE using insertion sort
	 * @param nodes A SymbolNode array
	 * @return A sorted SymbolNode array
	 */
	public static SymbolNode[] sort(SymbolNode[] nodes) {
		SymbolNode temp;
		int j;
		for (int i = 1 ; i < nodes.length ; i++) {
			j = i - 1;
			temp = nodes[i];
			while (j >= 0 && temp.compareTo(nodes[j])<0) {
				nodes[j+1] = nodes[j];
				j--;
			}
			nodes[j+1] = temp;
		}
		
		return nodes;
	}
	
	/**
	 * Creates a Priority Queue with LOW VALUES and LOW ASCII CODES having a higher priority
	 * @param frequencies A HashMap with character keys and frequency values
	 * @return an initial PriorityQueue
	 */
	public static PriorityQueue constructPriorityQueue(HashMap<Integer> frequencies) {
		PriorityQueue pq = new PriorityQueue();
		
		char[] keys = frequencies.getKeys();
		SymbolNode[] symbolNodes = new SymbolNode[keys.length];
		
		//create Symbol Nodes
		int i = 0;
		for (char c : keys) {
			symbolNodes[i] = new SymbolNode(c, frequencies.get(c));
			i++;
		}
		
		//sort by low value and low ascii
		sort(symbolNodes);
		
		//enqueue
		for (SymbolNode sn : symbolNodes) {
			pq.enqueue(sn);
		}
		return pq;
	}
	/**
	 * Creates a Huffman Coding Tree using a priority queue
	 * @param pq A Priority Queue to construct the Tree from
	 * @return The root of the created Huffman Coding Tree
	 */
	public static HuffmanNode constructTree(PriorityQueue pq) {
		
		HuffmanNode hn1 = null, hn2 = null, combined = null;
		
		while (pq.getSize() > 1) {
			
			//dequeue first 2 elements
			hn1 = pq.dequeue();
			hn2 = pq.dequeue();
			//create new node with higher priority node as the left child
			combined = new ParentNode(hn1, hn2);
			//enqueue created node back to the priority queue
			pq.enqueue(combined);
			
			
		}
		return pq.getRoot();
	}

}
