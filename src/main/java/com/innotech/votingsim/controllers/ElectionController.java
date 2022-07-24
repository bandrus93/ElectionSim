package com.innotech.votingsim.controllers;

import com.innotech.votingsim.subroutines.Campaign;

import javax.swing.*;
import java.util.Objects;

import static com.innotech.votingsim.subroutines.Campaign.ElectionType.PLURALITY;
import static com.innotech.votingsim.subroutines.Campaign.ElectionType.RUNOFF;

public class ElectionController extends Controller {
    private Campaign.ElectionType electionType;
    private final JPanel typeLine = new JPanel();
    private final JLabel typeLbl = new JLabel("Election type:");
    private final String[] types = {"Plurality", "Instant Runoff"};
    private final JComboBox<String> electionSelector = new JComboBox<>(types);
    private final JPanel electPane = new JPanel();
    private final JButton run = new JButton("Run Election");

    public ElectionController(ControllerType type) {
        super(type);
    }

    public JPanel getTypeLine() {
        return typeLine;
    }

    public JLabel getTypeLbl() {
        return typeLbl;
    }

    public Campaign.ElectionType getElectionType() {
        return electionType;
    }

    public JComboBox<String> getElectionSelector() {
        return electionSelector;
    }

    public JPanel getElectPane() {
        return electPane;
    }

    public JButton getRun() {
        return run;
    }

    public void setElectionType() {
        switch(Objects.requireNonNull(electionSelector.getSelectedItem()).toString()) {
            case "Plurality":
                electionType = PLURALITY;
                break;
            case "Instant Runoff":
                electionType = RUNOFF;
                break;
            default:
                System.out.println("Unknown election type");
        }
    }
}
