import java.util.ArrayList;
import java.util.Stack;

public class TestFindBuildOrder 
{
	public static void main()
	{
		String[] projects= {"a","b","c","d","e","f","g","e"};
		String[][] dependencies= {{"f","c"},{"f","a"},{"f","b"},{"c","a"},{"b","a"},{"a","e"},{"b","e"},{"d","g"}};
		
		System.out.println("The build order found with a BFS algorithm is:");
		if(findBuildOrderBFS(projects,dependencies)!=null)
			printArrayOfProjects(findBuildOrderBFS(projects,dependencies));
		else System.out.println("There is no possible build order!");
		
		System.out.println("The build order found with a DFS algorithm is:");
		if(findBuildOrderBFS(projects,dependencies)!=null)
			printStackOfProjects(findBuildOrderDFS(projects,dependencies));
		else System.out.println("There is no possible build order!");
			
	}
	

	private static void printStackOfProjects(Stack<Project> stack) 
	{
		while(!stack.isEmpty())
		{
			Project p=stack.pop();
			System.out.print(p.getName()+" ");
		}
		System.out.println();			
	}


	private static Stack<Project> findBuildOrderDFS(String[] projects, String[][] dependencies) 
	{
		GraphForProjects graph=buildGraph(projects,dependencies);
/*		
		System.out.println("Nodes in the Graph after building Graph:");
		printArrayOfProjects(graph.getNodes());*/
		
		return orderProjectsDFS(graph.getNodes());
	}


	private static Stack<Project> orderProjectsDFS(ArrayList<Project> projects) 
	{
		/* For each project, if Unvisited, then doDFS */
		Stack<Project> stack=new Stack<Project>();
		
		for(Project project : projects)
		{
			if(project.getState()==State.Unvisited)
			{
				if(!doDFS(project,stack))
					return null;
			}
		}
		
		return stack;
	}


	private static boolean doDFS(Project project, Stack<Project> stack) 
	{
		if(project.getState()==State.Visiting)
			return false; // cycle
		
		if(project.getState()==State.Unvisited)
		{
			project.setState(State.Visiting);
			
			ArrayList<Project> children=project.getChildren();
			for(Project child : children)
			{
				if(!doDFS(child,stack))
					return false;
			}
			
			project.setState(State.Visited);
			stack.push(project);
		}		
		return true;
	}


	private static void printArrayOfProjects(Project[] projects) 
	{
		if(projects==null)
			return;
		for(Project p:projects)
		{
			if(p==null)
				return;
			System.out.print(p.getName()+" ");
		}
		System.out.println();
	}
	private static void printArrayOfProjects(ArrayList<Project> projects) 
	{
		System.out.println("Nodes:\t Children: ");

		for(Project p:projects)
		{
			System.out.print(p.getName()+": ");
			ArrayList<Project> children=p.getChildren();
			for(Project child : children)
			{
				System.out.print(child.getName()+", ");
			}
			System.out.println();
		}
		System.out.println();
	}


	public static Project[] findBuildOrderBFS(String[] projects, String [][] dependencies)
	{
		GraphForProjects graph=buildGraph(projects,dependencies);
/*		
		System.out.println("Nodes in the Graph after building Graph:");
		printArrayOfProjects(graph.getNodes());*/
		
		return orderProjectsBFS(graph.getNodes());
	}

	private static GraphForProjects buildGraph(String[] projects, String[][] dependencies) 
	{
		GraphForProjects graph = new GraphForProjects();
		// call the Graph methods
		for(String projectName : projects)
		{
			graph.getOrCreateNode(projectName);
		}
		
		for(String[] dependency : dependencies)
		{
			String sourceName=dependency[0];
			String destName=dependency[1];
			graph.addEdge(sourceName, destName);
		}

		return graph;
	}
	
	private static Project[] orderProjectsBFS(ArrayList<Project> projects) 
	{
		Project[] order = new Project[projects.size()];
		
		int lastAddedToOrder = addNonDependent(order,projects,0);
		
		int toBeProcessed=0;
		
		while(toBeProcessed<order.length) 
		{
			Project current=order[toBeProcessed];
			
			if(current==null)
				return null; // circular dependency
			
			/* Remove myself as dependency */
			ArrayList<Project> children=current.getChildren();
			for(Project child : children)
				child.decrementNumDependencies();
			
			/* Add children that got free of dependencies */
			lastAddedToOrder = addNonDependent(order,children,lastAddedToOrder);
			toBeProcessed++;
		}		
		return order;
	}

	private static int addNonDependent(Project[] order, ArrayList<Project> projects, int offset) 
	{
		for(Project project : projects)
		{
			if(project.getNumDependencies()==0)
			{
				order[offset]=project;
				offset++;
			}
		}
		return offset;
	}

}
