package com.soccer.league;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.soccer.league.controller.SoccerGameController;

@SpringBootApplication
@ComponentScan("com.soccer.league")
public class SoccerLeagueApplication {
	
	
	public static void main(String[] args) throws Exception {
		ApplicationContext context= SpringApplication.run(SoccerLeagueApplication.class, args);
		
		SoccerGameController SoccerGameController = context.getBean(SoccerGameController.class);

		if(null != args) {
			SoccerGameController.planSoccerGameForSean(args[0],args[1]);
		}else {
			throw new Exception("Please provide game start date and json file name");
		}
		
	}

}
