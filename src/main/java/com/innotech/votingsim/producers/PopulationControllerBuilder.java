package com.innotech.votingsim.producers;

import com.innotech.votingsim.inputs.ControlInput;
import com.innotech.votingsim.inputs.Controller;
import com.innotech.votingsim.inputs.DecimalInput;
import com.innotech.votingsim.interfaces.ControllerBuilder;
import com.innotech.votingsim.models.Population;
import com.innotech.votingsim.views.PopulationView;
import com.innotech.votingsim.views.ViewElement;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PopulationControllerBuilder implements ControllerBuilder {
    private final PopulationController populationController = new PopulationController();
    private int inputCounter = 0;

    @Override
    public PopulationControllerBuilder addInput(ControlInput inputControl) {
        DecimalInput populationControl = (DecimalInput) inputControl;
        switch (inputCounter) {
            case 0:
                populationController.setVoterField(populationControl);
                break;
            case 1:
                populationController.setRadLeftField(populationControl);
                break;
            case 2:
                populationController.setModLeftField(populationControl);
                break;
            case 3:
                populationController.setCenterField(populationControl);
                break;
            case 4:
                populationController.setModRightField(populationControl);
                break;
            case 5:
                populationController.setRadRightField(populationControl);
                break;
            default:
                System.out.println("max input exceeded");
        }
        inputCounter++;
        return this;
    }

    @Override
    public PopulationControllerBuilder addView(ViewElement viewComponent) {
        PopulationView populationView = (PopulationView) viewComponent;
        populationView.setPopLine(populationView.buildLine(this.populationController.voterField.getLabel(), this.populationController.voterField.getInputField()));
        populationView.setRadLeftLine(populationView.buildLine(this.populationController.radLeftField.getLabel(), this.populationController.radLeftField.getInputField()));
        populationView.setModLeftLine(populationView.buildLine(this.populationController.modLeftField.getLabel(), this.populationController.modLeftField.getInputField()));
        populationView.setCenterLine(populationView.buildLine(this.populationController.centerField.getLabel(), this.populationController.centerField.getInputField()));
        populationView.setModRightLine(populationView.buildLine(this.populationController.modRightField.getLabel(), this.populationController.modRightField.getInputField()));
        populationView.setRadRightLine(populationView.buildLine(this.populationController.radRightField.getLabel(), this.populationController.radRightField.getInputField()));
        populationView.setLayout();
        this.populationController.setUserInterface(populationView);
        return this;
    }

    @Override
    public PopulationController build() {
        return populationController;
    }

    static class PopulationController extends Controller implements PropertyChangeListener {
        public static final Population demographics = new Population();
        private PopulationView userInterface;
        private DecimalInput voterField;
        private DecimalInput radLeftField;
        private DecimalInput modLeftField;
        private DecimalInput centerField;
        private DecimalInput modRightField;
        private DecimalInput radRightField;

        private PopulationController() {}

        public Population getDemographics() {
            return demographics;
        }

        public PopulationView getUserInterface() {
            return userInterface;
        }

        public void setUserInterface(PopulationView userInterface) {
            this.userInterface = userInterface;
        }

        public void setVoterField(DecimalInput voterField) {
            this.voterField = voterField;
        }

        public void setRadLeftField(DecimalInput radLeftField) {
            this.radLeftField = radLeftField;
        }

        public void setModLeftField(DecimalInput modLeftField) {
            this.modLeftField = modLeftField;
        }

        public void setCenterField(DecimalInput centerField) {
            this.centerField = centerField;
        }

        public void setModRightField(DecimalInput modRightField) {
            this.modRightField = modRightField;
        }

        public void setRadRightField(DecimalInput radRightField) {
            this.radRightField = radRightField;
        }

        public void setListeners(PropertyChangeListener changeListener) {
            voterField.getInputField().addPropertyChangeListener(changeListener);
            radLeftField.getInputField().addPropertyChangeListener(changeListener);
            modLeftField.getInputField().addPropertyChangeListener(changeListener);
            centerField.getInputField().addPropertyChangeListener(changeListener);
            modRightField.getInputField().addPropertyChangeListener(changeListener);
            radRightField.getInputField().addPropertyChangeListener(changeListener);
        }

        @Override
        public String getValue() {
            return "PopulationController";
        }

        @Override
        public JLabel getLabel() {
            return voterField.getLabel();
        }

        @Override
        public JComponent getInputField() {
            return voterField.getInputField();
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            Component trigger = (Component)evt.getSource();
            if(trigger.equals(voterField.getInputField()) && !voterField.getValue().equals("")) {
                demographics.setTotalPop(Long.parseLong(voterField.getValue()));
            } else if(trigger.equals(radLeftField.getInputField()) && !radLeftField.getValue().equals("")) {
                demographics.setRadLeft(Double.parseDouble(radLeftField.getValue()));
            } else if(trigger.equals(modLeftField.getInputField()) && !modLeftField.getValue().equals("")) {
                demographics.setModLeft(Double.parseDouble(modLeftField.getValue()));
            } else if(trigger.equals(centerField.getInputField()) && !centerField.getValue().equals("")) {
                demographics.setCenter(Double.parseDouble(centerField.getValue()));
            } else if(trigger.equals(modRightField.getInputField()) && !modRightField.getValue().equals("")) {
                demographics.setModRight(Double.parseDouble(modRightField.getValue()));
            } else if(trigger.equals(radRightField.getInputField()) && !radRightField.getValue().equals("")) {
                demographics.setRadRight(Double.parseDouble(radRightField.getValue()));
            }
        }

    }
}
