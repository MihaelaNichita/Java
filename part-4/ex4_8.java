import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class Graph 
{
	//ArrayList<GraphNode> nodes=new ArrayList<GraphNode>();
	private Map <GraphNode, Set<Edge>> adjacencySets;
	private int numNodes;
	protected int numEdges;

	public Graph() 
	{		
		adjacencySets = new HashMap <GraphNode, Set<Edge>> ();
		numNodes = 0;
		numEdges = 0;
	}

	public Map<GraphNode, Set<Edge>> getAdjacencySet() {
		return adjacencySets;
	}

	public int getNumNodes() {
		return numNodes;
	}

	public int getNumEdges() {
		return numEdges;
	}
	
	public boolean containsNode(GraphNode node)
	{
		return adjacencySets.containsKey(node);
	}
	
	public boolean addNode(GraphNode newNode)
	{
		if (newNode==null || containsNode(newNode))
			return false;

		Set<Edge> newAdjacencySet=new HashSet<Edge>();
		adjacencySets.put(newNode, newAdjacencySet);
		
		numNodes++;
		return true;		
	}
	
	public ArrayList<GraphNode> getNodeNeighbors(GraphNode node)
	{
		if (!containsNode(node))
			return null;
		
		Set<Edge> nodeEdges = adjacencySets.get(node);
		ArrayList<GraphNode> nodeNeighbors= new ArrayList<GraphNode>();
		
		for(Edge e : nodeEdges)
		{
			GraphNode neighbor= e.getDestination();
			nodeNeighbors.add(neighbor);
		}
		return nodeNeighbors;
	}
	
	public abstract boolean addEdge(GraphNode n1, GraphNode n2, int weight);
	public abstract boolean removeEdge(GraphNode n1, GraphNode n2, int weight);
	
	public boolean addEdgeFromTo(GraphNode source, GraphNode destination, int weight)
	{
		Edge newEdge= new Edge(source,destination, weight);
		Set<Edge> sourceEdges=adjacencySets.get(source);
		
		if(!sourceEdges.contains(newEdge))
		{
			sourceEdges.add(newEdge);
			return true;
		}
		
		return false;
	}
	
	public void printGraph()
	{
		Set<Entry<GraphNode,Set<Edge>>> setOfEntries=adjacencySets.entrySet();
		
		System.out.println("Nodes:   Edges:");
		
		for(Entry<GraphNode,Set<Edge>> entry : setOfEntries)
		{
			GraphNode node=entry.getKey();
			Set<Edge> edges=entry.getValue();

			System.out.print(""+node.getElement()+": \t ");
			
			int i=0;
			for(Edge edge : edges)
			{
				if(i>0)
					System.out.print("\t ");
				System.out.println(edge.toString());
				i++;
			}
			if(i==0)
				System.out.println();
		}
		
	}
	
	/*
	 * Route Between Nodes
	 */
	public boolean findRouteBetween2Nodes(GraphNode start, GraphNode end)
	{
		/*
		 * Breadth - First Search
		 * use a LinkedList which will operate as a Queue==nodesToProcess
		 */
		if(start==end)
			return true;
		
		LinkedList<GraphNode> nodesToProcess=new LinkedList<GraphNode>();
		
		/*
		 * Initializing all the nodes of the graph to Unvisited value is important
		 * I set it as a default value in the GraphNode class' definition
		 * if I didn't, the following code should be added in this function,
		 * but actually, because that information might have been altered by another method, 
		 * it's still better to initialize it here, only I need to implement one more method 
		 * getNodes()
		 */
		
		for(GraphNode n : getNodes()) 
			n.setState(State.Unvisited);
		
		start.setState(State.Visiting);
		nodesToProcess.add(start); // enqueue
		
		GraphNode currentNode;
		
		while(!nodesToProcess.isEmpty())
		{
			currentNode=nodesToProcess.removeFirst(); // dequeue
			
			if(currentNode!=null)
			{
				// for each node in the queue we go for the adjacent nodes
				ArrayList<GraphNode> neighbors=getNodeNeighbors(currentNode);
				for(GraphNode neighbor : neighbors)
				{
					if(neighbor.getState()==State.Unvisited)
					{
						if(neighbor==end)
							return true;
						else
						{
							neighbor.setState(State.Visiting);
							nodesToProcess.add(neighbor);
						}
					}
				}
				currentNode.setState(State.Visited);
			}
		}		
		return false;
	}

	public GraphNode[] getNodes() 
	{
		GraphNode[] nodes=new GraphNode[adjacencySets.size()];
		adjacencySets.keySet().toArray(nodes);
		return nodes;
	}
	
}

enum State {
	Unvisited,Visited,Visiting;
}
