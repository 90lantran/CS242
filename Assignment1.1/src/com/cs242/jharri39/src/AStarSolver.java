package com.cs242.jharri39.src;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.PriorityQueue;

import java.lang.Math;

import javax.management.RuntimeErrorException;

import com.cs242.jharri39.src.MazeNode;
import com.cs242.jharri39.src.MazeSolver;

/**
 * A* Maze solving algorithm implementation of the MazeSolver class.
 * 
 * @author Joshua Harris
 */
public class AStarSolver extends MazeSolver {
	
	private final int DIAGONAL_MOVEMENT_COST = 141;
	private final int LATERAL_MOVEMENT_COST = 100;

	private MazeNodeComparator comparator;
	private PriorityQueue openNodeList;
	private Hashtable closedNodeList;
	private Heuristic heuristic;

	/**
	 * @param start - start position for the maze
	 * @param finish - end position for the maze
	 * @param width - width of the maze
	 * @param height - height of the maze
	 * @param walls - array of the positions of  impassable nodes
	 * @param heuristic - the choice of heuristics to use to estimate distance
	 */
	public AStarSolver(int start, int finish, int width, int height, int[] walls, Heuristic.HeuristicChoice heuristic) {
		super(start, finish, width, height, walls);

		comparator = new MazeNodeComparator();
		openNodeList = new PriorityQueue<>(width * height, comparator);

		closedNodeList = new Hashtable<Point, MazeNode>();
		
		switch(heuristic) {
			case MANHATTAN_DISTANCE:
				this.heuristic = new ManhattanDistance(WIDTH, HEIGHT, FINISH);
				break;
				
			case DIAGONAL_DISTANCE:
				this.heuristic = new DiagonalDistance(WIDTH, HEIGHT, FINISH);
				break;
				
			case EUCLIDEAN_DISTANCE:
				this.heuristic = new EuclideanDistance(WIDTH, HEIGHT, FINISH);
				break;
				
			case EUCLIDEAN_SQUARED_DISTANCE:
				this.heuristic = new EuclideanSquaredDistance(WIDTH, HEIGHT, FINISH);
				break;
				
			case MANHATTAN_DISTANCE_TIE_BREAKER:
				this.heuristic = new ManhattanDistanceWithTieBreaker(WIDTH, HEIGHT, FINISH);
				break;
				
			default:
				this.heuristic = new ManhattanDistance(WIDTH, HEIGHT, FINISH);
				break;
		}
	}
	
	/**
	 * @param start - start position for the maze
	 * @param finish - end position for the maze
	 * @param width - width of the maze
	 * @param height - height of the maze
	 * @param walls - array of the positions of  impassable nodes
	 */
	public AStarSolver(int start, int finish, int width, int height, int[] walls) {
		super(start, finish, width, height, walls);

		comparator = new MazeNodeComparator();
		openNodeList = new PriorityQueue<>(width * height, comparator);

		closedNodeList = new Hashtable<Point, MazeNode>();
		
		// Default to using the Manhattan Distance heuristic
		heuristic = new ManhattanDistance(WIDTH, HEIGHT, FINISH);
	}

	/**
	 * This method begins solving the maze using the A* Algorithm
	 */
	@Override
	public void solveMaze() {
		// Add the StartNode to the OpenList
		MazeNode currentNode = BOARD[START % WIDTH][START / WIDTH];
		openNodeList.offer(currentNode);

		// While OpenList is not empty and the CurrentNode is not the finish
		while (!openNodeList.isEmpty() && currentNode.getPosition() != FINISH) {
			// Dequeue a Node CurrentNode from Open and Enqueue onto the Closed
			currentNode = (MazeNode) openNodeList.poll();
			closedNodeList.put(getPointFromNode(currentNode), currentNode);

			if (currentNode == null) {
				break;
			}

			// Find All the reachable Nodes that are not Walls
			ArrayList<MazeNode> neighboringNodes = findNeighborNodes(currentNode);
			if (neighboringNodes != null) {
				for (MazeNode mazeNode : neighboringNodes) {
					// If G is not set
					if (mazeNode.getTotalCost() == 0) {
						// Make the Current Node their parent
						mazeNode.setParentNode(currentNode.getPosition());

						// Calculate G and set G
						calculateAndSetMovementCost(currentNode, mazeNode);

						// Calculate H
						calculateDistanceToFinish(mazeNode);

						// Calculate F
						calculateTotalCost(mazeNode);

						// Add it to the OpenList
						openNodeList.offer(mazeNode);
					}

					// Else Check is G is improved by using current node
					else {
						// If it is improved
						int tempMovementCost = calculateMovementCost(
								currentNode, mazeNode);
						if (tempMovementCost < mazeNode.getMovementCost()) {
							// Make the Current Node their parent
							mazeNode.setParentNode(currentNode.getPosition());

							// Calculate New G
							calculateAndSetMovementCost(currentNode, mazeNode);

							// Calculate New F
							calculateTotalCost(mazeNode);

							// Dequeue and Enqueue from Open (reset its position
							// in the Queue)
							openNodeList.remove(mazeNode);
							openNodeList.offer(mazeNode);
						}
						// Else do nothing
					}
				}
			}
		}

	}

	/**
	 * This function returns the Point Object for a node based off of its
	 * position.
	 * 
	 * @param node
	 *            - the node which to calculate the Point from
	 * @return the Point for the node
	 */
	public Point getPointFromNode(MazeNode node) {
		int position = node.getPosition();

		int x = position % WIDTH;
		int y = position / WIDTH;

		Point results = new Point(x, y);

		return results;
	}

	/**
	 * FindNeighborNodes will return an array list of all the nodes that
	 * neighbor the given Node. It will not return nodes that are walls.
	 * 
	 * @param currentNode
	 *            - node to find neighbors around
	 * @return List of Neighbors
	 */
	public ArrayList<MazeNode> findNeighborNodes(MazeNode currentNode) {
		ArrayList<MazeNode> results = new ArrayList();

		int x = (currentNode.getPosition()) % WIDTH;
		int y = (currentNode.getPosition()) / WIDTH;

		MazeNode node = null;
		// North Node
		y -= 1;
		if (y >= 0) {
			node = BOARD[x][y];
			if (!node.isImpassable() && !closedNodeList.containsKey(new Point(x, y))) {
				results.add(node);
			}
		}

		// North East Node
		x += 1;
		if (x < WIDTH && y >= 0) {
			node = BOARD[x][y];
			if (!node.isImpassable() && !closedNodeList.containsKey(new Point(x, y))) {
				results.add(node);
			}
		}

		// East Node
		y += 1;
		if (x < WIDTH) {
			node = BOARD[x][y];
			if (!node.isImpassable() && !closedNodeList.containsKey(new Point(x, y))) {
				results.add(node);
			}
		}

		// South East Node
		y += 1;
		if (x < WIDTH && y < HEIGHT) {
			node = BOARD[x][y];
			if (!node.isImpassable() && !closedNodeList.containsKey(new Point(x, y))) {
				results.add(node);
			}
		}

		// South Node
		x -= 1;
		if (y < HEIGHT) {
			node = BOARD[x][y];
			if (!node.isImpassable() && !closedNodeList.containsKey(new Point(x, y))) {
				results.add(node);
			}
		}

		// South West Node
		x -= 1;
		if (x >= 0 && y < HEIGHT) {
			node = BOARD[x][y];
			if (!node.isImpassable() && !closedNodeList.containsKey(new Point(x, y))) {
				results.add(node);
			}
		}

		// West Node
		y -= 1;
		if (x >= 0) {
			node = BOARD[x][y];
			if (!node.isImpassable() && !closedNodeList.containsKey(new Point(x, y))) {
				results.add(node);
			}
		}

		// North West Node
		y -= 1;
		if (x >= 0 && y >= 0) {
			node = BOARD[x][y];
			if (!node.isImpassable() && !closedNodeList.containsKey(new Point(x, y))) {
				results.add(node);
			}
		}

		return results;
	}

	/**
	 * This method figures out whether the two nodes are diagonal or adjacent
	 * and then returns the results of the calculation.
	 * 
	 * @param currentNode
	 *            - current location to base the neighborNode location off of
	 * @param neighborNode
	 *            - node which to set the new movement cost
	 * @return new movement cost using the two nodes
	 */
	public int calculateMovementCost(MazeNode currentNode, MazeNode neighborNode) {
		int x1 = currentNode.getPosition() % WIDTH;
		int y1 = currentNode.getPosition() / WIDTH;
		
		int x2 = neighborNode.getPosition() % WIDTH;
		int y2 = neighborNode.getPosition() / WIDTH;
		
		int xDiff = Math.abs(x1 - x2);
		int yDiff = Math.abs(y1 - y2);
		
		int results = 0;
		// Diagonal Case
		if ((xDiff + yDiff) == 2) {
			results = currentNode.getMovementCost() + DIAGONAL_MOVEMENT_COST;
		}
		// Lateral Case
		else if ((xDiff + yDiff) == 1){
			results = currentNode.getMovementCost() + LATERAL_MOVEMENT_COST;
		}
		
		return results;
	}

	/**
	 * This method figures out whether the two nodes are diagonal or adjacent.
	 * It then updates the Movement Cost for the neighboring Node.
	 * 
	 * @param currentNode
	 *            - current location to base the neighborNode location off of
	 * @param neighborNode
	 *            - node which to set the new movement cost
	 */
	public void calculateAndSetMovementCost(MazeNode currentNode,
			MazeNode neighborNode) {
		int x1 = currentNode.getPosition() % WIDTH;
		int y1 = currentNode.getPosition() / WIDTH;
		
		int x2 = neighborNode.getPosition() % WIDTH;
		int y2 = neighborNode.getPosition() / WIDTH;
		
		int xDiff = Math.abs(x1 - x2);
		int yDiff = Math.abs(y1 - y2);
		
		// Diagonal Case
		if ((xDiff + yDiff) == 2) {
			neighborNode.setMovementCost(currentNode.getMovementCost() + DIAGONAL_MOVEMENT_COST);
		}
		// Lateral Case
		else if ((xDiff + yDiff) == 1){
			neighborNode.setMovementCost(currentNode.getMovementCost() + LATERAL_MOVEMENT_COST);
		}
	}

	/**
	 * This method with calculate the distance to the finish from the current
	 * node using the set Heuristic.
	 * 
	 * @param node
	 *            - node which to calculate the distance using the appropriate
	 *            Heuristic
	 */
	public void calculateDistanceToFinish(MazeNode node) {
		heuristic.calculateDistance(node);
	}

	/**
	 * This method sets the given node's Total Cost by adding the Movement Cost
	 * and Distance together.
	 * 
	 * @param node
	 *            - the mazeNode which to sum the movement and heuristic cost in
	 */
	public void calculateTotalCost(MazeNode node) {
		int movementCost = node.getMovementCost();
		double distanceCost = node.getHeuristicCost();
		
		node.setTotalCost(movementCost + (int)distanceCost);
	}

	/**
	 * The Comparator implementation for the priority queue for the Open List
	 * 
	 * @author Joshua Harris
	 */
	private class MazeNodeComparator implements Comparator<MazeNode> {

		/**
		 * Comparison function upon which the Comparator sorts Nodes
		 * 
		 * @param arg0
		 *            - the node which to compare with arg1
		 * @param arg1
		 *            - the node which to compare with arg0
		 */
		@Override
		public int compare(MazeNode arg0, MazeNode arg1) {
			// Get the total costs (F) for comparasion of the two nodes
			int leftCost = arg0.getTotalCost();
			int rightCost = arg1.getTotalCost();

			return leftCost - rightCost;
		}

	}
}
