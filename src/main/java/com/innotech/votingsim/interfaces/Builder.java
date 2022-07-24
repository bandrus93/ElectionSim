package com.innotech.votingsim.interfaces;

import com.innotech.votingsim.controllers.CandidateController;
import com.innotech.votingsim.controllers.ElectionController;
import com.innotech.votingsim.controllers.PopulationController;

public interface Builder {
    void addPopulationController(PopulationController popController);
    void addCandidateController(CandidateController candController);
    void addElectionController(ElectionController electController);
}
