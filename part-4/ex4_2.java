import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class BinaryTree 
{
	public static void main()
	{
		int [] arr= {1,2,3,4,5,6,7,8,9,10};
		BinaryTreeNode root1=createMinimalHeightBT(arr);
		root1.printBinaryTree();
		System.out.println();
		
		testDepths(root1);
		testBalanced(root1);
				
		BinaryTreeNode aBST=BST.createMinimalHeightBST(arr);
		BinaryTreeNode notaBST=root1;
		
		testBST(notaBST,aBST);
		
		int [] arr1= {5,4,6,3,7,2,8,1,9,10};
		BinaryTreeNode root3=createMinimalHeightBT(arr1);
		BinaryTreeNode root2=createMinimalHeightBT(arr1);
		
		System.out.println("Tree to which we'll add links to parents for each node:");
		root2.printBinaryTree();
		
		root2=addLinksToParents(root2);
		testFirstCommonAncestor(root3,root2);
		
		testCheckSubtree(root1);
		
		testPathsWithSum();
	}

	private static void testPathsWithSum() 
	{
		int [] arr= {10,5,-3,3,1,3,11,3,-2,2};
		BinaryTreeNode tree=createMinimalHeightBT(arr);
		int sum=8;
		System.out.println("Number of paths with sum="+sum+" in the following tree");
		tree.printBinaryTree();
		System.out.println("is "+countPathsWithSum(tree,sum));
	}

	private static int countPathsWithSum(BinaryTreeNode tree, int targetSum) 
	{
		return countPathsWithSum(tree,targetSum,0,new HashMap<Integer,Integer>());
	}

	private static int countPathsWithSum(BinaryTreeNode node, int targetSum, int runningSum,
			HashMap<Integer, Integer> pathsCount) 
	{
		if (node==null)
			return 0;
		
		/* Count paths with sum ending at current node. */
		runningSum+=node.value;
		int sum=runningSum-targetSum;
		int totalPaths=pathsCount.getOrDefault(sum, 0);
		
		/* If one runningSum == targetSum, then one additional path starts at root. */
		if(runningSum == targetSum)
			totalPaths++;
		
		/* Increment pathCount, recurse, then decrement pathCount for key==runningSum. */
		incrementHashTable(pathsCount,runningSum,1); 
		totalPaths+=countPathsWithSum(node.left,targetSum,runningSum,pathsCount);
		totalPaths+=countPathsWithSum(node.right,targetSum,runningSum,pathsCount);
		incrementHashTable(pathsCount,runningSum,-1);		
		
		return totalPaths;
	}

	private static void incrementHashTable(HashMap<Integer, Integer> hashTable, int key, int increment) 
	{
		int newCount=hashTable.getOrDefault(key, 0) + increment;
		/* Remove when zero */
		if(newCount==0)
			hashTable.remove(key);
		else hashTable.put(key, newCount);
		
	}

	private static void testCheckSubtree(BinaryTreeNode t1) 
	{
		System.out.println();
		
		int[] arr1= {1,2,3,4,5};
		int[] arr2= {4,8,9};
		
		BinaryTreeNode t2=createMinimalHeightBT(arr1);		
		BinaryTreeNode t3=createMinimalHeightBT(arr2);
		
		testCheckSubtree(t1,t2,t3,true);
		testCheckSubtree(t1,t2,t3,false);
		
		
	}
	

	private static void testCheckSubtree(BinaryTreeNode t1, BinaryTreeNode t2,BinaryTreeNode t3, boolean firstApproach) 
	{
		System.out.println("Using the "+ (firstApproach?"FIRST":"SECOND")+" approach,");
		
		System.out.println("We'll check whether tree 2 is subtree of tree 1.\n Tree 1 :");
		t1.printBinaryTree();
		System.out.println("Tree 2 :");
		t2.printBinaryTree();
		
		if(firstApproach?checkSubtree1(t1,t2):checkSubtree2(t1,t2))
			System.out.println("Tree 2 is subtree of Tree 1.");
		else System.out.println("Tree 2 is NOT a subtree of Tree 1.");
		System.out.println();

		System.out.println("We'll check whether tree 3 is subtree of tree 1.\n Tree 1 :");
		t1.printBinaryTree();
		System.out.println("Tree 3 :");
		t3.printBinaryTree();
		
		if(firstApproach?checkSubtree1(t1,t3):checkSubtree2(t1,t3))
			System.out.println("Tree 3 is subtree of Tree 1.");
		else System.out.println("Tree 3 is NOT a subtree of Tree 1.");
		System.out.println();
	}

	/*
	 * First approach is do a PRE-ORDER traversal for each tree and get the string equivalents.
	 * Assuming the trees contain only integers, the string will store a special character for each null node in the tree.
	 * Special character chosen = '*'
	 */
	private static boolean checkSubtree1(BinaryTreeNode t1, BinaryTreeNode t2) 
	{
		if(t2==null)
			return true;
		if(t1==null)
			return false;
		
		StringBuilder string1=new StringBuilder();
		StringBuilder string2=new StringBuilder();
		
		getOrderString(t1,string1);
		getOrderString(t2,string2);		
		
		return string1.indexOf(string2.toString())!=-1;
	}

	private static void getOrderString(BinaryTreeNode root, StringBuilder sb) 
	{
		if(root==null)
		{
			sb.append('*');
			return;
		}
		
		sb.append(root.value);
		getOrderString(root.left,sb);
		getOrderString(root.right,sb);
	}

	/*
	 * Alternative approach to check if t2 is a subtree of t1 is to:
	 * - search through the larger tree
	 *   - each time a node in t1 matched the root of t2, call matchTree
	 * - matchTree method will compare the two subtrees to see if they are identical
	 */
	private static boolean checkSubtree2(BinaryTreeNode t1, BinaryTreeNode t2) 
	{
		if(t2==null)
			return true;  //empty tree is always subtree
		else
			return isSubTree(t1,t2);
	}
	
	private static boolean isSubTree(BinaryTreeNode t1, BinaryTreeNode t2) 
	{
		if(t1==null)
			return false;
		if(t1.value==t2.value && matchTree(t1,t2))
			return true;
		return checkSubtree2(t1.left,t2) || checkSubtree2(t1.right,t2);
	}

	private static boolean matchTree(BinaryTreeNode t1, BinaryTreeNode t2) 
	{
		if(t1==null && t2==null)
			return true;
		else if(t1==null || t2==null)
				return false;
			else if(t1.value!=t2.value)
					return false;			
		return matchTree(t1.left,t2.left) && matchTree(t1.right,t2.right);
	}

	/*
	 * understand passing parameters (objects)
	 * in calling method print first the object returned and then the initial object
	 * DID it & the result is => it changes the actual object (because it works with references)
	 * you can actually make this function's return type VOID
	 */
	private static BinaryTreeNode addLinksToParents(BinaryTreeNode root1) 
	{
		System.out.println("addLinksToParents");
		if(root1.left==null && root1.right==null)
			return root1;
		
		if(root1.left!=null)
		{
			BinaryTreeNode left=addLinksToParents(root1.left);
			System.out.println("Node: "+left.value + " Parent: "+root1.value);
			left.parent=root1;
		}

		if(root1.right!=null)
		{
			BinaryTreeNode right=addLinksToParents(root1.right);
			System.out.println("Node: "+right.value + " Parent: "+root1.value);
			right.parent=root1;
		}
	
		return root1;

	}


	private static void testFirstCommonAncestor(BinaryTreeNode root1, BinaryTreeNode root2) 
	{
		System.out.println("We will find first common ancestors for several pairs of two nodes in the following Binary Tree:");
		root1.printBinaryTree();
		root2.printBinaryTree();

		System.out.println("Assuming each node in the tree has a link to its parent:");
				
		BinaryTreeNode node2=root2.left;
		BinaryTreeNode node3=root2.right;
		
		testCommonAncestorHelper1(root2,node2,node3);
		testCommonAncestorHelper1(root2,node2.left,node3.right);
		testCommonAncestorHelper1(root2,node2.left.left,node2.right);
		testCommonAncestorHelper1(root2,node2.left.right,node2.right);
		
		System.out.println("Assuming Nodes don't have links to their parents:");
		
		node2=root1.left;
		node3=root1.right;
		
		testCommonAncestor2(root1,node2,node3);
		testCommonAncestor2(root1,node2.left,node3.right);
		testCommonAncestor2(root1,node2.left.left,node2.right);
		testCommonAncestor2(root1,node2.left.right,node2.right);
		
		
	}

	private static void testCommonAncestor2(BinaryTreeNode root, BinaryTreeNode node1, BinaryTreeNode node2) 
	{
		
		BinaryTreeNode anc=firstCommonAncestor2(root,node1,node2);
		if(anc!=null)
			System.out.println("Common ancestor of node "+node1.value+" and node "+node2.value+" is: "+anc.value);
		else
			System.out.println("No common ancestor found");
		
	}


	/* No links to parents */
	private static BinaryTreeNode firstCommonAncestor2(BinaryTreeNode root, BinaryTreeNode node1,BinaryTreeNode node2) 
	{
		if(root==null || node1==null || node2 == null)
			return null;
		if(node1==node2)
			return node1;
		
		Result r=firstCommonAncestor2Helper(root,node1,node2);
		
		if(r.isAncestor)
			return r.node;
		else
			return null;		
	}
	

	private static Result firstCommonAncestor2Helper(BinaryTreeNode root, BinaryTreeNode n1,BinaryTreeNode n2) 
	{
		if(root==null) return new Result(null,false);
		
		Result rx=firstCommonAncestor2Helper(root.left,n1,n2);
		if(rx.isAncestor)
			return rx;
		
		Result ry=firstCommonAncestor2Helper(root.right,n1,n2);
		if(ry.isAncestor)
			return ry;
		
		if(rx.node!=null && ry.node!=null)
			return new Result(root,true); // root is the ancestor
		else
			if (root==n1 || root==n2)
			{
				boolean isAnc = rx.node!=null || ry.node!=null;
				return new Result(root,isAnc);
			}
			else
				return rx.node!=null?rx:ry;
	}

	private static void testCommonAncestorHelper1(BinaryTreeNode root2,BinaryTreeNode node2, BinaryTreeNode node3) 
	{
		BinaryTreeNode node1=firstCommonAncestor1(root2,node2,node3);
		if(node1!=null)
			System.out.println("Common ancestor of node "+node2.value+" and node "+node3.value+" is: "+node1.value);
		else
			System.out.println("No common ancestor found");
		
	}

	/* With links to parents */
	private static BinaryTreeNode firstCommonAncestor1(BinaryTreeNode root, BinaryTreeNode node1, BinaryTreeNode node2) 
	{
		if(root==null || node1==null || node2==null)
			return null;
		
		/* Check whether both nodes are in the tree */
		if(!covers(root,node1) || !covers(root,node2))
			return null;
		
		/* Check whether one node covers the other */
		if(covers(node1,node2))
			return node1;
		if(covers(node2,node1))
			return node2;
		
		/* Start with one node and as you go up to the root, check whether its sibling covers the other */
		while(node1.parent!=null)
		{
			BinaryTreeNode sibling=getSibling(node1);
			if(sibling!=null && covers(sibling,node2))
				return node1.parent;
			node1=node1.parent;			
		}		
		return null;
	}

	private static BinaryTreeNode getSibling(BinaryTreeNode node) 
	{
		if(node==null || node.parent==null)
			return null;
		
		if(node.parent.left==node)
			return node.parent.right;
		if(node.parent.right==node)
			return node.parent.left;
		
		return null;
	}

	private static boolean covers(BinaryTreeNode node1, BinaryTreeNode node2) 
	{

		if(node1==null) return false;
		if(node1==node2) return true;
		
		return covers(node1.left,node2) || covers(node1.right,node2);	
	}

	private static void testBalanced(BinaryTreeNode root) 
	{
		if(isBalanced(root))
			System.out.println("Balaced");
		else 	System.out.println("NOT Balaced");
		System.out.println();

		BinaryTreeNode leftMostNode=getLeftMostNode(root);
		leftMostNode.left=new BinaryTreeNode(11);
		leftMostNode.left.left=new BinaryTreeNode(12);
		root.printBinaryTree();
		
		if(isBalanced(root))
			System.out.println("Balaced");
		else 	System.out.println("NOT Balaced");
		System.out.println();
		
		leftMostNode.left=null;
		
	}

	private static void testDepths(BinaryTreeNode root) 
	{
		ArrayList<LinkedList<BinaryTreeNode>> levels1 = createLevelLinkedListDFS(root);
		printArrayListOfLinkedListsOfBinaryTreeNodes(levels1);
		
		ArrayList<LinkedList<BinaryTreeNode>> levels2 = createLevelLinkedListBFS(root);
		printArrayListOfLinkedListsOfBinaryTreeNodes(levels2);		
	}

	private static void testBST(BinaryTreeNode bt1,BinaryTreeNode bt2)
	{
		printHelperBST(isBST1(bt1));
		printHelperBST(isBST1(bt2));
		printHelperBST(isBST2(bt1));
		prev=null;
		printHelperBST(isBST2(bt2));			
	}
	
	private static void printHelperBST(boolean b)
	{
		if(b)
			System.out.println("is BST");
		else 	System.out.println("is NOT a BST");
		System.out.println();
	}
	
	private static BinaryTreeNode getLeftMostNode(BinaryTreeNode root) 
	{
		if (root.left==null)
			return root;
		return getLeftMostNode(root.left);
	}

	private static void printArrayListOfLinkedListsOfBinaryTreeNodes(
			ArrayList<LinkedList<BinaryTreeNode>> levels) 
	{
		if(levels.isEmpty())
		{
			System.out.println("Array is empty.");
			return;
		}
		
		int i=0;
		for(LinkedList<BinaryTreeNode> level : levels)
		{
			System.out.print("Level "+i+": ");
			while(!level.isEmpty())
			{
				System.out.print(level.removeFirst().value+" ");
			}
			System.out.println();
			i++;
		}
		System.out.println();
	}

	private static BinaryTreeNode createMinimalHeightBT(int[] arr) 
	{
		if(arr.length==0)
			return null;

		BinaryTreeNode root= new BinaryTreeNode(arr[0]);
		if(arr.length==1)
			return root;
		
		LinkedList<BinaryTreeNode> queue=new LinkedList<BinaryTreeNode>();
		queue.add(root);
		
		//int nodeToProcess=0;
		int i;
		for(i=1;i<arr.length-2;i=i+2)
		{
			BinaryTreeNode current=queue.removeFirst();
			
			current.left=new BinaryTreeNode(arr[i]);
			current.right=new BinaryTreeNode(arr[i+1]);
			
			queue.addLast(current.left);
			queue.addLast(current.right);
			
		}
		
		if(i==arr.length-2)
		{
			BinaryTreeNode current=queue.removeFirst();
			current.left=new BinaryTreeNode(arr[i]);
			current.right=new BinaryTreeNode(arr[i+1]);		
		}
		else if(i==arr.length-1)
				queue.removeFirst().left=new BinaryTreeNode(arr[i]);
			
		return root;
	}

	public static void createLevelLinkedListDFS(BinaryTreeNode root, ArrayList<LinkedList<BinaryTreeNode>> lists, int level)
	{
		if(root==null)
			return;
		
		//first, the list is null
		LinkedList<BinaryTreeNode> list=null;
		
		//level not contained in list: (size 0 : level 0)(size 1 : level 1)and so on
		if(lists.size()==level)
		{
			// create new level
			list=new LinkedList<BinaryTreeNode>();
			/*
			 * Levels are traversed in order, so, if this is the first time we've visited this level,
			 * we must have seen levels 0 through level-1
			 * We can therefore safely add the level at the end.
			 */
			lists.add(list);
		}
		else 
		{
			list=lists.get(level);
		}
		list.add(root);
		
		createLevelLinkedListDFS(root.left,lists,level+1);
		createLevelLinkedListDFS(root.right,lists,level+1);
		
	}
	
	public static ArrayList<LinkedList<BinaryTreeNode>> createLevelLinkedListDFS(BinaryTreeNode root)
	{
		ArrayList<LinkedList<BinaryTreeNode>> lists = new ArrayList<LinkedList<BinaryTreeNode>>();
		createLevelLinkedListDFS(root,lists,0);
		return lists;
	}
	
	//BFS
	/*
	 * With this approach, we want to iterate through the root first, then level 1, then level 2, and so on.
	 */
	public static ArrayList<LinkedList<BinaryTreeNode>> createLevelLinkedListBFS(BinaryTreeNode root)
	{
		ArrayList<LinkedList<BinaryTreeNode>> result = new ArrayList<LinkedList<BinaryTreeNode>>();
		
		LinkedList<BinaryTreeNode> currentLevel=new LinkedList<BinaryTreeNode>();
		
		if(root!=null)
			currentLevel.add(root);
		
		while(!currentLevel.isEmpty())
		{
			/* Add previous level to result*/
			result.add(currentLevel);
			LinkedList<BinaryTreeNode> parents=currentLevel;
			currentLevel=new LinkedList<BinaryTreeNode>();
			
			/* Add children to current Level*/
			for(BinaryTreeNode parent : parents)
			{
				
				if(parent.left!=null)
					currentLevel.add(parent.left);
				if(parent.right!=null)
					currentLevel.add(parent.right);
			}			
		}		
		return result;
	}
	
	/*
	 * Check if Balanced
	 * A balanced tree is defined to be a tree such that the heights of the two subtrees of any node never differ by more than one
	 * POST-ORDER
	 */
	
	public static boolean isBalanced(BinaryTreeNode root)
	{
		return checkHeight(root)!=Integer.MIN_VALUE;
	}

	/* Check height and if balanced at the same time */
	/* If for any node we get the difference in heights for the left and right subtrees bigger than 1 then return MIN_VALUE (error code), 
	 * else return the actual height of that subtree */
	
	private static int checkHeight(BinaryTreeNode root) 
	{
		if(root==null)
			return -1;
		
		int leftHeight=checkHeight(root.left);
		if(leftHeight==Integer.MIN_VALUE)
			return Integer.MIN_VALUE; // pass error up
		
		int rightHeight=checkHeight(root.right);
		if(rightHeight==Integer.MIN_VALUE)
			return Integer.MIN_VALUE; // pass error up
		
		int heightDiff=Math.abs(leftHeight-rightHeight);
		if(heightDiff>1)
			return Integer.MIN_VALUE;
		else return Math.max(leftHeight, rightHeight)+1;
	}
	
	public static boolean isBST1(BinaryTreeNode root)
	{
		return isBST(root, null, null);
	}
	
	/*
	 * PRE-ORDER => pass the limits of the interval in which all the values in the subtree must fit
	 * when branching left => update the maximum value
	 * when branching right => update the minimum value
	 */
	private static boolean isBST(BinaryTreeNode root, Integer min, Integer max)
	{
		if(root==null)
			return true;
		
		if((min!=null && root.value<min) || (max!=null && root.value>max))
			return false;	
		
		return isBST(root.left,min,root.value) && isBST(root.right,root.value,max);
	}
	
	
	/* IN-ORDER traversal => sorted array
	 * without additional space use=> keep track of the previous element & compare it to the current one
	 * declare it as an Integer so it can be null at first & pass it as a parameter */
	
	static Integer prev=null;
	private static boolean isBST2(BinaryTreeNode root) 
	{
		if(root==null)
			return true;
		
		if(!isBST2(root.left))
			return false;
		
		if(prev!=null && prev>root.value)
		{
			return false;
		}
		prev=root.value;
		if(!isBST2(root.right))
			return false;
		
		return true; 
	}	
}

/*
 * The Result class is important in distinguishing between the 2 cases:
 * 1. n1 is a child of n2 (or n2 is a child of n1)
 * 2. n2 is in the tree and n1 is not (n1 is in the tree and n2 is not)
 * Returning an object of type Result solves the problem by returning 2 values:
 * the node itself and a flag indicating whether this node is actually the common ancestor
 */
class Result
{
	BinaryTreeNode node;
	boolean isAncestor;
	
	Result(BinaryTreeNode n, boolean isAnc)
	{
		node=n;
		isAncestor=isAnc;
	}
}


