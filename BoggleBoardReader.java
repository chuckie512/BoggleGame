import java.util.*;
import java.io.*;

public class BoggleBoardReader {
	
	private char[][] board;
	private boolean successful;
	
	public BoggleBoardReader(){
		this("board1.txt");
	}
	
	public BoggleBoardReader(String fileName){
		successful = true;
		board = new char[4][4];
		
		try{
		Scanner fileScan = new Scanner(new FileInputStream(fileName));
		
		String str = fileScan.nextLine();
		if(str.length()<16){
			System.out.println("the file: " + fileName +" does not contain the appropriate number of characters.");
			successful = false;
			str = "abcdefghijklmnopq";
		}
		int pos = 0;
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				board[i][j] = str.charAt(pos);
				pos++;
			}
		}
		
		}
		catch(FileNotFoundException e){
			System.out.println("could not open the boggleBoard: " + fileName);
			successful = false;
		}
		
	}
	
	public char[][] getBoard(){
		return board;
	}
	
	public boolean wasSuccessful(){
		return successful;
	}
	
	
}