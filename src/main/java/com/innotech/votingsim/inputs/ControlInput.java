package com.innotech.votingsim.inputs;

import javax.swing.*;

public abstract class ControlInput {

    public abstract String getValue();
    public abstract JLabel getLabel();
    public abstract JComponent getInputField();

}
