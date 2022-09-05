package com.innotech.votingsim.subroutines;

import com.innotech.votingsim.models.Campaign;
import com.innotech.votingsim.models.Candidate;
import com.innotech.votingsim.models.Population;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public abstract class ElectionMethod {
    protected Campaign campaign;
    protected final PriorityQueue<Candidate> leaderboard;
    protected final HashMap<Candidate, Double> radLeftPool;
    protected final HashMap<Candidate, Double> modLeftPool;
    protected final HashMap<Candidate, Double> centerPool;
    protected final HashMap<Candidate, Double> modRightPool;
    protected final HashMap<Candidate, Double> radRightPool;
    protected Double radLeftBound = 0.00;
    protected Double modLeftBound = 0.00;
    protected Double centerBound = 0.00;
    protected Double modRightBound = 0.00;
    protected Double radRightBound = 0.00;

    public ElectionMethod(Campaign campaign) {
        this.campaign = campaign;
        this.leaderboard = new PriorityQueue<>(new TallyCounter());
        this.radLeftPool = new HashMap<>();
        this.modLeftPool = new HashMap<>();
        this.centerPool = new HashMap<>();
        this.modRightPool = new HashMap<>();
        this.radRightPool = new HashMap<>();
    }

    protected void pollVoters() {
        for(int i = 0; i < campaign.getCandidates().size(); i++) {
            Candidate candidate = campaign.getCandidates().get(i);
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
    }

    protected PriorityQueue<Candidate> tallyVotes(Population pop) {
        if(!radLeftPool.isEmpty()) {
            for (Map.Entry<Candidate, Double> eligible : radLeftPool.entrySet()) {
                Double approvalRating = eligible.getValue() / radLeftBound;
                eligible.getKey().countVotes(Math.round(pop.getRadLeft() * approvalRating));
            }
        }
        if(!modLeftPool.isEmpty()) {
            for (Map.Entry<Candidate, Double> eligible : modLeftPool.entrySet()) {
                Double approvalRating = eligible.getValue() / modLeftBound;
                eligible.getKey().countVotes(Math.round(pop.getModLeft() * approvalRating));
            }
        }
        if(!centerPool.isEmpty()) {
            for (Map.Entry<Candidate, Double> eligible : centerPool.entrySet()) {
                Double approvalRating = eligible.getValue() / centerBound;
                eligible.getKey().countVotes(Math.round(pop.getCenter() * approvalRating));
            }
        }
        if(!modRightPool.isEmpty()) {
            for (Map.Entry<Candidate, Double> eligible : modRightPool.entrySet()) {
                Double approvalRating = eligible.getValue() / modRightBound;
                eligible.getKey().countVotes(Math.round(pop.getModRight() * approvalRating));
            }
        }
        if(!radRightPool.isEmpty()) {
            for (Map.Entry<Candidate, Double> eligible : radRightPool.entrySet()) {
                Double approvalRating = eligible.getValue() / radRightBound;
                eligible.getKey().countVotes(Math.round(pop.getRadRight() * approvalRating));
            }
        }
        leaderboard.addAll(campaign.getCandidates());
        return leaderboard;
    }

    public abstract PriorityQueue<Candidate> runElection();

    public static class TallyCounter implements Comparator<Candidate> {

        @Override
        public int compare(Candidate o1, Candidate o2) {
            return (int)(o1.getTotalVotes() - o2.getTotalVotes());
        }

    }
}
