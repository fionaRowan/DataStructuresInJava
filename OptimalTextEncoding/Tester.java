import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ListIterator;




public class Tester {
	public static void main(String args []) throws IOException{
		try{

			String filePath = args[0];
			@SuppressWarnings("rawtypes")
			SeparateChainingHashTable hashTable = new SeparateChainingHashTable(true);
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String line;
			//create hash table of unique characters and their weight
			while ((line = br.readLine()) != null) {
				char[] cArray= line.toCharArray();
				for(int i=0; i<cArray.length; i++){
					CharAndWeight x = new CharAndWeight((Character) cArray[i]);
					hashTable.insert(x);
				}
			}
			br.close();
			hashTable.printTable();

			HuffTree[] newTrees = new HuffTree[199];

			for(int i = 0; i<hashTable.listLength(); i++){
				@SuppressWarnings("unchecked")
				List<CharAndWeight>[] list = hashTable.list();
				List<CharAndWeight> whichList =  list[i];
				ListIterator<CharAndWeight> j = whichList.listIterator();
				for(int k=0; k<newTrees.length; k++){
					while(whichList!=null &&j.hasNext()){
						CharAndWeight element = j.next();
						newTrees[i]= new HuffTree(element);
					}
				}
			}


			@SuppressWarnings({ "rawtypes", "unchecked" })
			BinaryHeap heap = new BinaryHeap(newTrees);

			heap.printHeap();
			
			HuffTree finished = null;
			while(!heap.isEmpty()){
				HuffTree a = (HuffTree) heap.deleteMin();
				if(!heap.isEmpty()){
					HuffTree b=(HuffTree) heap.deleteMin();
					HuffTree joined = new HuffTree(a, b);
					heap.insert(joined);
				}
				else{
					finished=a;
				}
			}
			
			finished.printTree();
			
			CharAndWeight[] toTable = HuffTree.updateCodeArray(finished.root()); 
			
			SeparateChainingHashTable codeTable = new SeparateChainingHashTable(false);
			
			for(int i =0; i<toTable.length; i++){
				
				CharAndWeight x = toTable[i];
				if (x.character==null)
					System.out.println("null");
				codeTable.insert(x);

				
			}
			
			codeTable.printTable();

			BufferedReader brr = new BufferedReader(new FileReader(filePath));
			String line2;
			String huffCode="";

			//create hash table of unique characters and their weight
			while ((line2 = brr.readLine()) != null) {
				char[] cArray= line2.toCharArray();
				for(int i=0; i<cArray.length; i++){
					
					huffCode+=codeTable.findCode(cArray[i]);
					
				}
			}
			brr.close();
			System.out.println(huffCode);

			System.out.println(finished.huffToText(huffCode));
			
			



		}catch (IOException e){
			e.getMessage();
		}catch (ArrayIndexOutOfBoundsException f){
			System.out.println("Please enter a file name.");
			f.printStackTrace();
		}catch(Exception g){
			System.out.println("something's up, not sure what.");
			g.printStackTrace();

		}finally{
			System.out.println("");
			System.out.println("Thanks for using Huffman Codes!");
		}
	}

	


}
