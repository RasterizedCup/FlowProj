package Pathfinder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
public class FlowBuilder extends PathFinder {
	
	/**
	 * This is the driving function of flowBuilder, which allows predictive optimal flow to be built through a graph given the following parameters.
	 * 
	 * @param path - list of all the pathways creating a given graph, containing start/end nodes, edge weights, and flow amounts and their relative capacities
	 * @param indexMap - hash map of string names of locations corresponding to integers (for ease of calculations)
	 * @param startNode - list of all the potential start points for this flow calculation
	 * @param endNode - list of all the potential end points for this flow calculation
	 */
	
	
	// tl;dr move flow vals to nodes and not paths, and fix up hashMap to be more useful
	public void simulate(ArrayList<PathWay> path, HashMap<Integer, String> indexMap, ArrayList<Integer> startNode, ArrayList<Integer> endNode) {
		/* Initialize flow items */
		ArrayList<PathWay> currPaths = path;
		ArrayList<Boolean> popped = new ArrayList<Boolean>(Collections.nCopies(indexMap.size(), false)); // array of booleans initialized to number of different nodes, all false
		ArrayList<ArrayList<Node>> shortestPath = dijkstra(currPaths, endNode, popped);
		
		
		
		// print all paths (debug)
		for(int i=0; i<shortestPath.size(); i++) {
			System.out.println("Shortest Paths to " + endNode.get(i));
			for(int j=0; j<shortestPath.get(i).size(); j++) {
				for(int k=0; k<shortestPath.get(i).get(j).getPath().size(); k++) {	// this is a clusterfuck you should consider refining
					System.out.print(shortestPath.get(i).get(j).getPath().get(k) + " ");
				}
				System.out.println();
			}
		}
		
		// at this point, we know all the shortest distances from every point, to each end point
		// now, begin routing flow from start points in a way that carries it optimally to an end point
	}
	
	// make parsing function for query to flow start and flow end
}
