
public class UndirectedGraph extends Graph{

	public UndirectedGraph() 
	{
		super();
	}

	@Override
	public boolean addEdge(GraphNode n1, GraphNode n2, int weight) 
	{
		addNode(n1);
		addNode(n2);
		
		boolean addEdgeSucces = addEdgeFromTo(n1,n2,weight) && addEdgeFromTo(n2,n1,weight);
		
		if(addEdgeSucces)
			numEdges++;
		return addEdgeSucces;
	}

	@Override
	public boolean removeEdge(GraphNode n1, GraphNode n2, int weight) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
