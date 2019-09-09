
public class ex5_6 {

	public static void testSwapOddEvenBits() {
		int n=756748345;
		System.out.println("\t\t\t\t       n="+n+"="+Integer.toBinaryString(n));
		n=swapOddEvenBits(n);
		System.out.println("After swapping odd bits with even bits: n="+n+"="+Integer.toBinaryString(n));
		
		n=-5679365;
		System.out.println("\t\t\t\t\t n="+n+"="+Integer.toBinaryString(n));
		n=swapOddEvenBits(n);
		System.out.println("After swapping odd bits with even bits: n="+n+"="+Integer.toBinaryString(n));
		System.out.println();
	}

	/* Swaps odd and even bits in an integer with as few instructions as possible (0<->1,2<->3,...)*/
	private static int swapOddEvenBits(int n) {
		/* Operate on odd bits first(shift them once to the right), then on even bits (shift them once to the left) and the merge the two values*/
		/* Logical right shift >>> used to fill the sign bit with zero */
		
		return ((n&0xAAAAAAAA)>>>1)|((n&0x55555555)<<1);
	}

}
