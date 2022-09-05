package com.innotech.votingsim.views;

import javax.swing.*;

public class GuiView extends ViewElement {
    private final JFrame contentWindow = new JFrame("Election Simulator");
    private final JTabbedPane controlSelector = new JTabbedPane();

    public JFrame getContentWindow() {
        return contentWindow;
    }

    public JTabbedPane getControlSelector() {
        return controlSelector;
    }

    public void setLayout() {
        contentWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentWindow.add(controlSelector);
        contentWindow.pack();
    }

}
