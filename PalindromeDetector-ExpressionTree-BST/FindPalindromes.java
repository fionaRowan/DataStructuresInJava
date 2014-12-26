/*
*	Fiona Rowan, Data structures
*	FindPalindromes class
*	reads in a text document of palindromes, detects whether or not each line is a palindrome
*	
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FindPalindromes {
	static String palindrome; 
	static String reverse; 
	static String secondHalf; 

	public static void main(String[] args) throws IOException{
		try{
			String filePath = args[0];
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String line;
			while ((line = br.readLine()) != null) {
				palindrome = line.replaceAll("[\\W]", "");
				reverse="";
				int length = palindrome.length();
				int half = length/2; 
				secondHalf = palindrome.substring(length-half, length);
				MyStack stack = new MyStack(half);

				for(int i=0; i<(half); i++){
					char c = (Character) palindrome.charAt(i);
					stack.push(c); 
				}
				for(int i=0;i<(half); i++){
					reverse= reverse + stack.pop(); 
				}

				if(isPalindrome()){
					System.out.println(line); 
				}


			}
			br.close();
		}catch(Exception h){
			System.out.println("check your file.");
		}
	}
	private static boolean isPalindrome(){
		boolean equal;
		if (reverse.equalsIgnoreCase(secondHalf)){
			equal=true;
		}
		else{
			equal=false; 
		}
		return equal;

	}

}

