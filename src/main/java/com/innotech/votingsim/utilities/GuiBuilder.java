package com.innotech.votingsim.utilities;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.*;

import com.innotech.votingsim.controllers.*;
import com.innotech.votingsim.interfaces.Builder;

public class GuiBuilder implements Builder {
	public static final Component contentPaddingX = Box.createRigidArea(new Dimension(10,0));
	public static final Component contentPaddingY = Box.createRigidArea(new Dimension(0,10));
	public static final Component borderPaddingX = Box.createRigidArea(new Dimension(20,0));
	public static final Component borderPaddingY = Box.createRigidArea(new Dimension(0,20));
	private final ContainerShell voteSimGui = new ContainerShell();
	
	public GuiBuilder() {
		
	}

	@Override
	public void addPopulationController(PopulationController uiController) {
		voteSimGui.addPopulationController(uiController);
	}

	@Override
	public void addCandidateController(CandidateController uiController) {
		voteSimGui.addCandidateController(uiController);
	}

	@Override
	public void addElectionController(ElectionController uiController) {
		voteSimGui.addElectionController(uiController);
	}

	public ContainerShell build() {
		voteSimGui.getTabbedPane().addTab("Population", voteSimGui.getPopulationController().getPopPane());
		voteSimGui.getTabbedPane().addTab("Candidates", voteSimGui.getCandidateController().getCandPane());
		voteSimGui.getTabbedPane().addTab("Election Results", voteSimGui.getElectionController().getElectPane());
		voteSimGui.getMainView().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		voteSimGui.getMainView().getContentPane().add(voteSimGui.getTabbedPane());
		voteSimGui.getMainView().pack();
		voteSimGui.getMainView().setVisible(true);
		return voteSimGui;
	}
	
}
