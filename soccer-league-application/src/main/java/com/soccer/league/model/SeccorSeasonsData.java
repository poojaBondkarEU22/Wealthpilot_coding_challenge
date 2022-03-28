package com.soccer.league.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties
public class SeccorSeasonsData {
	
	private String league;
	private String country;
	private List<Teams> teams;
	
	public SeccorSeasonsData() {
	}

	public SeccorSeasonsData(String league, String country, List<Teams> teams) {
		super();
		this.league = league;
		this.country = country;
		this.teams = teams;
	}

	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<Teams> getTeams() {
		return teams;
	}

	public void setTeams(List<Teams> teams) {
		this.teams = teams;
	}

	@Override
	public String toString() {
		return "Season [league=" + league + ", country=" + country + ", teams=" + teams + "]";
	}
}
