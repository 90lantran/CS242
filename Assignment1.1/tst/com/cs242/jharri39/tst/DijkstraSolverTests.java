package com.cs242.jharri39.tst;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.cs242.jharri39.src.AStarSolver;
import com.cs242.jharri39.src.DijkstraSolver;
import com.cs242.jharri39.src.MazeNode;
import com.cs242.jharri39.src.Heuristic.HeuristicChoice;
import com.cs242.jharri39.src.MazeSolver;

public class DijkstraSolverTests {
	private MazeSolver mazeSolver1;
	private MazeSolver mazeSolver2;
	private MazeSolver mazeSolver3;
	
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
	 * Before method to setup for following Tests.
	 */
	@Before
	public void setUp(){
		int[] wallArray1 = {6, 12, 13, 18, 19};
		mazeSolver1 = new DijkstraSolver(0, 24, 5, 5, wallArray1, HeuristicChoice.MANHATTAN_DISTANCE);
		
		int[] wallArray2 = {1, 5, 6};
		mazeSolver2 = new DijkstraSolver(0, 9, 5, 2, wallArray2, HeuristicChoice.MANHATTAN_DISTANCE);
		
		int[] wallArray3 = {3, 4, 5, 6, 9, 10, 11, 14, 18, 19, 21, 22, 23, 27};
		mazeSolver3 = new DijkstraSolver(1, 25, 7, 4, wallArray3, HeuristicChoice.MANHATTAN_DISTANCE);
	}
	
	/**
	 * Tests Solving a maze using the A* Algorithm on a normal maze
	 */
	@Test
	public void testSolveMaze1() {
		mazeSolver1.solveMaze();
		
		String resultingMaze1 = drawMaze(mazeSolver1);
		assertTrue("Result:\n" + resultingMaze1 + "\nExpected:\n" + answerMaze1,
				resultingMaze1.equals(answerMaze1));
		System.out.println(resultingMaze1);
	}
	
	/**
	 * Tests Solving a maze using the A* Algorithm on a Maze with no solution
	 */
	@Test
	public void testSolveMaze2() {
		mazeSolver2.solveMaze();
		
		String resultingMaze2 = drawMaze(mazeSolver2);
		assertTrue("Result:\n" + resultingMaze2 + "\nExpected:\n" + answerMaze2,
				resultingMaze2.equals(answerMaze2));
	}
	
	/**
	 * Tests Solving a maze using the A* Algorithm on a 7 x 4 Maze
	 */
	@Test
	public void testSolveMaze3() {
		mazeSolver3.solveMaze();
		
		String resultingMaze3 = drawMaze(mazeSolver3);
		assertTrue("Result:\n" + resultingMaze3 + "\nExpected:\n" + answerMaze4,
				resultingMaze3.equals(answerMaze4));
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
