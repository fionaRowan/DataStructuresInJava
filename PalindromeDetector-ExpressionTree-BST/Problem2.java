/*
*	Fiona Rowan, Data structures
*	Problem2 class
*	reads a string containing a space-delimited postfix expression, and using a stack creates an expression tree.
*	outputs the expression in infix and prefix notation and then evaluates the expression.
*/

import java.util.Scanner;

public class Problem2 {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String args[]){
		try{
			Scanner input = new Scanner(System.in);
			System.out.println("Welcome to Fiona's program! Please enter a space-delimnited post-fix expression to begin the fun.");
			String raw = input.nextLine(); 
			input.close(); 
			String[] split = raw.split("\\s+");
			MyStack s = new MyStack(split.length);
			ExpressionTree[] trees = new ExpressionTree[split.length];
			for(int i=0; i<split.length; i++){
				trees[i] = new ExpressionTree();
				trees[i].root = new ExpressionTree.TreeNode(split[i]);
			}

			for(int i = 0; i<trees.length; i++){

				//if element in the array of tree roots is not an operator, we push it onto the stack
				if(!(trees[i].root).isOperator())
					s.push(trees[i].root);

				//when we find an operator, we pop the stack twice (if it isn't empty) to give the operator children
				else{

					if (s.isEmpty()) 
						throw new UnderflowException();
					else
						trees[i].root.right = (ExpressionTree.TreeNode) s.pop(); 
					if (s.isEmpty()) 
						throw new UnderflowException();
					else
						trees[i].root.left = (ExpressionTree.TreeNode) s.pop(); 

					//put the root node with an operator back on the stack
					s.push(trees[i].root);

				}

			}

			String inFix = ExpressionTree.inFix((ExpressionTree.TreeNode) s.top());
			String preFix = ExpressionTree.printPreFix((ExpressionTree.TreeNode) s.top());
			double evaluate = ExpressionTree.evaluate((ExpressionTree.TreeNode) s.pop());

			//if there's anything left on the stack after evaluation, that's no good
			if(!s.isEmpty()){
				throw new NonEmptyStackException();
			}
			System.out.println("Prefix expression: " + preFix);
			System.out.println("Infix expression: " + inFix);
			System.out.println("Evaluates to: "+ evaluate);
			
	

		}catch (UnderflowException e){
			System.out.println("There weren't enough operands for that operator!");
		}catch(ArrayIndexOutOfBoundsException f){
			System.out.println("Array out of bounds, son.");
		}catch(NonEmptyStackException g){
			System.out.println("There were too many operands in that expression!");
		}


	}
}