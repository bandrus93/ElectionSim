package com.innotech.votingsim.subroutines;

import com.innotech.votingsim.models.Campaign;
import com.innotech.votingsim.models.Candidate;

import java.util.Iterator;
import java.util.PriorityQueue;

public class InstantRunoffMethod extends ElectionMethod {

    public InstantRunoffMethod(Campaign campaign) {
        super(campaign);
    }

    @Override
    public PriorityQueue<Candidate> runElection() {
        int electRound = 1;
        boolean isCalled = false;
        while(!isCalled) {
            Object[] resultArray = this.tallyVotes(campaign.getDemographics()).toArray();
            Candidate winner = (Candidate)resultArray[resultArray.length - 1];
            if(100.0 * winner.getTotalVotes() / campaign.getDemographics().getTotalPop() <= 50) {
                Iterator<Candidate> eliminator = leaderboard.iterator();
                int place = campaign.getCandidates().size() - electRound;
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
        return leaderboard;
    }

}
