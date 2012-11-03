package com.cs242.jharri39.src;

import com.cs242.jharri39.src.MazeNode;

/**
 * This is the generic Maze Solver Class designed to be inherited and implement
 * the solveMaze() method
 * 
 * @author Joshua Harris
 * 
 */
public class MazeSolver {

	protected MazeNode[][] BOARD;
	protected final int START;
	protected final int FINISH;
	protected final int WIDTH;
	protected final int HEIGHT;
	protected final int[] WALLS;

	/**
	 * @param start
	 * @param finish
	 * @param width
	 * @param height
	 * @param walls
	 */
	public MazeSolver(int start, int finish, int width, int height, int[] walls) {
		START = start;
		FINISH = finish;
		WIDTH = width;
		HEIGHT = height;
		WALLS = walls;

		BOARD = new MazeNode[WIDTH][HEIGHT];
		for (int x = 0; x < WIDTH; x++) {
			for (int y = 0; y < HEIGHT; y++) {
				BOARD[x][y] = new MazeNode();
				BOARD[x][y].setPosition(y * WIDTH + x);
			}
		}

		// Set the flag to make the Nodes impassable if they are walls.
		if (WALLS != null) {
			for (int i = 0; i < WALLS.length; i++) {
				int wallPosition = WALLS[i];
				int x = wallPosition % WIDTH;
				int y = wallPosition / WIDTH;
				BOARD[x][y].setImpassable(true);
			}
		}
	}

	/**
	 * The main function to be overwritten
	 */
	public void solveMaze() {

	}

	public int getSTART() {
		return START;
	}

	public int getFINISH() {
		return FINISH;
	}

	public int getWIDTH() {
		return WIDTH;
	}

	public int getHEIGHT() {
		return HEIGHT;
	}

	public int[] getWALLS() {
		return WALLS;
	}

	public MazeNode[][] getBOARD() {
		return BOARD;
	}

}
