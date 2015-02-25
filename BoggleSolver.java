import java.util.*;


public class BoggleSolver {
	
	char[][] board;
	DictionaryInterface dict;
	ArrayList<String> solution;
	
	public BoggleSolver(char[][] b, DictionaryInterface d){
		board = b;
		dict = d;
		
		solution = new ArrayList<String>();
	}
	
	
	
	public ArrayList<String> solve(){
		
		boolean[][] isVisited = new boolean[board.length][board[0].length];
		
		for(int i = 0; i<board.length; i++){
			for(int j = 0; j<board[i].length; j++){
				isVisited[i][j] = false;
			}
		}
		
		for(int i = 0; i<board.length; i++){
			for(int j = 0; j<board[i].length; j++){
				solver("",i,j, isVisited);
				
			}
		}
		return solution;
	}
	
	// i + j are where you are, str is the word, isVisited is where the letters in str are from
	private void solver(String str, int i, int j, boolean[][] isVisited){
		
		
		str+=board[i][j]+"";
		
		isVisited[i][j] = true;
		
		
		str = str.toLowerCase();
		
		if(dict.search(new StringBuilder(str))<=0){	// not prefix not word, go back
			isVisited[i][j]=false;
			
			return;
		}
		else if(dict.search(new StringBuilder(str))==1){		// is prefix but not a word, keep going
			
			
			continueOn(str,i,j,isVisited);
			
			
		}
		else if(dict.search(new StringBuilder(str))==2){ // is a word but not a prefix, go back
			isVisited[i][j]=false;
			if(str.length()>2&&!solution.contains(str.toLowerCase()))
				solution.add(str.toLowerCase());
			return;
		}
		else if(dict.search(new StringBuilder(str))==3){	// is a word and a prefix, keep going;
			
			if(str.length()>2&&!solution.contains(str.toLowerCase()))
				solution.add(str.toLowerCase());
			
			continueOn(str,i,j,isVisited);
			
		}
		isVisited[i][j]=false;
		return;
		
	}
	
	private void continueOn(String str, int i, int j, boolean[][] isVisited){	//helper method for solver, used for making sure the recursion is called correctly 
		//calls solver for all unvisited locations in bounds
		if(i+1<board.length){
			if(!isVisited[i+1][j]){
				solver(str,i+1,j,isVisited);
			}
			if(j-1>=0&&!isVisited[i+1][j-1]){
				solver(str,i+1,j-1,isVisited);
			}
			if(j+1<board[i+1].length&&!isVisited[i+1][j+1]){
				solver(str,i+1,j+1,isVisited);
			}
		}
		if(i-1>=0){
			if(!isVisited[i-1][j]){
				solver(str,i-1,j,isVisited);
			}
			if(j-1>=0&&!isVisited[i-1][j-1]){
				solver(str,i-1,j-1,isVisited);
			}
			if(j+1<board[i-1].length&&!isVisited[i-1][j+1]){
				solver(str,i-1,j+1,isVisited);
			}
		}
		if(j-1>=0&&!isVisited[i][j-1]){
			solver(str,i,j-1,isVisited);
		}
		if(j+1<board[i].length&&!isVisited[i][j+1]){
			solver(str,i,j+1, isVisited);
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}