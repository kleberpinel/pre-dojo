package com.dojo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Match {
	//If this is CounterStriker game, we have to teams :)
	//private List<Player> cops;
	//private List<Player> terrorists;
	
	private List<Player> players;
	private Map<String, Integer> guns;
	private List<Death>deaths;
	private Date start;
	private Date end;
	private Long matchId;
	
	public Match(Date start){
		this.start = start;
	}
	
	public Date getStart() {
		return start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}

	public void setStart(Date start) {
		this.start = start;
	}
	
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	
	public List<Player> getPlayers() {
		if(players == null){
			players = new ArrayList<Player>();
		}
		return players;
	}
	
	public void setGuns(HashMap<String, Integer> guns) {
		this.guns = guns;
	}
	
	public Map<String, Integer> getGuns() {
		if(guns == null){
			guns = new HashMap<String, Integer>();
		}
		return guns;
	}
	
	public void setDeaths(List<Death> deaths) {
		this.deaths = deaths;
	}
	
	public List<Death> getDeaths() {
		if(deaths == null){
			deaths = new ArrayList<Death>();
		}
		return deaths;
	}

	/**
	 * Adding player to the Match
	 * @param playerName
	 * @return
	 */
	public Player addPlayer(String playerName) {
		Player player = null;
		if(!this.getPlayers().contains(playerName)){
			player = new Player(playerName);
			this.getPlayers().add(player);
		} else {
			player = this.getPlayers().get(this.getPlayers().indexOf(playerName));
		}
		return player;
	}

	/**
	 * Registring one death in the match
	 * @param killer
	 * @param dead
	 * @param gunName
	 */
	public void addDeath(Player killer, Player dead, String gunName, Date moment) {
		this.addGun(gunName);
		
		Death death = new Death(killer,dead,gunName,moment);

		killer.getGuns().add(gunName);
		
		getDeaths().add(death);
		
		System.out.println("["+killer+"] has killed ["+ dead +"] using [" + gunName + "]");
	}

	private void addGun(String gunName) {
		Integer integer = this.getGuns().get(gunName);
		if(integer == null){
			this.getGuns().put(gunName, 1);
		} else {
			this.getGuns().put(gunName, integer++);
		}
	}
	
	
	
}
