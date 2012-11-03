package com.cs242.jharri39.src;

import com.cs242.jharri39.src.heuristics.Heuristic;
import com.cs242.jharri39.src.heuristics.Heuristic.HeuristicChoice;


public class DijkstraSolver extends MazeSolver{

	
	public DijkstraSolver(int start, int finish, int width, int height,
			int[] walls, HeuristicChoice heuristic) {
		super(start, finish, width, height, walls);
		// TODO Auto-generated constructor stub
	}

	public DijkstraSolver(int start, int finish, int width, int height,
			int[] walls) {
		super(start, finish, width, height, walls);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * This method begins solving the maze using the Dijkstra's Algorithm
	 */
	@Override
	public void solveMaze(){
		
	}
	
}
