
public class ex5_7 {


	public static void testMaxLengthFlipBit(int n) {

		int maxLen=maxLenFlipBit(n);
		System.out.println("NR="+n+"="+Integer.toBinaryString(n));
		if(maxLen==Integer.BYTES*8)
			System.out.print("Already maximum number of 1s : ");
		else
			System.out.print("Length of the longest sequence of 1s that can be created by flipping exactly 1 bit from 0 to 1 : ");
		System.out.println(maxLen);
		System.out.println();
	}
	
	/* Solution without keeping track of previous bit. */
	/* PROBLEM SOLVED when only keeping track of the current sequence length :
	 * when we find a lonely bit of zero, we don't do anything, just go for the nextBit 
	 * (we add the flop bit to the seqLength only when comparing it to maxLength)
	 * SO when we find a bit of zero, followed by a bit of zero, we compare the currentSeqLength+1 to maxLength
	 * (+1 because seqLength didn't count the flop bit )*/
	
	/*
	 * PROBLEM : we can get more sequences of 1s with only one zero between them:
	 * current solution doesn't do anything when (current bit is zero and separates two sequences of 1s)
	 * so it gets further counting, as if it's just one sequence of 1s
	 * SOLUTION : 
	 * 1. keep track of both currentSeqLength and previousSeqLength individually
	 *    maxLength will add the lengths
	 * 2. create boolean variable unitedSeq which tells whether currentSequence is made out of two sequences 
	 *    - DOESN'T WORK - we need to keep track of the length of the previous sequence alone,
	 *      to see whether, by flipping the bit between current and previous, we would get a bigger length
	 */
	private static int maxLenFlipBit(int n) {
		if(n==~0)
			return Integer.BYTES*8;
		
		if(n==0)
			return 1;
		
		// check if n is a power of two (has exactly 1 bit of 1)
		if((n&(n-1))==0) 
			return 2;
		
		int prevSeqLen=0;
		int currSeqLen=0;
		int maxLen=1;
		
		// work with negatives as well
		while(n!=0){
			if((n&1)==1) // current bit is one
				currSeqLen++;
			else {// current bit is zero				
				prevSeqLen=(n&2)==0?0:currSeqLen;
				currSeqLen=0; 
				
			}
			maxLen=Integer.max(currSeqLen+prevSeqLen+1, maxLen);
			n>>>=1;
		}
		return maxLen;
	}
}
