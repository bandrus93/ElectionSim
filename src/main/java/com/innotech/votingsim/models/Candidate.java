package com.innotech.votingsim.models;

public class Candidate {
	private String name = "";
	private String alignment = "";
	private Integer energy = 0;
	private Integer intelligence = 0;
	private Integer wit = 0;
	private Integer levelHeadedness = 0;
	private Integer speakAbility = 0;
	private Double swayScore;
	private Long totalVotes = 0L;
	
	public Candidate() {
		
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

	public boolean checkNull() {
		if (name.equals("")) {
			return false;
		} else if (alignment.equals("")) {
			return false;
		} else if (energy == 0) {
			return false;
		} else if (intelligence == 0) {
			return false;
		} else if (wit == 0) {
			return false;
		} else if (levelHeadedness == 0) {
			return false;
		} else return speakAbility != 0;
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
}
