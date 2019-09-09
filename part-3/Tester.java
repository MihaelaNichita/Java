
public class Test {
	static void TestFixedMultiStack(FixedMultiStack stack) throws Exception
	{
		stack.push(0, 1);
		stack.push(1, 2);
		stack.push(2, 3);
		stack.push(0, 4);
		stack.push(1, 5);
		stack.push(2, 6);
		stack.push(0, 7);
		stack.push(1, 8);
		stack.push(2, 9);
			
		stack.printStack(0);
		stack.printStack(1);
		stack.printStack(2);
		
		System.out.println("Peek in stack #1 is: "+stack.peek(0));
		System.out.println("Peek in stack #2 is: "+stack.peek(1));
		System.out.println("Peek in stack #3 is: "+stack.peek(2));
		
		System.out.println("Popped element in stack #1: "+stack.pop(0));
		System.out.println("Popped element in stack #2: "+stack.pop(1));
		System.out.println("Popped element in stack #3: "+stack.pop(2));
		
		System.out.println("Peek in stack #1 is: "+stack.peek(0));
		System.out.println("Peek in stack #2 is: "+stack.peek(1));
		System.out.println("Peek in stack #3 is: "+stack.peek(2));
		System.out.println();
	}
	
	static void TestMultiStack(MultiStack stack) throws Exception
	{
		MultiStack stack2=new MultiStack(3, 4);
		stack2.push(0, 1);
		stack2.push(1, 2);
		stack2.push(2, 3);
		stack2.push(2, 4);
		stack2.push(1, 5);
		stack2.push(2, 6);
		stack2.push(2, 7);
		stack2.push(1, 8);
		stack2.push(2, 9);
				
		stack2.printStack(0);
		stack2.printStack(1);
		stack2.printStack(2);
		
		System.out.println("Popped element in stack #1: "+stack2.pop(0));
		System.out.println("Popped element in stack #3: "+stack2.pop(2));
		stack2.push(0, 10);
		stack2.push(0, 11);
		stack2.push(0, 12);
		stack2.push(0, 13);
		stack2.push(0, 14);
		
		stack2.printStack(0);
		stack2.printStack(1);
		stack2.printStack(2);
	
		stack2.printArray();
		System.out.println();
	}
	
	private static void TestStackWithMin(StackWithMin stack) 
	{
		stack.push(8);
		stack.push(2);
		stack.push(-1);
		stack.push(-8);
		stack.push(3);
		stack.push(-17);
		stack.push(1);
		
		System.out.println("Min: "+stack.min());
		
		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();
		
		System.out.println("Min: "+stack.min());

	}
	

	private static void TestSetOfStacks(SetOfStacks setOfStacks) throws Exception 
	{
		setOfStacks.push(1);
		setOfStacks.push(2);
		setOfStacks.push(3);
		setOfStacks.push(4);
		setOfStacks.push(5);
		setOfStacks.push(6);
		
		setOfStacks.printStacksPeeks();
		
		System.out.println("Popped element: "+setOfStacks.pop());
		System.out.println("Popped element: "+setOfStacks.pop());
		System.out.println("Popped element: "+setOfStacks.pop());
		System.out.println("Popped element: "+setOfStacks.pop());
		
		setOfStacks.printStacksPeeks();

		
		
	}

	
	public static void main(String args[]) throws Exception
	{
		FixedMultiStack stack=new FixedMultiStack(5);
		TestFixedMultiStack(stack);
		
		MultiStack stack2=new MultiStack(3, 4);
		TestMultiStack(stack2);
		
		StackWithMin stack3=new StackWithMin();
		TestStackWithMin(stack3);
		
		SetOfStacks setOfStacks=new SetOfStacks();
		TestSetOfStacks(setOfStacks);
	}
}
