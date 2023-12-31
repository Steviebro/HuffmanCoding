package Tree;
/**
 * A HuffmanNode descendant class holding a Symbol/Character.
 * @author Steven Gingras (40098045)
 *
 */
public class SymbolNode extends HuffmanNode {
	
	private char symbol;
	
	
	public SymbolNode(char symbol, int value) {
		super(value);
		this.symbol = symbol;
		setAsciiCode((int)symbol);
	}
	
	//ACCESSORS
	
	public char getSymbol() {
		return this.symbol;
	}
	
	public int compareTo(SymbolNode other) {
		if (this.getValue()<other.getValue())
			return -1;
		if (this.getValue()>other.getValue())
			return 1;
		if (this.getAsciiCode() < other.getAsciiCode())
			return -1;
		else
			return 1;
	}
	
	
	//MUTATORS
	
	public String display() {
		return "{"+symbol+":"+getValue()+":"+getAsciiCode()+"}";
	}
	
	public String toString() {
		return "==============================="
				+ "\nType: SYMBOL NODE"
				+ "\nSymbol: "+symbol
				+ "\nValue: "+getValue()
				+ "\nAscii Code: "+getAsciiCode()+"\n";
	}

	

}
