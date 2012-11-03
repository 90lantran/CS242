package com.cs242.jharri39.tst;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cs242.jharri39.src.MazeNode;
import com.cs242.jharri39.src.MazeSolver;
import com.cs242.jharri39.src.heuristics.DiagonalDistance;
import com.cs242.jharri39.src.heuristics.EuclideanDistance;
import com.cs242.jharri39.src.heuristics.EuclideanSquaredDistance;
import com.cs242.jharri39.src.heuristics.Heuristic;
import com.cs242.jharri39.src.heuristics.ManhattanDistance;
import com.cs242.jharri39.src.heuristics.ManhattanDistanceWithTieBreaker;

public class HeuristicTests {
	
	private Heuristic heuristic;
	private MazeNode node;
	
	
	/**
	 * Setup method for tests 
	 */
	@Before
	public void setUp(){
		node = new MazeNode();
		node.setPosition(0);
	}
	
	/**
	 * Tests that the Manahattan Distance Heuristic is functioning properly
	 */
	@Test
	public void testManahattanDistance(){
		heuristic = new ManhattanDistance(10, 10, 28);
		
		heuristic.calculateDistance(node);
		
		assertFalse(node.getHeuristicCost() == 0);
		
		assertTrue("Result: " + node.getHeuristicCost(), node.getHeuristicCost() == 1000);
	}
	
	/**
	 * Tests that the Diagonal Distance Heuristic is functioning properly
	 */
	@Test
	public void testDiagonalDistance(){
		heuristic = new DiagonalDistance(10, 10, 28);
		
		heuristic.calculateDistance(node);
		
		assertFalse(node.getHeuristicCost() == 0);
		
		assertTrue("Result: " + node.getHeuristicCost(), node.getHeuristicCost() == 882);
	}
	
	/**
	 * Tests that the Euclidean Distance Heuristic is functioning properly
	 */
	@Test
	public void testEuclideanDistance(){
		heuristic = new EuclideanDistance(10, 10, 28);
		
		heuristic.calculateDistance(node);
		
		assertFalse("Result: " + node.getHeuristicCost(), node.getHeuristicCost() == 0);
		
		assertTrue("Result: " + node.getHeuristicCost(), node.getHeuristicCost() == (Math.sqrt(68)*100));
	}
	
	/**
	 * Tests that the Euclidean Squared Distance Heuristic is functioning 
	 * properly
	 */
	@Test
	public void testEuclideanSquaredDistance(){
		heuristic = new EuclideanSquaredDistance(10, 10, 28);
		
		heuristic.calculateDistance(node);
		
		assertFalse(node.getHeuristicCost() == 0);
		
		assertTrue("Result: " + node.getHeuristicCost(), node.getHeuristicCost() == 6800);
	}
	
	/**
	 * Tests that the Manahattan Distance with a Tie Breaker Heuristic is 
	 * functioning properly
	 */
	@Test
	public void testManahattanDistanceTieBreaker(){
		heuristic = new ManhattanDistanceWithTieBreaker(10, 10, 28);
		
		heuristic.calculateDistance(node);
		
		assertFalse(node.getHeuristicCost() == 0);
		
		assertTrue("Result: " + node.getHeuristicCost(), node.getHeuristicCost() == (1000 * 1.001));
	}
	
	/**
	 * This is a function I made in order to visualize a maze for testing.
	 * 0's are normal nodes
	 * #'s are walls
	 * S is the start
	 * F is the finish
	 * *'s should be the resulting path
	 * 
	 * @param maze
	 * @return String resultMaze
	 */
	public static String drawMaze(MazeSolver maze) {
		int width = maze.getWIDTH();
		int height = maze.getHEIGHT();
		int start = maze.getSTART();
		int finish = maze.getFINISH();
		MazeNode[][] board = maze.getBOARD();
		char[][] charBoard = new char[width][height];

		// For simplicity I am inserting the normal nodes and impassable nodes
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (board[x][y].isImpassable()) {
					charBoard[x][y] = '#';
				} else {
					charBoard[x][y] = '0';
				}
			}
		}
		
		// Setting the start and finish nodes
		if (start != -1) {
			int x = start % width;
			int y = start / width;
			
			charBoard[x][y] = 'S';
		}
		
		if (finish != -1) {
			int x = finish % width;
			int y = finish / width;
			
			charBoard[x][y] = 'F';
			
			//Insert the resulting path if it exists
			int parent = board[x][y].getParentNode();
			while(parent != -1 && parent != start) {
				x = parent % width;
				y = parent / width;
				charBoard[x][y] = '*';
				parent = board[x][y].getParentNode();
			}
		}
	
		//Create the resulting String
		String resultMaze = new String();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				resultMaze += charBoard[x][y];
			}
			resultMaze += "\n";
		}
		
		return resultMaze;
	}

}
