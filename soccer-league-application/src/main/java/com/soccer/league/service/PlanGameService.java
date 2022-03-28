package com.soccer.league.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.soccer.league.model.Teams;

public interface PlanGameService {
	
	public List<ArrayList<String>> createTeamPairs(List<Teams> teams);
	
	public Map<String, List<String>> createGameSchedule(List<String> teamList, List<String> dateList) ;

}
