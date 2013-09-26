package com.dojo.report;

import static org.junit.Assert.*;
import org.junit.Test;

public class GameStatisticsTest {

	@Test
	public void testNumberOfDeath(){
		GameStatistics gameStatistics = new GameStatistics();
		
		assertTrue(5 == gameStatistics.numberOfDeath(11348965l));
		assertTrue(4 == gameStatistics.numberOfDeath(8573948l));
		assertTrue(4 == gameStatistics.numberOfDeath(19898974l));
		
		assertTrue(2 == gameStatistics.howManyDeath("Bart"));
		assertTrue(0 == gameStatistics.howManyDeath("Roman"));
		
	}
}
