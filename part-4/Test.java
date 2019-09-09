
public class Test {

	public static void main(String[] args) 
	{
		Graph g1=new UndirectedGraph();
		//testGraph(g1);
		
	    //System.out.println("Directed Graph:");
		Graph g2=new DirectedGraph();
		//testGraph(g2);

		TestFindBuildOrder.main();		
		BST.main();
		BinarySearchTree.main();
		BinaryTree.main();
		
		//TestPassingParameters.main();
		
		

	}

	public static void testGraph(Graph g1)
	{

		
		GraphNode n1=new GraphNode(1);
		GraphNode n2=new GraphNode(2);
		GraphNode n3=new GraphNode(3);
		GraphNode n4=new GraphNode(4);
		GraphNode n5=new GraphNode(5);
		GraphNode n6=new GraphNode(6);
		
		/*
		 * For the UndirectedGraph the following two edges should be considered identical
		 * Here it's important to have the equals method correctly overridden for the Edge type
		 * because it's called when checking if a set of edges contains a specific edge
		 */
		g1.addEdge(n1, n2, 0);
		g1.addEdge(n2, n1, 0);
		
		g1.addEdge(n3, n2, 0);
		g1.addEdge(n1, n4, 0);
		g1.addEdge(n5, n6, 0);
		g1.addEdge(n6, n1, 0);
		g1.addEdge(n2, n4, 0);
		
		System.out.println();
		g1.printGraph();
		System.out.println();
		System.out.println("Number of nodes: "+ g1.getNumNodes());
		System.out.println("Number of edges: "+ g1.getNumEdges());
		System.out.println();
		
		if(g1.findRouteBetween2Nodes(n2,n5))
			System.out.println("There is route.");
		else 		System.out.println("There is no route.");

		System.out.println();
	}

	
}
