/*
*	Fiona Rowan, Data structures
*	ExpressionTree class 
*	Problem2 uses ExpressionTree and its inner class TreeNodes to build binary expression tree using a stack.
*	methods: insert, printPrefix, inFix, evaluate.
*
*/


public class ExpressionTree {
	TreeNode root; 

	public ExpressionTree(){ 
		root=null; 
	}
	

	//inserts a treenode into expression tree
	public void insert(TreeNode node){ 
		if(root.right!=null)
			root.right = node;
		else if(root.left !=null)
			root.right = node;
		else 
			;
	}

	//prints in prefix notation
	public static String printPreFix(TreeNode top) {
		String prefix = top.data; 
		if(top.left != null){
			prefix = prefix + " " +printPreFix(top.left); 
			prefix = prefix + " "+ printPreFix(top.right);
		}
		else if(top.right !=null)
			prefix = prefix + " " +printPreFix(top.right);
		else 
			; 
		return prefix; 
		
	}
	
	
	//prints in infix notation
	public static String inFix(TreeNode top) {
		String infix; 
		if(top.isOperator())
			infix = "(" + inFix(top.left)+ ")" + top.data + "(" + inFix(top.right) + ")";
		else
			infix = top.data;
		return infix;
		
		
	}

	//evaluates the expressiontree to a double
	public static double evaluate(TreeNode top) {

		double evaluate; 
		if(top.isOperator()){
			if(top.data.equals("+"))
				evaluate = (evaluate(top.left)) + (evaluate(top.right));
			else if(top.data.equals("-"))
				evaluate = (evaluate(top.left)) - (evaluate(top.right));
			else if(top.data.equals("*"))
				evaluate = (evaluate(top.left)) * (evaluate(top.right));
			else 
				evaluate = (evaluate(top.left)) / (evaluate(top.right));			
		}
			
		else
			evaluate = Integer.parseInt(top.data);
		return evaluate;
	}
	
	//inner class treenode
	public static class TreeNode {
		String data; 
		TreeNode left, right; 
		public TreeNode(String dat){
			this(dat, null, null);
		}

		private TreeNode(String dat, TreeNode l, TreeNode r){
			data = dat; 
			left=null; 
			right=null; 
		}
		
		public boolean isOperator(){
			boolean operator; 
			if(data.equals("+") || data.equals("-") || data.equals("*") || data.equals("/"))
				operator = true; 
			else
				operator = false; 
			return operator; 
		}
	}



}