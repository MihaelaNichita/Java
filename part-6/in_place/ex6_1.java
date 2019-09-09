package in_place;
/**Number Swapper */
public class ex6_1 
{

	public static void main(String[] args) 
	{
		int a=4;
		int b=3;
		System.out.println("Before swapping: a="+a+", b="+b);
		
		swapNumbers1(a,b);
		swapNumbers1(a,b);
		
		

	}
	
	/* The function swaps a number in place. */
	public static void swapNumbers1(int a, int b)
	{
		a=a-b;
		b=a+b;
		a=b-a;
		System.out.println("After swapping 1: a="+a+", b="+b);
	}
	
	public static void swapNumbers2(int a, int b)
	{
		a=a^b;
		b=a^b;
		a=a^b;
		System.out.println("After swapping 2: a="+a+", b="+b);
	}

}
