
public class DirectedGraph extends Graph{

	public DirectedGraph() 
	{
		super();
	}

	@Override
	public boolean addEdge(GraphNode source, GraphNode destination, int weight) 
	{
		addNode(source);
		addNode(destination);
		
		boolean addEdgeSucces = addEdgeFromTo(source,destination,weight);
		
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
