/*
 * Fiona Rowan
 * 
 * DataStructures, Programming 3
 * Indexer Class
 * Uses AVL tree to index words of
 * file inputted in commandline
 * 
 */





import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Indexer {

	public static void main(String args[]) throws IOException{
		
		try{
			
			AvlTree<String> index = new AvlTree<>(); 
			String filePath = args[0];
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String line;
			int lineCount = 1; 
			while ((line = br.readLine()) != null) {
				String[] words = line.split("[\\s]");
				for(int i=0; i<words.length; i++){
					if(words[i].length()>1){
					if(words[i].charAt(words[i].length()-1)=='.' || words[i].charAt(words[i].length()-1)==',' || words[i].charAt(words[i].length()-1)==':' ||words[i].charAt(words[i].length()-1)==':' || words[i].charAt(words[i].length()-1)==')')
						words[i] = words[i].replace(words[i].substring(words[i].length()-1), "");
					}
					if(words[i].matches(".*\\w.*"))
						index.insert(words[i], lineCount);
				}
				lineCount++;
			}
			br.close();
			index.printTree();
		}catch (IOException e){
			e.getMessage();
		}catch (ArrayIndexOutOfBoundsException f){
			System.out.println("Please enter a file name.");
		}finally{
			System.out.println("");
			System.out.println("Thanks for using the indexer!");
		}

	}
}
