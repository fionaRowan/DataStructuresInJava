README

Fiona Rowan

1: PALINDROME DETECTOR
classes: FindPalindromes.java, MyStack.java
text file: palindromes.txt (given)

FindPalindromes:
The file path is given as the only commandline argument of this program.
Each line is read in as a String. 
The line is stored as a String (named "palindrome") without spaces or punctuation.
The second half of this String is stored in another String, "secondHalf".
A new MyStack is created, with stack array of half the length of "palindrome" String. 
The first half of "palindrome" String is pushed onto the stack, character by character.
Then we pop the stack character by character and store the resulting String in variable "reverse".
Then print if the result of the isPalindrome() boolean is "true."
isPalindrome() returns true if "reverse" is the same as "secondHalf", ignoring cases. 

MyStack: 
This class implements stack ADT as an array.
The size of the stack (length of array) is passed into MyStack constructor. 
An integer instance variable "counter" keeps track of the number of elements in the array.
To clear, set counter to 0. 
To push, set stack[counter] to new element and increment counter. 
If counter is 0, stack isEmpty.
To see the top of the stack, return stack[counter-1].
To pop the stack, return the top of the stack and then decrement counter by 1.

2: EXPRESSION TREE
classes: MyStack.java, Problem2.java, ExpressionTree.java

MyStack:
Same as in Problem1. 

ExpressionTree: 
The insert method inserts a new node as right child of root of tree.
If the root already has a right child, it inserts the node as left child.
printPrefix prints the ExpressionTree in prefix notation with recursion. 
The "prefix" string is set equal to the tree root. 
Then, if the root has a left child, the prefix expression is set equal to itself plus the result of calling the PrintPrefix method with the left child as an argument, then again with the right child of the argument. 
If the tree has right child but not right child, "prefix" is set equal to itself plus the result of calling the PrintPrefix method with the right child as an argument. 
infix prints the ExpressionTree in infix notation with recursion. 
If the root is an operator, we set the infix String equal to inFix(the left child) enclosed in parantheses + the root + inFix(the right child) enclosed in parantheses. 
If the root is not an operator, the inFix method returns the value of the node. 
The evaluate method is like the inFix method, except once we know that the root is an operator, we must check which operator it is. 
INNER CLASS: TreeNode
Constructor takes the data to be held by the new TreeNode, as well as pointers to its right and left children. 
The isOperator method checks to see if the TreeNode data is equal to one of the four binary operators. 

Problem2: 
Program prompts user for a postfix expression, which must be space-delimited. 
The String tokens are put into an array, named "split".
A MyStack is created of the same array length. 
An array of ExpressionTrees is created named "trees", where each tree has a single root node, with their "data" instance variables equal to their corresponding tokens in the "split" array.
We now build the tree with a for-loop, using the MyStack. 
If the ExpressionTree at trees[i] is not an operator, we push it onto the stack.
If it is an operator, we pop the stack twice, inserting the popped elements as children to the current tree root. We push the root node onto the stack, with its new children hanging off. 
Once the ExpressionTree is build, there should only be one TreeNode in the stack, with a tree hanging off of it. 
We now print the prefix and infix notations of the expression, and evaluate the ExpressionTree for the user. 

3: BINARY SEARCH TREE WITH LAZY DELETION
classes: BinarySearchTree.java, BinarySearchTester.java

BinarySearchTree:
remove method: using compareResult, we call remove(left) or remove(right), if the item to remove is less than or greater than the root, respectively. When/if we find the node to delete, we simply change its instance boolean variable "isDeleted" to true.
insert method: if the value does not exist in the tree, we insert a new binary node as a leaf. If the value exists in the tree but in a node in which "isDeleted" is true, then we mark "isDeleted" as false. 
findMin method: if the root is not null, then check if there are values in the left subtree that have not been deleted - if leftSubtreeExists is true, then recursively call findMin with the left child as the argument. If a leftSubtree does not exist and the root has not been deleted, the root contains the minimum value. Otherwise, if there are undeleted nodes in the right subtree (rightSubtreeExists is true), then recursively call findMin with the right child as an argument. 
findMax method: this method is similar to findMin. If rightSubtreeExists, recursively call findMax on right subtree. Otherwise if the root has not been deleted, return the root node. Otherwise, if leftSubtreeExists, recursively call findMax with the left child as an argument. 
leftSubtreeExists boolean method: recursive method to find out if there are any undeleted nodes in the left subtree of the node passed in as an argument. If the node is not null and the left child is not null, and the left node is not deleted, then the boolean returns true. Otherwise, if the leftSubtree or rightSubtree exists for the left child, then the boolean returns true. Otherwise, it returns false and all nodes in left subtree have been deleted. 
rightSubtreeExists method: mirror method of leftSubtreeExists method. 
contains method: Find where node is supposed to be by recursively calling contains with arguments of either left or right child of root node. If the location found points to null, then tree does not contain value. If the location found has a node that has been deleted, then tree does not contain value. Otherwise, tree contains value! 
printTree method: print values of tree in order, but ignore deleted nodes. 
isEmpty method: returns true if the total number of nodes (whether or not they have been deleted) is equal to the total number of deleted nodes. nodesCounter (increments when new BinaryNode is created in insert method) and deletedCounter (increments every time a node is marked as deleted, and decremented every time a BinaryNode is re-inserted) keep track of these totals. This method is O(1)!
INNER CLASS: BinaryNode<AnyType> 
Same as Weiss', except now there is an additional boolean instance variable called isDeleted. At instantiation, this boolean is equal to false. 

BinarySearchTester: 
This class tests modified method in BinarySearchTree. 
First, it builds a tree by inserting a bunch of integers. 
Then, it prints the content, tests contains and isEmpty methods, returns max and min values.
Then we delete some items and test printTree, contains, max, and min again. 
Then we lazy-delete all items and test isEmpty. 





