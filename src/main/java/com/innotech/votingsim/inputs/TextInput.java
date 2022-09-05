package com.innotech.votingsim.inputs;

import javax.swing.*;

public class TextInput extends ControlInput {
    private final JLabel label;
    private final JFormattedTextField inputField = new JFormattedTextField();

    public TextInput(String label) {
        this.label = new JLabel(label);
        inputField.setColumns(30);
    }

    public JLabel getLabel() {
        return label;
    }

    public JFormattedTextField getInputField() {
        return inputField;
    }

    @Override
    public String getValue() {
        return inputField.getValue() != null ? inputField.getValue().toString() : "";
    }

}
