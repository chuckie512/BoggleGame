// 1-28-15
// cas275@pitt.edu
public class TrieNode<E>{
	
	private E data;
	private TrieNode<E> nextNode;
	private TrieNode<E> sibling;
	
	public TrieNode(){
		data = null;
		nextNode = null;
		sibling = null;
	}
	
	public TrieNode(E in){
		data = in;
	}
	
	public TrieNode<E> getNext(){
		return nextNode;
	}
	
	public TrieNode<E> getSibling(){
		return sibling;
	}
	
	public void setData(E in){
		data = in;
	}
	
	public void setNext(TrieNode<E> next){
		nextNode = next;
	}
	public void setNext(E data){
		nextNode = new TrieNode<E>(data);
	}
	
	public E getData(){
		return data;
	}
	
	public void setSibling(TrieNode<E> sib){
		sibling = sib;
	}
	public void setSibling(E data){
			sibling = new TrieNode<E>(data);
	}
	
	public boolean hasSibling(){
		return sibling != null;
	}
	
}