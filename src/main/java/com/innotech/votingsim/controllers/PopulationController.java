package com.innotech.votingsim.controllers;

import com.innotech.votingsim.models.Population;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PopulationController extends Controller implements PropertyChangeListener {
	private final Population pop = new Population();
	private final JPanel popLine = new JPanel();
	private final JLabel totalPopLbl = new JLabel("Total number of registered voters:");
	private final JFormattedTextField voterField = new JFormattedTextField(NumberFormat.getNumberInstance());
	private final JPanel radLeftLine = new JPanel();
	private final JLabel radLeftLbl = new JLabel("Percentage of radical left voters:");
	private final JFormattedTextField radLeftField = new JFormattedTextField(NumberFormat.getNumberInstance());
	private final JPanel modLeftLine = new JPanel();
	private final JLabel modLeftLbl = new JLabel("Percentage of moderate left voters:");
	private final JFormattedTextField modLeftField = new JFormattedTextField(NumberFormat.getNumberInstance());
	private final JPanel centerLine = new JPanel();
	private final JLabel centerLbl = new JLabel("Percentage of centrist voters:");
	private final JFormattedTextField centerField = new JFormattedTextField(NumberFormat.getNumberInstance());
	private final JPanel modRightLine = new JPanel();
	private final JLabel modRightLbl = new JLabel("Percentage of moderate right voters:");
	private final JFormattedTextField modRightField = new JFormattedTextField(NumberFormat.getNumberInstance());
	private final JPanel radRightLine = new JPanel();
	private final JLabel radRightLbl = new JLabel("Percentage of radical right voters:");
	private final JFormattedTextField radRightField = new JFormattedTextField(NumberFormat.getNumberInstance());
	private final JPanel popPane = new JPanel();
	
	public PopulationController(ControllerType type) {
		super(type);
	}

	public Population getPop() {
		return pop;
	}
	
	public JPanel getPopLine() {
		return popLine;
	}

	public JFormattedTextField getVoterField() {
		return voterField;
	}
	
	public JPanel getRadLeftLine() {
		return radLeftLine;
	}

	public JFormattedTextField getRadLeftField() {
		return radLeftField;
	}
	
	public JPanel getModLeftLine() {
		return modLeftLine;
	}

	public JFormattedTextField getModLeftField() {
		return modLeftField;
	}
	
	public JPanel getCenterLine() {
		return centerLine;
	}

	public JFormattedTextField getCenterField() {
		return centerField;
	}
	
	public JPanel getModRightLine() {
		return modRightLine;
	}

	public JFormattedTextField getModRightField() {
		return modRightField;
	}
	
	public JPanel getRadRightLine() {
		return radRightLine;
	}

	public JFormattedTextField getRadRightField() {
		return radRightField;
	}

	public JLabel getTotalPopLbl() {
		return totalPopLbl;
	}

	public JLabel getRadLeftLbl() {
		return radLeftLbl;
	}

	public JLabel getModLeftLbl() {
		return modLeftLbl;
	}

	public JLabel getCenterLbl() {
		return centerLbl;
	}

	public JLabel getModRightLbl() {
		return modRightLbl;
	}

	public JLabel getRadRightLbl() {
		return radRightLbl;
	}

	public JPanel getPopPane() {
		return popPane;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Component trigger = (Component)evt.getSource();
		if(trigger.equals(voterField) && voterField.getValue() != null) {
			pop.setTotalPop(Long.parseLong(voterField.getValue().toString()));
		} else if(trigger.equals(radLeftField) && radLeftField.getValue() != null) {
			pop.setRadLeft(Double.parseDouble(radLeftField.getValue().toString()));
		} else if(trigger.equals(modLeftField) && modLeftField.getValue() != null) {
			pop.setModLeft(Double.parseDouble(modLeftField.getValue().toString()));
		} else if(trigger.equals(centerField) && centerField.getValue() != null) {
			pop.setCenter(Double.parseDouble(centerField.getValue().toString()));
		} else if(trigger.equals(modRightField) && modRightField.getValue() != null) {
			pop.setModRight(Double.parseDouble(modRightField.getValue().toString()));
		} else if(trigger.equals(radRightField) && radRightField.getValue() != null) {
			pop.setRadRight(Double.parseDouble(radRightField.getValue().toString()));
		}
	}
}
