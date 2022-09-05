package com.innotech.votingsim.producers;

import com.innotech.votingsim.inputs.*;
import com.innotech.votingsim.interfaces.ControllerBuilder;
import com.innotech.votingsim.models.Campaign;
import com.innotech.votingsim.models.Candidate;
import com.innotech.votingsim.views.GuiView;
import com.innotech.votingsim.views.ViewElement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.PriorityQueue;

public class GuiBuilder implements ControllerBuilder {
	private final Gui gui = new Gui();
	
	public GuiBuilder() {
		
	}

	@Override
	public GuiBuilder addInput(ControlInput inputControl) {
		switch (inputControl.getValue()) {
			case "PopulationController":
				PopulationControllerBuilder.PopulationController controller = (PopulationControllerBuilder.PopulationController) inputControl;
				controller.setListeners(controller);
				this.gui.setPopulationController(controller);
				break;
			case "CandidateController":
				CandidateControllerBuilder.CandidateController candidateController = (CandidateControllerBuilder.CandidateController) inputControl;
				candidateController.setListeners(candidateController);
				candidateController.setActionCommands(this.gui);
				this.gui.setCandidateController(candidateController);
				break;
			case "ElectionController":
				ElectionControllerBuilder.ElectionController electionController = (ElectionControllerBuilder.ElectionController) inputControl;
				electionController.setActionCommands(this.gui);
				this.gui.setElectionController(electionController);
				break;
			default:
				System.out.println("max input exceeded");
		}
		return this;
	}

	@Override
	public GuiBuilder addView(ViewElement viewComponent) {
		GuiView guiView = (GuiView) viewComponent;
		guiView.getControlSelector().addTab("Population", gui.populationController.getUserInterface().getContentPane());
		guiView.getControlSelector().addTab("Candidates", gui.candidateController.getCandidateView().getContentPane());
		guiView.getControlSelector().addTab("Election Results", gui.electionController.getElectionView().getContentPane());
		guiView.setLayout();
		this.gui.setGuiView(guiView);
		return this;
	}

	public Gui build() {
		this.gui.electionController.setCampaign(new Campaign(this.gui.populationController.getDemographics()));
		return gui;
	}

	public static class Gui extends Controller implements ActionListener {
		private GuiView guiView;
		private PopulationControllerBuilder.PopulationController populationController;
		private CandidateControllerBuilder.CandidateController candidateController;
		private ElectionControllerBuilder.ElectionController electionController;

		private Gui() {

		}

		public GuiView getGuiView() {
			return guiView;
		}

		public void setGuiView(GuiView guiView) {
			this.guiView = guiView;
		}

		public void setPopulationController(PopulationControllerBuilder.PopulationController populationController) {
			this.populationController = populationController;
		}

		public void setCandidateController(CandidateControllerBuilder.CandidateController candidateController) {
			this.candidateController = candidateController;
		}

		public void setElectionController(ElectionControllerBuilder.ElectionController electionController) {
			this.electionController = electionController;
		}

		@Override
		public String getValue() {
			return "Gui";
		}

		@Override
		public JLabel getLabel() {
			return new JLabel("Election Simulator");
		}

		@Override
		public JComponent getInputField() {
			return guiView.getControlSelector();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
				case "align":
					candidateController.getCandidate().setAlignment(candidateController.getAllySelector().getValue());
					break;
				case "add":
					candidateController.getCandidateView().appendList(candidateController.getCandidate());
					electionController.getCampaign().addCandidate(candidateController.getCandidate());
					candidateController.clearCandidateData();
					candidateController.getClearAllInput().getInputField().setEnabled(true);
					candidateController.getAddInput().getInputField().setEnabled(false);
					if (!populationController.getDemographics().checkNull() && candidateController.getCandidate().checkNull()) {
						electionController.getRunInput().getInputField().setEnabled(true);
					} else if (electionController.getRunInput().getInputField().isEnabled()) {
						electionController.getRunInput().getInputField().setEnabled(false);
					}
					break;
				case "clear":
					electionController.getCampaign().getCandidates().clear();
					candidateController.getCandidateView().clearList();
					electionController.getRunInput().getInputField().setEnabled(false);
					break;
				case "bind":
					electionController.setElectionMethod(electionController.getElectionSelector().getValue());
					break;
				case "run":
					PriorityQueue<Candidate> result = electionController.getMethod().runElection();
					electionController.getElectionView().displayResult(result);
					break;
				default:
					System.out.println("Unknown action command");
			}
		}

	}
}
