package com.cs242.jharri39.src;

/**
 * This class was made to hold all the information for a Node and make it seem
 * 
 * @author Joshua Harris
 */
public class MazeNode {
	
	private boolean isImpassable;
	private int parentNode;
	private int totalCost;
	private int movementCost;
	private double heuristicCost;
	private int position;
	private boolean isVisited;
	
	public MazeNode(){
		isImpassable = false;
		parentNode = -1;
		position = -1;
		totalCost = 0;
		movementCost = 0;
		heuristicCost = 0;
		isVisited = false;
	}

	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

	public int getPosition() {
		return position;
	}
	
	public void setPosition(int position){
		this.position = position;
	}

	public boolean isImpassable() {
		return isImpassable;
	}

	public void setImpassable(boolean isImpassable) {
		this.isImpassable = isImpassable;
	}

	public int getParentNode() {
		return parentNode;
	}

	public void setParentNode(int parentNode) {
		this.parentNode = parentNode;
	}

	public int getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	public int getMovementCost() {
		return movementCost;
	}

	public void setMovementCost(int movementCost) {
		this.movementCost = movementCost;
	}

	public double getHeuristicCost() {
		return heuristicCost;
	}

	public void setHeuristicCost(double d) {
		this.heuristicCost = d;
	}
	
	
}