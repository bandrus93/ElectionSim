package com.innotech.votingsim.inputs;

import javax.swing.*;
import java.text.NumberFormat;

public class DecimalInput extends ControlInput {
    private final JLabel label;
    private final JFormattedTextField inputField;

    public DecimalInput(String label) {
        this.label = new JLabel(label);
        this.inputField = new JFormattedTextField(NumberFormat.getNumberInstance());
        inputField.setColumns(8);
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
