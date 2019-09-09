package numbers;

import java.util.Arrays;
// SmallestDifference 
public class ex6_3 
{
	public static void main(String[] args)
	{
		int[] arr1 = {75,25,18,4};
		int[] arr2 = {-5,-48,-52,-86,383,-20};
		
		int diff=smallestDifference(arr1,arr2);
		System.out.println("Smallest Differcence is: "+diff);
	}
	
	/* Given two arrays of integers, compute the pair of values
	 * (one value in each array) with the smallest non-negative difference. */	
	public static int smallestDifference(int arr1[],int arr2[]) 
	{
		Arrays.sort(arr1);
		Arrays.sort(arr2);
		
		int i=0, j=0;
		int minDiff=Integer.MAX_VALUE;
		
		while(i<arr1.length && j<arr2.length)
		{
			int currDiff=Math.abs(arr1[i]-arr2[j]);
			
			if (currDiff<minDiff)
				minDiff=currDiff;
			
			/* Increment the iterator of the array that stores a smaller value at the current position. */
			if(arr1[i]<arr2[j]) i++;
			else j++;
		}
		
		return minDiff;
	}

}
