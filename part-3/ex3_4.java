import java.util.*;
@SuppressWarnings("serial")
public class StackWithMin extends Stack <Integer>
{
	Stack<Integer> min;
	
	public StackWithMin() 
	{
		super();
		min=new Stack<Integer>();
	}
	
	public void push (int value)
	{
		if(value<=min())
			min.push(value);		
		super.push(value);	
	}
	
	public Integer pop ()
	{
		/* we need the min that we had before we pushed this value => keep track of min at each push*/
		int item=super.peek();
		if (item==min())
			min.pop();
		
		return super.pop();
	}
	
	public int min ()
	{
		if(min.isEmpty())	
			return Integer.MAX_VALUE;
		return min.peek();
	}
	
	
}
