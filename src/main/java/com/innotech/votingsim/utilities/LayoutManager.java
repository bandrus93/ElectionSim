package com.innotech.votingsim.utilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Iterator;
import java.util.Objects;
import java.util.PriorityQueue;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.innotech.votingsim.controllers.ContainerShell;
import com.innotech.votingsim.models.Candidate;
import com.innotech.votingsim.subroutines.Campaign;

import static com.innotech.votingsim.utilities.GuiBuilder.borderPaddingY;
import static com.innotech.votingsim.utilities.GuiBuilder.contentPaddingY;

public class LayoutManager implements PropertyChangeListener, ActionListener {
	private ContainerShell gui;
	
	public LayoutManager() {

	}
	
	public void buildGUI() {
		GuiBuilder guiBuilder = new GuiBuilder();
		guiBuilder.addPopulationController(ControllerFactory.getPopulationInstance());
		guiBuilder.addCandidateController(ControllerFactory.getCandidateInstance());
		guiBuilder.addElectionController(ControllerFactory.getElectionInstance());
		gui = guiBuilder.build();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "align":
			gui.getCandidateController().updateAlignment(Objects.requireNonNull(
					gui.getCandidateController().getAllySelector().getSelectedItem()
			));
			break;
		case "add":
			gui.getCandidateController().addCand();
			gui.getMainView().validate();
			break;
		case "clear":
			Candidate.candidates.clear();
			gui.getCandidateController().resetCandView();
			gui.getMainView().validate();
			break;
		case "bind":
			gui.getElectionController().setElectionType();
		case "run":
			Campaign campaign = new Campaign(gui.getElectionController().getElectionType(), gui.getPopulationController().getPop());
			Thread electionRunner = new Thread(campaign);
			electionRunner.start();
			while (electionRunner.isAlive()) {
				try {
					this.wait(50);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
			displayResult(campaign.getElectionResult());
		default:
			System.out.println("Unknown action command");
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		try {
			if (gui.getCandidateController().getCand().checkSum()) {
				gui.getCandidateController().getAdd().setEnabled(true);
			}
			if (gui.getPopulationController().getPop().checkSum() && Candidate.candidates != null) {
				gui.getElectionController().getRun().setEnabled(true);
			}
		} catch(NullPointerException n) {
			n.printStackTrace();
		}
	}
	
	public void displayResult(PriorityQueue<Candidate> resultList) {
		Iterator<Candidate> winnerator = resultList.iterator();
		Object[][] resultData = new Object[resultList.size()][3];
		Object[] resultHeaders = {"Candidate","Total Votes","Popular Vote"};
		int rank = resultList.size() - 1;
		Long votesCast = 0L;
		while(winnerator.hasNext()) {
			Candidate prospective = winnerator.next();
			resultData[rank][0] = prospective.getName();
			resultData[rank][1] = prospective.getTotalVotes();
			votesCast += prospective.getTotalVotes();
			rank--;
		}
		for(int i = resultList.size() - 1; i >= 0; i--) {
			Double candVotes = Double.parseDouble(resultData[i][1].toString());
			Double percent = (candVotes / votesCast) * 100;
			resultData[i][2] = percent.toString() + "%";
		}
		JTable resultTable = new JTable(resultData, resultHeaders);
		JScrollPane tablePane = new JScrollPane(resultTable);
		resultTable.setFillsViewportHeight(true);
		gui.getElectionController().getElectPane().add(tablePane, 3);
		gui.getElectionController().getElectPane().add(contentPaddingY);
		gui.getElectionController().getElectPane().add(gui.getElectionController().getRun());
		gui.getElectionController().getElectPane().add(borderPaddingY);
	}
}
