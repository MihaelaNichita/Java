import java.util.ArrayList;
import java.util.LinkedList;

public class BST 
{
	public static void main()
	{
		int [] arr= {1,2,3,4,5,6,7,8,9,10};
		BinaryTreeNode root1=createMinimalHeightBST(arr);
		root1.printBinaryTree();
		System.out.println();
		
		testFindSuccessor(arr);
		testBSTtoAllPossibleArrays();

	}
	
	private static void testBSTtoAllPossibleArrays() 
	{
		int [] arr= {5,10,15,20,25,50};
		BinaryTreeNode root1=createMinimalHeightBST(arr);
		root1.printBinaryTree();
		
		ArrayList<LinkedList<Integer>> lists1=allLists(root1);
		
		printArrayOfLinkedLists(lists1);
		
	}

	private static void printArrayOfLinkedLists(ArrayList<LinkedList<Integer>> lists) 
	{
		if(lists==null)
			return;
		
		for(LinkedList<Integer> list : lists)
		{
			for(Integer el : list)
			{
				System.out.print(el+"->");
			}
			System.out.println();
		}		
	}

	private static ArrayList<LinkedList<Integer>> allLists(BinaryTreeNode root) 
	{
		ArrayList<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();
		
		if (root == null)
		{
			LinkedList<Integer> list=new LinkedList<Integer>();
			result.add(list);
			return result;
		}
		//System.out.println("Inside allLists, before recursion, root="+root.value);
		
		LinkedList<Integer> prefix=new LinkedList<Integer>();
		prefix.add(root.value);
		
		ArrayList<LinkedList<Integer>> leftResults=allLists(root.left);
		ArrayList<LinkedList<Integer>> rightResults=allLists(root.right);

		for(LinkedList<Integer> left : leftResults)
			for(LinkedList<Integer> right : rightResults)
			{
				ArrayList<LinkedList<Integer>> woven=new ArrayList<LinkedList<Integer>>();
				weaveLists(left,right,woven,prefix);
				result.addAll(woven);
			}		
		//System.out.println("Lists so far:");
		//printArrayOfLinkedLists(result);
		return result;
	}
	
	
/*
 * weaveLists will weave pairs of lists together in all possible ways
 * the strategy is to remove the head from one list, recurse, and then do the same thing with the other list
 */
	@SuppressWarnings("unchecked")
	private static void weaveLists(LinkedList<Integer> left, LinkedList<Integer> right,
			ArrayList<LinkedList<Integer>> weaved, LinkedList<Integer> prefix) 
	{
/*		System.out.print("Inside weaveLists prefix = ");
		for(Integer el:prefix)
		{
			System.out.print(el+"->");
		}
		System.out.println();*/
		
		if(left.size()==0 || right.size()==0)
		{
			LinkedList<Integer> list = (LinkedList<Integer>) prefix.clone();
			list.addAll(left);
			list.addAll(right);
			
			weaved.add(list);
/*			System.out.println("Lists so far:");
			printArrayOfLinkedLists(weaved);*/
			return;
		}
		
/*		System.out.print("Inside weaveLists left = ");
		for(Integer el:left)
		{
			System.out.print(el+"->");
		}
		System.out.println();*/
		
		int headLeft=left.removeFirst();
		prefix.add(headLeft);
		weaveLists(left,right,weaved,prefix);
		prefix.removeLast();
		left.addFirst(headLeft);
		
/*		System.out.print("Inside weaveLists right = ");
		for(Integer el:right)
		{
			System.out.print(el+"->");
		}
		System.out.println();
		System.out.println();*/
		
		int headRight=right.removeFirst();
		prefix.add(headRight);
		weaveLists(left,right,weaved,prefix);
		prefix.removeLast();
		right.addFirst(headRight);	
	}

	private static void testFindSuccessor(int[] arr) 
	{	
		BinaryTreeNode root5=createMinimalHeightBSTwithParents(arr);
		root5.printBinaryTree();
		System.out.println();
		
		BinaryTreeNode current=root5.left.left;
		for(int i=0;i<arr.length;i++)
		{
			BinaryTreeNode currentsNext=findSuccessor(current);
			if(currentsNext==null)
				return;
			System.out.println("IN-ORDER Successor of "+current.value+" is: "+currentsNext.value);
			current=currentsNext;
		}
		System.out.println();
	}

	/* create a BST with minimal height, being given a sorted (increasing order) array with unique integer elements */
	public static BinaryTreeNode createMinimalHeightBST(int[] arr)
	{
		return createMinimalHeightBST(arr,0,arr.length-1);
	}

	private static BinaryTreeNode createMinimalHeightBST(int[] arr, int start, int end) 
	{
		if(end<start)			
			return null;
		
		int mid=(start+end)/2;
		BinaryTreeNode n = new BinaryTreeNode(arr[mid]);
		n.left=createMinimalHeightBST(arr,start,mid-1);
		n.right=createMinimalHeightBST(arr, mid+1, end);
		return n;		
	}
	public static BinaryTreeNode createMinimalHeightBSTwithParents(int[] arr)
	{
		return createMinimalHeightBSTwithParents(arr,0,arr.length-1,null);
	}
	private static BinaryTreeNode createMinimalHeightBSTwithParents(int[] arr, int start, int end, BinaryTreeNode par) 
	{
		if(end<start)			
			return null;
		
		int mid=(start+end)/2;
		BinaryTreeNode n = new BinaryTreeNode(arr[mid]);
		n.left=createMinimalHeightBSTwithParents(arr,start,mid-1,n);
		n.parent=par;
		n.right=createMinimalHeightBSTwithParents(arr, mid+1, end,n);
		return n;		
	}
	
	
	private static BinaryTreeNode findSuccessor(BinaryTreeNode node)
	{
		if(node==null)
			return null;
		
		if(node.right!=null)
			return leftMost(node.right);
		else 
			return firstLeftChild(node);
			
	}

	private static BinaryTreeNode firstLeftChild(BinaryTreeNode node) 
	{
		while(node.parent!=null && node==node.parent.right)
		{
			node=node.parent;
		}
		
		return node.parent;
	}

	private static BinaryTreeNode leftMost(BinaryTreeNode node) 
	{
		while(node.left!=null)
			node=node.left;
		
		return node;
	}
	
	
	


}
