package com.cs242.jharri39.src.IO;

import java.io.IOException;
import java.util.Scanner;

import com.cs242.jharri39.src.AStarSolver;
import com.cs242.jharri39.src.DijkstraSolver;
import com.cs242.jharri39.src.MazeNode;
import com.cs242.jharri39.src.MazeSolver;
import com.cs242.jharri39.src.heuristics.DiagonalDistance;
import com.cs242.jharri39.src.heuristics.EuclideanDistance;
import com.cs242.jharri39.src.heuristics.EuclideanSquaredDistance;
import com.cs242.jharri39.src.heuristics.Heuristic;
import com.cs242.jharri39.src.heuristics.ManhattanDistance;
import com.cs242.jharri39.src.heuristics.ManhattanDistanceWithTieBreaker;

public class MazeIO {
	private MazeSettings mazeSettings;
	private int solverChoice;
	private MazeSolver solver;
	private Heuristic heuristic;
	
	public void readMaze(){
		System.out.println("Please enter the file location for the Maze: ");
		Scanner input = new Scanner(System.in);
		String path = input.nextLine();
		
		try {
			mazeSettings = ImageHandler.convertImageToMaze(path);
		} catch (IOException e) {
			this.readMaze();
			return;
		}
		
		
		boolean isInvalidOption = true;
		while(isInvalidOption) {
			System.out.println("Please Enter choice:\n1.A* Solver\n2. Dijkstra Solver");
			String choice = input.nextLine();
			if("1".equals(choice)){
				solverChoice = 1;
				isInvalidOption = false;
			}
			else if("2".equals(choice)){
				solverChoice = 2;
				isInvalidOption = false;
			}
		}
		
		isInvalidOption = true;
		while(isInvalidOption) {
			System.out.println("Please Enter choice:\n1. Diagonal Distance\n2. Euclidean Distance\n" +
					"3. Euclidean Squared Distance\n4. Manhattan Distance\n5.Manhattan Distance Tie Breaker");
			String choice = input.nextLine();
			if("1".equals(choice)){
				heuristic = new DiagonalDistance(mazeSettings.WIDTH, mazeSettings.HEIGHT, mazeSettings.FINISH);
				isInvalidOption = false;
			}
			else if("2".equals(choice)){
				heuristic = new EuclideanDistance(mazeSettings.WIDTH, mazeSettings.HEIGHT, mazeSettings.FINISH);
				isInvalidOption = false;
			}
			else if("3".equals(choice)){
				heuristic = new EuclideanSquaredDistance(mazeSettings.WIDTH, mazeSettings.HEIGHT, mazeSettings.FINISH);
				isInvalidOption = false;
			}
			else if("4".equals(choice)){
				heuristic = new ManhattanDistance(mazeSettings.WIDTH, mazeSettings.HEIGHT, mazeSettings.FINISH);
				isInvalidOption = false;
			}
			else if("5".equals(choice)){
				heuristic = new ManhattanDistanceWithTieBreaker(mazeSettings.WIDTH, mazeSettings.HEIGHT, mazeSettings.FINISH);
				isInvalidOption = false;
			}
		}
		
		switch(solverChoice){
			case 1:
				solver = new AStarSolver(mazeSettings.START, mazeSettings.FINISH, mazeSettings.WIDTH, 
						mazeSettings.HEIGHT, mazeSettings.WALLS);
				solver.setHeuristic(heuristic);
				break;
			case 2:
				solver = new DijkstraSolver(mazeSettings.START, mazeSettings.FINISH, mazeSettings.WIDTH, 
						mazeSettings.HEIGHT, mazeSettings.WALLS);
				solver.setHeuristic(heuristic);
				break;
		}
	}
	
	public boolean isSolveable(){
		solver.solveMaze();
		MazeNode[][] board = solver.getBOARD();
		int x = solver.getFINISH() % solver.getWIDTH();
		int y = solver.getFINISH() / solver.getWIDTH();
		
		MazeNode finish = board[x][y];
		if(finish.getParentNode() != -1){
			return true;
		}
		
		return false;
	}
	
	public void solveMaze(){
		if(!isSolveable()) {
			System.out.println("Maze is not solveable, not output will be given.");
			return;
		}
		
		System.out.println("Please provide the destination for the solved maze: ");
		Scanner input = new Scanner(System.in);
		String path = input.nextLine();
		
		try {
			ImageHandler.convertMazeToImage(solver, path);
		} catch (IOException e) {
			this.solveMaze();
			return;
		}
	}
	
	public static void main(String [] args){
		MazeIO mazeIO = new MazeIO();
		System.out.println("Welcome to my Maze Solver!\nIf you want to quit type q!");
		
		while(true){
			System.out.println("1. Read Maze\n2. Solve Maze\nq. Quit");
			Scanner input = new Scanner(System.in);
			String choice = input.nextLine();
			switch(choice){
				case "1":
					mazeIO.readMaze();
					break;
				case "2":
					mazeIO.solveMaze();
					break;
				case "q":
					return;
				default:
					System.out.println("Input not recognized. Try Again.");
			}
		}
	}
	
	
}
