// 2021 FALL CS 445 LAB #3  STUDENT STARTER FILE

import java.io.*;
import java.util.*;

public class LinkedList<T extends Comparable<T>>
{
	private Node<T> head;  // pointer to the front (first) element of the list
	private Node<T> tail;  // pointer to the last elem of the list ( caboose or tail node)

	public LinkedList()
	{
		head = null; // compiler does this anyway. just for emphasis
		tail = head;
	}

	// USE THE TOSTRING AS OUR PRINT
	public String toString()
	{
		String toString = "";

		for (Node<T> curr = head; curr != null; curr = curr.next)
		{
			toString += curr.data;		// WE ASSUME OUR T TYPE HAS toString() DEFINED
			if (curr.next != null)
				toString += " -> ";
		}

		return (String) (toString + " ");
	}

	// ########################## Y O U   W R I T E   T H E S E    M E T H O D S
	// . . .AND ANY SUPPORTING METHODS YOU NEED FOR THEM
	public int size() // OF COURSE MORE EFFICIENT to KEEP COUNTER BUT YOU  MUST WRITE LOOP
	{
		int count = 1;
		Node<T> curr = head;
		if(head == null){
			count = 0;
			return count;
		}
		while(curr.next != null){
				curr = curr.next;
				count++;
		}
		return count;
	}
	// THIS VERSION JUST LOADS THE LISTS FROM THE FILE BEFORE THEY ARE MERGED
	public void insertAtTail( T data )
	{
		// YOUR CODE HERE
		//basecase
		if(size() == 0){
	 		insertAtFront(data);
			return;
		}

		Node<T> curr = head;

		while(curr.next != null){
			curr = curr.next;
		}
		Node<T> last = new Node<T>(data, null);
		curr.next = last;

	}

	public void insertAtFront(T data)
	{
		//creates new node head, puts data in the head, and sets last head
		//as the new next
		head = new Node<T>(data,head);
	}
	public void insertInOrder(T  data)
	{
		//base case 1
		if(head == null){
			 insertAtFront(data);
			 return;
		 }
		 //base case 2
		 //if the incoming data is less than the one already in
		 if(data.compareTo(head.data) < 0){
			 insertAtFront(data);
			 return;
		 }
		 	Node<T> curr = head;
			// Node<T> previous = null;
		 //base case 3
		 //^ the above case tells us that if data < head.data it would put it
		 //in the front, if that isn't true you can just put it in the back
		 if(head.next == null){
			 insertAtTail(data);
			 return;
		 }
		//curr stops at the last element that is still less than data
		//and inserts
		while(curr.next != null && data.compareTo(curr.next.data) > 0){
			// previous = curr;
			curr = curr.next;
		}
		Node<T> insert = new Node<T>(data, curr.next);
		curr.next = insert;
	}

	public LinkedList<T> merge( LinkedList<T> other )  // think 'sorted union' but only 1 pass allowed
	{
	 LinkedList<T> merge = new LinkedList<T>();
	 //points to the first head in the 1st linked list
	 Node<T> curr = this.head;
	 //points to the second head in the 2nd linked list
	 Node<T> curr2 = other.head;
	 //points to the last element in the merged linked list

	 //checks head of both lists
	 if(curr.data.compareTo(curr2.data) < 0){
		 merge.insertAtFront(curr.data);
		 curr = curr.next;
	 }else{
		 merge.insertAtFront(curr2.data);
		 curr2= curr2.next;
	 }
	 //sets tail to merge.head to keep track of the nodes
	 tail = merge.head;


	 while(curr != null && curr2 != null){
	 //checks which two of the nodes is bigger
	 //puts that node in the merged list and sets tail
	 if (curr.data.compareTo(curr2.data) < 0){
	 		merge.insertAtTail(curr.data);
			curr = curr.next;
 		}else{
			merge.insertAtTail(curr2.data);
			curr2 = curr2.next;

		}
		tail = tail.next;

	 //don't increment tail because it is already pointing to what was
	 //added after operation above
 	}
	 if(curr != null) tail.next = curr;
	 else if(curr2 != null) tail.next = curr2;
	 return merge;
	}
} //END OF LINKEDLIST CLASS DEFINITION

// NODE CLASS
 class Node<T>
{
  T data;
  Node<T> next;

  Node(T data)
  {
    this( data, null );
  }
  Node(T data, Node<T> next)
  {
    this.data = data;
    this.next = next;
  }

  public String toString()
  {
	  return "" + data; // stringify the data
  }

} //EOF
