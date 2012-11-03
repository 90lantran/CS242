package com.cs242.jharri39.src;

/**
 * The Diagonal Distance Heuristic implementation for estimating the distance
 * from a node to the finish.
 * 
 * @author Joshua Harris
 */
public class DiagonalDistance implements Heuristic {

	private final int WIDTH;
	private final int HEIGHT;
	private final int FINISH;

	// Scalars to affect the cost for Diagonal and Lateral Movement
	private final int DIAGONAL_COST = 141;
	private final int LATERAL_COST = 100;

	public DiagonalDistance(int width, int height, int finish) {
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
		
		int diagonalResults = calculateDiagonalMovement(nodeX, nodeY, finishX, finishY);
		int lateralResults = calculateStraightMovement(nodeX, nodeY, finishX, finishY);
		
		int results = (DIAGONAL_COST * diagonalResults) + (LATERAL_COST * 
				(lateralResults - (2 * diagonalResults)));
		
		node.setHeuristicCost(results);
	}

	/**
	 * this calculates the Diagonal Movement for the Diagonal Distance
	 * Algorithm
	 * 
	 * @param nodeX - x positon for the node
	 * @param nodeY - y postion for the node
	 * @param finishX - x postion for the finish node
	 * @param finishY - y position for the finish node
	 * @return the result of the diagonal movement calculation
	 */
	private int calculateDiagonalMovement(int nodeX, int nodeY, int finishX,
			int finishY) {
		int xResults = Math.abs(nodeX - finishX);
		int yResults = Math.abs(nodeY - finishY);

		int results = Math.min(xResults, yResults);

		return results;
	}

	/**
	 * this calculates the Straight Movement for the Diagonal Distance
	 * Algorithm
	 * @param nodeX - x positon for the node
	 * @param nodeY - y postion for the node
	 * @param finishX - x postion for the finish node
	 * @param finishY - y position for the finish node
	 * @return the result of the straight movement calculation
	 */
	private int calculateStraightMovement(int nodeX, int nodeY, int finishX,
			int finishY) {
		int xResults = Math.abs(nodeX - finishX);
		int yResults = Math.abs(nodeY - finishY);

		return xResults + yResults;
	}

}
