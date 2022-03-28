package com.soccer.league.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soccer.league.constants.ApplicationConstants;
import com.soccer.league.exception.ApplicationException;
import com.soccer.league.model.PlayGameUtilityBean;
import com.soccer.league.model.SeccorSeasonsData;
import com.soccer.league.service.JsonFileReadService;
import com.soccer.league.service.PlanSoccerGameService;
import com.soccer.league.util.PlanGameUtlity;

@Component
public class SoccerGameController {
	
	private static final Logger logger = (Logger) LogManager.getLogger(SoccerGameController.class);
	
	@Autowired
	private PlanSoccerGameService planSoccerGameService;
	
	@Autowired
	private JsonFileReadService jsonFileReadService;
	
	@Autowired
	private PlanGameUtlity planGameUtlity;
	

	//String startDateForRound = "05-03-2022";  
	//String fileName = "soccer_teams.json";
	
	
	List<String> gameDatesList = null;
	int noOfDaysForRound;
	
	
	public void planSoccerGameForSean(String startDateForRound,String fileName) {
			logger.info("Enter planSoccerGameForSean for start date : "+ startDateForRound + " and json file : "+fileName);
			
			if(isValidDate(startDateForRound)) {
				if(isDaySaturday(startDateForRound)) {
					try {
						SeccorSeasonsData seccorSeasonsData = (SeccorSeasonsData) jsonFileReadService.readFileData(fileName);
						
						if(null != seccorSeasonsData) {
							List<ArrayList<String>> teamPairsForRounds = planSoccerGameService.createTeamPairs(seccorSeasonsData.getTeams());
							if(null != teamPairsForRounds && !teamPairsForRounds.isEmpty()) {
								
								for(int i=0 ; i < teamPairsForRounds.size() ; i ++) {
									if(ApplicationConstants.NO_OF_GAMES_PER_DAY != 0) {
										noOfDaysForRound = (int) Math.ceil(teamPairsForRounds.get(i).size()/ApplicationConstants.NO_OF_GAMES_PER_DAY);
									}
									gameDatesList =planGameUtlity.getDatesForRounds(new PlayGameUtilityBean(startDateForRound,noOfDaysForRound,
																							i,ApplicationConstants.NEXT_ROUND_AFTER_NO_OF_DAYS));
									
									Map<String, List<String>> schedule = planSoccerGameService.createGameSchedule(teamPairsForRounds.get(i), gameDatesList);
									if(null != schedule) {
										System.out.println("************************** Round_"+(i+1)+"******************************");
										planGameUtlity.displayGameSchedule(schedule);
									}
									startDateForRound = gameDatesList.get(noOfDaysForRound-1);
								}
							
							}else {
								throw new ApplicationException("No Teams Found");
							}
						}else {
							throw new ApplicationException("No Data Found");
						}
					}catch(Exception e) {
						e.printStackTrace();
						throw new ApplicationException(e.getMessage());
					}
				}else {
					throw new ApplicationException("Start date is not saturday");
				}
			}else {
				throw new ApplicationException("Invalid Start Date");
			}
			
			
	}
	
	public boolean isValidDate(String date) {
		SimpleDateFormat format = new SimpleDateFormat(ApplicationConstants.DATE_FORMAT);
		try {
			format.parse(date);
		}catch (ParseException e) {
			return false;
		}
		return true;
	}
	
	public boolean isDaySaturday(String date) {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(new SimpleDateFormat(ApplicationConstants.DATE_FORMAT).parse(date));
		} catch (ParseException e) {
			return false;
		}
		if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
			return true;
		else
			return false;
	}

}
