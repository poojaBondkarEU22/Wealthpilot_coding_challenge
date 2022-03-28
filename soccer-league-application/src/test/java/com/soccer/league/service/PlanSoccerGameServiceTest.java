package com.soccer.league.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.soccer.league.model.Teams;
import com.soccer.league.util.PlanGameUtlity;
@RunWith(JUnit4.class)
class PlanSoccerGameServiceTest {

	
	private PlanSoccerGameService planSoccerGameService;
	private PlanGameUtlity planGameUtlity;
	
	Teams team1 = new Teams("Volksbank Kickers");
	Teams team2 = new Teams("Arminia Sparkasse");
	Teams team3 = new Teams("DJ FC");
	Teams team4 = new Teams("1. FC Marco");
	Teams team5 = new Teams("Borussia Helvetia");
	Teams team6 = new Teams("SC Graz");
	
	List<Teams> teamList = null;
	ArrayList<String>  round1 = null;
	ArrayList<String>  round2 = null;
	
	@BeforeEach
	void init() {
		
		planSoccerGameService = new PlanSoccerGameService();
		planGameUtlity = new PlanGameUtlity();
		
		teamList = Stream.of(team1,team2,team3,team4,team5,team6).collect(Collectors.toList());;
		
		round1 = new ArrayList<String>();
		round2 = new ArrayList<String>();
		
		round1.add(team1.getName()+"-"+team2.getName());
		round1.add(team1.getName()+"-"+team3.getName());
		round1.add(team1.getName()+"-"+team4.getName());
		round1.add(team1.getName()+"-"+team5.getName());
		round1.add(team1.getName()+"-"+team6.getName());
		round1.add(team2.getName()+"-"+team3.getName());
		round1.add(team2.getName()+"-"+team4.getName());
		round1.add(team2.getName()+"-"+team5.getName());
		round1.add(team2.getName()+"-"+team6.getName());
		round1.add(team3.getName()+"-"+team4.getName());
		round1.add(team3.getName()+"-"+team5.getName());
		round1.add(team3.getName()+"-"+team6.getName());
		round1.add(team4.getName()+"-"+team5.getName());
		round1.add(team4.getName()+"-"+team6.getName());
		round1.add(team5.getName()+"-"+team6.getName());
		
		
		round2.add(team2.getName()+"-"+team1.getName());
		round2.add(team3.getName()+"-"+team1.getName());
		round2.add(team4.getName()+"-"+team1.getName());
		round2.add(team5.getName()+"-"+team1.getName());
		round2.add(team6.getName()+"-"+team1.getName());
		round2.add(team3.getName()+"-"+team2.getName());
		round2.add(team4.getName()+"-"+team2.getName());
		round2.add(team5.getName()+"-"+team2.getName());
		round2.add(team6.getName()+"-"+team2.getName());
		round2.add(team4.getName()+"-"+team3.getName());
		round2.add(team5.getName()+"-"+team3.getName());
		round2.add(team6.getName()+"-"+team3.getName());
		round2.add(team5.getName()+"-"+team4.getName());
		round2.add(team6.getName()+"-"+team4.getName());
		round2.add(team6.getName()+"-"+team5.getName());
		
	}
	
	@Test
	void testCreateTeamPair() {
		
		List<ArrayList<String>> seasonRounds= new ArrayList<ArrayList<String>>();
		seasonRounds.add(round1);
		seasonRounds.add(round2);
		assertEquals(seasonRounds.size(), planSoccerGameService.createTeamPairs(teamList).size());
	}
	
	@Test
	void testRound1TeamPair() {
		assertTrue("Invalid Pairs", round1.containsAll(planSoccerGameService.createTeamPairs(teamList).get(0)));
	}
	
	@Test
	void testRound2TeamPair() {
		assertTrue("Invalid Pairs", round2.containsAll(planSoccerGameService.createTeamPairs(teamList).get(1)));
	}
	
	@Test
	void testCreateGameSchedule() {
		/* Assumption : no of games to be play on day is 3 .
		 * Can be change in ApplicationConstant File
		 */
		
		List<String> dateList = new ArrayList<String>();
		dateList.add("05-03-2022");dateList.add("12-03-2022");dateList.add("19-03-2022");dateList.add("26-03-2022");dateList.add("02-04-2022");
		Map<String,List<String>> data= new LinkedHashMap<String, List<String>>();
		List<String> day1List = Stream.of(round1.get(0),round1.get(1),round1.get(2)).collect(Collectors.toList());
		List<String> day2List = Stream.of(round1.get(3),round1.get(4),round1.get(5)).collect(Collectors.toList());
		List<String> day3List = Stream.of(round1.get(6),round1.get(7),round1.get(8)).collect(Collectors.toList());
		List<String> day4List = Stream.of(round1.get(9),round1.get(10),round1.get(11)).collect(Collectors.toList());
		List<String> day5List = Stream.of(round1.get(12),round1.get(13),round1.get(14)).collect(Collectors.toList());
		data.put(dateList.get(0),day1List);
		data.put(dateList.get(1),day2List);
		data.put(dateList.get(2),day3List);
		data.put(dateList.get(3),day4List);
		data.put(dateList.get(4),day5List);

		
		assertTrue("Invalid Game Schedule",data.equals(planSoccerGameService.createGameSchedule(round1,dateList)));
	}

}
