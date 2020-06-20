package Pathfinder;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.HashMap;
public class PathFinder {
	
	private PriorityQueue<Node> Pqueue = new PriorityQueue<Node>();
	
	// data input format per node [i], has values of edge distance and flow amount
	// for input tracking, use a map (key: string (location name), value: integer (ascending from 0 representing location index)
	// for actual input use custom data type "PathWay", holds: start_index, end_index, edge_weight, flow_capacity/currFlow

	// this assumes graphs are NOT directed
	//FIND WAY CURR ARRAYLIST IS EFFECTED BY SETPATH OF OTHER PAIRS
	public ArrayList<ArrayList<Node>> dijkstra(ArrayList<PathWay> path, ArrayList<Integer> origin, ArrayList<Boolean> popped) {
		ArrayList<ArrayList<Node>> pairList = new ArrayList<ArrayList<Node>>(); // will be return value
		// popped list will have a 1:1 ratio with nodes, so whenever something is popped, that i'th 
		// element can be set to true
		for(int itr = 0; itr < origin.size(); itr++) {
			pairList.add(new ArrayList<Node>());		// push back number of array lists (per start point)
			Pqueue.add(new Node(0, origin.get(itr))); // add origin to pQueue
			for(int i=0; i<popped.size(); i++) {
				popped.set(i, false);
			}
			while(!Pqueue.isEmpty()) {
				Node curr = Pqueue.poll();
				
				// since we can push multiple of the same node into a queue, we must check if that node was popped
				while(popped.get(curr.getNode()) && !Pqueue.isEmpty()) {
					curr = Pqueue.poll();
				}
				
				// base case break loop (implies the pQueue is empty and the last pop has already been popped)
				if(popped.get(curr.getNode())) {
					break;
				}
				
				// index from origin starting
				if(curr.getNode() == origin.get(itr)) {
					curr.setPathInit(origin.get(itr));
				}
				
				pairList.get(itr).add(curr);					// add current pair to pairList[itr]
				popped.set(curr.getNode(), true);	// set popped index to true
				for(int i=0; i<path.size(); i++) {
					ArrayList<Integer> copy = new ArrayList<Integer>(curr.getPath());		// exists to send copy of a path (by value), inefficient but lol
					// if this path connects from the start node (make pair with end node)
					if(path.get(i).getStartInd() == curr.getNode()) {
						// if given node has not already been popped out of the pQueue
						if(!popped.get(path.get(i).getEndInd())) {
							Node toPqueue = new Node(curr.getDistance() + path.get(i).getEdgeDist(), path.get(i).getEndInd());
							toPqueue.setPath(copy, path.get(i).getEndInd()); //establish path to beginning from this node
							Pqueue.add(toPqueue);
						}
					}
					// if this path connects from the end node (make pair with start node)
					else if(path.get(i).getEndInd() == curr.getNode()) {
						if(!popped.get(path.get(i).getStartInd())) {
							Node toPqueue = new Node(curr.getDistance() + path.get(i).getEdgeDist(), path.get(i).getStartInd());
							toPqueue.setPath(copy, path.get(i).getStartInd());
							Pqueue.add(toPqueue);
						}						
					}
				}
			}
		}
		return pairList;
	}
}

/**
 * Goal is, for a given graph where ever route has a flow value, and given a certain volume of input
 * needed to be transfered, we can calculate the optimal way to transfer goods. Maybe something fancier.
 **/

// inputs can be from multiple locations
// output can arrive at multiple locations
// decide the best deliver route that gives the most likely least travel time