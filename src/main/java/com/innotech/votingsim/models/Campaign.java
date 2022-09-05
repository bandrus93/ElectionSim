package com.innotech.votingsim.models;

import java.util.ArrayList;

public class Campaign {
	private final Population demographics;
	private final ArrayList<Candidate> candidates;

	public Campaign(Population population) {
		this.demographics = population;
		this.candidates = new ArrayList<>();
	}

	public Population getDemographics() {
		return demographics;
	}

	public void addCandidate(Candidate candidate) {
		candidate.computeSwayScore();
		candidates.add(candidate);
	}

	public ArrayList<Candidate> getCandidates() {
		return candidates;
	}

}
