import java.lang.Exception;
import java.util.*;

public class FixedMultiStack {
	private int stackCapacity;
	private int numberOfStacks=3;
	private int[] values;
	private int[] sizes;
	
	public FixedMultiStack(int stackCap) {
		stackCapacity=stackCap;
		values=new int[stackCapacity*numberOfStacks];
		sizes=new int[numberOfStacks];
	}
	
	public void push(int stackNum, int value) throws Exception 
	{
		if (isFull(stackNum))
			throw new Exception("Stack is full.");
		
		sizes[stackNum]++;
		values[getPeekIndex(stackNum)]=value;
	}


	public int pop(int stackNum) 
	{
		if(isEmpty(stackNum))
			throw new EmptyStackException();
		
		int element=peek(stackNum);
		values[getPeekIndex(stackNum)]=0;
		sizes[stackNum]--;
		
		return element;
	}
	
	public int peek(int stackNum) 
	{
		if(isEmpty(stackNum))
			throw new EmptyStackException();
				
		return values[getPeekIndex(stackNum)];
	}
	
	public boolean isEmpty(int stackNum) 
	{
		if(stackNum>numberOfStacks)
			throw new IndexOutOfBoundsException("There are only "+numberOfStacks+" stacks.");
		return (sizes[stackNum]==0);

	}
	
	public boolean isFull(int stackNum) 
	{
		return (sizes[stackNum]==stackCapacity);

	}
	
	
	private int getPeekIndex(int stackNum) 
	{
		if(isEmpty(stackNum))
			throw new EmptyStackException();
		
		return stackNum*stackCapacity+sizes[stackNum]-1;
	}
	
	public void printStack(int stackNum)
	{
		if(isEmpty(stackNum))
			throw new EmptyStackException();
		
		System.out.print("Stack #"+ (stackNum+1) +": ");
		
		int offset=stackNum*stackCapacity;
		
		for(int i=offset;i<offset+sizes[stackNum];i++)
		{
			System.out.print(values[i]+" ");
		}
		System.out.println();
	}

}
