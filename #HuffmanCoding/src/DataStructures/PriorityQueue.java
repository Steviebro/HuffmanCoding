package DataStructures;

import Tree.*;
/**
 * A Heap-based Priority Queue with highest priority determined by LOW VALUE followed by LOW ASCII CODE as a tiebreaker
 * @author Steven Gingras (40098045)
 *
 */
public class PriorityQueue {
	
	private static final int INITIAL_SIZE = 64;
	
	private HuffmanNode[] elements;
	private int size;
	
	public PriorityQueue() {
		size = 0;
		elements = new HuffmanNode[INITIAL_SIZE];
	}
	
	public int getSize() {
		return this.size;
	}
	
	public HuffmanNode getRoot() {
		return this.elements[0];
	}
	
	public int getParent(int index) {
		return (index - 1) >> 1;
	}
	
	public int getLeftChild(int index) {
		return (2*index)+1;
	}
	
	public int getRightChild(int index) {
		return (2*index)+2;
	}
	/**
	 * Determines if the passed index holds a leaf
	 * @param index an int of the index
	 * @return True if it is a leaf, false otherwise
	 */
	public boolean isLeaf(int index) {
		return (elements[getLeftChild(index)] == null && elements[getRightChild(index)] == null);
	}
	/**
	 * Doubles the size of the elements array when more space is required
	 */
	public void grow() {
		HuffmanNode[] newArray = new HuffmanNode[2*elements.length];
		int i = 0;
		for (HuffmanNode hn : elements) {
			newArray[i] = hn;
			i++;
		}
		
		elements = newArray;
	}
	
	/**
	 * Swaps the two values at the passed indices
	 * @param i1
	 * @param i2
	 */
	public void swap(int i1, int i2) {
		HuffmanNode temp = elements[i1];
		elements[i1] = elements[i2];
		elements[i2] = temp;
	}
	/**
	 * Top-down recursive method for fixing the heap.
	 * @param index an int of the index
	 */
	public void fixHeap(int index) {
		
		//has no children
		if (isLeaf(index))
			return;
				
		
		int value = elements[index].getValue();
		long ascii = elements[index].getAsciiCode();
		int leftValue = elements[getLeftChild(index)].getValue();
		long leftAscii = elements[getLeftChild(index)].getAsciiCode();
		
		//has 1 child
		if (elements[getRightChild(index)] == null) {
			if (value > leftValue) {
				swap(index, getLeftChild(index));
				fixHeap(getLeftChild(index));
			}
			else if (value == leftValue && ascii > leftAscii) {
				swap(index, getLeftChild(index));
				fixHeap(getLeftChild(index));
			}
		}
		
		//has both children
		else {
			int rightValue = elements[getRightChild(index)].getValue();
			long rightAscii = elements[getRightChild(index)].getAsciiCode();
			//condition: parent is greater than one of the children
			if (value > leftValue || value > rightValue) {
				
				//condition: left child is smaller than right child
				if (leftValue < rightValue) {
					swap(index, getLeftChild(index));
					fixHeap(getLeftChild(index));
				}
				
				//condition: right child is smaller than left child
				else if (leftValue > rightValue){
					swap(index, getRightChild(index));
					fixHeap(getRightChild(index));
				}
				//condition: left and right children are equal --> tiebreaker: ascii
				else {
					if (leftAscii < rightAscii) {
						swap(index, getLeftChild(index));
						fixHeap(getLeftChild(index));
					}
					else {
						swap(index, getRightChild(index));
						fixHeap(getRightChild(index));
					}
				}
			}
			//condition: parent is equal to one of the children --> tiebreaker: ascii
			else if (value == leftValue || value == rightValue){
				
				//condition: parent is equal to left and parent ascii is greater than left
				if (value != rightValue && ascii > leftAscii) {
					swap(index, getLeftChild(index));
					fixHeap(getLeftChild(index));
				}
				
				//condition: parent is equal to right and parent ascii is greater than right
				else if (value != leftValue && ascii > rightAscii) {
					swap(index, getRightChild(index));
					fixHeap(getRightChild(index));
				}
				
				//else parent is equal to left and right and condition: parent ascii is greater than one of the children
				else if (ascii > leftAscii || ascii > rightAscii){
					
					//condition: left ascii is less than right ascii and parent ascii is greater than left ascii
					if (leftAscii < rightAscii && ascii > leftAscii) {
						swap(index, getLeftChild(index));
						fixHeap(getLeftChild(index));
					}
					//condition: right ascii is less than left ascii and parent ascii is greater than right ascii
					else if (rightAscii < leftAscii && ascii > rightAscii) {
						swap(index, getRightChild(index));
						fixHeap(getRightChild(index));
					}
				}
			}
		}
	}
	
	/**
	 * Dequeues the highest priority Node from the priority queue.
	 * @return The highest priority Node
	 */
	public HuffmanNode dequeue() {
		HuffmanNode removed = elements[0];
		elements[0] = elements[size-1];
		elements[size-1] = null;
		size--;
		fixHeap(0);
		return removed;
	}
	/**
	 * Enqueues the passed Node to the priority queue
	 * @param hn a Node to enqueue
	 */
	public void enqueue(HuffmanNode hn) {
		if (size == elements.length)
			grow();
		
		elements[size] = hn;
		int added = size;
		boolean swapped = true;
		
		while (getParent(added) >= 0 && swapped) {
			swapped = false;
			if (elements[added].getValue() < elements[getParent(added)].getValue()) {
				swap(added, getParent(added));
				added = getParent(added);
				swapped = true;
			}
			else if (elements[added].getValue() == elements[getParent(added)].getValue()
					&& elements[added].getAsciiCode() < elements[getParent(added)].getAsciiCode()) {
				swap(added, getParent(added));
				added = getParent(added);
				swapped = true;
			}
		}
		size++;
	}
	
	public String toString() {
		String display = "";
		for (HuffmanNode hn : elements) {
			if (hn != null)
				display = display.concat(hn.display()+"\t");
		}
		
		return display;
	}

}
