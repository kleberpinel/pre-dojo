package com.dojo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Player {
	private String name;
	private Map<Date, Death> deads;
	private List<String> guns;
	
	public Player(String playerName) {
		this.name = playerName;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof String){
			String string = (String) obj;
			if(this.name.equals(string)){
				return true;
			} else {
				return false;
			}
		}
		if(obj instanceof Player){
			Player player = (Player) obj;
			if(this.name.equals(player.getName())){
				return true;
			} else {
				return false;
			}
		}
		return super.equals(obj);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<Date, Death> getDeads() {
		return deads;
	}

	public void setDeads(Map<Date, Death> deads) {
		this.deads = deads;
	}

	public List<String> getGuns() {
		if(guns == null){
			guns = new ArrayList<String>();
		}
		return guns;
	}

	public void setGuns(List<String> guns) {
		this.guns = guns;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	
}
