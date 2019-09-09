
import java.util.ArrayList;
import java.util.Stack;
import java.lang.Exception;

public class ex3_1 
{
	ArrayList<Stack<Integer>> stacks=new ArrayList<Stack<Integer>>();
	int capacity;
	
	public SetOfStacks(int capacity)
	{
		this.capacity=capacity;
	}
	
	public void push(int value)
	{
		Stack<Integer> last=getLastStack();
		
		if(last!=null && !isFull(last))
			last.push(value);
		else
		{
			Stack<Integer> stack=new Stack<Integer>();
			stack.push(value);	
			stacks.add(stack);
		}		
	}
	
	public int pop() throws Exception
	{
		Stack<Integer> last=getLastStack();
		if(last==null)
			throw new Exception("No stack in the list. ");
		
		int item=last.pop();
		if(last.size()==0)
			stacks.remove(stacks.size()-1);
		return item;
	}

	private boolean isFull(Stack<Integer> stack) 
	{
		return stack.size()>=capacity;
	}
	
	private boolean isEmpty() 
	{
		Stack<Integer> last=getLastStack();
		return last==null || last.isEmpty();
	}
	

	@SuppressWarnings("unchecked")
	private Stack<Integer> getLastStack() 
	{
		if(stacks.size()==0)
			return null;
		return stacks.get(stacks.size()-1);
	}
	
	public void printStacksPeeks()
	{
		int i=1;
		for(Stack<Integer> stack : stacks)
		{
			System.out.print("Top of stack #"+i+": ");
			System.out.println(stack.peek());
			i++;
		}
	}
	
	/*
	 * If we pop an element from stack 1, we need to remove the bottom of stack 2 and push it onto stack 1
	 */


//	private int leftShift(int index, boolean removeTop)
//	{
//		Stack<Integer> stack=stacks.get(index);
//		
//		int removed_item;
//		
//		if(removeTop)
////			removed_item=(int) stack.pop();
////		else
////			removed_item=stack.removeBottom();
//		
//		if(stack.isEmpty())
//			stacks.remove(index);
//		else if(stack.size()>index+1)
//		{
//			int v=leftShift(index+1, false);
//			stack.push(v);
//		}
//		return removed_item;
//	}	
}

//class Stack
//{
//	private int capacity, size=0;
//	public Node top,bottom;
//	
//	public Stack(int capacity)
//	{
//		this.capacity=capacity;
//	}
//	
//	public boolean isFull () {return size==capacity;}
//	public boolean isEmpty () {return size==0;}
//	
//	public void join(Node below, Node above)
//	{
//		
//	}
//}
