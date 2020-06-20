package Pathfinder;
import java.util.ArrayList;

public class Node implements Comparable<Object>{
	private int distance;
	private int location;
	private int maxFlow, currFlow;
	private ArrayList<Integer> pathFromOrigin;
	
	// for normal graph
	public Node(int distance, int location) {
		this.distance = distance;
		this.location = location;
		currFlow = -1;
		maxFlow = -1;
		this.pathFromOrigin = new ArrayList<Integer>();
	}
	
	// for graph calculating flow
	public Node(int location, int curr, int capacity) {
		this.location = location;
		currFlow = curr;
		maxFlow = capacity;
		this.pathFromOrigin = new ArrayList<Integer>();
	}
	
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	public int getDistance() {
		return this.distance;
	}
	
	public int getNode() {
		return this.location;
	}
	
	// for initial insert
	public void setPathInit(int next) {
		this.pathFromOrigin.add(next);
	}
	
	// for every subsequent path finder
	public void setPath(ArrayList<Integer> path, int next) {
		this.pathFromOrigin = path;
		this.pathFromOrigin.add(next);
	}
	
	public ArrayList<Integer> getPath(){
		return this.pathFromOrigin;
	}
	
	public int getFlowCap() {
		return maxFlow;
	}
	
	public void addFlow(int add) {
		currFlow += add;
	}
	
	public int getAvailFlow() {
		return maxFlow - currFlow;
	}
	
	public void printPath() {
		System.out.println("Path for " + location);
		for(int path : pathFromOrigin) {
			System.out.print(path + " ");
		}
		System.out.println();
	}
	
	@Override
	public int compareTo(Object o) {
		Node comp = (Node)o;
		
		if (distance > comp.distance) {
			return 1;
		}
		else
			return -1;
	}
}
