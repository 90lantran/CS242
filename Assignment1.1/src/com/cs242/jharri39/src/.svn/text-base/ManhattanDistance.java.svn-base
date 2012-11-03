package com.cs242.jharri39.src;

/**
 * Manhanttan Distance Heuristic implementation.
 * 
 * @author Joshua Harris
 */
public class ManhattanDistance implements Heuristic {

	private final int WIDTH;
	private final int HEIGHT;
	private final int FINISH;
	
	// Scales for the cost of Lateral Movement
	private final int LATERAL_COST = 100;

	public ManhattanDistance(int width, int height, int finish) {
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

			int distance = Math.abs(finishX - nodeX)
					+ Math.abs(finishY - nodeY);

			node.setHeuristicCost(LATERAL_COST * distance);
	}

}
