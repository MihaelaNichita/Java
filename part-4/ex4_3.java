import java.util.ArrayList;

public class BinaryTreeNode 
{
	int value=0;
	String flag=null;
	BinaryTreeNode left=null;
	BinaryTreeNode right=null;
	BinaryTreeNode parent=null;
	
	public BinaryTreeNode(String f) {flag=f;}
	public BinaryTreeNode(int val)
	{
		value=val;
	}

	public void printBinaryTree() 
	{
		BinaryTreeNode root=this;
		
		ArrayList<BinaryTreeNode> queue=new ArrayList<BinaryTreeNode>();
		
		queue.add(root);
		
		int i=0;
		int nextEnter=0;
		int k=2;
		int numNullNodes=0;

		
		while(true)
		{
			BinaryTreeNode node=queue.remove(0);
			if(i-1==nextEnter-(k/2))
				numNullNodes=0;
			if(node.flag=="*")
			{
				System.out.print("* ");
				numNullNodes++;
			}
			else
			{
				
				//System.out.print(node.value+" ");
				// test parents:
				System.out.print("("+node.value+",");
				if(node.parent==null)
					System.out.print("null) ");
				else
					System.out.print(node.parent.value+") ");	
			}
			
			if(node.left!=null)
			{
				queue.add(node.left);
			}
			else
			{
				BinaryTreeNode noNode=new BinaryTreeNode("*");
				queue.add(noNode);
			}
			
			if(node.right!=null)
			{
				queue.add(node.right);
			}
			else
			{
				BinaryTreeNode noNode=new BinaryTreeNode("*");
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
