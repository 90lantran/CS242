package com.cs242.jharri39.tst;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({AStarSolverTests.class, DijkstraSolverTests.class, 
	HeuristicTests.class})
public class AllTests {
	
}
