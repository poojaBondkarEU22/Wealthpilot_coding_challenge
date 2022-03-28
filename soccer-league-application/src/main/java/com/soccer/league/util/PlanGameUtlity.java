package com.soccer.league.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.stereotype.Component;

import com.soccer.league.constants.ApplicationConstants;
import com.soccer.league.model.PlayGameUtilityBean;


@Component
public class PlanGameUtlity {
	
	private static final Logger logger = (Logger) LogManager.getLogger(PlanGameUtlity.class);
	
	public void displayGameSchedule(Map<String,List<String>> data) {
		
		if(null != data) {
			System.out.println("--------------------------------------------------------------------------");
			System.out.printf("%-15s %-30s %-30s\n", "Date", "Team 1", "Team 2");  
			System.out.println("--------------------------------------------------------------------------");
			for(Map.Entry<String, List<String>> map :  data.entrySet()) {
				for(String str :  map.getValue()) {
					String[] arr = str.split("-");
					System.out.printf("%-15s %-30s %-30s\n", map.getKey(),arr[0] ,arr[1] );
				}
			}
			System.out.println("--------------------------------------------------------------------------");
			
		}
		
	}
	
	
	
	public List<String> getDatesForRounds(PlayGameUtilityBean playGameUtilityBean) throws Exception {
		
		//logger.debug("Enter getDatesForRounds for start date : "+ playGameUtilityBean.getGameStartDate());
		
		LocalDate inputDate = null;
		List<String> dateList = new ArrayList<String>();
		String dateToBeUsed = null;
		
		if(null != playGameUtilityBean.getGameStartDate()) {
			
			if(playGameUtilityBean.getRoundNumber() != 0) {
				//logger.debug("No of days to added for next round  is "+ playGameUtilityBean.getNextRoundAfterNoOfDays());
				dateToBeUsed = LocalDate.parse(playGameUtilityBean.getGameStartDate(),
												DateTimeFormatter.ofPattern(ApplicationConstants.DATE_FORMAT))
										.plusDays(playGameUtilityBean.getNextRoundAfterNoOfDays())
										.format(DateTimeFormatter.ofPattern(ApplicationConstants.DATE_FORMAT));
			}else {
				dateToBeUsed =  playGameUtilityBean.getGameStartDate();
			}
			
			dateList.add(0, dateToBeUsed);
			if(playGameUtilityBean.getNoOfDaysForRound() != 0) {
				for(int i =1  ; i< playGameUtilityBean.getNoOfDaysForRound() ; i++) {
					inputDate = LocalDate.parse(dateToBeUsed, DateTimeFormatter.ofPattern(ApplicationConstants.DATE_FORMAT));
					String nextSaturday  = inputDate.with(TemporalAdjusters.next(DayOfWeek.SATURDAY))
											.format(DateTimeFormatter.ofPattern(ApplicationConstants.DATE_FORMAT)).toString();
					
					
					dateList.add(nextSaturday);
					dateToBeUsed = nextSaturday;
				}
			}else {
				throw new Exception("Invalid number of days for a round");
			}
		}else {
			throw new Exception("Game start date not found"); 
		}
		return dateList;
	}

}
