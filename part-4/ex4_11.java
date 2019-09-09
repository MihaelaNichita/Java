import java.util.ArrayList;
import java.util.HashMap;

/*
	 * BUILD ORDER
	 * Being given a list of projects and a list of dependencies (which is a list of pairs of projects,
	 * where the 2nd project is dependent on the first project)
	 * All of the project dependencies must be built before the project is,
	 * Find a build order that will allow the projects to be built. 
	 * If there is no valid build order, return an error.
*/

public class Project 
{
	private ArrayList<Project> children = new ArrayList<Project>();
	
	// keep also children here -> for checking whether the array(set) contains it already
	private HashMap<String,Project> map=new HashMap<String,Project>(); 
	
	private String name;
	private int numDependencies=0;
	private State state=State.Unvisited;
	
	public Project(String name) {this.name=name;}
		
	public void addChild(Project node)
	{
		if (!map.containsKey(node.getName()))
		{
			children.add(node);
			map.put(node.getName(), node);
			node.incrementNumDependencies();
		}		
	}

	public ArrayList<Project> getChildren() {return children;}

	public String getName() {return name;}

	public int getNumDependencies() {return numDependencies;}

	public void incrementNumDependencies() {numDependencies++;}
	public void decrementNumDependencies() {numDependencies--;}

	public State getState() {return state;}
	public void setState(State st) {state=st;}

}
