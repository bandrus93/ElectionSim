package com.innotech.votingsim.views;

import javax.swing.*;

public class PopulationView extends ViewElement {
    private JPanel contentPane;
    private JPanel popLine;
    private JPanel radLeftLine;
    private JPanel modLeftLine;
    private JPanel centerLine;
    private JPanel modRightLine;
    private JPanel radRightLine;

    public JPanel getContentPane() {
        return contentPane;
    }

    public void setPopLine(JPanel popLine) {
        this.popLine = popLine;
    }

    public void setRadLeftLine(JPanel radLeftLine) {
        this.radLeftLine = radLeftLine;
    }

    public void setModLeftLine(JPanel modLeftLine) {
        this.modLeftLine = modLeftLine;
    }

    public void setCenterLine(JPanel centerLine) {
        this.centerLine = centerLine;
    }

    public void setModRightLine(JPanel modRightLine) {
        this.modRightLine = modRightLine;
    }

    public void setRadRightLine(JPanel radRightLine) {
        this.radRightLine = radRightLine;
    }

    public JPanel buildLine(JLabel label, JFormattedTextField inputField) {
        JPanel inputView = new JPanel();
        inputView.add(ViewElement.borderPaddingX);
        inputView.add(label);
        inputView.add(inputField);
        inputView.add(ViewElement.borderPaddingX);
        return inputView;
    }

    public void setLayout() {
        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.add(ViewElement.borderPaddingY);
        contentPane.add(popLine);
        contentPane.add(ViewElement.contentPaddingY);
        contentPane.add(radLeftLine);
        contentPane.add(ViewElement.contentPaddingY);
        contentPane.add(modLeftLine);
        contentPane.add(ViewElement.contentPaddingY);
        contentPane.add(centerLine);
        contentPane.add(ViewElement.contentPaddingY);
        contentPane.add(modRightLine);
        contentPane.add(ViewElement.contentPaddingY);
        contentPane.add(radRightLine);
        contentPane.add(ViewElement.borderPaddingY);
    }

}
