package numbers;

// TrailingZeros
public class ex6_2 
{
	public static void main(String[] args) 
	{
		int n=10;
		System.out.println("n="+n);
		
		int tz=trailingFactorialZeros(n);
		System.out.println("Number of trailing zeros for n! = "+tz);

	}
	
	/* The algorithm computes the number of trailing zeros in n factorial. */
	public static int trailingFactorialZeros(int n)
	{
		/* It's equal to the number of pairs of 2 and 5 multiples in n's factors. */
		/* Gotcha: there are factors that contribute more fives (e.g. 25=5*5)   */
		if(n<0)
			return -1;
		int count=0;
		for(int i=5;n/i>0;i*=5)
			count+=n/i;
		return count;
	}

}
