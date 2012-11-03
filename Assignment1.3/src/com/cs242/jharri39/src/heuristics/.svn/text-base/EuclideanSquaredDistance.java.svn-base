package com.cs242.jharri39.src.heuristics;

import com.cs242.jharri39.src.MazeNode;

/**
 * The Euclidean Squared Distance Heuristic implementation for 
 * estimating the distance from a node to the finish.
 * 
 * @author Joshua Harris
 */
public class EuclideanSquaredDistance implements Heuristic{

	private final int WIDTH;
	private final int HEIGHT;
	private final int FINISH;
	
	// Scalar for the heuristic
	private final int SCALAR = 100;
	
	public EuclideanSquaredDistance(int width, int height, int finish){
		WIDTH = width;
		HEIGHT = height;
		FINISH = finish;
	}
	
	@Override
	public void calculateDistance(MazeNode node) {
		int nodeX = node.getPosition() % WIDTH;
		int nodeY = node.getPosition() / WIDTH;
		
		int finishX = FINISH % WIDTH;
		int finishY = FINISH / WIDTH;
		
		int xResults = (int) Math.pow(nodeX - finishX, 2);
		int yResults = (int) Math.pow(nodeY - finishY, 2);
		
		double results =  (SCALAR * (xResults + yResults));
		
		node.setHeuristicCost(results);			
	}

}
