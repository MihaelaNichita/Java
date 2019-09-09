
public class Edge 
{
	private final GraphNode source;
	private final GraphNode destination;
	private final int weight;
	
	public Edge(GraphNode source, GraphNode destination, int weight) 
	{
		this.source = source;
		this.destination = destination;
		this.weight = weight;
	}

	public GraphNode getSource() {
		return source;
	}

	public GraphNode getDestination() {
		return destination;
	}

	public int getWeight() {
		return weight;
	}

	@Override
	public String toString() {
		return "Edge [source=" + source.getElement() + ", destination=" + destination.getElement() + ", weight=" + weight + "]";
	}
	


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + weight;
		return result;
	}

	
}
