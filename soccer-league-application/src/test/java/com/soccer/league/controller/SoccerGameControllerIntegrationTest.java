package com.soccer.league.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
class SoccerGameControllerIntegrationTest {
	
	private SoccerGameController soccerGameController;
	
	@BeforeEach
	void init() {
		soccerGameController = new SoccerGameController();
	}

	
	@Test
	void shouldBeInValidDateFormat() {
		String date = "05-03-2022";
		assertEquals(true, soccerGameController.isValidDate(date),"Invalid Date");
	}
	
	@Test
	void shouldBeSaturday() {
		String date = "05-03-2022";
		assertEquals(true, soccerGameController.isDaySaturday(date),"Should be saturday");
	}

}
