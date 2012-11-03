package com.cs242.jharri39.tst;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

import com.cs242.jharri39.src.AStarSolver;
import com.cs242.jharri39.src.MazeNode;
import com.cs242.jharri39.src.MazeSolver;
import com.cs242.jharri39.src.IO.ImageHandler;
import com.cs242.jharri39.src.IO.MazeSettings;
import com.cs242.jharri39.src.heuristics.Heuristic;
import com.cs242.jharri39.src.heuristics.Heuristic.HeuristicChoice;
import com.cs242.jharri39.src.heuristics.ManhattanDistance;

public class IOTests {

	@Test
	public void testSolveMaze() throws IOException{
		MazeSettings mazeInfo = ImageHandler.convertImageToMaze("c:/Users/Joshua/Desktop/maze0.bmp");
		AStarSolver maze = new AStarSolver(mazeInfo.START, mazeInfo.FINISH, mazeInfo.WIDTH, mazeInfo.HEIGHT, 
				mazeInfo.WALLS, HeuristicChoice.MANHATTAN_DISTANCE);
		File original = new File("c:/Users/Joshua/Desktop/orig.txt");
		String drwOrig = drawMaze(maze);
		BufferedWriter out = new BufferedWriter(new FileWriter(original));
		out.write(drwOrig);
		File solved = new File("c:/Users/Joshua/Desktop/final.txt");
		maze.solveMaze();
		String drwSolved = drawMaze(maze);
		out = new BufferedWriter(new FileWriter(solved));
		out.write(drwSolved);
		
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
