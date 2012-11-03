package com.jharri39.cs242Assignment1.MazeTst;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.jharri39.cs242Assignment1.MazeSrc.AStarSolver;
import com.jharri39.cs242Assignment1.MazeSrc.MazeNode;
import com.jharri39.cs242Assignment1.MazeSrc.MazeSolver;

/**
 * This is the test suite for my Maze Solving Library
 * @author Joshua Harris
 */
public class MazeTests {
	
	/*
	 * The following strings are the expected results of some of the tests
	 * after the drawMaze method is called.
	 */
	private String answerMaze1 = 
			"S0000\n" +
			"*#000\n" +
			"0*##0\n" +
			"00*##\n" +
			"000*F\n";
	
	private String answerMaze2 = 
			"S#000\n" +
			"##00F\n";
	
	private String answerMaze3 = 
			"0S0####\n" +
			"00###00\n" +
			"#000##0\n" +
			"###0F0#\n";
	
	private String answerMaze4 = 
			"0S0####\n" +
			"0*###00\n" +
			"#0*0##0\n" +
			"###*F0#\n";
	
	/**
	 * Test the constructor for a MazeNode Object
	 */
	@Test
	public void MazeNodeConstructorTest() {
		MazeNode node = new MazeNode();
		
		assertTrue(node.getHeuristicCost() == 0);
		assertTrue(node.getMovementCost() == 0);
		assertTrue(node.getParentNode() == -1);
		assertTrue(node.getPosition() == -1);
		assertTrue(node.getTotalCost() == 0);
		assertFalse(node.isImpassable());
	}
	
	/**
	 * Test the constructor for a normal MazeSolver Object
	 */
	@Test
	public void MazeSolverConstructorTest(){
		int[] wallArray3 = {3, 4, 5, 6, 9, 10, 11, 14, 18, 19, 21, 22, 23, 27};
		MazeSolver mazeSolver3 = new MazeSolver(1, 25, 7, 4, wallArray3);
		
		String resultingMaze3 = drawMaze(mazeSolver3);
		assertTrue("\n" + resultingMaze3 + "\n" + answerMaze3 + "Mazes do not Match", resultingMaze3.equals(answerMaze3));
	}
	
	/**
	 * Test the constructor for a normal AStarSolver Object
	 */
	@Test
	public void AStarSolverConstructorTest(){
		int[] wallArray3 = {3, 4, 5, 6, 9, 10, 11, 14, 18, 19, 21, 22, 23, 27};
		MazeSolver mazeSolver3 = new AStarSolver(1, 25, 7, 4, wallArray3);
		
		String resultingMaze3 = drawMaze(mazeSolver3);
		assertTrue("\n" + resultingMaze3 + "\n" + answerMaze3 + "Mazes do not Match", resultingMaze3.equals(answerMaze3));
	}
	
	/**
	 * Tests Solving a maze using the A* Algorithm on a normal maze
	 */
	@Test
	public void SolveMaze1() {
		int[] wallArray1 = {6, 12, 13, 18, 19};
		MazeSolver mazeSolver1 = new AStarSolver(0, 24, 5, 5, wallArray1);

		mazeSolver1.SolveMaze();
		
		String resultingMaze1 = drawMaze(mazeSolver1);
		assertTrue("Result:\n" + resultingMaze1 + "\nExpected:\n" + answerMaze1,
				resultingMaze1.equals(answerMaze1));
	}
	
	/**
	 * Tests Solving a maze using the A* Algorithm on a Maze with no solution
	 */
	@Test
	public void SolveMaze2() {
		int[] wallArray2 = {1, 5, 6};
		MazeSolver mazeSolver2 = new AStarSolver(0, 9, 5, 2, wallArray2);
		
		mazeSolver2.SolveMaze();
		
		String resultingMaze2 = drawMaze(mazeSolver2);
		assertTrue("Result:\n" + resultingMaze2 + "\nExpected:\n" + answerMaze2,
				resultingMaze2.equals(answerMaze2));
	}
	
	/**
	 * Tests Solving a maze using the A* Algorithm on a 7 x 4 Maze
	 */
	@Test
	public void SolveMaze3() {
		int[] wallArray3 = {3, 4, 5, 6, 9, 10, 11, 14, 18, 19, 21, 22, 23, 27};
		MazeSolver mazeSolver3 = new MazeSolver(1, 25, 7, 4, wallArray3);
		
		mazeSolver3.SolveMaze();
		
		String resultingMaze3 = drawMaze(mazeSolver3);
		assertTrue("Result:\n" + resultingMaze3 + "\nExpected:\n" + answerMaze4,
				resultingMaze3.equals(answerMaze4));
	}
	
	/**
	 * This tests that the findNeighbor Function works correctly
	 */
	@Test
	public void findNeighborTest1(){
		AStarSolver solver = new AStarSolver(0, 24, 5, 5, null);
		
		MazeNode[][] board = solver.getBOARD();
		ArrayList<MazeNode> resultingNodes = solver.FindNeighborNodes(board[2][2]);
		
		if (resultingNodes.size() != 8){
			fail("Resulting list of nodes contained the inncorrect amount of Nodes.\n" +
					"Expected: 8    Result was: " + resultingNodes.size());
					
		}
	}
	
	/**
	 * This tests that the findNeighbor Function works correctly but is slightly harder
	 * than the previous test
	 */
	@Test
	public void findNeighborTest2(){
		int[] wallArray3 = {3, 4, 5, 6, 9, 10, 11, 14, 18, 19, 21, 22, 23, 27};
		AStarSolver mazeSolver3 = new AStarSolver(1, 25, 7, 4, wallArray3);
		
		MazeNode[][] board = mazeSolver3.getBOARD();
		ArrayList<MazeNode> resultingNodes = mazeSolver3.FindNeighborNodes(board[3][2]);
		
		if (resultingNodes.size() != 3){
			fail("Resulting list of nodes contained the inncorrect amount of Nodes.\n" +
					"Expected: 3    Result was: " + resultingNodes.size());
					
		}
	}

	/**
	 * Tests if the movement cost is calculated correctly for nodes that are adjacent
	 */
	@Test
	public void MovementCostNorth(){
		AStarSolver solver = new AStarSolver(0, 24, 5, 5, null);
		
		MazeNode[][] board = solver.getBOARD();
		
		board[2][2].setMovementCost(200);
		solver.CalculateMovementCost(board[2][2], board[2][1]);
		
		assertTrue("Expected: 300   Result: " + board[2][1].getMovementCost(), board[2][1].getMovementCost() == 300);
	}
	
	/**
	 * Tests if the movement cost is calculated correctly for nodes that are adjacent
	 */
	@Test
	public void MovementCostEast(){
		AStarSolver solver = new AStarSolver(0, 24, 5, 5, null);
		
		MazeNode[][] board = solver.getBOARD();
		
		board[2][2].setMovementCost(200);
		solver.CalculateMovementCost(board[2][2], board[3][2]);
		
		assertTrue("Expected: 300   Result: " + board[3][2].getMovementCost(), board[3][2].getMovementCost() == 300);
	}
	
	/**
	 * Tests if the movement cost is calculated correctly for nodes that are Diaganol
	 */
	@Test
	public void MovementCostNorthWest(){
		AStarSolver solver = new AStarSolver(0, 24, 5, 5, null);
		
		MazeNode[][] board = solver.getBOARD();
		
		board[2][2].setMovementCost(200);
		solver.CalculateMovementCost(board[2][2], board[1][1]);
		
		assertTrue("Expected: 341   Result: " + board[1][1].getMovementCost(), board[1][1].getMovementCost() == 341);
	}
	
	/**
	 * Tests if the movement cost is calculated correctly for nodes that are Diaganol
	 */
	@Test
	public void MovementCostSouthEast(){
		AStarSolver solver = new AStarSolver(0, 24, 5, 5, null);
		
		MazeNode[][] board = solver.getBOARD();
		
		board[2][2].setMovementCost(200);
		solver.CalculateMovementCost(board[2][2], board[3][3]);
		
		assertTrue("Expected: 341   Result: " + board[3][3].getMovementCost(), board[3][3].getMovementCost() == 341);
	}
	
	/**
	 * Tests if the Heuristic for estimating distance to the finish is correct using the Manhattan Distance
	 */
	@Test
	public void HeuristicCalculationTest1(){
		int[] wallArray3 = {3, 4, 5, 6, 9, 10, 11, 14, 18, 19, 21, 22, 23, 27};
		AStarSolver mazeSolver3 = new AStarSolver(1, 25, 7, 4, wallArray3);
		
		MazeNode[][] board = mazeSolver3.getBOARD();
		
		mazeSolver3.CalculateDistanceToFinish(board[4][3]);
		
		assertTrue("Expexted: 10   Result: " + board[4][3].getHeuristicCost(), board[4][3].getHeuristicCost() == 10);
	}
	
	/**
	 * Tests if the Heuristic for estimating distance to the finish is correct using the Manhattan Distance
	 */
	@Test
	public void HeuristicCalculationTest2(){
		int[] wallArray3 = {3, 4, 5, 6, 9, 10, 11, 14, 18, 19, 21, 22, 23, 27};
		AStarSolver mazeSolver3 = new AStarSolver(1, 25, 7, 4, wallArray3);
		
		MazeNode[][] board = mazeSolver3.getBOARD();
		
		mazeSolver3.CalculateDistanceToFinish(board[1][2]);
		
		assertTrue("Expexted: 50   Result: " + board[1][2].getHeuristicCost(), board[1][2].getHeuristicCost() == 50);
	}
	
	/**
	 * Tests if the Total Cost is calculated correctly
	 */
	@Test
	public void TotalCostTest(){
		AStarSolver solver = new AStarSolver(0, 24, 5, 5, null);
		MazeNode[][] board = solver.getBOARD();

		board[2][2].setHeuristicCost(100);
		board[2][2].setMovementCost(200);
		
		solver.CalculateTotalCost(board[2][2]);
		
		assertTrue("Expected: 300   Result: " + board[2][2].getTotalCost(), board[2][2].getTotalCost() == 300);
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
