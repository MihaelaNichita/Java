
/**BinaryDoubleToString */
public class ex5_1 {
	public static void testBinaryDoubleToString() {
		double n=0.109375;
		String bin=binaryDoubleToString1(n);
		System.out.println("Binary representation of "+n+" on 32 bits: " +bin);
		
		n=.12109375;
		bin=binaryDoubleToString2(n);
		System.out.println("Binary representation of "+n+" on 32 bits: " +bin);
		System.out.println();
	}

	private static String binaryDoubleToString1(double n) {	
		if(n>=1 || n<=0)
			return "ERROR";
		
		StringBuilder bin=new StringBuilder();
		bin.append('.');

		while(n>0){
			if(bin.length()>32)
				return "ERROR";
			
			/* If we only update n as n*2 on each iteration, no matter if current n is >=1 or not,
			 * after the first time expression n>=1 evaluates to being true, 
			 * every subsequent test on the same expression will return true, 
			 * because n only gets bigger and bigger, so the method would always return "ERROR" 
			 * SOLUTION : */
			double r=n*2;
			
			if(r>=1){
				bin.append(1);
				n=r-1;
			}
			else{
				bin.append(0);
				n=r;
			}
		}
		return bin.toString();
	}
	
	/*
	 * Rather than multiplying the number by two and comparing it to one, 
	 * we can compare the number to a decreasing negative power of two 
	 * (.5 then .25 and so on) 
	 */
	private static String binaryDoubleToString2(double n) {	
		if(n>=1 || n<=0)
			return "ERROR";
		
		StringBuilder bin=new StringBuilder();
		bin.append('.');
		
		double frac=.5;

		while(n>0){
			if(bin.length()>32)
				return "ERROR";
			
			if(n>=frac){
				bin.append(1);
				n-=frac;
			}
			else bin.append(0);
			
			frac/=2;
		}
		return bin.toString();
	}
}
