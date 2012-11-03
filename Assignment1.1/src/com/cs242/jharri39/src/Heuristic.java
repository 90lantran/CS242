package com.cs242.jharri39.src;

/**
 * This is the interface for the Heuristic Functions. It is designed
 * to contain a function that is expected to be implemented for each
 * Desirable Heuristic.
 *  
 * @author Joshua Harris
 */
public interface Heuristic {
	
	public static enum HeuristicChoice {
		MANHATTAN_DISTANCE, DIAGONAL_DISTANCE, EUCLIDEAN_DISTANCE, 
		EUCLIDEAN_SQUARED_DISTANCE, MANHATTAN_DISTANCE_TIE_BREAKER
	}
	
	/**
	 * uses the implemented heuristic to estimate the distance from the
	 * current node to the finish
	 * 
	 * @param node - the node to calculate the distance on
	 */
	public void calculateDistance(MazeNode node);
}
