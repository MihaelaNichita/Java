import java.security.InvalidParameterException;

public class ex5_2 {

	public static void testGetNextAndPrev(int n) 
	{
		if(n<0)
			throw new InvalidParameterException("Number must be positive");
		
		System.out.println("n="+n+"="+Integer.toBinaryString(n));
		int next=getNext(n);
		if(next!=-1)
			System.out.println("Next smallest number with the same number of 1 bits = "+next+" = "+Integer.toBinaryString(next));
		else
			System.out.println("There is no bigger number with the same number of 1s.");
		
		int prev=getPrev(n);
		if(prev!=-1)
			System.out.println("Next largest number (previous) with the same number of 1 bits = "+prev+" = "+Integer.toBinaryString(prev));
		else
			System.out.println("There is no smaller number with the same number of 1s.");
		System.out.println();
	}

	private static int getPrev(int n) {
		if(n==0 || n==(~0)>>>1) return -1;
		
		/* compute c1==number of trailing 1s  and c0==number of 0s to the right of the bit to be flipped from 1 to 0 */
		int c0=0;
		int c1=0;
		int c=n;
		
		while((c&1)==1){
			c1++;
			c>>=1;
		}
		
		while((c&1)==0 && c!=0){
			c0++;
			c>>=1;
		}
		
		/* ERROR: if n=00..00 11..11, then there is no smaller number with the same number of 1s */
		if(c0+c1==31)
			return -1;
		
		return getPrevHelper1(n,c0,c1);
		//return n-getPrevHelper2(c0,c1);
	}

	/* Clever Arithmetic and Bit Manipulation */
	private static int getPrevHelper2(int c0, int c1) {
		return (1<<c1)+(1<<(c0-1))-1;
	}


	/* Only Bit Manipulation */
	private static int getPrevHelper1(int n, int c0, int c1) {		
		/* Position of rightmost non-trailing one */
		int p=c0+c1;
		
		/* Clear from bit p onwards, using &mask 11...11 0 00...00*/
		n&=(~0)<<(p+1); //  or ~((1<<(p+1))-1)
				
		/* Add back c1+1 bits of 1 starting from position p-1, using |mask 00..00 0 11..1 00..00 */
		n|=((1<<(c1+1))-1)<<(c0-1);
		return n;
	}


	/* 
	 * Because we work with positive integers only, MSB needs to be zero,
	 * so we can't flip that zero to one
	 */
	private static int getNext(int n) {
		if(n==0 || n==(~0)>>>1) return -1; // biggest positive number binary representation: 011..11

		/* compute c0==number of trailing 0s  and c1==number of 1s to the right of the bit to be flipped from 0 to 1 */
		int c0=0;
		int c1=0;
		int c=n;
		
		while((c&1)==0 && (c!=0)){
			c0++;
			c>>=1;
		}
		
		while((c&1)==1){
			c1++;
			c>>=1;
		}
		
		/* ERROR: if n=011..11 00..00, then there is no bigger number with the same number of 1s */
		/* Can (c0+c1==0) ever be true if n!=0 ? */
		if(c0+c1==31)
			return -1;
		
		//return getNextHelper1(n,c0,c1);
		return n+getNextHelper2(c0,c1);
	}

	private static int getNextHelper2(int c0, int c1) {
		return (1<<c0)+(1<<(c1-1))-1;
	}


	private static int getNextHelper1(int n, int c0, int c1) {		
		/* Position of rightmost non-trailing zero */
		int p=c0+c1; // max position is 30, not 31, because bit 31 needs to be zero (positive integer)
		
		/* Flip rightmost non-trailing zero */
		n|=(1<<p);
		
		/* Clear all bits after p */
		n&=(~0<<p);
		
		/* Add back c1-1 1s to the right */
		n|=(1<<(c1-1))-1;
		return n;
	}
}
