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
	float timeA = 0;
	float timeL = 0;
	ArrayList<Character> listA = new ArrayList<Character>();
	LinkedList<Character> listL = new LinkedList<Character>();

	/**
	 * constructor for the class
	 */
	public CountSubstrings() {
		//empty
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		try
		{
			CountSubstrings c = new CountSubstrings();

			Scanner input = new Scanner(System.in);

			System.out.print("Please enter the directory to your desired file: " + "\n");
			String dir = input.nextLine();
			dir += "\\";

			System.out.print("Please enter the file you want to perform the search on: " + "\n");
			String file = input.nextLine();
			String dF = dir + file;

			System.out.print("Please enter the word you want to search for: " + "\n");
			String word = input.nextLine();

			input.close();

			c.compare(word, dF);
		}

		catch(IOException e)
		{
			System.out.println("file not found");
		}

	}


	/**
	 * parses through ADT to search for a user specified string
	 * @param word: word to search for
	 * @param dF: ADT to be parsed
	 * @throws IOException 
	 */
	public void compare(String word, String dF) throws IOException
	{
		try
		{
			System.out.println("The String <" + word + "> will be searched in file " + dF + "\n");

			BufferedReader reader = new BufferedReader(new FileReader(dF));

			String read = reader.readLine();

			for(int i = 0; i < word.length(); i++)
			{
				listA.add(word.charAt(i));
			}

			for(int i = 0; i < word.length(); i++)
			{
				listL.add(word.charAt(i));
			}

			while(read!=null){

				StringTokenizer obj= new StringTokenizer(read);

				while(obj.hasMoreTokens())
				{
					ArrayList<Character> charArrayList = new ArrayList<Character>();
					LinkedList<Character> charLinkedList = new LinkedList<Character>();

					String token = obj.nextToken();

					for(int i=0;i<token.length();i++)
					{
						charArrayList.add(token.charAt(i));
						charLinkedList.add(token.charAt(i));
					}

					//count time for ArrayList
					long startA = System.currentTimeMillis();
					if(findBrute(charArrayList,listA)!=-1){
						countA++;
					}
					long endA = System.currentTimeMillis();
					timeA = timeA + (endA-startA);
					//end of timing block

					//count time for LinkedList
					long startL = System.currentTimeMillis();
					if(findBrute( charLinkedList,listL)!=-1){
						countL++;
					}
					long endL = System.currentTimeMillis();
					timeL = timeL + (endL-startL);
					//end of timing block

				}
				read = reader.readLine();
			}

			System.out.println(countA + "matches, delivered in " + timeA + " milliseconds.");
			System.out.println(countL + "matches, delivered in " + timeL + " milliseconds.");
		}
		catch(IOException e)
		{
			System.out.println("Error!");
			System.exit(1);
		}
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
