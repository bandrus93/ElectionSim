package com.innotech.votingsim.inputs;

import javax.swing.*;
import java.text.NumberFormat;

public class NumeralInput extends ControlInput {
    private final JLabel label;
    private final JFormattedTextField inputField = new JFormattedTextField(NumberFormat.getIntegerInstance());

    public NumeralInput(String label) {
        this.label = new JLabel(label);
        inputField.setColumns(3);
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
