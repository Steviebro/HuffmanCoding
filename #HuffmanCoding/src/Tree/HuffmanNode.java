package Tree;

/**
 * The ancestor Node class for a Huffman Tree
 * @author Steven Gingras (40098045)
 *
 */
public class HuffmanNode {
	
	private int value;
	private long asciiCode;
	
	public HuffmanNode() {
		asciiCode = -1;
	}
	
	public HuffmanNode(int value) {
		this();
		this.value = value;
	}
	
	//ACCESSORS
	
	public int getValue() {
		return this.value;
	}
	
	public long getAsciiCode() {
		return this.asciiCode;
	}
	
	//MUTATORS
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public void setAsciiCode(long asciiCode) {
		this.asciiCode = asciiCode;
	}
	
	public String display() {
		return null;
	}
	
	public String toString() {
		return "==============================="
				+ "\nType: HUFFMAN NODE"
				+ "\nValue: "+getValue()
				+ "\nAscii Code: "+getAsciiCode()+"\n";
	}
	

}
