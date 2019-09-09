import java.util.ArrayList;

public class TestPassingParameters 
{

	public static void main()
	{
		BinaryTreeNode node=new BinaryTreeNode(1);
		ArrayList<Integer> arr=new ArrayList<Integer>();
		callingMethod(node,arr);
		for(Integer el : arr)
			System.out.print(el+" ");
		
	}
	
	public static void callingMethod(BinaryTreeNode node, ArrayList<Integer> array)
	{
		int x=10;
		node.left=new BinaryTreeNode(2);
		//array.add(x);
		calledMethod(node,10,array);
		node.printBinaryTree();
		//System.out.println("value of x in callingMethod after calling calledMethod: "+x);
	}
	
	public static void calledMethod(BinaryTreeNode node, int x,ArrayList<Integer> array)
	{
		//node.right=new BinaryTreeNode(3);
		/*
		 * This doesn't affect the object in the calling method, because it's trying to change the object, 
		 * not one of its fields
		 * Changing the object passed as a parameter is not possible in java because the calledMethod holds just a copy
		 * of the callingMethod's pointer, not the pointer itself
		 */
		node=null; 
		//x++;
		//System.out.println("value of x in calledMethod: "+x);
		//array.add(x);
	}

	
}
