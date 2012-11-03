package com.jharri39.cs242Assignment1.MazeSrc;

import java.util.ArrayList;

/**
 * A* Maze solving algorithm implementation of the 
 * MazeSolver class. 
 * 
 * @author Joshua Harris
 */
public class AStarSolver extends MazeSolver{
	
	//PriorityQueue OpenNodeList
	//PriorityQueue ClosedNodeList
	
	
	/**
	 * @param start
	 * @param finish
	 * @param width
	 * @param height
	 * @param walls
	 */
	public AStarSolver(int start, int finish, int width, int height, int[] walls) {
		super(start, finish, width, height, walls);
		//initialize Priority Queue for OpenList
		//initialize Priority Queue for the ClosedList
		
		//Make the StartNode's Values 0
	}
	
	/**
	 * This method begins solving the maze using the A* Algorithm
	 */
	@Override
	public void SolveMaze() {
		//Add the StartNode to the OpenList
		
		//While OpenList is not empty and the CurrentNode is not the finish
			//Dequeue a Node CurrentNode from Open and Enqueue onto the Closed
			//Find All the reachable Nodes that are not Walls
				//Make the Current Node their parent
				
				//If G is not set 
					//Calculate G
					//Calculate H
					//Calculate F
					//Add it to the OpenList
				//Else Check is G is improved by using current node
					//If it is improved
						//Calculate New G
						//Calculate New F
						//Set New Parent
						//Dequeue and Enqueue from Open (reset its position in the Queue)
					//Else do nothing
		
	}
	
	/**
	 * FindNeighborNodes will return an array list of all the nodes that neighbor the given 
	 * Node. It will not return nodes that are walls.
	 * 
	 * @param currentNode
	 * @return List of Neighbors
	 */
	public ArrayList<MazeNode> FindNeighborNodes(MazeNode currentNode) {
		return null;
	}

	/**
	 * This method figures out whether the two nodes are diagonal or adjacent. It then updates
	 * the Movement Cost for the neighboring Node.
	 * 
	 * @param currentNode
	 * @param neighborNode
	 */
	public void CalculateMovementCost(MazeNode currentNode, MazeNode neighborNode) {

	}
	
	/**
	 * This method with calculate the distance to the finish from the current node using the
	 * set Heuristic.
	 * 
	 * @param node
	 */
	public void CalculateDistanceToFinish(MazeNode node){
		
	}
	
	/**
	 * This method sets the given node's Total Cost by adding the Movement Cost and Distance
	 * together.
	 * 
	 * @param node
	 */
	public void CalculateTotalCost(MazeNode node){

	}
}

