package com.soccer.league.model;

import org.springframework.stereotype.Component;

@Component
public class PlayGameUtilityBean {
	
	private String gameStartDate;
	private int noOfDaysForRound;
	private int roundNumber;
	private int nextRoundAfterNoOfDays;
	
	
	public PlayGameUtilityBean() {
	}
	
	public PlayGameUtilityBean(String gameStartDate, int noOfDaysForRound, int roundNumber, int nextRoundAfterNoOfDays) {
		super();
		this.gameStartDate = gameStartDate;
		this.noOfDaysForRound = noOfDaysForRound;
		this.roundNumber = roundNumber;
		this.nextRoundAfterNoOfDays = nextRoundAfterNoOfDays;
	}


	public String getGameStartDate() {
		return gameStartDate;
	}


	public void setGameStartDate(String gameStartDate) {
		this.gameStartDate = gameStartDate;
	}


	public int getNoOfDaysForRound() {
		return noOfDaysForRound;
	}


	public void setNoOfDaysForRound(int noOfDaysForRound) {
		this.noOfDaysForRound = noOfDaysForRound;
	}

	public int getRoundNumber() {
		return roundNumber;
	}


	public void setRoundNumber(int roundNumber) {
		this.roundNumber = roundNumber;
	}


	public int getNextRoundAfterNoOfDays() {
		return nextRoundAfterNoOfDays;
	}


	public void setNextRoundAfterNoOfDays(int nextRoundAfterNoOfDays) {
		this.nextRoundAfterNoOfDays = nextRoundAfterNoOfDays;
	}


	@Override
	public String toString() {
		return "PlayGameUtilityBean [gameStartDate=" + gameStartDate + ", noOfDaysForRound=" + noOfDaysForRound
				+ ", roundNumber=" + roundNumber + ", nextRoundAfterNoOfDays=" + nextRoundAfterNoOfDays + "]";
	}
	
	

}
