import java.io.*;
import java.util.*;

public class MyHashCode
{
	private int numBuckets;
	// private Bucket[] buckets; // YOU MUST DESIGN THIS CLASS FOR PROJECT
	private int[] bucketSizes;
	private int idealBucketSize;
	private int size; // # keys stored in set

	public MyHashCode( int numBuckets, int idealBucketSize )
	{	size=0;
		this.numBuckets = numBuckets;
		this.idealBucketSize = idealBucketSize;
		bucketSizes = new int[numBuckets]; // OF KEYS IN EACH [i] BUCKET
	}
	// KEYS ARE -NOT- REALLY BEING ADDED IN THIS LAB. JUST HASHED, AND BUCKET COUNTER INCREMENTED
	public boolean add( String key )
	{
		int h = hashOf( key, numBuckets ); // h MUST BE IN [0..numBuckets-1]
		++bucketSizes[h];
		++size;
		return true;
	}
	// RETURNS INT IN RANGE [0..numBuckets-1]
	private int hashOf( String key, int numBuckets ) //  number returned -MUST- MUST BE IN [0..numBuckets-1]
	{
		int result = 0;
			for (int i = 0; i < key.length(); i++){
          result = ((key.charAt(i) + (result * 7)) % numBuckets);
		}
		return Math.abs(result % numBuckets);

	}
	public void printStats()
	{
		System.out.format("#OfBucket: %d idealBucketSize: %d #OfKeysHashed: %d\n", bucketSizes.length, idealBucketSize, size() );

		if (bucketSizes.length < 100 )  System.out.println("Bucket  Size   +/- ");
		if (bucketSizes.length < 100 ) System.out.println("-------------------");
		int minBucketSize=bucketSizes[0], maxBucketSize=bucketSizes[0];

		for (int i=0 ; i<numBuckets ; ++i)
		{
			if (bucketSizes.length < 100 ) System.out.format("%5d %5d  %5d\n",i,bucketSizes[i],bucketSizes[i]-idealBucketSize );
			if ( bucketSizes[i] > maxBucketSize )
				maxBucketSize=bucketSizes[i];
			else if ( bucketSizes[i] < minBucketSize )
				minBucketSize=bucketSizes[i];
		}
		if (bucketSizes.length < 100 )  System.out.println("-------------------");
		System.out.format("minBucketSize %d  maxBucketSize %d\n",minBucketSize,maxBucketSize);
		System.out.format("stdDev %4.2f  var %4.2f\n",stdDev(bucketSizes),variance(bucketSizes) );
	}

	// Function for calculating variance
	double variance(int a[])
	{
		int sum = 0;
		for (int i = 0; i < a.length; i++)
			sum += a[i];
		double mean = (double)sum / (double)a.length;

		double sqDiff = 0.0;
		for (int i = 0; i < a.length; i++)
			sqDiff += (a[i] - mean) * (a[i] - mean);
		return sqDiff / a.length;
	}

	double stdDev(int a[])
	{
		return Math.sqrt(variance(a));
	}
	public int size()
	{
		return size;
	}
} //END CLASS
