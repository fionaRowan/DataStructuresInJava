/*
 * Fiona Rowan
 * 
 * DataStructures, Programming 3
 * SpellChecker Class
 * Uses hash table to check spelling of 
 * file inputted in commandline
 * 
 */






import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class SpellChecker {
	static SeparateChainingHashTable<String> dictionary;
	public static void main(String[] args) throws IOException{
		try{
			
			//file paths that will be needed
			String bigDictPath = args[0];
			String smallDictPath = args[1]; 
			String inputPath = args[2]; 
			
			//for iterating through files later
			String line;
			String[] inputLine; 
			int lineCounter = 0;


			//we iterate through the dictionary files once to find their combined size
			BufferedReader bigDict = new BufferedReader(new FileReader(bigDictPath));

			while((line = bigDict.readLine()) != null){
				lineCounter++;
			}
			bigDict.close();

			BufferedReader smallDict = new BufferedReader(new FileReader(smallDictPath));
			while((line = smallDict.readLine()) != null){
				lineCounter++;
			}
			smallDict.close();
			
			//we make the size of the dictionary hash table the sum of the two dict file sizes.
			dictionary = new  SeparateChainingHashTable<>(lineCounter + 10);

			BufferedReader bigDict2 = new BufferedReader(new FileReader(bigDictPath));

			while((line = bigDict2.readLine()) != null){
				String line2 = (line.replaceAll("\\s+","")).toLowerCase();
				dictionary.insert(line2.toLowerCase());
			}
			bigDict2.close();

			//hash small dictionary file
			BufferedReader smallDict2 = new BufferedReader(new FileReader(smallDictPath));
			while((line = smallDict2.readLine()) != null){
				String line2 = (line.replaceAll("\\s+","")).toLowerCase();
				dictionary.insert(line2.toLowerCase());
			}
			smallDict2.close();
			lineCounter=0;

			//check to see if input words are in hash table
			BufferedReader input = new BufferedReader(new FileReader(inputPath));
			while((line = input.readLine())!=null){
				inputLine = line.split("\\s+");
				for(int i=0; i<inputLine.length; i++){
					String word = inputLine[i].toLowerCase();

					if(!dictionary.contains(word) && word.matches(".*\\w.*") && word.length()>1){
						
						//remove punctuation at the end
						if(word.charAt(word.length()-1)=='.' || word.charAt(word.length()-1)==',' || word.charAt(word.length()-1)==':' ||word.charAt(word.length()-1)==':' || word.charAt(word.length()-1)==')')
							word = word.replace(word.substring(word.length()-1), "");
						
						//see if dictionary hash table contains the word
						if(!dictionary.contains(word)){
							System.out.println("Mispelled word: " + inputLine[i]+" @ line "+(lineCounter+1));
							int options = 0;
							//add one character
							String addOptions = addChar(word);
							if(addOptions != ""){
								System.out.println("Try adding a character. Option(s): " + addOptions);
								options ++;
							}
							String removeOptions="";
							if(word.length()>0)
								removeOptions = removeChar(word);	
							if(removeOptions != ""){
								System.out.println("Try removing a character. Option(s): " + removeOptions);
								options ++; 
							}
							String swapOptions = "";
							if(word.length()>1)
								swapOptions = swapChar(word);
							if(swapOptions !=""){
								System.out.println("Try swapping a character. Option(s): "+swapOptions);
								options++;
							}
							if(options == 0){
								System.out.println("There are no options.");
							}
							System.out.println("");
						}
					}
				}
				lineCounter++; 
			}

			input.close();

		}catch(IOException e){
			System.out.println("Please check your file!");
		}catch(ArrayIndexOutOfBoundsException f){
			System.out.println("Please enter three files.");
		}finally{
			System.out.println("Thanks for spell-checking!");
		}



	}
	
	//see if adding a character will make input a word
	private static String addChar(String x){
		String options = "";
		int xLength= x.length();
		String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "'"};
		for(int i = 0; i<alphabet.length; i++){
			if(dictionary.contains(alphabet[i]+ x))
				options = options + alphabet[i] + x + "; ";
			for(int j =0; j<xLength; j++){
				if(dictionary.contains(x.substring(0, j)+alphabet[i] + x.substring(j, xLength)))
					options = options + x.substring(0, j)+alphabet[i] + x.substring(j, xLength) + ", ";
			}
		}
		return options;
	}

	//see if removing a character will make input a word
	private static String removeChar(String x){
		String options = "";
		int xLength = x.length(); 
		if(dictionary.contains(x.substring(1, xLength)))
			options = options + x.substring(1, xLength) + "; ";
		for(int i = 1; i<xLength; i++){
			if(dictionary.contains(x.substring(0, i)+x.substring(i+1,  xLength)))
				options = options + x.substring(0, i)+x.substring(i+1,  xLength)+"; ";
		}
		return options; 
	}

	//see if swapping adjacent characters will make input a word
	private static String swapChar(String x){
		String options = "";
		int i = 0;
		String swapped;
		for(int j = 1; j<x.length(); j++){
			swapped = x.substring(0,i) + x.charAt(j)+ x.charAt(i)+ x.substring(j+1, x.length());
			if(dictionary.contains(swapped)){
				options = options + swapped + "; ";
			}
			i++;
		}
		return options; 
	}
}

