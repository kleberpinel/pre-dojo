package com.dojo.model;

import java.util.Date;

public class Death {
	
	private Player dead;
	private Player killer;
	private String gunUsed;
	private Date moment;
	
	public Death(Player killer, Player dead, String gunName, Date moment) {
		this.dead = dead;
		this.killer = killer;
		this.gunUsed = gunName;
		this.moment = moment;
	}

	public Player getDead() {
		return dead;
	}

	public void setDead(Player dead) {
		this.dead = dead;
	}

	public Player getKiller() {
		return killer;
	}

	public void setKiller(Player killer) {
		this.killer = killer;
	}

	public String getGunUsed() {
		return gunUsed;
	}

	public void setGunUsed(String gunUsed) {
		this.gunUsed = gunUsed;
	}

	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}
	
}
