package Driver;

import java.util.Scanner;
import Tree.*;
import DataStructures.*;

public class HuffCoder {
	/**
	 * Uses the HuffmanTreeGenerator class methods to create a Huffman Coding Tree
	 * @param fileName a String of the file name to use as a reference
	 * @return a HuffmanNode of the root of the created Tree
	 */
	public static HuffmanNode generateHuffmanCodingTree(String fileName) {
		
		//A) read file to string
		String input = HuffmanTreeGenerator.readFile(fileName);
		
		//B) count occurrences of symbols
		HashMap<Integer> frequencies = HuffmanTreeGenerator.countSymbols(input);
		
		//C) construct priority queue
		PriorityQueue pq = HuffmanTreeGenerator.constructPriorityQueue(frequencies);

		//D) construct tree
		HuffmanNode root = HuffmanTreeGenerator.constructTree(pq);
		
		return root;
	}
	/**
	 * Recursive method used by encode() method for creating a Map of the Huffman codes from a Huffman Tree
	 * @param root a HuffmanNode in the tree (beginning at the root)
	 * @param currentCode a string with the current code for reaching this node
	 * @param codes a HashMap of the Huffman codes (empty at first)
	 * @return a HashMap of character keys with values corresponding to their Huffman Code
	 */
	public static HashMap<String> mapCodes(HuffmanNode root, String currentCode, HashMap<String> codes) {
		
		//if root is parent
		if (root instanceof ParentNode) {
			//call left
			codes = mapCodes(((ParentNode)root).getLeftChild(), currentCode.concat("1"), codes);
			//call right
			codes = mapCodes(((ParentNode)root).getRightChild(), currentCode.concat("0"), codes);
		}
		//else root is symbol --> add to map
		else {
			codes.put(((SymbolNode)root).getSymbol(), currentCode);
			return codes;
		}
		
		return codes;
	}
	/**
	 * Encodes a Huffman Code for a String input using a Huffman Tree
	 * @param input The String input to encode
	 * @param root The Huffman Tree to reference for the encoding
	 * @return A Huffman Code
	 */
	public static String encode(String input, HuffmanNode root) {
		
		String result = "";
		char[] word = input.toLowerCase().toCharArray();
		
		//create map of codes
		HashMap<String> codes = new HashMap<>();
		codes = mapCodes(root, "", codes);
		
		//for each character, check map for that character's code
		for (char c : word) {
			result = result.concat(codes.get(c));
		}
		
		return result;
	}
	/**
	 * Takes a Huffman Code input and decodes it into a String
	 * @param input The Huffman Code input
	 * @param root The Huffman Tree to reference for the decoding
	 * @return A decoded String 
	 */
	public static String decode(String input, HuffmanNode root) {
		
		HuffmanNode current = root;
		char[] code = input.toCharArray();
		String message = "";
		
		for (char c : code) {
			//traverse left if next number is 1
			if (c == '1') {
				current = ((ParentNode)current).getLeftChild();
			}
			//traverse right if next number is 0
			else if (c == '0') {
				current = ((ParentNode)current).getRightChild();
			}
			
			
			//if landing on a symbol after traversing, get the symbol and return to the root
			if (current instanceof SymbolNode) {
				message = message.concat(Character.toString(((SymbolNode)current).getSymbol()));
				current = root;
			}
		}
		
		return message;
	}
	/**
	 * Creates a Huffman Coding tree from an input file then either decodes or encodes a message
	 * @param args a String array with the file name followed by the option to either decode or encode
	 * @throws Exception If the passed arguments are invalid
	 */
	public static void main(String[] args) throws Exception {
		
		if (args.length != 2 || !(args[1].equals("encode") || args[1].equals("decode")))
			throw new Exception("Invalid arguments");
		
		String fileName = args[0];
		String operation = args[1];
		
		System.out.println("Welcome to the HuffCoder program for encoding and decoding messages with a variable length scheme!");
		
		//STEP 1 : build huffman code tree based on filename
		HuffmanNode root = generateHuffmanCodingTree(fileName);
		
		
		//STEP 2 : do the operation encode or decode
		Scanner keyboard = new Scanner(System.in);
		String message;
		
		if (operation.equals("encode")) {
			
			System.out.print("Please enter the message to be encoded: ");
			message = keyboard.nextLine().trim();
			System.out.println("The encoded message is: ");
			System.out.println(encode(message, root));
		}
		
		else if (operation.equals("decode")) {
			System.out.print("Please enter the message to be decoded: ");
			message = keyboard.next();
			System.out.println("The decoded message is: ");
			System.out.println(decode(message, root));
		}
		
		keyboard.close();
	}
}
