READ ME

Fiona Rowan 
COMS3134 Data structures
Programming Assignment 3

Program 1:
Classes: Indexer.java, AvlTree.java

INDEXER CLASS
This is where the main method lives. This takes an input file as a command-line argument. 
It goes through each line in the input and inputs it into the AVL tree, as well as its 
position. Then it prints the tree.
My input was spider-man.txt, which contains the "History of Toilets" on Wikipedia.

AVLTREE CLASS
This has been adapted from Weiss' code. The insert method has been modified to take an int
position. If a word being inserted already exists in the tree, we simply add its position
into the existing AvlNode's list of positions. Otherwise, we make a new node. 
In the AvlNode inner class, an added static variable is a myLinkedList position, which 
holds the positions at which each of the words exists in the input file. 
The second inner class called myLinkedList is a data type implementing singly linked list.
This is used to hold the position of each word. 
Therefore, each AvlTree has nodes of two parts: the data part (word), and the myLinkedList part 
(positions). 

Program 2: 
Classes: SpellChecker.java, SeparateChainingHashTable.java

SPELLCHECKER CLASS
This is where the main method lives. It is a command-line program that takes three files,
in this order: bigDictionary.txt, smallDictionary.txt, input.txt. First, the two 
dictionary files are iterated through to find the sum of the number of lines in both.
Then, a hash table is created of that size. Then, we check to see if the dictionary 
hash table contains the words in the input file. If a word is not in the dictionary hash
table, then we use the addChar (add each letter of the alphabet into every position of the
word and check if the word is now in the dictionary), removeChar (remove one char at a 
time to see if the word is now in the dictionary), and the swapChar (swap adjacent 
characters to see if the word is now in the dictionary) methods. Then, the misspelled words
and possible options for correction are printed.
My input was spider-man.txt, which contains the "History of Toilets" on Wikipedia.

SEPARATECHAININGHASHTABLE CLASS
This is very close to Weiss' code. 

