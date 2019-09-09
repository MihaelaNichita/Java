
public class BSTNode 
{

	private int data=0;
	public BSTNode left=null;
	public BSTNode right=null;
	private int size=0;
	
	String flag=null;
	public BSTNode(String f) {flag=f;}
	
	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public BSTNode(int d) 
	{
		data=d;
		size=1;		
	}
	
	void insertInOrder(int d)
	{
		/* no test for null because you can only call this method for an object of this class created before, 
		 * it can't be null */
		
		if(d<data)
			if(left==null) // check for null for left and right
				left=new BSTNode(d);
			else left.insertInOrder(d);
		else 
			if(data<d)
				if(right==null)
					right=new BSTNode(d);
				else right.insertInOrder(d);
		size++; // increment size starting with leaves	
	}
	
	// pre-order
	public BSTNode find(int d)
	{
		if(data==d)
			return this;
		
		if(d<data)
			return left==null?null:left.find(d);
		else 
			return right==null?null:right.find(d);		
	}

	/* Get the kth node in the tree considering its in order traversal */
	public BSTNode getKthNode(int k) 
	{
		int leftSize=left!=null?left.getSize():0;
		
		if(leftSize>k)
			return left.getKthNode(k);
		else if(leftSize<k)
		{
			/* Skipping over leftSize+1 nodes, so subtract them. */
			return right.getKthNode(k-leftSize-1);
		}
		else return this;
	}

	public BSTNode findParent(int d) 
	{
		if(d<data && left!=null)
			if(left.data==d)
				return this;
			else 
				return left.findParent(d);
		else if(right!=null)
				if(right.data==d)
					return this;
				else
					return right.findParent(d);
		else
			return null;
	}

	/* Called for the node to be deleted */
	public int deleteChild(BSTNode parent,int d) 
	{
			if(left==null || right==null) 
			{
				if(this==parent.left)
				{
					parent.left=left!=null?left:right;
					return this.data;
				}
				else //this==parent.right
				{
					parent.right=left!=null?left:right;
					return this.data;
				}
			}
			else if(left==null && right==null)
			{
				if(this==parent.left)
				{
					parent.left=null;
					return this.data;
				}
				else //this==parent.right
				{
					parent.right=null;
					return this.data;
				}
			}
			else
			{
				/* Delete the minimum from left subtree (or maximum from right subtree) and return its value */
				return left.deleteChild(this,d);
			}
	}
	

}
