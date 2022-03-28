package com.soccer.league.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class Teams {
	
	private String name;
	private String founding_date;
	
	public Teams() {
		
	}

	public Teams(String name) {
		super();
		this.name = name;
	}

	public Teams(String name, String founding_date) {
		super();
		this.name = name;
		this.founding_date = founding_date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFounding_date() {
		return founding_date;
	}

	public void setFounding_date(String founding_date) {
		this.founding_date = founding_date;
	}

	@Override
	public String toString() {
		return "Teams [name=" + name + ", founding_date=" + founding_date + "]";
	}
}
