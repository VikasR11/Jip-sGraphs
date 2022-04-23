import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * A Graph implementation
 * 
 * 
 * @author Jaap Singh
 * 
 */
public class Graph {
	/**
	 * Creating a private inner class for Edge
	 * 
	 * @author Jaap Singh
	 *
	 */
	protected static class Edge {
		protected String dest; // destination node
		protected int weight; // weight that this edge carries
		
		
		/**
		 * Constructor for an unweighted edge
		 * @param d - destination
		 */
		public Edge(String d) {
			this.dest = d;
			this.weight = 0;
		}
		
		/**
		 * Constructor for a weighted edge
		 * @param d - destination
		 * @param w - weight value
		 */
		public Edge(String d, int w) {
			this.dest = d;
			this.weight = w;
		}
	}
	
	// Node - Neighbors
    protected HashMap<String, Set<Edge>> graphList;
    
    /**
     * Create new Graph object.
     */
    public Graph() {
        this.graphList = new HashMap<>();
    }
    
    /**
     * Adds a new node to the graph
     * 
     * @param name - node
     * @return true if it's a duplicate, false otherwise
     */
    public boolean addNode(String name) {
        if (this.graphList.containsKey(name)) {
            return true; // if there are duplicates
        }
        this.graphList.put(name, new HashSet<Edge>());
        return false; // no duplicates
    }
    
    /**
     * 
     * @param names - to be added to graph
     * @return true if there's a duplicate, false otherwise
     */
    public boolean addNodes(String[] names) {
    	boolean hasDuplicates = false;
    	for (String n: names) { 
    		// traversing through list and adding each name
    		hasDuplicates = addNode(n);
    	}
    	return hasDuplicates;
    }
    
    
    /**
     * Add new edge between nodes from and to 
     * 
     * @param from - start node.
     * @param to - destination node.
     * @return true if successfully created the edge, false otherwise
     */
    public boolean addEdge(String from, String to) {
    	// making sure that both nodes exist in graph
        if (this.graphList.containsKey(from) && this.graphList.containsKey(to)) {
        	this.graphList.get(from).add(new Edge(to));
            this.graphList.get(to).add(new Edge(from));
            return true;
        }
        return false;
        
    }
    
    /**
     * Add edges between node from and nodes in toList
     * 
     * @param from - start node.
     * @param toList - list of destination nodes.
     * @return true if from exists in graph, false otherwise
     */
    public boolean addEdges(String from, String[] toList) {
    	if (this.graphList.containsKey(from)) { // making sure from exists
    		for (String to: toList) {
    			addEdge(from, to);
        	}
    		return true;
    	}
        return false;
        
    }
    
    
    /**
     * Remove a node from the graph.
     * 
     * @param name the node that will be removed
     * @return true if successfully removed, false otherwise
     */
    public boolean removeNode(String name) {
        if (!this.graphList.containsKey(name)) {
            return false;
        }
        this.graphList.remove(name); // deleting name and it's neighbors
        for (String otherNode: this.graphList.keySet()) {
        	// deleting name from neighbor nodes
        	for (Edge e: this.graphList.get(otherNode)) {
        		if (e.dest.equals(name)) {
        			this.graphList.get(otherNode).remove(e);
        		} // might cause exception
        	}

        }
        return true;
    }
    
    /**
     * Removes the nodes in nodeList
     * 
     * @param nodeList - list of nodes to be removed
     * @return true
     */
    public boolean removeNodes(String[] nodeList) {
    	for (String name: nodeList) {
    		// traversing through each node and removing that node
    		removeNode(name);
    	}
        return true;
    }
    
    /**
     * Prints the graph
     */
    public void printGraph() {
    	for (String node: this.graphList.keySet()) {
    		System.out.print(node+" "); // printing out the current node
    		for (Edge to: this.graphList.get(node)) {
    			// printing out all of the neighbors of the nodes
    			System.out.print(to.dest+" ");
    		}
    		System.out.println(); // new line 
    	}
    }
    
    /**
     * Creates a graph from a .txt file
     * @param fileName - to read a graph from
     * @return Graph object
     * @throws Exception - if file not found
     */
    public Graph read(String fileName) throws Exception {
    	Graph graph = new Graph();
    	File file = new File(fileName);
    	Scanner scan = new Scanner(file);
    	while (scan.hasNextLine()) {
    		// we keep reading the file as long as a next line exists
    		String toAdd = scan.nextLine();
    		String root = toAdd.substring(0, 1); // adding root first
    		graph.addNode(root);
    		for (int i = 2; i < toAdd.length(); i += 2) {
    			String neighbor = toAdd.substring(i, i+1);
    			// we then add all of the neighbors as nodes
    			graph.addNode(neighbor);
    			// we add an edge between root and neighbor
    			graph.addEdge(root, neighbor);    				
    		}
    	}
    	scan.close();
    	return graph;
    }
}
