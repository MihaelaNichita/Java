import java.util.Stack;

class NodeAndBounds{
    BinaryTreeNode node;
    int upperBound, lowerBound;

    NodeAndBounds(BinaryTreeNode n, int l, int u){
        node = n;
        upperBound = u;
        lowerBound = l; 
    }
}


public class ValidBST{

    /**Vulnerable to stack overflow */
    public static boolean isBST(BinaryTreeNode root){
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean isBST(BinaryTreeNode root, Integer minLim, Integer maxLim){
        if (root == null)
            return true;
        
        if (root.value < minLim || root.value >= maxLim)
            return false;

        return isBST(root.left, minLim, root.value) && isBST(root.right, root.value, maxLim);
    }

    /**DFS iterative */
    public static boolean isBSTIterative(BinaryTreeNode root){
        Stack<NodeAndBounds> stack = new Stack<NodeAndBounds>();
        stack.push(new NodeAndBounds(root, Integer.MIN_VALUE, Integer.MAX_VALUE));

        while (!stack.isEmpty()){
            NodeAndBounds nodeBounds = stack.pop();
            BinaryTreeNode node = nodeBounds.node;
            int lowerBound = nodeBounds.lowerBound;
            int upperBound = nodeBounds.upperBound;
            
            if (node.value > upperBound || node.value <= lowerBound)
                return false;
            
            if (node.right != null)
                stack.push(new NodeAndBounds(node.right, node.value, upperBound));
            if (node.left != null)
                stack.push(new NodeAndBounds(node.left, lowerBound, node.value));
        }
        return true;
    }

    public static void test(){
        BinaryTreeNode n1 = new BinaryTreeNode(1);
        BinaryTreeNode n2 = new BinaryTreeNode(2);
        BinaryTreeNode n3 = new BinaryTreeNode(3);
        BinaryTreeNode n4 = new BinaryTreeNode(4);
        BinaryTreeNode n5 = new BinaryTreeNode(5);
        BinaryTreeNode n6 = new BinaryTreeNode(6);
        BinaryTreeNode n7 = new BinaryTreeNode(7);
        BinaryTreeNode n8 = new BinaryTreeNode(8);
        BinaryTreeNode n9 = new BinaryTreeNode(9);

        n5.left = n3;
        n5.right = n7;
        n3.left = n2;
        n3.right = n4;
        n7.left = n6;
        n7.right = n8;
        n8.left = n1;

        boolean b = isBSTIterative(n5);
        System.out.println("Is BST = " + b);
    }
}