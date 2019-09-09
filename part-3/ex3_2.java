import java.lang.Exception;
import java.util.*;

public class MultiStack 
{
	/* A class that holds some informations about each individual stack*/
	public class StackInfo
	{
		public int start, size, capacity;
		public StackInfo(int start, int capacity)
		{
			this.capacity=capacity;
			this.start=start;
		}
		
		public boolean isFull() {return size==capacity;}
		public boolean isEmpty(){return size==0;}
		
		public int peekIndex()
		{
			if(this.isEmpty())
				return start;
			return adjustIndex(start+size-1);
		}

		public int capacityIndex(int stackNum) 
		{
			return adjustIndex(start+capacity-1);
		}

		public boolean isWithinStackCapacity(int index) 
		{
			/*  Index outside of bounds of array */
			if(index<0 || index>=values.length)
				return false;
			
			/* If index wraps around, adjust it => project it as if it wouldn't wrap */
			int contiguousIndex= index<start ? index+values.length : index;
			int end=start+capacity-1;
			return start<= contiguousIndex && contiguousIndex<=end;		
		}
		
		
	}
	
	public StackInfo[] info;
	private int[] values;
	
	public MultiStack( int numberOfStacks, int defaultCapacity)
	{
		values=new int[numberOfStacks*defaultCapacity];
		info=new StackInfo[numberOfStacks];
		
		for(int i=0;i<numberOfStacks;i++)
		{
			info[i]=new StackInfo(i*defaultCapacity,defaultCapacity);
		}
	}
	
	public void push (int stackNum, int value) throws Exception
	{
		if(allStacksAreFull())
			throw new Exception("All stacks are full.");
		
		/*
		 * If this stack is full, expand it.
		 */
		
		StackInfo stack=info[stackNum];
		if(stack.isFull())
			expand(stackNum);
		
		/*
		 * increment size == stack pointer
		 * give the element at (index of the top element) the value
		 */
		
		stack.size++;
		values[stack.peekIndex()]=value;
	}
	
	public int pop(int stackNum)
	{
		StackInfo stack=info[stackNum];
		if(stack.isEmpty())
			throw new EmptyStackException();
		
		/* Remove last element */
		int element=peek(stackNum);
		values[stack.peekIndex()]=0;
		stack.size--; // shrink size
		
		return element;
	}
	
	public int peek(int stackNum)
	{
		StackInfo stack = info[stackNum];
			
		return values[stack.peekIndex()];
	}

	private void expand(int stackNum) 
	{
		System.out.println("Expanding stack #"+(stackNum+1));
		StackInfo stack=info[stackNum];
		/*
		 * what comes after the last stack is the first stack
		 */
		shift((stackNum + 1)%info.length);
		stack.capacity++;
		//System.out.println("Capacity of stack #"+(stackNum+1)+": "+stack.capacity);

	}

	private void shift(int stackNum) 
	{
		System.out.println("Shrinking stack #"+(stackNum+1));
		StackInfo stack=info[stackNum];
		
		/*
		 * If stack is at its full capacity, then move the next stack over by one element
		 * This stack can now claim the freed index
		 */
		if(stack.isFull())
		{
			shift((stackNum + 1)%info.length);
			stack.capacity++;
			//System.out.println("Capacity of stack #"+(stackNum+1)+": "+stack.capacity);

		}
		
		/* Shift all elements in stack by one */
		int index=stack.capacityIndex(stackNum);
		while(stack.isWithinStackCapacity(index))
		{
			//System.out.println("Before: Value at index #"+index+ " of stack #"+(stackNum+1)+": "+values[index]);
			values[index]=values[previousIndex(index)];
			//System.out.println("Value at previous index #"+previousIndex(index)+ " of stack #"+(stackNum+1)+": "+values[previousIndex(index)]);
			//System.out.println("After: Value at index #"+index+ " of stack #"+(stackNum+1)+": "+values[index]);
			index=previousIndex(index);
			
		}
		
		/* Update stack info (stack that has been shrank) */
		values[stack.start]=0;
		stack.start=nextIndex(stack.start);
		//System.out.println("Start index of stack #"+(stackNum+1)+": "+stack.start);
		//System.out.println("Value at start index of stack #"+(stackNum+1)+": "+values[stack.start]);


		stack.capacity--;	
		//System.out.println("Capacity of stack #"+(stackNum+1)+": "+stack.capacity);
	}

	private int nextIndex(int index) {
		return adjustIndex(index+1);
	}

	private int previousIndex(int index) 
	{
		return adjustIndex(index-1);
	}
	
	private int adjustIndex(int i) 
	{
		int max=values.length;
		
		/* For when index becomes negative*/
		return ((i%max)+max)%max;
	}


	private boolean allStacksAreFull() 
	{
		for(int i=0;i<info.length;i++)
			if ((info[i].capacity)!=(info[i].size))
				return false;		
		return true;
	}
	
	public void printStack(int stackNum)
	{
		StackInfo stack=info[stackNum];
		if(stack.isEmpty())
			throw new EmptyStackException();
		
		System.out.print("Stack #"+ (stackNum+1) +": ");
		
		int end=stack.peekIndex();

		int index=stack.start;
		while(stack.isWithinStackCapacity(index))
		{
			System.out.print(values[index]+" ");
			index=nextIndex(index);
		}
		System.out.println();
	}

	public void printArray() 
	{
		System.out.print("Values: ");

		for(int i=0;i<values.length;i++)
			System.out.print(values[i]+" ");
		System.out.println();		
	}
	
	
	
	
}
