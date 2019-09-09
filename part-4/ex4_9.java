import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

public class GraphForProjects 
{
	// project == node
	ArrayList<Project> nodes=new ArrayList<Project>();
	HashMap <String,Project> map=new HashMap <String,Project>();
	
	public Project getOrCreateNode(String name)
	{
		if(!map.containsKey(name))
		{
			Project node=new Project(name);
			nodes.add(node);
			map.put(name, node);
		}
		return map.get(name);
	}
	
	public void addEdge(String sourceName, String destName)
	{
		Project parent=getOrCreateNode(sourceName);
		Project child=getOrCreateNode(destName);
		parent.addChild(child);
	}
	
	public ArrayList<Project> getNodes(){return nodes;}

}
