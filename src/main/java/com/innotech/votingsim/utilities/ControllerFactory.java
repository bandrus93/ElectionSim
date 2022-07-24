package com.innotech.votingsim.utilities;

import javax.swing.BoxLayout;

import com.innotech.votingsim.VotingSimApplication;
import com.innotech.votingsim.controllers.CandidateController;
import com.innotech.votingsim.controllers.Controller;
import com.innotech.votingsim.controllers.ElectionController;
import com.innotech.votingsim.controllers.PopulationController;

import static com.innotech.votingsim.utilities.GuiBuilder.*;

public class ControllerFactory {
	
	public static PopulationController getPopulationInstance() {
		PopulationController popController = new PopulationController(Controller.ControllerType.POPULATION);
		popController.getVoterField().setColumns(8);
		popController.getVoterField().addPropertyChangeListener(VotingSimApplication.uiManager);
		popController.getVoterField().addPropertyChangeListener(popController);
		popController.getPopLine().add(borderPaddingX);
		popController.getPopLine().add(popController.getTotalPopLbl());
		popController.getPopLine().add(popController.getVoterField());
		popController.getPopLine().add(borderPaddingX);
		popController.getRadLeftField().setColumns(8);
		popController.getRadLeftField().addPropertyChangeListener(VotingSimApplication.uiManager);
		popController.getRadLeftField().addPropertyChangeListener(popController);
		popController.getRadLeftLine().add(borderPaddingX);
		popController.getRadLeftLine().add(popController.getRadLeftLbl());
		popController.getRadLeftLine().add(popController.getRadLeftField());
		popController.getRadLeftLine().add(borderPaddingX);
		popController.getModLeftField().setColumns(8);
		popController.getModLeftField().addPropertyChangeListener(VotingSimApplication.uiManager);
		popController.getModLeftField().addPropertyChangeListener(popController);
		popController.getModLeftLine().add(borderPaddingX);
		popController.getModLeftLine().add(popController.getModLeftLbl());
		popController.getModLeftLine().add(popController.getModLeftField());
		popController.getModLeftLine().add(borderPaddingX);
		popController.getCenterField().setColumns(8);
		popController.getCenterField().addPropertyChangeListener(VotingSimApplication.uiManager);
		popController.getCenterField().addPropertyChangeListener(popController);
		popController.getCenterLine().add(borderPaddingX);
		popController.getCenterLine().add(popController.getCenterLbl());
		popController.getCenterLine().add(popController.getCenterField());
		popController.getCenterLine().add(borderPaddingX);
		popController.getModRightField().setColumns(8);
		popController.getModRightField().addPropertyChangeListener(VotingSimApplication.uiManager);
		popController.getModRightField().addPropertyChangeListener(popController);
		popController.getModRightLine().add(borderPaddingX);
		popController.getModRightLine().add(popController.getModRightLbl());
		popController.getModRightLine().add(popController.getModRightField());
		popController.getModRightLine().add(borderPaddingX);
		popController.getRadRightField().setColumns(8);
		popController.getRadRightField().addPropertyChangeListener(VotingSimApplication.uiManager);
		popController.getRadRightField().addPropertyChangeListener(popController);
		popController.getRadRightLine().add(borderPaddingX);
		popController.getRadRightLine().add(popController.getRadRightLbl());
		popController.getRadRightLine().add(popController.getRadRightField());
		popController.getRadRightLine().add(borderPaddingX);
		popController.getPopPane().setLayout(new BoxLayout(popController.getPopPane(), BoxLayout.Y_AXIS));
		popController.getPopPane().add(borderPaddingY);
		popController.getPopPane().add(popController.getPopLine());
		popController.getPopPane().add(GuiBuilder.contentPaddingY);
		popController.getPopPane().add(popController.getRadLeftLine());
		popController.getPopPane().add(GuiBuilder.contentPaddingY);
		popController.getPopPane().add(popController.getModLeftLine());
		popController.getPopPane().add(GuiBuilder.contentPaddingY);
		popController.getPopPane().add(popController.getCenterLine());
		popController.getPopPane().add(GuiBuilder.contentPaddingY);
		popController.getPopPane().add(popController.getModRightLine());
		popController.getPopPane().add(GuiBuilder.contentPaddingY);
		popController.getPopPane().add(popController.getRadRightLine());
		popController.getPopPane().add(borderPaddingY);
		return popController;
	}
	
	public static CandidateController getCandidateInstance() {
		CandidateController candController = new CandidateController(Controller.ControllerType.CANDIDATE);
		candController.getNameField().setColumns(30);
		candController.getNameField().addPropertyChangeListener(VotingSimApplication.uiManager);
		candController.getNameField().addPropertyChangeListener(candController);
		candController.getNameLine().add(candController.getNameLbl());
		candController.getNameLine().add(candController.getNameField());
		candController.getAllySelector().setActionCommand("align");
		candController.getAllySelector().addActionListener(VotingSimApplication.uiManager);
		candController.getAlignLine().add(candController.getAlignment());
		candController.getAlignLine().add(candController.getAllySelector());
		candController.getEnergyField().setColumns(3);
		candController.getEnergyField().addPropertyChangeListener(VotingSimApplication.uiManager);
		candController.getEnergyField().addPropertyChangeListener(candController);
		candController.getEnergyLine().add(candController.getEnergyLbl());
		candController.getEnergyLine().add(candController.getEnergyField());
		candController.getIntelligenceField().setColumns(3);
		candController.getIntelligenceField().addPropertyChangeListener(VotingSimApplication.uiManager);
		candController.getIntelligenceField().addPropertyChangeListener(candController);
		candController.getIntLine().add(candController.getIntLbl());
		candController.getIntLine().add(candController.getIntelligenceField());
		candController.getWitField().setColumns(3);
		candController.getWitField().addPropertyChangeListener(VotingSimApplication.uiManager);
		candController.getWitField().addPropertyChangeListener(candController);
		candController.getWitLine().add(candController.getWitLbl());
		candController.getWitLine().add(candController.getWitField());
		candController.getLevelHeadField().setColumns(3);
		candController.getLevelHeadField().addPropertyChangeListener(VotingSimApplication.uiManager);
		candController.getLevelHeadField().addPropertyChangeListener(candController);
		candController.getLevelLine().add(candController.getLevelHeadLbl());
		candController.getLevelLine().add(candController.getLevelHeadField());
		candController.getSpeakAbilityField().setColumns(3);
		candController.getSpeakAbilityField().addPropertyChangeListener(VotingSimApplication.uiManager);
		candController.getSpeakAbilityField().addPropertyChangeListener(candController);
		candController.getSpeakLine().add(candController.getSpeakAbilityLbl());
		candController.getSpeakLine().add(candController.getSpeakAbilityField());
		candController.getClear().setEnabled(false);
		candController.getClear().setActionCommand("clear");
		candController.getClear().addActionListener(VotingSimApplication.uiManager);
		candController.getAdd().setEnabled(false);
		candController.getAdd().setActionCommand("add");
		candController.getAdd().addActionListener(VotingSimApplication.uiManager);
		candController.getControlLine().setLayout(new BoxLayout(candController.getControlLine(), BoxLayout.X_AXIS));
		candController.getControlLine().add(borderPaddingX);
		candController.getControlLine().add(candController.getClear());
		candController.getControlLine().add(contentPaddingX);
		candController.getControlLine().add(candController.getAdd());
		candController.getControlLine().add(borderPaddingX);
		candController.getCandPane().setLayout(new BoxLayout(candController.getCandPane(), BoxLayout.Y_AXIS));
		candController.getCandPane().add(borderPaddingY);
		candController.getCandPane().add(candController.getCandView());
		candController.getCandPane().add(contentPaddingY);
		candController.getCandPane().add(candController.getNameLine());
		candController.getCandPane().add(contentPaddingY);
		candController.getCandPane().add(candController.getAlignLine());
		candController.getCandPane().add(contentPaddingY);
		candController.getCandPane().add(candController.getEnergyLine());
		candController.getCandPane().add(contentPaddingY);
		candController.getCandPane().add(candController.getIntLine());
		candController.getCandPane().add(contentPaddingY);
		candController.getCandPane().add(candController.getWitLine());
		candController.getCandPane().add(contentPaddingY);
		candController.getCandPane().add(candController.getLevelLine());
		candController.getCandPane().add(contentPaddingY);
		candController.getCandPane().add(candController.getSpeakLine());
		candController.getCandPane().add(contentPaddingY);
		candController.getCandPane().add(candController.getControlLine());
		candController.getCandPane().add(borderPaddingY);
		return candController;
	}

	public static ElectionController getElectionInstance() {
		ElectionController electController = new ElectionController(Controller.ControllerType.ELECTION);
		electController.getElectionSelector().setActionCommand("bind");
		electController.getElectionSelector().addActionListener(VotingSimApplication.uiManager);
		electController.getTypeLine().add(electController.getTypeLbl());
		electController.getTypeLine().add(electController.getElectionSelector());
		electController.getRun().setEnabled(false);
		electController.getRun().setActionCommand("run");
		electController.getRun().addActionListener(VotingSimApplication.uiManager);
		electController.getElectPane().setLayout(new BoxLayout(electController.getElectPane(), BoxLayout.Y_AXIS));
		electController.getElectPane().add(borderPaddingY);
		electController.getElectPane().add(electController.getTypeLine());
		electController.getElectPane().add(contentPaddingY);
		electController.getElectPane().add(electController.getRun());
		electController.getElectPane().add(borderPaddingY);
		return electController;
	}
	
}
