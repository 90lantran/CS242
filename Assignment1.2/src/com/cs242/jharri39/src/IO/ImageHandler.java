package com.cs242.jharri39.src.IO;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import com.cs242.jharri39.src.MazeNode;
import com.cs242.jharri39.src.MazeSolver;

/**
 * This is class is to serve as the Image translation class to be used for 
 * with IO operations.
 * 
 * @author Joshua Harris
 */

public class ImageHandler {

	/**
	 * This function is responsible for creating the input needed to create a 
	 * mazeSolver object given some image file.
	 * 
	 * @param path - the string representation of the file path to the image.
	 * @return MazeSettings object that contains the parameters to create a 
	 * 	MazeSolver object
	 * @throws IOException - thrown only if the file path is incorrect.
	 */
	public static MazeSettings convertImageToMaze(String path) throws IOException{
		File input = new File(path);
		BufferedImage image;
		try {
			image = ImageIO.read(input);
		} catch (IOException e) {
			System.out.println("Path location: " + path + " is not a valid path.");
			throw e;
		}
		
		MazeSettings result = new MazeSettings();
		
		int width, height;
		width = image.getWidth();
		height = image.getHeight();
		
		result.WIDTH = width;
		result.HEIGHT = height;
		
		Color color = null;
		ArrayList<Integer> walls = new ArrayList<Integer>();
		// Go through image white spaces are registered as walls
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				color = new Color(image.getRGB(x, y));
				
				// Check for the start location
				if(result.START == -1 && color.equals(Color.BLACK)){
					result.START = (y * width + x);
				}
				
				if(color.equals(Color.WHITE)) {
					walls.add(y * width + x);
				}
			}
		}
		
		// copy over the walls information
		result.WALLS = new int[walls.size()];
		for(int i = 0; i < walls.size(); i++) {
			result.WALLS[i] = walls.get(i);
		}
		
		// finding the finish position
		int index = width * height;
		while(result.FINISH == -1 && index >= 0){
			index--; 
			color = new Color(image.getRGB(index % width, index / width));
			if(color.equals(Color.BLACK)) {
				result.FINISH = index;
			}
		}
		
		return result;
	}
	
	/**
	 * This function will write an image to the file path specified given a maze.
	 * Like before walls are white and normal space is black. A solution (if one 
	 * exists) is drawn in red.
	 * 
	 * @param maze - mazeSolver object of the maze to be drawn.
	 * @param path - path of where to save the maze to.
	 * @throws IOException - thrown only if the file path is incorrect.
	 */
	public static void convertMazeToImage(MazeSolver maze, String path) throws IOException{
		BufferedImage image = new BufferedImage(maze.getWIDTH(), maze.getHEIGHT(), 
				BufferedImage.TYPE_3BYTE_BGR);
		MazeNode[][] board = maze.getBOARD();
		for(int x = 0; x < maze.getWIDTH(); x++) {
			for(int y = 0; y < maze.getHEIGHT(); y++) {
				if(board[x][y].isImpassable()) {
					image.setRGB(x, y, Color.WHITE.getRGB());
				}
				else {
					image.setRGB(x, y, Color.BLACK.getRGB());
				}
			}
		}
		
		int current = maze.getFINISH();
		int x = current % maze.getWIDTH();
		int y = current / maze.getWIDTH();
		current = board[x][y].getParentNode();
		
		while(current != maze.getSTART() && current != -1){
			x = current % maze.getWIDTH();
			y = current / maze.getWIDTH();
			image.setRGB(x, y, Color.RED.getRGB());
			
			current = board[x][y].getParentNode();
		}
		
		File output = new File(path);
		try {
			ImageIO.write(image, "bmp", output);
		} catch (IOException e) {
			System.out.println("Path location: " + path + " is not a valid path.");
			throw e;
		}
	}
	
}
