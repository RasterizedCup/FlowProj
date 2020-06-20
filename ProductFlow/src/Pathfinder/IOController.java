package Pathfinder;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.sql.*;
public class IOController {
	public static void main(String[] args) {
		
		// parse an input file (as indicated in PathFinder.java notes)
		// map each string input to an integer value
		// read into array list, send to flow builder
		
		// eventually find a way to properly link a database to this 
		
		System.out.println("start");
		ArrayList<PathWay> path = new ArrayList<>();	//constructor (startInd, endInd, weight, flowCap)
		HashMap<Integer, String> indexMap = new HashMap<Integer, String>(); // this will be built from input
		// for example, we have inputs "Augusta, New York, Columbia". These would be mapped to 0, 1, 2 respectively
		// there are no duplicate mappings, as this is merely meant as a reference index
		FlowBuilder flowbuilder = new FlowBuilder();
		// below is a test input before implementing proper IO
		// proper IO is intended to extract from a database with fields representing most of this data
		// add strings to hash map
		/* STUFF TO PULL FROM SQL */
		// eventually this will just use a for loop to pull from a database
		indexMap.put(0, "Columbia");
		indexMap.put(1, "NewYork");
		indexMap.put(2, "Augusta");
		indexMap.put(3, "SanFran");
		indexMap.put(4, "Seattle");
		indexMap.put(5, "Wherever");
		ArrayList<Node> node = new ArrayList<>();
		for(int i=0; i< indexMap.size(); i++) {
			node.add(new Node(i, 0, 15-i)); // constructor gives node location, curr flow, then max flow
		}
		// add indexed pathways
		// change path ways to two pairs and a weight
		// DRAW HOW THIS IS GONNA BE LAIN OUT
		path.add(new PathWay(node.get(0), node.get(1), 3));
		path.add(new PathWay(node.get(0), node.get(2), 5));
		path.add(new PathWay(node.get(1), node.get(2), 1));
		path.add(new PathWay(node.get(1), node.get(3), 8));
		path.add(new PathWay(node.get(2), node.get(4), 2));
		path.add(new PathWay(node.get(3), node.get(4), 4));
		path.add(new PathWay(node.get(2), node.get(5), 4));
		path.add(new PathWay(node.get(1), node.get(5), 8));
		/* USER INPUT */
		ArrayList<Integer> start = new ArrayList<Integer>();
		ArrayList<Integer> end = new ArrayList<Integer>();
		start.add(3);
		start.add(5);
		end.add(0);
		end.add(1);
		end.add(2);
		
		flowbuilder.simulate(path, indexMap, start, end);
		System.out.println("done");
	}
	

}
