package Pathfinder;

public class PathWay {
	private int constructType;
	Node start;
	Node end;
	int startInd;
	int endInd;
	int edgeDist;
	boolean popped;  // exist purely as a helper for pathFinding algorithm
	
	// debug constructor
	public PathWay(int sI, int eI, int eD) {
		startInd = sI;
		endInd = eI;
		edgeDist = eD;
		popped = false;
		constructType = 0;	// indicates constructor with integers
	}
	
	// actual constructor
	public PathWay(Node start, Node end, int eD) {
		this.start = start;
		this.end = end;
		edgeDist = eD;
		popped = false;
		constructType = 1; // indicates constructor with pairs
	}
	
	public int getStartInd() {
		if(constructType == 1)
			return start.getNode();
		return startInd;
	}
	
	public int getEndInd() {
		if(constructType == 1)
			return end.getNode();
		return endInd;
	}
	
	public int getEdgeDist() {
		return edgeDist;
	}
	
	// sets this path to "popped" from the pqueue for pathfinding
	public void setPopped() {
		popped = true;
	}
	
	// returns whether this pathway has been popped from the pathfinding pqueue yet
	public boolean popped() {
		return popped;
	}
}
