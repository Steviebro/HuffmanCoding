# HuffmanCoding


This application first creates a huffman coding tree based on an input text file included as the first argument.
Then, based on the second argument ("encode" or "decode"), a message is either encoded or decoded using the created tree.

Creating the Huffman Tree:
In Huffman coding, nodes are created for each symbol (contained in the input text) which is assigned a value based on the frequency of a its occurrence in an input text. These nodes are then placed in a priority queue in ascending order based on this frequency value.
The two nodes with the smallest values become the children of a newly created parent node with a value equal to the sum of its children’s values. The coding tree is completed when only one node remains. Optimal codes are generated since symbols with higher frequencies are assigned shorter codes.

Variable-length encoding:
The length of a Huffman code is inversely proportional to the frequency of the corresponding symbol. Assigning shorter codes to symbols with higher frequencies reduces the code length in the average case since shorter codes are used more often than longer ones.
This is useful for data compression since it minimizes the number of bits required to store a message. A variable length scheme allows symbol encoding in one bit while a fixed length scheme would require more bits per symbol to ensure it can encode more symbols. Overall, less bits are used for data storage in Huffman code.

Encoding/Decoding:
In both of these cases, once the Huffman Coding tree has been created and each symbol is assigned a Huffman code, the entered message is either decoded or encoded based on the assigned codes.
