import java.awt.List;
import java.util.*;

public class Test 
{

	public static void main(String ars[])
	{
		LinkedListNode dupsList=createDupsList();
		System.out.println("Initial list:");
		dupsList.printList();
		System.out.println();
		
		deleteDups(dupsList);
		System.out.println("List after deleting duplicates:");
		dupsList.printList();
		System.out.println();
		
		int k=8;
		System.out.print(k+"th to last element in the list: ");
		System.out.println(returnKthToLast(dupsList,8));
		System.out.println();
		
		System.out.println("List after deleting the 3rd node (a middle node):");
		deleteMiddleNode(dupsList.next.next);
		dupsList.printList();
		System.out.println();

		
		System.out.println("Trying to delete the last node:");
		deleteMiddleNode(dupsList.next.next.next.next.next.next.next);
		System.out.println();
		
		int x=4;
		LinkedListNode list2=createListToPartition();
		System.out.println("Initial list to partitionate:");
		list2.printList();
		list2=partition(list2,x);
		System.out.println("List after partitionating it around "+x);
		list2.printList();
		System.out.println();
		
		LinkedListNode numberList1=createNumberList(5);
		LinkedListNode numberList2=createNumberList(7);
		System.out.println("Lists (in reverse order) to add:" );
		numberList1.printList();
		numberList2.printList();
		System.out.println("Sum list: ");
		addLists1(numberList1,numberList2).printList();
		System.out.println();
		
		LinkedListNode numberList3=createNumberList(5);
		LinkedListNode numberList4=createNumberList(9);
		System.out.println("Lists (in forward order) to add:" );
		numberList3.printList();
		numberList4.printList();
		System.out.println("Sum list: ");
		addLists2(numberList3,numberList4).printList();
		System.out.println();
		
		LinkedListNode palindromeList=createPalindromeList(5);
		System.out.print("List: ");
		palindromeList.printList();
		System.out.print("Is palindrome: ");
		System.out.println(isPalindrome1(palindromeList));
		System.out.println();

		System.out.print("Is palindrome: ");
		System.out.println(isPalindrome2(palindromeList));
		System.out.println();
		
		System.out.println("Find intersection node between the 2 lists:");
		palindromeList.printList();
		numberList4.printList();
		System.out.print("Intersection node: ");
		LinkedListNode intersection=findIntersection(palindromeList,numberList4);
		if(intersection!=null)
			System.out.println(intersection.data);
		else
			System.out.println("There is no intersection");
		System.out.println();

		
		LinkedListNode intersectionNode=getKthNode(list2, 3);
		System.out.print("List: ");
		list2.printList();
		System.out.println("3rd Node: "+ intersectionNode.data);


		LinkedListNode list1=createListThatIntersectsAnotherList(intersectionNode);
		list1.printList();
		System.out.println();
		
		System.out.println("Find intersection node between the 2 lists:");
		list1.printList();
		list2.printList();
		System.out.print("Intersection node: ");
		intersection=findIntersection(list1,list2);
		if(intersection!=null)
			System.out.println(intersection.data);
		else
			System.out.println("There is no intersection");
		System.out.println();
		
		LinkedListNode circularList=createCircularList(4,10);
		
		LinkedListNode loopHead=findLoopHead(circularList);
		System.out.print("Loop Head: ");
		if(loopHead==null)
			System.out.println("It's not a circular linked list");
		else System.out.println(loopHead.data);
	
	}



	private static LinkedListNode findLoopHead(LinkedListNode list) {
		if(list==null)
			return null;
		
		LinkedListNode slow=list;
		LinkedListNode fast=list;
		
		while(fast!=null && fast.next!=null)
		{
			slow=slow.next;
			fast=fast.next.next;
			if(slow==fast)
				break;
		}

		if(fast==null || fast.next==null)
			return null;
		
		slow=list;
		while(slow!=fast)
		{
			slow=slow.next;
			fast=fast.next;

		}
		return slow;
	}




	private static LinkedListNode createCircularList(int nonLoop, int length) {
		LinkedListNode circularList=new LinkedListNode(1);
		LinkedListNode current=circularList;
		
		for(int i=2;i<=nonLoop+1;i++)
		{
			LinkedListNode node=new LinkedListNode(i);
			current.next=node;
			current=node;
		}
		
		LinkedListNode loopHead=current;
		
		for(int i=nonLoop+2;i<=length;i++)
		{
			LinkedListNode node=new LinkedListNode(i);
			current.next=node;
			current=node;
		}
		
		current.next=loopHead;
		
		return circularList;
	}



	private static LinkedListNode createListThatIntersectsAnotherList(LinkedListNode intersectionNode) 
	{
		LinkedListNode list=createDupsList();
		ListInfo info=getSizeAndTail(list);
		info.tail.next=intersectionNode;
		return list;
	}



	private static LinkedListNode getKthNode(LinkedListNode list, int k) 
	{
		if(list==null)
			return null;
		while(list!=null && k>1)
		{
			list=list.next;
			k--;
		}
		
		return list;
	}


	static void deleteDups(LinkedListNode head)
	{
		if(head==null)
			return;
		HashSet<Integer> nodes=new HashSet<Integer>();
		LinkedListNode curr=head;
		LinkedListNode prev=null;
		while(curr!=null)
		{
			if(nodes.contains(curr.data))
			{
				/* Delete that node */ 
				prev.next=curr.next;
			}
			else
			{
				nodes.add(curr.data);
				prev=curr; //change prev pointer only if we didn't delete the current node
			}	
			curr=curr.next;
		}		
	}
	
	/*
	 * Two pointers trick
	 */
	public static int returnKthToLast(LinkedListNode list, int k)
	{
		if (list==null)
			return 0;
		LinkedListNode p1=list;
		LinkedListNode p2=list;
		
		for(int i=0;i<k && p1!=null;i++)
		{
			p1=p1.next;
		}

		while(p1!=null)
		{
			p1=p1.next;
			p2=p2.next;
		}
		return p2.data;		
	}
	
	
	public static void deleteMiddleNode(LinkedListNode n)
	{
		if(n==null || n.next==null)
		{
			System.out.println("Node is not a middle node");
			return;
		}
		System.out.println("Deleting a middle node...");
		
		n.data=n.next.data;
		n.next=n.next.next;
	}
	
	
	/*
	 * Write code to partition a linked list around a value x, such that all nodes less than x come
	 * before all nodes greater than or equal to x. If x is contained within the list, the values of x only need to be 
	 * after the elements less than x. The partition element (x) can appear anywhere in the right partition,
	 * it doesn't need to appear between the left and right partition. 
	 */
	
	public static LinkedListNode partition(LinkedListNode head, int x)
	{
		if(head==null)
		{
			System.out.println("Empty list");
			return null;
		}
		
		LinkedListNode current=head.next;
		LinkedListNode prev=head;
		while (current!=null)
		{
			if(current.data<x)
			{
				/* Insert the node at the start (left partition) */
				LinkedListNode n=new LinkedListNode(current.data);
				n.next=head;
				
				/* Update the head */
				head=n;
				
				/* Delete the node */
				prev.next=current.next;
			}
			else 
				prev=current;
			current=current.next;
		}		
		return head;
	}
	
	
	public static LinkedListNode createDupsList()
	{
		LinkedListNode list=new LinkedListNode(0);
		LinkedListNode curr=list;
		for(int i=1;i<=5;i++) 
		{
			LinkedListNode n=new LinkedListNode(i);
			curr.next=n;
			curr=curr.next;	
		}
		for(int i=4;i<=8;i++) 
		{
			LinkedListNode n=new LinkedListNode(i);
			curr.next=n;
			curr=curr.next;	
		}
		return list;
	}
	
	
	public static LinkedListNode createListToPartition()
	{
		LinkedListNode list=new LinkedListNode(7);
		LinkedListNode curr=list;
		for(int i=4;i<=7;i++) 
		{
			LinkedListNode n=new LinkedListNode(i);
			curr.next=n;
			curr=curr.next;	
		}
		for(int i=0;i<=3;i++) 
		{
			LinkedListNode n=new LinkedListNode(i);
			curr.next=n;
			curr=curr.next;	
		}
		return list;
	}
	
	
	/*
	 * I have two numbers represented by a linked list, where each node contains a single digit. 
	 * The digits are stored in reverse order, such that the 1's digit is at the head of the list (most significant)
	 * Write a function that adds the two numbers and returns the sum as a linked list.
	 */
	// ITERATIVE solution
	public static LinkedListNode addLists1(LinkedListNode l1, LinkedListNode l2)
	{
		if(l1==null || l2==null)
			return null;
		
		LinkedListNode sumHead=new LinkedListNode((l1.data+l2.data)%10);
		LinkedListNode sumCurr=sumHead;
		int carry=(l1.data+l2.data)/10;
		l1=l1.next;
		l2=l2.next;
		
		while(l1!=null || l2!=null || carry!=0)
		{
			int value=carry;
			if(l1!=null)
			{
				value+=l1.data;
				l1=l1.next;	
			}
			if(l2!=null)
			{
				value+=l2.data;
				l2=l2.next;				
			}

			LinkedListNode node= new LinkedListNode(value%10);
			sumCurr.next=node;			
			sumCurr=sumCurr.next;
			carry=value/10;			
		}	
		return sumHead;
	}
	
	
	/*
	 * Sum Lists in the case the digits are stored in forward order
	 */
	
	static class PartialSum
	{
		public LinkedListNode sum=null;
		public int carry=0;
		
	}
	
	public static LinkedListNode addLists2(LinkedListNode l1,LinkedListNode l2)
	{
		if (l1==null || l2==null)
			return null;
		
		int len1=length(l1);
		int len2=length(l2);
		
		if(len1<len2)
			l1=padList(l1,len2-len1);
		else
			if (len1>len2)
				l2=padList(l2,len1-len2);
		
		System.out.println("\tLists after padding the shorter one with zeros: ");
		System.out.print("\t");
		l1.printList();
		System.out.print("\t");
		l2.printList();

		PartialSum sum=addListsHelper(l1,l2);

		if (sum.carry==0)
			return sum.sum;
		else
			return insertBefore(sum.sum,sum.carry);
	}
	
	
	
	public static int length(LinkedListNode list)
	{
		LinkedListNode node=list;
		int len=0;
		while(node!=null)
		{
			node=node.next;
			len++;
		}
		return len;
	}
	
	
	public static LinkedListNode padList(LinkedListNode list, int padding)
	{
		LinkedListNode head=list;
		
		for(int i=0;i<padding;i++)
		{
			head=insertBefore(head,0);
		}		
		return head;
	}
	
	// RECURSIVE
	public static PartialSum addListsHelper(LinkedListNode l1,LinkedListNode l2)
	{
		if(l1==null && l2==null)
		{
			PartialSum sum=new PartialSum();
			return sum;
		}
		
		PartialSum sum=addListsHelper(l1.next,l2.next);
		int value=sum.carry+l1.data+l2.data;
		
		LinkedListNode result=insertBefore(sum.sum,value%10);
		sum.sum=result;
		sum.carry=value/10;				
		return sum;
	}
	
	public static LinkedListNode insertBefore(LinkedListNode list, int value)
	{
		LinkedListNode node=new LinkedListNode(value);
		if(list!=null)
		{
			node.next=list;
		}
		return node;
	}
	
	public static LinkedListNode createNumberList(int x)
	{
		LinkedListNode list=new LinkedListNode(x);
		LinkedListNode curr=list;
		for(int i=3;i<=x;i=i+2) 
		{
			LinkedListNode n=new LinkedListNode(i);
			curr.next=n;
			curr=curr.next;	
		}

		return list;
	}
	
	/*
	 * Implement a function to check if a linked list is a palindrome
	 * Iterative Approach + Fast Runner/Slow Runner Technique
	 */
	
	public static boolean isPalindrome1(LinkedListNode list)
	{
		LinkedListNode slow=list;
		LinkedListNode fast=list;
		
		Stack<Integer> stack=new Stack<Integer>();
				
		while(fast!=null && fast.next!=null)
		{
			stack.push(slow.data);
			fast=fast.next.next;
			slow=slow.next;
		}
		
		if(fast!=null)
			slow=slow.next;
		
		while(slow!=null)
		{
			int top=stack.pop();
			if (slow.data!=top)
				return false;
			slow=slow.next;
		}
		
		return true;
	}
	
	
	/*
	 * Recursive Approach
	 */
	
	public static class Result{
		public Result(LinkedListNode head, boolean b) {
			node=head;
			isPal=b;
		}
		public LinkedListNode node;
		public boolean isPal;
	}
	
	public static boolean isPalindrome2(LinkedListNode list)
	{
		int len=lengthOfList(list);
		Result r=isPalindromeRecurse(list,len);
		
		return r.isPal;
	}
	
	public static Result isPalindromeRecurse(LinkedListNode head, int len)
	{
		// when does head hit null?
		
		if (head==null || len<=0){ //even number of nodes
			return new Result(head, true);			
		}
		else if(len==1){ // odd number of nodes
			return new Result(head.next,true);
		}
		
		/* Recurse on sublist */
		Result res=isPalindromeRecurse(head.next, len-2);
	
		/* If child calls are not palindrome, pass back up a failure */
		if(!res.isPal || res.node==null){ // when res.node==null????
			return res;
		}
		
		/* Check if matches the corresponding node on the other side */
		res.isPal=(head.data==res.node.data);
		
		/* Return corresponding node */
		res.node=res.node.next;
			
		return res;
	}
	
	public static int lengthOfList(LinkedListNode head)
	{
		int len=0;
		while(head!=null)
		{
			len++;
			head=head.next;
		}		
		return len;
	}
	
	
	/*
	 * Given two singly linked lists determine if the two intersect.
	 * Return the intersecting node. Note than intersection is defined by reference, not by value.
	 * That is, if the kth node in the first linked list is the exact same node as the jth node of the second linked list,
	 * then they are intersecting. 
	 */
	
	static class ListInfo{
		int len=0;
		LinkedListNode tail=null;
		
		public ListInfo(int len, LinkedListNode tail)
		{
			this.len=len;
			this.tail=tail;
		}
	}
	public static LinkedListNode findIntersection(LinkedListNode l1, LinkedListNode l2)
	{
		if(l1==null || l2==null)
			return null;
		
		ListInfo info1=getSizeAndTail(l1);
		ListInfo info2=getSizeAndTail(l2);
		
		/* Check if they intersect */
		if (info1.tail!=info2.tail)
			return null;

		/* Test for equal and different lengths */
		LinkedListNode shorter=info1.len<info2.len?l1:l2;
		LinkedListNode longer=info1.len<info2.len?l2:l1;
		
		longer=getKthNode(longer,Math.abs(info1.len-info2.len)+1);

		while(shorter!=longer)
		{
			shorter=shorter.next;
			longer=longer.next;
		}
		return shorter;
		
	}
	
	


	private static ListInfo getSizeAndTail(LinkedListNode list) 
	{
		if (list==null)
			return null;
		
		int len = 1;
		
		while(list.next!=null)
		{
			len++;
			list=list.next;
		}
		
		return new ListInfo(len,list);
	}


	public static LinkedListNode createPalindromeList(int x)
	{
		LinkedListNode middle1=new LinkedListNode(11);
		LinkedListNode head=middle1;
		LinkedListNode middle2=new LinkedListNode(8);
		LinkedListNode end=middle2;
		middle1.next=middle2;
		
		for(int i=x;i>=0;i--)
		{
			LinkedListNode node1=new LinkedListNode(i);
			node1.next=head;
			head=node1;
			
			LinkedListNode node2=new LinkedListNode(i);
			end.next=node2;
			end=node2;
		}
		
		return head;
	}
}


class LinkedListNode
{
	public int data;
	public LinkedListNode next;
	
	public LinkedListNode(int data){
		this.data=data;
	}
	public LinkedListNode() {			
	}
	
	public void printList()
	{
		LinkedListNode curr=this;
		while(curr!=null)
		{
			System.out.print(curr.data);
			curr=curr.next;
			if(curr!=null)
				System.out.print("->");
			else System.out.println();
		}
	}	
}