package com.cs242.jharri39.tst;

import org.junit.Test;
import static org.mockito.Mockito.*;


import com.cs242.jharri39.src.AStarSolver;
import com.cs242.jharri39.src.DijkstraSolver;

public class MockTests {

	@Test
	public void testAStarSolver(){
		AStarSolver solver = mock(AStarSolver.class);
		
		solver.solveMaze();
		solver.calculateAndSetMovementCost(null, null);
		solver.calculateDistanceToFinish(null);
		solver.calculateMovementCost(null, null);
		solver.calculateTotalCost(null);
		
		verify(solver).solveMaze();
		verify(solver).calculateAndSetMovementCost(null, null);
		verify(solver).calculateDistanceToFinish(null);
		verify(solver).calculateMovementCost(null, null);
		verify(solver).calculateTotalCost(null);
	}
	
	@Test
	public void testDijstraSolver(){
		DijkstraSolver solver = mock(DijkstraSolver.class);
		
		solver.solveMaze();
		
		verify(solver).solveMaze();
	}
}
