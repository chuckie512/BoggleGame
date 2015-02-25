import java.util.*;
import java.io.*;

public class MyBoggle {
	
	
	
	
	public static void main(String [] args){
		
		
		String fileName;
		DictionaryInterface dict;
		DictionaryInterface solution;
		char[][] board;
		
		fileName = "board1.txt";	//the default board if none are specified 
		dict = new DLBTrie();
		
		
		if(args.length>0){
			fileName = args[0];
		}
	
		solution = new DLBTrie();
		
		
		
		// filling the dictionary
		fillDict(dict);
		
		// reading the board and placing it into a char[][] 
		BoggleBoardReader bRead = new BoggleBoardReader(fileName);
		board = bRead.getBoard();
		
		if(!bRead.wasSuccessful()){	//did we read in a board?
			System.exit(1);
		}
		
		//prints the boggle board to the console 
		printBoard(board,fileName);
		
		BoggleSolver solv = new BoggleSolver(board,dict);
		ArrayList<String> sol = solv.solve();
		
		for(String s:sol){
			solution.add(s.toLowerCase());
		}

		Scanner in = new Scanner(System.in);
		System.out.println("enter all the words you can find.  Enter abcd to stop");
		String input = in.nextLine();
		
		
		ArrayList<String> guess = new ArrayList<String>();
		while(!input.equalsIgnoreCase("abcd")){
			printBoard(board,fileName);
			if(solution.search(new StringBuilder(input))>1){
				if(!guess.contains(input) ){
					
					guess.add(input);
					System.out.println("The board does contain " + input +"!  You have found " + guess.size() +" words.");
				}
				else{
					System.out.println("you have already found that word!");
				}
			}
			else{
				System.out.println("invalid word, guess again!");
			}
			System.out.println("guess another word or enter abcd to stop");
			input = in.nextLine();
			
		}
		
		System.out.println("The solutions are:\n\n");
		
		//printing results

		System.out.println(sol.size());
		Collections.sort(sol);
		for(int i = 0; i<sol.size(); i++){
			System.out.println(sol.get(i));
		}
		
		

		System.out.println("you have found " + guess.size() + " words out of " + sol.size() + ".");
		System.out.println("for a total of: " + (((1.0*guess.size())/(1.0*sol.size()))*100) + "%.  Good job!");
		
		

		
		
		
	}
	
	// this method fills the dictionary with words from "dictionary.txt"  these words should be separated by a '\n'
	private static boolean fillDict(DictionaryInterface D){
		try{
			Scanner fileScan = new Scanner(new FileInputStream("dictionary.txt"));
			String st;
			while (fileScan.hasNext())
			{
				st = fileScan.nextLine();
				D.add(st);
				
			}
		}
		catch(FileNotFoundException e){
			System.out.println("could not fill the dictionary.  Is \"dictionary.txt\" in the directory?");
			System.exit(1);
			return false;
		}
		return true;
	}
	
	// prints out the board
	private static void printBoard(char[][] b, String fileName){
		System.out.println("\nBoard from: " + fileName +"\n");
		
		for(int i =0; i<b.length; i++){
			for(int j =0; j<b[i].length; j++){
				System.out.print(b[i][j] + " ");
			}
			System.out.println();
		}
		System.out.print("\n");
	}
	
	
	
	
	
}