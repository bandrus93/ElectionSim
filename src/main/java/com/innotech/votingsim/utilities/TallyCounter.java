package com.innotech.votingsim.utilities;

import java.util.Comparator;

import com.innotech.votingsim.models.Candidate;

public class TallyCounter implements Comparator<Candidate> {

	@Override
	public int compare(Candidate o1, Candidate o2) {
		return (int)(o1.getTotalVotes() - o2.getTotalVotes());
	}

}
