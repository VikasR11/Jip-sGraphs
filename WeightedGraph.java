import java.io.File;
import java.util.Scanner;

/**
 * Weighted Graph implementation
 * 
 * @author Jaap Singh
 *
 */
public class WeightedGraph extends Graph {
	
	/**
	 * Constructor that calls on parent
	 */
	public WeightedGraph() {
		super();
	}
	
	/**
	 * Adds a weighted edge
	 * @param from - source
	 * @param to - destination
	 * @param weight - weight of edge
	 * @return true if edge was successfully created, false otherwise
	 */
	public boolean addWeightedEdge(String from, String to, int weight) {
		// making sure that both nodes exist in graph
        if (super.graphList.containsKey(from) && super.graphList.containsKey(to)) {
        	super.graphList.get(from).add(new Edge(to, weight));
        	return true;
        }
        return false;
	}
	
	/**
	 * 
	 * @param from - source
	 * @param toList - list of destination nodes
	 * @param weightList - list of weight nodes 
	 * @return true if from exists in graph, false otherwise
	 */
	public boolean addWeightedEdges(String from, String[] toList, int[] weightList) {
		if(this.graphList.containsKey(from)) {
			for (int i = 0; i < toList.length; i++) {
				// adding each toList node with corresponding weight
				addWeightedEdge(from, toList[i], weightList[i]);
			}
			return true;
		}
		return false;
	}
	
	/**
	 * prints the weighted graph
	 */
	public void printWeightedGraph() {
		for (String node: super.graphList.keySet()) {
    		System.out.print(node+" "); // printing out the current node
    		for(Edge e: super.graphList.get(node)) {
    			// printing out weight then destination
    			System.out.print(e.weight+" "+e.dest+" ");
    		}
    		System.out.println();
		}
	}
	
	/**
	 * Creates a weighted graph from a .txt file
	 * @param fileName - to read a graph from
	 * @return - Graph object
	 * @throws Exception - if file is not found
	 */
	public Graph readWeightedGraph(String fileName) throws Exception {
		WeightedGraph graph = new WeightedGraph();
    	File file = new File(fileName);
    	Scanner scan = new Scanner(file);
    	while (scan.hasNextLine()) {
    		// we keep reading the file as long as a next line exists
    		String toAdd = scan.nextLine();
    		String root = toAdd.substring(0, 1); // adding root first
    		graph.addNode(root);
    		for (int i = 2; i < toAdd.length(); i += 4) {
    			// extracting weight and converting it to an int
    			int weightVal = Integer.parseInt(toAdd.substring(i, i+1));
    			String neighbor = toAdd.substring(i+2, i+3);
    			// we then add all of the neighbors as nodes
    			graph.addNode(neighbor);
    			// we add an edge between root and neighbor
    			graph.addWeightedEdge(root, neighbor, weightVal);    				
    		}
    	}
    	scan.close();
    	return graph;
	}

}
