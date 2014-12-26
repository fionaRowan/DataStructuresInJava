import java.text.DecimalFormat;



public class HuffTree implements Comparable<HuffTree>{
	HuffNode root; 
	int counter=0;

	//single node hufftree
	public HuffTree(CharAndWeight x){
		root = new HuffNode(x.character, x.weight);
		counter++;
	}

	//hufftree of sub-hufftrees
	public HuffTree(HuffTree a, HuffTree b){
		root = new HuffNode(a, b);
		root.frequency=root.left.frequency+root.right.frequency;
		Codify(root);
		if(a.root.left==null &&a.root.right==null &&b.root.left ==null && b.root.right ==null)
			counter+= 2;
		else
			counter +=1;
	}


	public void printTree(){
		if(root.frequency>0){
			printTree(root);

		}
		else{
			System.out.println("Empty Tree!");

		}
	}
	private void printTree(HuffNode rt)
	{
		if( rt != null )
		{
			printTree( rt.left );
			if(rt.car!=null)
				System.out.println( rt.car +": "+", "+rt.code);
			printTree( rt.right );
		}
	}

	public HuffNode root(){
		return root;
	}

	public String toString(){
		String tree = "";
		tree+= this.root.car;
		return tree;
	}

	private void Codify(HuffNode r){
		String onLeft ="0", onRight="1";
		Codify(r.left, onLeft);
		Codify(r.right, onRight);
		//all leaves to the left, node.code=onLeft+code. same with right.  	
	}

	private void Codify(HuffNode r, String addedCode){
		//add code to leaf
		
		
		if(r.left == null && r.right == null){
			r.code = addedCode + r.code;
			int length = r.code.length();
			r.nodeElements[0].code=r.code;
		}
		if(r.left !=null)
			Codify(r.left, addedCode);
		if(r.right !=null)
			Codify(r.right, addedCode);
	}
	
	public static CharAndWeight[] updateCodeArray(HuffNode rt){
		CharAndWeight[] x;
		if(rt.left==null && rt.right==null){
			rt.nodeElements[0].code = rt.code;
			x=rt.nodeElements;
		}
		else{
			CharAndWeight[] l = updateCodeArray(rt.left);
			CharAndWeight[] r = updateCodeArray(rt.right);
			for(int i =0; i<l.length; i++){
				rt.nodeElements[i] = l[i];
			}
			int leftLength = l.length;
			for(int i = 0; i<r.length; i++){
				rt.nodeElements[leftLength]=r[i];
				leftLength++;
			}
			x=rt.nodeElements;
			
		}
		return x;
	}
	
	public String huffToText(String huff){
		String text="";
		HuffNode current=root;
		char[] binary = huff.toCharArray();
		
		for(int i =0; i<binary.length; i++){
			if (((Character) binary[i]).equals('0')){
				//go left
				current = current.left;
				
			}
			else{
				current=current.right;
				//go right
			}
			//if current is a leaf, return char
			if(current.left ==null && current.right==null){
				text+= current.car;
				current=root;
			}
		}	
		
		
		return text;
	}


	public static class HuffNode{
		Character car;
		double frequency;
		String code="";
		CharAndWeight[] nodeElements;
		HuffNode left, right;
		public HuffNode(Character c, double weight){
			car=c;
			frequency=weight;
			nodeElements = new CharAndWeight[1];
			nodeElements[0]= new CharAndWeight(car);
			left=null;
			right=null;
		}
		public HuffNode(HuffTree a, HuffTree b){
			car=null;
			frequency = 0;
			left=a.root;
			right=b.root;
			int arrayLength = left.nodeElements.length + right.nodeElements.length;
			nodeElements = new CharAndWeight[arrayLength];
//			
		}
	}


	@Override
	public int compareTo(HuffTree o) {
		int comparison;
		if (this.root.frequency < o.root.frequency){
			comparison=-1;
		}
		else if(this.root.frequency > o.root.frequency){
			comparison=1;
		}
		else{
			comparison=0;
		}
		return comparison;
	}
}
