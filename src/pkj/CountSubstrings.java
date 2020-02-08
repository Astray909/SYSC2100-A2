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
	ArrayList<Character> cListA;
	LinkedList<Character> cListL;

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

			boolean s = false;
			while(!s)
			{
				System.out.print("What operating system are you using? Type U for linux/MacOs or W for windows." + "\n");
				String sys = input.nextLine();

				if(sys.contentEquals("w") || sys.contentEquals("W"))
				{
					dir += "\\";
					s = true;
				}
				else if(sys.contentEquals("u") || sys.contentEquals("U"))
				{
					dir += "/";
					s = true;
				}
				else
				{
					System.out.println("Invalid system type, please try again");
				}
			}

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

			BufferedReader r = new BufferedReader(new FileReader(dF));

			String read = r.readLine();

			for(int i = 0; i < word.length(); i++)
			{
				listA.add(word.charAt(i));
				listL.add(word.charAt(i));
			}

			while(read!=null){

				StringTokenizer obj= new StringTokenizer(read);

				while(obj.hasMoreTokens())
				{
					time(obj);
				}
				read = r.readLine();
			}

			System.out.println("Using ArrayList, found " + countA + "matches, delivered in " + timeA + " milliseconds.");
			System.out.println("Using LinkedList, found " + countL + "matches, delivered in " + timeL + " milliseconds.");
		}
		catch(IOException e)
		{
			System.out.println("Error! File not found or invalid String");
			System.exit(1);
		}
	}

	/**
	 * records the processing time of the two different ADT types
	 * @param obj: StringTokenizer object
	 */
	private void time(StringTokenizer obj)
	{
		cListA = new ArrayList<Character>();
		cListL = new LinkedList<Character>();

		String token = obj.nextToken();

		for(int i=0;i<token.length();i++)
		{
			cListA.add(token.charAt(i));
			cListL.add(token.charAt(i));
		}

		//count time for ArrayList
		long startA = System.currentTimeMillis();
		if(findBrute(cListA,listA)!=-1){
			countA++;
		}
		long endA = System.currentTimeMillis();
		timeA = timeA + (endA-startA);
		//end of timing block

		//count time for LinkedList
		long startL = System.currentTimeMillis();
		if(findBrute( cListL,listL)!=-1){
			countL++;
		}
		long endL = System.currentTimeMillis();
		timeL = timeL + (endL-startL);
		//end of timing block
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
