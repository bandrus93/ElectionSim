package com.innotech.votingsim.inputs;

import javax.swing.*;
import java.util.Arrays;

public class SpinnerInput extends ControlInput {
    private final JLabel label;
    private final JComboBox<String> inputField;

    public SpinnerInput(String label, Object[] inputOptions) {
        this.label = new JLabel(label);
        this.inputField = new JComboBox<>(Arrays.copyOf(inputOptions, inputOptions.length, String[].class));
    }

    public JLabel getLabel() {
        return label;
    }

    public JComboBox<String> getInputField() {
        return inputField;
    }

    @Override
    public String getValue() {
        return inputField.getSelectedItem() != null ? inputField.getSelectedItem().toString() : "";
    }

}
