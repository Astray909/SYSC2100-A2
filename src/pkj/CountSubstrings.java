/**
 * 
 */
package pkj;

import java.io.*;
import java.util.*;

/**
 * @author Jia Chen Huang 101073186
 * @version Feb 7, 2020
 *
 */
public class CountSubstrings {

	int countA = 0;
	int countL = 0;

	/**
	 * constructor for the class
	 */
	public CountSubstrings() {
		//empty constructor
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Please enter the directory to your desired file: ");
		String dir = input.nextLine();

		System.out.print("Please enter the file you want to perform the search on: ");
		String file = input.nextLine();
		String dF = dir + file;

		System.out.print("Please enter the word you want to search for: ");
		String word = input.nextLine();
		
		input.close();

		compare(word, dF);
	}

	public static void compare(String word, String dF)
	{
		System.out.println("The String " + word + " will be searched in file " + dF);
		return;
	}
	
	//findBrute from CUlearn
	/*
	 * Returns the lowest index at which substring pattern begins in text (or
	 * else -1).
	 */

	private static int findBrute(List<Character> text, List<Character> pattern) {
			int n = text.size();
			int m = pattern.size();
			for (int i = 0; i <= n - m; i++) { // try every starting index 
								     // within text
				int k = 0; // k is index into pattern
				while (k < m && text.get(i + k) == pattern.get(k))
					// kth character of pattern matches
					k++;
				if (k == m) // if we reach the end of the pattern,
					return i; // substring text[i..i+m-1] is a match
			}
			return -1; // search failed
	}


}
