import org.junit.jupiter.api.Test;

class Tester {
	
	WeightedGraph graph = new WeightedGraph();
	
	
	@Test
	void testMethod() {
		graph.addNode("A");
		graph.addNode("B");
		graph.addNode("C");
		graph.addNode("D");
		graph.addNode("E");
		String[] toList = {"A", "B", "D", "E"};
		int[] weights = {1, 4, 10, 2};
		graph.addWeightedEdge("A", "B", 3);
		graph.addWeightedEdge("A", "C", 1);
		graph.addWeightedEdges("C", toList, weights);
		graph.addEdge("D", "A");
		
		graph.printGraph();
		
		// Should print
		/*
		A C B D 
		B 
		C A D B E 
		D A 
		E 
		*/
		
		graph.printWeightedGraph();
		
		// Should print
		/*
		A 1 C 3 B 0 D 
		B 
		C 1 A 10 D 4 B 2 E 
		D 0 A 
		E 
		*/
		
		
		

	}

}
