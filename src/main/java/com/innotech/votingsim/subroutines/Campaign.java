package com.innotech.votingsim.subroutines;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.PriorityQueue;

import org.springframework.stereotype.Component;

import com.innotech.votingsim.models.Candidate;
import com.innotech.votingsim.models.Population;
import com.innotech.votingsim.utilities.TallyCounter;

@Component
public class Campaign implements Runnable {
	private ElectionType electionType;
	private Population pop;
	private PriorityQueue<Candidate> leaderboard = new PriorityQueue<Candidate>(new TallyCounter());
	public enum ElectionType {
		PLURALITY,
		RUNOFF,
		APPROVAL
	}
	
	public Campaign() {
		
	}
	
	public Campaign(ElectionType type, Population pop) {
		this.electionType = type;
		this.pop = pop;
	}

	public PriorityQueue<Candidate> getElectionResult() {
		return leaderboard;
	}

	@Override
	public void run() {
		HashMap<Candidate, Double> radLeftPool = new HashMap<Candidate, Double>();
		HashMap<Candidate, Double> modLeftPool = new HashMap<Candidate, Double>();
		HashMap<Candidate, Double> centerPool = new HashMap<Candidate, Double>();
		HashMap<Candidate, Double> modRightPool = new HashMap<Candidate, Double>();
		HashMap<Candidate, Double> radRightPool = new HashMap<Candidate, Double>();
		Double radLeftBound = 0.00;
		Double modLeftBound = 0.00;
		Double centerBound = 0.00;
		Double modRightBound = 0.00;
		Double radRightBound = 0.00;
		for(int i = 0; i < Candidate.candidates.size(); i++) {
			Candidate candidate = Candidate.candidates.get(i);
			switch(candidate.getAlignment()) {
			case "Radical Left":
				radLeftPool.put(candidate, candidate.getSwayScore());
				radLeftBound += candidate.getSwayScore();
				if(candidate.getSwayScore() >= 8.3) {
					centerPool.put(candidate, candidate.getSwayScore() * .82);
					centerBound += (candidate.getSwayScore() * .82);
				} else if(candidate.getSwayScore() >= 8.05) {
					modLeftPool.put(candidate, candidate.getSwayScore() * .74);
					modLeftBound += (candidate.getSwayScore() * .74);
				}
				break;
			case "Moderate Left":
				modLeftPool.put(candidate, candidate.getSwayScore());
				modLeftBound += candidate.getSwayScore();
				radLeftPool.put(candidate, candidate.getSwayScore() * .74);
				radLeftBound += (candidate.getSwayScore() * .74);
				centerPool.put(candidate, candidate.getSwayScore() * .91);
				centerBound += (candidate.getSwayScore() * .91);
				if(candidate.getSwayScore() >= 8.05) {
					modRightPool.put(candidate, candidate.getSwayScore() * .61);
					modRightBound += (candidate.getSwayScore() * .61);
				}
				break;
			case "Center":
				centerPool.put(candidate, candidate.getSwayScore());
				centerBound += candidate.getSwayScore();
				modLeftPool.put(candidate, candidate.getSwayScore() * .87);
				modLeftBound += (candidate.getSwayScore() * .87);
				modRightPool.put(candidate, candidate.getSwayScore() * .87);
				modRightBound += (candidate.getSwayScore() * .87);
				if(candidate.getSwayScore() >= 7.45) {
					radLeftPool.put(candidate, candidate.getSwayScore() * .48);
					radLeftBound += (candidate.getSwayScore() * .48);
					radRightPool.put(candidate, candidate.getSwayScore() * .48);
					radRightBound += (candidate.getSwayScore() * .48);
				}
				break;
			case "Moderate Right":
				modRightPool.put(candidate, candidate.getSwayScore());
				modRightBound += candidate.getSwayScore();
				radRightPool.put(candidate, candidate.getSwayScore() * .74);
				radRightBound += (candidate.getSwayScore() * .74);
				centerPool.put(candidate, candidate.getSwayScore() * .91);
				centerBound += (candidate.getSwayScore() * .91);
				if(candidate.getSwayScore() >= 8.05) {
					modLeftPool.put(candidate, candidate.getSwayScore() * .61);
					modLeftBound += (candidate.getSwayScore() * .61);
				}
				break;
			case "Radical Right":
				radRightPool.put(candidate, candidate.getSwayScore());
				radRightBound += candidate.getSwayScore();
				if(candidate.getSwayScore() >= 8.3) {
					centerPool.put(candidate, candidate.getSwayScore() * .82);
					centerBound += (candidate.getSwayScore() * .82);
				} else if(candidate.getSwayScore() >= 8.05) {
					modRightPool.put(candidate, candidate.getSwayScore() * .74);
					modRightBound += (candidate.getSwayScore() * .74);
				}
				break;
			}
		}
		int electRound = 1;
		boolean isCalled = false;
		while(!isCalled) {
			if(!radLeftPool.isEmpty()) {
				Iterator<Entry<Candidate, Double>> radLeftIterator = radLeftPool.entrySet().iterator();
				while(radLeftIterator.hasNext()) {
					Entry<Candidate,Double> eligible = radLeftIterator.next();
					Double approvalRating = eligible.getValue() / radLeftBound;
					eligible.getKey().countVotes(Math.round(pop.getRadLeft() * approvalRating));
				}
			}
			if(!modLeftPool.isEmpty()) {
				Iterator<Entry<Candidate, Double>> modLeftIterator = modLeftPool.entrySet().iterator();
				while(modLeftIterator.hasNext()) {
					Entry<Candidate,Double> eligible = modLeftIterator.next();
					Double approvalRating = eligible.getValue() / modLeftBound;
					eligible.getKey().countVotes(Math.round(pop.getModLeft() * approvalRating));
				}
			}
			if(!centerPool.isEmpty()) {
				Iterator<Entry<Candidate, Double>> centerIterator = centerPool.entrySet().iterator();
				while(centerIterator.hasNext()) {
					Entry<Candidate,Double> eligible = centerIterator.next();
					Double approvalRating = eligible.getValue() / centerBound;
					eligible.getKey().countVotes(Math.round(pop.getCenter() * approvalRating));
				}
			}
			if(!modRightPool.isEmpty()) {
				Iterator<Entry<Candidate, Double>> modRightIterator = modRightPool.entrySet().iterator();
				while(modRightIterator.hasNext()) {
					Entry<Candidate,Double> eligible = modRightIterator.next();
					Double approvalRating = eligible.getValue() / modRightBound;
					eligible.getKey().countVotes(Math.round(pop.getModRight() * approvalRating));
				}
			}
			if(!radRightPool.isEmpty()) {
				Iterator<Entry<Candidate, Double>> radRightIterator = radRightPool.entrySet().iterator();
				while(radRightIterator.hasNext()) {
					Entry<Candidate,Double> eligible = radRightIterator.next();
					Double approvalRating = eligible.getValue() / radRightBound;
					eligible.getKey().countVotes(Math.round(pop.getRadRight() * approvalRating));
				}
			}
			for(int j = 0; j < Candidate.candidates.size(); j++) {
				leaderboard.add(Candidate.candidates.get(j));
			}
			Object[] resultArray = leaderboard.toArray();
			Candidate winner = (Candidate)resultArray[resultArray.length - 1];
			if(electionType == ElectionType.RUNOFF && winner.getTotalVotes() / pop.getTotalPop() <= 0.5) {
				Iterator<Candidate> eliminator = leaderboard.iterator();
				int place = Candidate.candidates.size() - electRound;
				while(eliminator.hasNext()) {
					Candidate onDeck = eliminator.next();
					if(place > 0) {
						onDeck.setTotalVotes(0L);
					} else {
						if(radLeftPool.containsKey(onDeck)) {
							radLeftPool.replace(onDeck, 0.0);
						}
						if(modLeftPool.containsKey(onDeck)) {
							modLeftPool.replace(onDeck, 0.0);
						}
						if(centerPool.containsKey(onDeck)) {
							centerPool.replace(onDeck, 0.0);
						}
						if(modRightPool.containsKey(onDeck)) {
							modRightPool.replace(onDeck, 0.0);
						}
						if(radRightPool.containsKey(onDeck)) {
							radRightPool.replace(onDeck, 0.0);
						}
					}
					place--;
				}
				leaderboard.clear();
				electRound++;
			} else {
				isCalled = true;
			}
		}
	}
}
