package com.cs242.jharri39.src.heuristics;

import com.cs242.jharri39.src.MazeNode;


/**
 * The Manhattan Distance with a Tie Breaker Heuristic implementation for 
 * estimating the distance from a node to the finish.
 * 
 * @author Joshua Harris
 */
public class ManhattanDistanceWithTieBreaker implements Heuristic{
	
	private final int WIDTH;
	private final int HEIGHT;
	private final int FINISH;
	
	// cost for moving in lateral directions
	private final double LATERAL_COST = 100.0;
	// Nudge to help break ties
	private final double NUDGE = 1.0/1000.0;
	
	public ManhattanDistanceWithTieBreaker(int width, int height, int finish){
		WIDTH = width;
		HEIGHT = height;
		FINISH = finish;
	}

	@Override
	public void calculateDistance(MazeNode node) {
		int finishX = FINISH % WIDTH;
		int finishY = FINISH / WIDTH;

		int nodeX = node.getPosition() % WIDTH;
		int nodeY = node.getPosition() / WIDTH;

		double distance = Math.abs(finishX - nodeX)
				+ Math.abs(finishY - nodeY);
		
		double nudge = 1.0 + NUDGE;
		
		double manhattanDistance = LATERAL_COST * distance;
		
		double results = nudge * manhattanDistance;

		node.setHeuristicCost(results);
		
	}

}