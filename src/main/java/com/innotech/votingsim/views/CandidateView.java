package com.innotech.votingsim.views;

import com.innotech.votingsim.inputs.ActionInput;
import com.innotech.votingsim.inputs.ControlInput;
import com.innotech.votingsim.models.Candidate;

import javax.swing.*;
import java.util.List;

public class CandidateView extends ViewElement {
    private JPanel contentPane;
    private JPanel nameLine;
    private JPanel energyLine;
    private JPanel intLine;
    private JPanel witLine;
    private JPanel levelLine;
    private JPanel speakLine;
    private JPanel alignLine;
    private JPanel controlLine;
    private final JTextArea candidateListView = new JTextArea("No candidates to show", 5, 30);
    private final JScrollPane candidateListWindow = new JScrollPane(candidateListView);
    private int listItems = 0;

    public JPanel getContentPane() {
        return contentPane;
    }

    public void setNameLine(JPanel nameLine) {
        this.nameLine = nameLine;
    }

    public void setEnergyLine(JPanel energyLine) {
        this.energyLine = energyLine;
    }

    public void setIntLine(JPanel intLine) {
        this.intLine = intLine;
    }

    public void setWitLine(JPanel witLine) {
        this.witLine = witLine;
    }

    public void setLevelLine(JPanel levelLine) {
        this.levelLine = levelLine;
    }

    public void setSpeakLine(JPanel speakLine) {
        this.speakLine = speakLine;
    }

    public void setAlignLine(JPanel alignLine) {
        this.alignLine = alignLine;
    }

    public void setControlLine(JPanel controlLine) {
        this.controlLine = controlLine;
    }

    public JPanel buildLine(ControlInput inputField) {
        JPanel inputLine = new JPanel();
        inputLine.add(inputField.getLabel());
        inputLine.add(inputField.getInputField());
        return inputLine;
    }

    public JPanel buildLine(List<ActionInput> inputs) {
        JPanel commandLine = new JPanel();
        commandLine.add(ViewElement.borderPaddingX);
        for (ActionInput button : inputs) {
            commandLine.add(button.getInputField());
            commandLine.add(ViewElement.borderPaddingX);
        }
        return commandLine;
    }

    public void setLayout() {
        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.add(ViewElement.borderPaddingY);
        contentPane.add(candidateListWindow);
        contentPane.add(ViewElement.contentPaddingY);
        contentPane.add(nameLine);
        contentPane.add(ViewElement.contentPaddingY);
        contentPane.add(alignLine);
        contentPane.add(ViewElement.contentPaddingY);
        contentPane.add(energyLine);
        contentPane.add(ViewElement.contentPaddingY);
        contentPane.add(intLine);
        contentPane.add(ViewElement.contentPaddingY);
        contentPane.add(witLine);
        contentPane.add(ViewElement.contentPaddingY);
        contentPane.add(levelLine);
        contentPane.add(ViewElement.contentPaddingY);
        contentPane.add(speakLine);
        contentPane.add(ViewElement.contentPaddingY);
        contentPane.add(controlLine);
        contentPane.add(ViewElement.borderPaddingY);
    }

    public void appendList(Candidate candidate) {
        if (listItems == 0) {
            candidateListView.replaceRange(candidate.getName(), 0, 21);
        } else {
            candidateListView.append("\n" + candidate.getName());
        }
        listItems++;
        contentPane.validate();
    }

    public void clearList() {
        for (int i = candidateListView.getLineCount(); i > 0; i--) {
            if (i == 1) {
                candidateListView.replaceRange("No candidates to show", 0, 29);
            } else {
                candidateListView.replaceRange("", (30 * (i - 1)) - 1, (30 * i) - 1);
            }
        }
        listItems = 0;
        contentPane.validate();
    }

}
