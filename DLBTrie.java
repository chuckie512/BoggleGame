import java.util.*;

public class DLBTrie implements DictionaryInterface{
	
	protected TrieNode<Character> root;
	protected int size;
	
	
	//null contstructor 
	public DLBTrie(){
		root = null;
		size = 0;
	}
	
	
	// adds String s to the trie
	public boolean add(String s){
		
		s=s.toLowerCase();
		s=s.trim();
		
		if(s.length()==0){	//returns if s was empty or only whitespace
			return false;
		}
		
		if(size == 0){	// first entry
			TrieNode<Character> node = new TrieNode<Character>(s.charAt(0));
			root = node;
			//System.out.println("adding a word, DLBTrie add");
		}
		
		TrieNode<Character> iter = root;
		
		for(int i=0; i<s.length(); i++){
			
			if(i>0){ // doesn't run on first loop
				if(iter.getNext() == null){	// if the next is null then there is no need to check for siblings and can just add the word
					iter.setNext(s.charAt(i));
					iter=iter.getNext();	
					continue;
				}
				iter=iter.getNext();	// moves down the trie
			}
			
			while(true){	
				
				if(iter.getData()!=null&&iter.getData() == s.charAt(i) ){	//if the char is already in the Trie
					break;
				}
				
				if(iter.hasSibling()){	//moving horizontal if possible
					iter = iter.getSibling();
					continue;
				}
				
				if(!iter.hasSibling()){	// adds a sibling
					iter.setSibling(new TrieNode<Character>(s.charAt(i)));
					iter = iter.getSibling();
					break;
				}
				
				System.out.println("error 1 in DLBTrie add( " + s +" ) on the " + i + " char.");		// one really never should get here...
				return false;

			}
			
			
		
		}
		
		// adding the null node
		if(iter.getNext()== null){	//iter has no child
			iter.setNext(new TrieNode<Character>(null));
		}
		
		else{
			iter=iter.getNext();
			
			while(iter.hasSibling()){
				iter=iter.getSibling();
			}
			
			iter.setSibling(new TrieNode<Character>(null));
		}
		
		size++;
		return true;
		
	}
	/**
		searchs horizontally on the trie for a node
		
		@return returns the node with data, or null if data wasn't found
	*/
	private TrieNode<Character> hasSibling(TrieNode<Character> iter, char data){
		
		if(iter == null){
			return null;
		}
		
		if(iter.getData()!=null&&iter.getData()==data){
			return iter;
		}
		
		while(iter.hasSibling()){
			iter = iter.getSibling();
			if(iter.getData()==data){
				return iter;
			}
		}
		return null;
		
	}
	
	private boolean hasNullSibling(TrieNode<Character> iter){
		
		if(iter == null){
			return false;
		}
		
		if(iter.getData()==null){
			return true;
		}
		
		while(iter.hasSibling()){
			iter = iter.getSibling();
			if(iter.getData()==null){
				return true;
			}
		}
		return false;
		
	}
	
	// the meat and potatoes of the class
	public int search(StringBuilder s){
		String str = s.toString();
		str = str.toLowerCase();
		
		TrieNode<Character> iter = root;
		
		for(int i = 0; i<str.length(); i++){
			iter = this.hasSibling(iter, str.charAt(i));
			if(iter == null){
				return 0;	// is not a preface or a word
			}
			iter = iter.getNext();	// every letter has a child node
		}
		
		
		
		boolean isPrefix = false;
		boolean isWord = false;
		if(this.hasNullSibling(iter)){	//if the child of the last letter node is null or has a null sibling, str is a word
			isWord = true;
		}
		
		if(iter.hasSibling()||iter.getData()!=null){	//if there is more than one sibling or if the only child does not have null data, it is a prefix			
			isPrefix = true;
		}
		
		
		if(isWord && isPrefix){
			return 3;
		}
		
		if(isWord && !isPrefix){
			return 2;
		}
		
		if(isPrefix && !isWord){
			return 1;
			
		}
		System.out.println("the search in DLBTrie.java encountered an error searching for: " + str);
		return -1; // should not get here
		
	}
	
	
}