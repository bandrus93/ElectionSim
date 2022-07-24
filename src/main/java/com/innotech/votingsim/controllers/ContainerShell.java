package com.innotech.votingsim.controllers;

import javax.swing.*;

public class ContainerShell {
	private final JFrame mainView = new JFrame("Election Simulator");
	private final JTabbedPane tabbedPane = new JTabbedPane();
	private PopulationController populationController;
	private CandidateController candidateController;
	private ElectionController electionController;

	public JFrame getMainView() {
		return mainView;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public void addPopulationController(PopulationController uiController) {
		this.populationController = uiController;
	}

	public void addCandidateController(CandidateController uiController) {
		this.candidateController = uiController;
	}

	public void addElectionController(ElectionController uiController) {
		this.electionController = uiController;
	}
	
	public PopulationController getPopulationController() {
		return populationController;
	}

	public CandidateController getCandidateController() {
		return candidateController;
	}

	public ElectionController getElectionController() {
		return electionController;
	}
	
}
