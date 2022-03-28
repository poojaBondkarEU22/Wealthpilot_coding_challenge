package com.soccer.league.service;


import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.soccer.league.model.SeccorSeasonsData;

@RunWith(JUnit4.class)
class JsonFileReadServiceTest {
	

	private JsonFileReadService jsonFileReadService;
	
	@BeforeEach
	void setUp() {
		jsonFileReadService = new JsonFileReadService();
	}


	@Test
	void shouldJsonFileOnly() {
		String fileName="soccer_teams.json";
		assertEquals(true, jsonFileReadService.isJsonFile(fileName));
	}
	
	@Test
	void shouldBeValidJson() {
		String fileName="soccer_teams.json";
		assertEquals(true, jsonFileReadService.isValidJsonData(fileName),"Invalid Json File");
	}
	
	@Test
	void testReadFileData() {
		String fileName="soccer_teams.json";
		SeccorSeasonsData  seasonData = (SeccorSeasonsData) jsonFileReadService.readFileData(fileName);
		assertNotNull("Season data should not be null", seasonData);
	}
	
	

}
