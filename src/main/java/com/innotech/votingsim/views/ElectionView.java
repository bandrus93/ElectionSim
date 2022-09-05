package com.innotech.votingsim.views;

import com.innotech.votingsim.inputs.ActionInput;
import com.innotech.votingsim.inputs.ControlInput;
import com.innotech.votingsim.models.Candidate;

import javax.swing.*;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class ElectionView extends ViewElement {
    private JPanel contentPane;
    private JPanel typeLine;
    private JPanel commandLine;


    public JPanel getContentPane() {
        return contentPane;
    }

    public void setTypeLine(JPanel typeLine) {
        this.typeLine = typeLine;
    }

    public void setCommandLine(JPanel commandLine) {
        this.commandLine = commandLine;
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
        contentPane.add(typeLine);
        contentPane.add(ViewElement.contentPaddingY);
        contentPane.add(commandLine);
        contentPane.add(ViewElement.borderPaddingY);
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
            double percent = (candVotes / votesCast) * 100;
            resultData[i][2] = percent + "%";
        }
        JTable resultTable = new JTable(resultData, resultHeaders);
        JScrollPane tablePane = new JScrollPane(resultTable);
        resultTable.setFillsViewportHeight(true);
        contentPane.add(tablePane, 3);
    }

}
