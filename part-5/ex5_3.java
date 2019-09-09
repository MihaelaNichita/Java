/*
 * Being given two 32-bit numbers, N and M, and two bit positions, i and j,
 * the method will insert M into N, such that M starts at bit j and ends at bit i.
 * Assume that bits j through i have enough space to fit all of M.
 */

public class ex5_3 {
    
    public static void main(String[] args) {
        int n = 1000;
        int m = 77;
        System.out.println("n = " + Integer.toBinaryString(n));
        System.out.println("m = " + Integer.toBinaryString(m));


        int afterInsertion = insertIntegerIntoInteger(n,m,3,8);
        System.out.println(Integer.toBinaryString(afterInsertion));
    }

    public static int insertIntegerIntoInteger(int n, int m, int i, int j) {
        /* Clear all bits j through i */
        int mask = (~0)<<(j+1) | ((1<<i)-1);
        System.out.println("mask = " + Integer.toBinaryString(mask));

        n &= mask;
        return n & mask | (m << i);
    }




	public static void testInsertMintoN() 
	{
		int N=201;
		System.out.println("N : "+Integer.toBinaryString(N));
		int M=7;
		System.out.println("M : "+Integer.toBinaryString(M));
		int j=6;
		int i=2;
		N=insertMintoN2(M,N,j,i);
		System.out.println("N after inserting M: "+Integer.toBinaryString(N));	
		System.out.println();
	}

	/* Solution #1 */
	public static int insertMintoN1(int m, int n, int j, int i)
	{
		/* Step 1: Create mask 11..1 00..0 11..1 */	
		int mask=getMask1(j,i);
		
		/* Clear bits j through i in N 
		 * && Shift M so that it lines up with bits j through i 
		 * && Merge M and N */
		return (n&mask)|(m<<i);
	}

	private static int getMask1(int j, int i) 
	{
		int allOnes=~0;
		System.out.println("allOnes : "+Integer.toBinaryString(allOnes));
		
		int left=allOnes<<(j+1);
		System.out.println("left : "+Integer.toBinaryString(left));
		
		int right=(1<<i)-1;
		System.out.println("right : "+Integer.toBinaryString(right));
		
		int mask=left|right;
		System.out.println("mask : "+Integer.toBinaryString(mask));
		return mask;
	}
	
	/* Solution #2 */
	public static int insertMintoN2(int m, int n, int j, int i)
	{
		/* Step 1: Create mask 11..1 00..0 11..1 */	
		int mask=getMask2(j,i);
		
		/* Clear bits j through i in N 
		 * && Shift M so that it lines up with bits j through i 
		 * && Merge M and N */
		return (n&mask)|(m<<i);
	}

	private static int getMask2(int j, int i) 
	{
		int mSize=j-i+1;
		
		int sizeOnes=(1<<mSize)-1;
		System.out.println("sizeOnes : "+Integer.toBinaryString(sizeOnes));
		
		int negMask=sizeOnes<<i;
		System.out.println("Mask : "+Integer.toBinaryString(~negMask));

		return ~negMask;
    }
    

}
