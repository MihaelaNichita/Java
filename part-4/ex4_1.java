import java.util.ArrayList;
import java.util.Random;

public class BinarySearchTree 
{
	public BSTNode root=null;
	
	public void insertInOrder(int d)
	{
		if (root==null)
			root=new BSTNode(d);
		else root.insertInOrder(d);
	}
	
	public static void main ()
	{
		BinarySearchTree bst1=new BinarySearchTree();
		int[] arr= {8,3,15,1,5,20,2,16,25};
		
		for(int i=0;i<arr.length;i++)
			bst1.insertInOrder(arr[i]);
		
		bst1.printTree();	
		
		for(int i=0;i<10;i++)
		{
			BSTNode randomNode=bst1.getRandomNode();
			System.out.println("Random node: "+randomNode.getData());
		}
		System.out.println();
		
		bst1.deleteNode(8);
		bst1.printTree();
	}
	
	public BSTNode getRandomNode()
	{
		if(root==null)
			return null;
		Random random=new Random();
		
		/* The random integer number returned will be within the limits (inclusive): [0,root.size-1] */
		/* k = the kth node in the in order traversal of the tree */
		int k=random.nextInt(root.getSize());
		return root.getKthNode(k);
	}
	
	public void deleteNode(int d)
	{
		if(root==null)
			return;
		BSTNode parent=null;
		BSTNode node=root;
		if(root.getData()!=d)
		{
			parent=root.findParent(d);
			node=parent.left!=null && parent.left.getData()==d ? parent.left:parent.right;
		}
		
		int deleted=node.deleteChild(parent,d);
		if(deleted!=d)
			node.setData(deleted);
		
		System.out.println("Successfully deleted.");

	}
	
	public void printTree() 
	{		
		ArrayList<BSTNode> queue=new ArrayList<BSTNode>();
		
		queue.add(root);
		
		int i=0;
		int nextEnter=0;
		int k=2;
		int numNullNodes=0;

		
		while(true)
		{
			BSTNode node=queue.remove(0);
			if(i-1==nextEnter-(k/2))
				numNullNodes=0;
			if(node.flag=="*")
			{
				System.out.print("* ");
				numNullNodes++;
			}
			else
				System.out.print(node.getData()+" ");
			
			
			if(node.left!=null)
			{
				queue.add(node.left);
			}
			else
			{
				BSTNode noNode=new BSTNode("*");
				queue.add(noNode);
			}
			
			if(node.right!=null)
			{
				queue.add(node.right);
			}
			else
			{
				BSTNode noNode=new BSTNode("*");
				queue.add(noNode);
			}
			
			if(numNullNodes==k/2)
				break;
			
			if(i==nextEnter)
			{
				System.out.println();
				nextEnter+=k;
				k*=2;
			}			
			i++;
		}
		System.out.println();
	}
	

}
