# WealthPoint Coding Challenge

Task is to build a system that  plans the games of the next season. 
It will receive daata from a json file, plan game schedule for the given teams.

## Content
- Problem statement
- Expected output
- Setup & Execution


# Problem Statement
1.	The wealthpilot soccer league is about to start. Your task is to write an application, that plans the games of the next season.
2.	The teams can be read from the file soccer.json
3.	When the plan for the season is created the application displays it in the console like that:
Date            Team 1         Team 2
11.12.2018  Erzgebirge Aue     Kreuther Fürth
18.12.2018  Holstein Kiel          Dynamo Dresden
4.	Die season starts on the 05.03.2022 
5.	Games are played on Saturdays 17:00
6.	After all, teams have played against each other they have a 3 weeks break.
7.	After the break, they play again, in the same order as in round 1, but at the opponent's home. So team 1 becomes team 2 and vice versa.
8.	The simple solution is to have one game each weekend. The advanced solution has as many games as possible.


### Input data

{
  "league": "Wealthpilot first league",
  "country": "Germany",
  "teams": [
    {
      "name": "DJ FC"
    },
    {
      "name": "1. FC Marco"
    },
  ]
}


### Expected output 
Date        Team 1    		Team 2
05.03.2022  DJ FC     		1. FC Marco
02.04.2022  1. FC Marco     DJ FC


# Setup
## Framework Requirements
  - JVM running on your local machine
  
### Running
	- go to jar location 
java -jar soccer-league-application-0.0.1-SNAPSHOT.jar 05-03-2022 soccer_teams.json

