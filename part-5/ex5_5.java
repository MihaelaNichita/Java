
public class ex5_5 {

	public static void testNumOfBitsToFlip() {
		int a=-47564738;
		int b=54678934;
		System.out.println("In order to convert A="+a+"="+Integer.toBinaryString(a));
		System.out.println("\t\t\tto B="+b+"="+Integer.toBinaryString(b));
		int result=numOfBitsToFlip(a,b);
		System.out.println("\twe need to flip "+result+" bits.");
		System.out.println();
	}
	
	/* Determines the number of bits you would need to flip to convert integer A to integer B*/
	private static int numOfBitsToFlip(int a, int b) {
		if(a==b)
			return 0;
		
		int count=0;
		for(int xor=a^b;xor!=0;xor&=(xor-1)){
			count++;
		}
		return count;
	}
}
