
public class GraphNode 
{
	private Integer element;
	private State state=State.Unvisited;
	
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public GraphNode(Integer el)
	{
		element=el;
	}

	public Integer getElement() {
		return element;
	}

	public void setElement(Integer element) {
		this.element = element;
	}
}
