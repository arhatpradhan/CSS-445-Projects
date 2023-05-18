// 2021 FALL CS 445 LAB #4  STUDENT STARTER FILE

import java.io.*;
import java.util.*;

public class LL_Recursive<T>
{
	private Node<T> head;  // pointer to the front (first) element of the list

	public LL_Recursive()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// INSERTS NEW NODE AT FRONT PUSHING EXISTING NODES BACK ONE PLACE
	public void insertAtFront(T data)
	{
		head = new Node<T>(data,head);
	}

 	// MUST USE (CALL) search() METHOD IN THIS CODE TO DETERMINE THE RETURN VALUE
 	// NO LOOPS ALLOWED NO RECURSION ALLOWED.  THE SEARCH WILL BE RECURSIVE THOUGH
 	public boolean contains( T key )
 	{
 		 if(search(key) != null) return true;
		 else return false;

	}

	// #####  W R I T E  (O R  R E-W R I T E)  T H E S E   M E T H O D S   R E C U R S I V E L Y ####

	// COPY ALL NODES FROM OTHER LIST INTO THIS LIST. WHEN COMPLETED THIS LIST WILL BE IDENTICAL TO OTHER
	// MUST USE RECURSION. THIS MUST BE A DEEP COPY OF THE OTHER LIST INTO THIS LIST
	// YOU WILL HAVE TO WRITE A HELPER METHOD. THE HELPER WILL BE RECURSIVE
	public LL_Recursive( LL_Recursive<T> other )
	{
		copyHelper(other.head);  // got you started here
	}
	private void copyHelper( Node<T> otherHead )
	{
		LL_Recursive<T> copy = new LL_Recursive<T>();
		Node<T> curr = otherHead;
		if(curr != null){
			copy.insertAtFront(curr.data);
		}
		copyHelper(curr.next);
	}

	// YOU MUST WRITE THIS USING RECURSION
	// YOU WILL HAVE TO WRITE A HELPER METHOD. THE HELPER WILL BE RECURSIVE
	public void insertAtTail(T data)
	{
		//if list is empty insert at front
		if(head == null){
			insertAtFront(data);
			return;
		 }else{
			insertAtTailHelper(head, data);
		}
		// YOUR CODE HERE. MUST USE insertAtFront() IN BASE CASE
	}
	//takes in a node and data
	private void insertAtTailHelper(Node<T> curr, T data){
		//basecase
		if(curr.next == null){
		 curr.next = new Node<T>(data, null);
		 return;
	 }
		insertAtTailHelper(curr.next, data);
	}

	// YOU MUST WRITE THIS USING RECURSION
	// YOU WILL HAVE TO WRITE A HELPER METHOD. THE HELPER WILL BE RECURSIVE
	public int size()
	{
		return sizeHelper(head); //JUST TO MAKE IT COMPILE. REPLACE WITH YOUR CODE
	}
	private int sizeHelper(Node<T> curr){
		if(curr == null) return 0;
		return 1 + sizeHelper(curr.next);
	}

	// USE THE TOSTRING AS OUR PRINT.  ***MUST RE-WRITE USING RECURSION***
	// YOU WILL HAVE TO WRITE A HELPER METHOD. THE HELPER WILL BE RECURSIVE
	public String toString()
	{
		Node<T> curr = head;
		return	toStringHelper(curr);
	}

	private String toStringHelper(Node<T> curr){
		String toString = "";
		if(curr == null) return toString;
		toString += curr.data;
		if(curr.next != null) toString += " -> ";
		return  toString + toStringHelper(curr.next);

	}


	// MUST BE RECURSIVE. YOU WILL HAVE TO WRITE A HELPER METHOD. THE HELPER WILL BE RECURSIVE
	public Node<T> search( T key )
	{
		Node<T> curr = head;
		return searchHelper(curr, key);
	}
	private Node<T> searchHelper(Node<T> curr, T key){
		//Basecase if looked through whole list and can't find key
		//return null
		if(curr == null) return null;
		if(curr.data.equals(key)){
			return curr;
		}
	 return searchHelper(curr.next, key);

	}

} //END OF LL_Recursive CLASS


///////////////////////////////////////////////////////////////////////////////////////////////////

class Node<T>
{ T data;
  Node<T> next;
  Node() { this( null, null ); }
  Node(T data){this( data, null ); }
  Node(T data, Node<T> next) { this.data=data; this.next=next; }
  public String toString() { return ""+data; }
} //END OF NODE CLASS
