package com.dojo.report;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dojo.model.Death;
import com.dojo.model.Match;
import com.dojo.model.Player;

public class GameStatistics {
	
	//For multiple Matchs
	private Map<Date, Match> matchMap;
	
	private static Match currentMatch;
	
	public static Match getCurrentMatch(){
		return currentMatch;
	}
	
	public void setCurrentMatch(Date startDate) {
		currentMatch = this.getMatch(startDate);
	}
	
	public GameStatistics(){
		this.loadDataResults();
	}
	
	public void loadDataResults() {
		try {
			File f = new File("resources/game.log");
			
			FileReader fileReader = new FileReader(f);
			BufferedReader br = new BufferedReader(fileReader);
		    try {
		        StringBuilder sb = new StringBuilder();
		        String line;
				line = br.readLine();	
					
				Match match = null;
		        while (line != null) {
		        	
		        	if(line.contains("New match")){
		        		this.initializeNewMatch(line);
		        	}
		        	
		        	if(GameStatistics.getCurrentMatch() != null){
		        		if(line.contains("killed")){
			        		this.addDeath(line);
			        	}
		        		if(line.contains("has ended")){
			        		this.endMatch(line);
			        	}
		        	}
		            line = br.readLine();
		        }
		    } finally {
		        br.close();
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void endMatch(String line) {
		Match match = GameStatistics.getCurrentMatch();
		
		Date dateLog = StringUtil.getDateLog(line);
		match.setEnd(dateLog);
		
		System.out.println("Match ended at ["+ StringUtil.sdf.format(GameStatistics.getCurrentMatch().getEnd())
				+"] with id ["+GameStatistics.getCurrentMatch().getMatchId()+"]");
	}

	private void addDeath(String line) {
		Match match = GameStatistics.getCurrentMatch();
		Date dateLog = StringUtil.getDateLog(line);
		
		String logInfo = StringUtil.getLogInfo(line);
		
		String[] split = logInfo.split(" ");
		
		Player player1 = match.addPlayer(split[0]);
		Player player2 = match.addPlayer(split[2]);
		
		if(!player1.getName().equals("<WORLD>")){
			match.addDeath(player1,player2,split[4],dateLog);
		}
	}

	/**
	 * Return new match that has been started
	 * @param line
	 * @return
	 */
	private Match initializeNewMatch(String line) {
		
		Date dateLog = StringUtil.getDateLog(line);
		this.newMatchReport(dateLog);
		setCurrentMatch(dateLog);
		
		String logInfo = StringUtil.getLogInfo(line);
		
		Match match = GameStatistics.getCurrentMatch();
		
		String[] split = logInfo.split(" ");
		match.setMatchId(Long.parseLong(split[2]));
		System.out.println("Match started at ["+ StringUtil.sdf.format(GameStatistics.getCurrentMatch().getStart())
				+"] with id ["+GameStatistics.getCurrentMatch().getMatchId()+"]");
		
		return match;
	}
	
	

	public void newMatchReport(Date startDate) {
		this.addMatch(startDate, new Match(startDate));
		this.setCurrentMatch(startDate);
	}

	public Match getMatch(Date startDate) {
		return this.getMatchMap().get(startDate);
	}
	
	public void setMatchMap(Map<Date, Match> matchMap) {
		this.matchMap = matchMap;
	}
	
	public Map<Date, Match> getMatchMap() {
		return matchMap;
	}
	
	public Map<Date, Match> addMatch(Date date, Match match) {
		if(this.getMatchMap() == null){
			this.setMatchMap(new HashMap<Date,Match>());
		}
		matchMap.put(date, match);
		return matchMap;
	}

	public int howManyDeath(String dead) {
		Set<Date> keySet = this.matchMap.keySet();
		
		int counter = 0;
		
		for (Date date : keySet) {
			Match match = this.matchMap.get(date);
			List<Death> deaths = match.getDeaths();
			
			for (Death death : deaths) {
				if(death.getDead().getName().equals(dead)){
					counter ++;
				}
			}
		}
		
		return counter;
	}
	
	public Integer numberOfDeath(Long matchId){
		
		Set<Date> keySet = this.matchMap.keySet();
		
		for (Date date : keySet) {
			Match match = this.matchMap.get(date);
			if(matchId.equals(match.getMatchId())){
				return match.getDeaths().size();
			}
		}
		
		return 0;
	}
}
