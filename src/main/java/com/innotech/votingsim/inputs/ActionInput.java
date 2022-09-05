package com.innotech.votingsim.inputs;

import javax.swing.*;

public class ActionInput extends ControlInput {
    private final JLabel label;
    private final JButton inputField;

    public ActionInput(String label) {
        this.label = new JLabel(label);
        this.inputField = new JButton(label);
    }

    public JLabel getLabel() {
        return label;
    }

    public JButton getInputField() {
        return inputField;
    }

    @Override
    public String getValue() {
        return label.getText();
    }

}
