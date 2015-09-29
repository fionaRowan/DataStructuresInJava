README

Fiona Rowan


FIRST: input file from commandline 
Tester, SeparateChainingHashTable, HuffTree, CharAndWeight, BinaryHeap.
test.txt was my test file!

Tester.java

THE MAIN METHOD LIVES HERE.

It inputs file from commandline. 
Reads in characters from file. 
Makes hash table of unique characters and corresponding frequencies. 
Creates array of HuffTrees from this hash table. 
Makes array of HuffTrees into MinHeap using BuildHeap. 
Creates one big hufftree from deleting min from heap. 
Makes hash table out of characters and their huffCode using this hufftree. 
Prints the huffman code of file out. 
Translates back into text. 
Every stage (hashtable, minheap, hufftree, second hashtable, huffcode, original text from huffcode).


SeparateChainingHashTable.java

modified from Weiss. 
Is an array of lists. 
It takes in CharAndWeight objects.
If measuring weight, weight is true, and everytime character that already exists is inserted, 
weight of its corresponding CharAndWeight node is incremented. 

HuffTree.java

binary tree that implements Huffman tree ADT. 
Makes tree out of inner class HuffNode objects. 
Once tree is built, code of each leaf is updated. 
Has a method for using tree object to translate huff code back to text. 


CharAndWeight.java

Object that contains a character, its frequency in the first stages of encoding, and its
huffman code in the last stages. 

BinaryHeap.java

modified from Weiss. 


SECOND: GUI

ActionTester, SeparateChainingHashTable, HuffTree, CharAndWeight, BinaryHeap, 
HuffNodeComponent, HuffNodeShape


GUI : 
Must input text and turn to huffman code before you can see tree!


ActionTester.java

Many of the same methods we see from Tester, but lets user interact with frame.


HuffNodeComponent.java

extends JComponent
can be passed into other J-things that take component as parameter


HuffNodeShape.java

Here, nodes and lines are drawn.


