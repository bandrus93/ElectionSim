package com.innotech.votingsim.controllers;

import com.innotech.votingsim.models.Candidate;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CandidateController extends Controller implements PropertyChangeListener {
	private Candidate cand = new Candidate();
	private JPanel candPane = new JPanel();
	private JPanel energyLine = new JPanel();
	private final JLabel energyLbl = new JLabel("Energy:");
	private JFormattedTextField energyField = new JFormattedTextField(NumberFormat.getIntegerInstance());
	private JPanel intLine = new JPanel();
	private final JLabel intLbl = new JLabel("Intelligence:");
	private JFormattedTextField intelligenceField = new JFormattedTextField(NumberFormat.getIntegerInstance());
	private JPanel witLine = new JPanel();
	private final JLabel witLbl = new JLabel("Wit:");
	private JFormattedTextField witField = new JFormattedTextField(NumberFormat.getIntegerInstance());
	private JPanel levelLine = new JPanel();
	private final JLabel levelHeadLbl = new JLabel("Level Headedness:");
	private JFormattedTextField levelHeadField = new JFormattedTextField(NumberFormat.getIntegerInstance());
	private JPanel speakLine = new JPanel();
	private final JLabel speakAbilityLbl = new JLabel("Speaking Ability:");
	private JFormattedTextField speakAbilityField = new JFormattedTextField(NumberFormat.getIntegerInstance());
	private JPanel nameLine = new JPanel();
	private final JLabel nameLbl = new JLabel("Name:");
	private JFormattedTextField nameField = new JFormattedTextField();
	private JPanel alignLine = new JPanel();
	private final JLabel alignment = new JLabel("Alignment:");
	private final String[] alignments = {"Radical Left", "Moderate Left", "Center", "Moderate Right", "Radical Right"};
	private final JComboBox<String> allySelector = new JComboBox<String>(alignments);
	private JTextArea candList = new JTextArea("No candidates to show", 5, 30);
	private JScrollPane candView = new JScrollPane(candList);
	private JPanel controlLine = new JPanel();
	private final JButton add = new JButton("Add Candidate");
	private final JButton clear = new JButton("Clear All");

	public CandidateController(ControllerType type) {
		super(type);
	}

	public Candidate getCand() {
		return cand;
	}

	public void addCand() {
		cand.computeSwayScore();
		Candidate runMate = new Candidate(cand);
		Candidate.candidates.add(runMate);
		if(Candidate.candidates.size() == 1) {
			candList.replaceRange(cand.getName(), 0, 21);
			clear.setEnabled(true);
		} else {
			candList.append("\n" + cand.getName());
		}
		this.clearCandData();
		add.setEnabled(false);
	}

	public JPanel getCandPane() {
		return candPane;
	}

	public JPanel getEnergyLine() {
		return energyLine;
	}

	public JLabel getEnergyLbl() {
		return energyLbl;
	}

	public JFormattedTextField getEnergyField() {
		return energyField;
	}

	public JPanel getIntLine() {
		return intLine;
	}

	public JLabel getIntLbl() {
		return intLbl;
	}

	public JFormattedTextField getIntelligenceField() {
		return intelligenceField;
	}

	public JPanel getWitLine() {
		return witLine;
	}

	public JLabel getWitLbl() {
		return witLbl;
	}

	public JFormattedTextField getWitField() {
		return witField;
	}

	public JPanel getLevelLine() {
		return levelLine;
	}

	public JLabel getLevelHeadLbl() {
		return levelHeadLbl;
	}

	public JFormattedTextField getLevelHeadField() {
		return levelHeadField;
	}

	public JPanel getSpeakLine() {
		return speakLine;
	}

	public JLabel getSpeakAbilityLbl() {
		return speakAbilityLbl;
	}

	public JFormattedTextField getSpeakAbilityField() {
		return speakAbilityField;
	}

	public JPanel getNameLine() {
		return nameLine;
	}

	public JLabel getNameLbl() {
		return nameLbl;
	}

	public JFormattedTextField getNameField() {
		return nameField;
	}

	public JPanel getAlignLine() {
		return alignLine;
	}

	public JLabel getAlignment() {
		return alignment;
	}

	public void updateAlignment(Object alignment) {
		cand.setAlignment(alignment.toString());
	}

	public String[] getAlignments() {
		return alignments;
	}

	public JComboBox<String> getAllySelector() {
		return allySelector;
	}

	public JTextArea getCandList() {
		return candList;
	}

	public JScrollPane getCandView() {
		return candView;
	}

	public JPanel getControlLine() {
		return controlLine;
	}

	public JButton getAdd() {
		return add;
	}

	public JButton getClear() {
		return clear;
	}

	private void clearCandData() {
		nameField.setValue("");
		energyField.setValue(4);
		intelligenceField.setValue(4);
		witField.setValue(4);
		levelHeadField.setValue(4);
		speakAbilityField.setValue(4);
	}

	public void resetCandView() {
		candList.setText("No candidates to show");
		clear.setEnabled(false);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Component trigger = (Component)evt.getSource();
		if(trigger.equals(nameField)) {
			cand.setName(nameField.getText());
		} else if(trigger.equals(energyField) && energyField.getValue() != null) {
			cand.setEnergy(Integer.parseInt(energyField.getValue().toString()));
		} else if(trigger.equals(intelligenceField) && intelligenceField.getValue() != null) {
			cand.setIntelligence(Integer.parseInt(intelligenceField.getValue().toString()));
		} else if(trigger.equals(witField) && witField.getValue() != null) {
			cand.setWit(Integer.parseInt(witField.getValue().toString()));
		} else if(trigger.equals(levelHeadField) && levelHeadField.getValue() != null) {
			cand.setLevelHeadedness(Integer.parseInt(levelHeadField.getValue().toString()));
		} else if(trigger.equals(speakAbilityField) && speakAbilityField.getValue() != null) {
			cand.setSpeakAbility(Integer.parseInt(speakAbilityField.getValue().toString()));
		}
	}
}
