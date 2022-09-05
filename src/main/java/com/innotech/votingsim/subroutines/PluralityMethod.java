package com.innotech.votingsim.subroutines;

import com.innotech.votingsim.models.Campaign;
import com.innotech.votingsim.models.Candidate;

import java.util.PriorityQueue;

public class PluralityMethod extends ElectionMethod {

    public PluralityMethod(Campaign campaign) {
        super(campaign);
    }

    @Override
    public PriorityQueue<Candidate> runElection() {
        pollVoters();
        return tallyVotes(campaign.getDemographics());
    }

}
