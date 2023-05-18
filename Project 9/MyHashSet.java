import java.io.*;
import java.util.*;

public class MyHashSet implements HS_Interface
{	private int numBuckets; // changes over life of the hashset due to resizing the array
	private Node[] bucketArray;
	private int size; // total # keys stored in set right now

	// THIS IS A TYPICAL AVERAGE BUCKET SIZE. IF YOU GET A LOT BIGGER THEN YOU ARE MOVING AWAY FROM (1)
	private final int MAX_ACCEPTABLE_AVE_BUCKET_SIZE = 20;  // MAY CHOSE ANOTHER NUMBER

	public MyHashSet( int numBuckets )
	{	size=0;
		this.numBuckets = numBuckets;
		bucketArray = new Node[numBuckets]; // array of linked lists
		System.out.format("IN CONSTRUCTOR: INITIAL TABLE LENGTH=%d RESIZE WILL OCCUR EVERY TIME AVE BUCKET LENGTH EXCEEDS %d\n", numBuckets, MAX_ACCEPTABLE_AVE_BUCKET_SIZE );
	}

	private int hashOf( String key ) //  number returned -MUST- MUST BE IN [0..numBuckets-1]
	{
		int hash = 0;
		for (int i = 0; i < key.length(); i++)
            hash = ((key.charAt(i) + (hash * 7)) % numBuckets);
		return Math.abs(hash % numBuckets);
	}
	public boolean add( String key )
	{
		int hash = hashOf(key);
		if (contains(key)) return false;
		if (bucketArray[hash] == null)
			bucketArray[hash] = new Node(key, null);
		else
		{
			Node curr = bucketArray[hash];
            while(curr.next != null){
                curr = curr.next;
							}
		curr.next = new Node(key,null);
		}
		++size; // you just added a key to one of the lists
		if ( size > MAX_ACCEPTABLE_AVE_BUCKET_SIZE * this.numBuckets)
			upSize_ReHash_AllKeys(); // DOUBLE THE ARRAY .length THEN REHASH ALL KEYS
		return true;
	}

	public boolean remove(String key)
	{
        Node check = search(key);
		if(check == null)
			return false;

        Node curr = bucketArray[hashOf(key)];
		if(curr.data.equals(key)){
			bucketArray[hashOf(key)] = bucketArray[hashOf(key)].next;
			return true;
      }

		while(!curr.next.data.equals(key))
			curr = curr.next;

		if (curr.next.next == null){
			curr.next = null;
			return true;
		}
		curr.next = curr.next.next;
		return true;
    }

	public Node search(String key)
	{
		int hash = hashOf(key);
		Node curr = bucketArray[hash];
		while(curr != null){
			if(curr.data.equals(key))
				return curr;
			curr = curr.next;
		}
		return null;
	}

	public boolean contains( String key )
	{
		return (search(key) != null);
	}

	public void clear()
	{
		bucketArray = new Node[bucketArray.length];
	}

	public int size()
	{
		return size;
	}

	public boolean isEmpty()
	{
		return (size == 0);
	}

	private void upSize_ReHash_AllKeys()
	{
		System.out.format("KEYS HASHED=%10d UPSIZING TABLE FROM %8d to %8d REHASHING ALL KEYS\n", size, bucketArray.length, bucketArray.length*2  );

		Node[] biggerArray = new Node[ bucketArray.length * 2 ];
		this.numBuckets = biggerArray.length;

		for (Node n : bucketArray){
			Node curr = n;
			while (curr != null){
				String key = curr.data;
				int hash = hashOf(key);
				Node var = new Node(key,biggerArray[hash]);
				biggerArray[hash] = var;
				curr = curr.next;
			}
		}
		bucketArray = biggerArray;
	} // END OF UPSIZE & REHASH

} //END MyHashSet CLASS

class Node{
	String data;
	Node next;
	public Node ( String data, Node next ){
		this.data = data;
		this.next = next;
	}
}
