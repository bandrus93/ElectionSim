package com.innotech.votingsim.models;

import java.util.ArrayList;

public class Candidate {
	public static ArrayList<Candidate> candidates = new ArrayList<>();
	private String name;
	private String alignment;
	private Integer energy;
	private Integer intelligence;
	private Integer wit;
	private Integer levelHeadedness;
	private Integer speakAbility;
	private Double swayScore;
	private Long totalVotes = 0L;
	
	public Candidate() {
		
	}
	
	public Candidate(Candidate clone) {
		this.name = clone.getName();
		this.alignment = clone.getAlignment();
		this.energy = clone.getEnergy();
		this.intelligence = clone.getIntelligence();
		this.wit = clone.getWit();
		this.levelHeadedness = clone.getLevelHeadedness();
		this.speakAbility = clone.getSpeakAbility();
		this.swayScore = clone.getSwayScore();
	}

	public String getName() {
		return name;
	}

	public String getAlignment() {
		return alignment;
	}

	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}

	public Integer getEnergy() {
		return energy;
	}

	public Integer getIntelligence() {
		return intelligence;
	}

	public Integer getWit() {
		return wit;
	}

	public Integer getLevelHeadedness() {
		return levelHeadedness;
	}

	public Integer getSpeakAbility() {
		return speakAbility;
	}
	
	public Double getSwayScore() {
		return this.swayScore;
	}
	
	public void computeSwayScore() {
		this.swayScore = (this.energy * .15) + (this.intelligence * .25) + (this.wit * .35) + (this.levelHeadedness * .1) + (this.speakAbility * .15);
	}

	public Long getTotalVotes() {
		return totalVotes;
	}
	
	public void setTotalVotes(Long totalVotes) {
		this.totalVotes = totalVotes;
	}

	public void countVotes(Long segmentVotes) {
		this.totalVotes += segmentVotes;
	}
	
	public boolean checkSum() {
		return this.alignment != null && this.energy != null && this.intelligence != null && this.levelHeadedness != null && this.name != null && this.speakAbility != null && this.wit != null;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEnergy(Integer energy) {
		this.energy = energy;
	}

	public void setIntelligence(Integer intelligence) {
		this.intelligence = intelligence;
	}

	public void setWit(Integer wit) {
		this.wit = wit;
	}

	public void setLevelHeadedness(Integer levelHeadedness) {
		this.levelHeadedness = levelHeadedness;
	}

	public void setSpeakAbility(Integer speakAbility) {
		this.speakAbility = speakAbility;
	}

}
