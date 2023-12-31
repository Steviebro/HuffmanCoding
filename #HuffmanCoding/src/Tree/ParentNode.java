package Tree;
/**
 * A HuffmanNode descendant class holding two children nodes.
 * Ascii Codes are simply assigned high timestamp values to ensure stability in the Priority Queue.
 * @author Steven Gingras (40098045)
 *
 */
public class ParentNode extends HuffmanNode {
	
	private static long timestamp = 1000000;
	
	private HuffmanNode leftChild;
	private HuffmanNode rightChild;

	public ParentNode(HuffmanNode leftChild, HuffmanNode rightChild) {
		super();
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.setValue(leftChild.getValue() + rightChild.getValue());
		this.setAsciiCode(timestamp++);
	}
	
	//ACCESSORS
	
	public HuffmanNode getLeftChild() {
		return this.leftChild;
	}
	
	public HuffmanNode getRightChild() {
		return this.rightChild;
	}
	
	
	//MUTATORS
	
	public String display() {
		return "{Parent:"+getValue()+":"+getAsciiCode()+"}";
	}
	
	public String toString() {
		return "==============================="
				+ "\nType: PARENT NODE"
				+ "\nValue: "+getValue()
				+ "\nAscii Code: "+getAsciiCode()
				+ "\nLeft Child Value: "+leftChild.getValue()
				+ "\nLeft Child Ascii Code: "+leftChild.getAsciiCode()
				+ "\nRight Child Value: "+rightChild.getValue()
				+ "\nRight Child Ascii Code: "+rightChild.getAsciiCode()+"\n";
	}


}
