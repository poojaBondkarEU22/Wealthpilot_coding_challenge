package com.soccer.league.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.stereotype.Component;

import com.soccer.league.constants.ApplicationConstants;
import com.soccer.league.exception.ApplicationException;
import com.soccer.league.model.Teams;

@Component
public class PlanSoccerGameService implements PlanGameService{
	
	private static final Logger logger = (Logger) LogManager.getLogger(PlanSoccerGameService.class);

	@Override
	public List<ArrayList<String>> createTeamPairs(List<Teams> teams) {
		//logger.debug("Enter createTeamPairs");
		List<ArrayList<String>> teamPairsForRounds= new ArrayList<ArrayList<String>>();
		if(null != teams  && !teams.isEmpty()) {
			ArrayList<String>  round1 = new ArrayList<String>();
			ArrayList<String>  round2 = new ArrayList<String>();
			for(int i=0 ; i < teams.size() ; i++) {
				for(int j = i+1 ; j < teams.size() ; j++) {
					round1.add(teams.get(i).getName()+"-"+teams.get(j).getName());
					round2.add(teams.get(j).getName()+"-"+teams.get(i).getName());
				}
			}
			teamPairsForRounds.add(round1);
			teamPairsForRounds.add(round2);
		}else {
			throw new ApplicationException("Teams not found to plan game");
		}
		//logger.debug("Exit createTeamPairs");
		return teamPairsForRounds;
		
	}

	@Override
	public Map<String, List<String>> createGameSchedule(List<String> teamList, List<String> dateList) {
		
		Map<String,List<String>> data= new LinkedHashMap<String, List<String>>();
		List<String> team = null;
		int gameCounter = 1,index=0;
		if(null != teamList && !teamList.isEmpty()
					&& null != dateList && !dateList.isEmpty()) {
			for(String strDate : dateList) {
				team = new ArrayList<>();
				while(gameCounter <= ApplicationConstants.NO_OF_GAMES_PER_DAY && index < teamList.size()) {
					team.add(teamList.get(index));
					index++;gameCounter++;
				}
				data.put(strDate, team);
				gameCounter=1;
			}
		}else {
			throw new ApplicationException("Failed to create game schedule");
		}
		
		return data;
	}

}
