package com.innotech.votingsim.producers;

import com.innotech.votingsim.inputs.*;
import com.innotech.votingsim.interfaces.ControllerBuilder;
import com.innotech.votingsim.models.Candidate;
import com.innotech.votingsim.views.CandidateView;
import com.innotech.votingsim.views.ViewElement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

public class CandidateControllerBuilder implements ControllerBuilder {
    private final CandidateController candidateController = new CandidateController();
    private int inputCounter = 0;

    @Override
    public ControllerBuilder addInput(ControlInput inputControl) {
        switch (inputCounter) {
            case 0:
                candidateController.setNameField((TextInput) inputControl);
                break;
            case 1:
                candidateController.setAllySelector((SpinnerInput) inputControl);
                break;
            case 2:
                candidateController.setEnergyField((NumeralInput) inputControl);
                break;
            case 3:
                candidateController.setIntelligenceField((NumeralInput) inputControl);
                break;
            case 4:
                candidateController.setWitField((NumeralInput) inputControl);
                break;
            case 5:
                candidateController.setLevelHeadField((NumeralInput) inputControl);
                break;
            case 6:
                candidateController.setSpeakAbilityField((NumeralInput) inputControl);
                break;
            case 7:
                candidateController.setAddInput((ActionInput) inputControl);
                break;
            case 8:
                candidateController.setClearAllInput((ActionInput) inputControl);
            default:
                System.out.println("max input exceeded");
        }
        inputCounter++;
        return this;
    }

    @Override
    public ControllerBuilder addView(ViewElement viewComponent) {
        CandidateView candidateView = (CandidateView) viewComponent;
        candidateView.setNameLine(candidateView.buildLine(this.candidateController.nameField));
        candidateView.setAlignLine(candidateView.buildLine(this.candidateController.allySelector));
        candidateView.setEnergyLine(candidateView.buildLine(this.candidateController.energyField));
        candidateView.setIntLine(candidateView.buildLine(this.candidateController.intelligenceField));
        candidateView.setWitLine(candidateView.buildLine(this.candidateController.witField));
        candidateView.setLevelLine(candidateView.buildLine(this.candidateController.levelHeadField));
        candidateView.setSpeakLine(candidateView.buildLine(this.candidateController.speakAbilityField));
        candidateView.setControlLine(candidateView.buildLine(Arrays.asList(this.candidateController.addCandidate,this.candidateController.clearAll)));
        candidateController.getAddInput().getInputField().setEnabled(false);
        candidateController.getClearAllInput().getInputField().setEnabled(false);
        candidateView.setLayout();
        this.candidateController.setCandidateView(candidateView);
        return this;
    }

    @Override
    public Controller build() {
        return candidateController;
    }

    static class CandidateController extends Controller implements PropertyChangeListener {
        private final Candidate candidate = new Candidate();
        private NumeralInput energyField;
        private NumeralInput intelligenceField;
        private NumeralInput witField;
        private NumeralInput levelHeadField;
        private NumeralInput speakAbilityField;
        private TextInput nameField;
        private SpinnerInput allySelector;
        private CandidateView candidateView;
        private ActionInput addCandidate;
        private ActionInput clearAll;

        private CandidateController() {

        }

        public Candidate getCandidate() {
            return candidate;
        }

        public void setEnergyField(NumeralInput energyField) {
            this.energyField = energyField;
        }

        public void setIntelligenceField(NumeralInput intelligenceField) {
            this.intelligenceField = intelligenceField;
        }

        public void setWitField(NumeralInput witField) {
            this.witField = witField;
        }

        public void setLevelHeadField(NumeralInput levelHeadField) {
            this.levelHeadField = levelHeadField;
        }

        public void setSpeakAbilityField(NumeralInput speakAbilityField) {
            this.speakAbilityField = speakAbilityField;
        }

        public void setNameField(TextInput nameField) {
            this.nameField = nameField;
        }

        public SpinnerInput getAllySelector() {
            return allySelector;
        }

        public void setAllySelector(SpinnerInput allySelector) {
            this.allySelector = allySelector;
        }

        public CandidateView getCandidateView() {
            return candidateView;
        }

        public void setCandidateView(CandidateView candidateView) {
            this.candidateView = candidateView;
        }

        public ActionInput getAddInput() {
            return addCandidate;
        }

        public void setAddInput(ActionInput addCandidate) {
            this.addCandidate = addCandidate;
        }

        public ActionInput getClearAllInput() {
            return clearAll;
        }

        public void setClearAllInput(ActionInput clearAll) {
            this.clearAll = clearAll;
        }

        public void clearCandidateData() {
            nameField.getInputField().setText("");
            energyField.getInputField().setValue(4);
            intelligenceField.getInputField().setValue(4);
            witField.getInputField().setValue(4);
            levelHeadField.getInputField().setValue(4);
            speakAbilityField.getInputField().setValue(4);
        }

        public void setListeners(PropertyChangeListener changeListener) {
            nameField.getInputField().addPropertyChangeListener(changeListener);
            energyField.getInputField().addPropertyChangeListener(changeListener);
            intelligenceField.getInputField().addPropertyChangeListener(changeListener);
            witField.getInputField().addPropertyChangeListener(changeListener);
            levelHeadField.getInputField().addPropertyChangeListener(changeListener);
            speakAbilityField.getInputField().addPropertyChangeListener(changeListener);
        }

        public void setActionCommands(ActionListener actionListener) {
            allySelector.getInputField().addActionListener(actionListener);
            allySelector.getInputField().setActionCommand("align");
            addCandidate.getInputField().addActionListener(actionListener);
            addCandidate.getInputField().setActionCommand("add");
            clearAll.getInputField().addActionListener(actionListener);
            clearAll.getInputField().setActionCommand("clear");
        }

        @Override
        public String getValue() {
            return "CandidateController";
        }

        @Override
        public JLabel getLabel() {
            return new JLabel("Candidates");
        }

        @Override
        public JComponent getInputField() {
            return addCandidate.getInputField();
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            Component trigger = (Component) evt.getSource();
            System.out.println(trigger.toString());
            System.out.println(nameField.getValue());
            if (trigger.equals(nameField.getInputField()) && !nameField.getValue().equals("")) {
                candidate.setName(nameField.getValue());
            } else if (trigger.equals(energyField.getInputField()) && !energyField.getValue().equals("")) {
                candidate.setEnergy(Integer.parseInt(energyField.getValue()));
            } else if (trigger.equals(intelligenceField.getInputField()) && !intelligenceField.getValue().equals("")) {
                candidate.setIntelligence(Integer.parseInt(intelligenceField.getValue()));
            } else if (trigger.equals(witField.getInputField()) && !witField.getValue().equals("")) {
                candidate.setWit(Integer.parseInt(witField.getValue()));
            } else if (trigger.equals(levelHeadField.getInputField()) && !levelHeadField.getValue().equals("")) {
                candidate.setLevelHeadedness(Integer.parseInt(levelHeadField.getValue()));
            } else if (trigger.equals(speakAbilityField.getInputField()) && !speakAbilityField.getValue().equals("")) {
                candidate.setSpeakAbility(Integer.parseInt(speakAbilityField.getValue()));
            }
            System.out.println("Name: " + this.getCandidate().getName()
                    + "\nAlignment: " + this.getCandidate().getAlignment()
                    + "\nEnergy: " + this.getCandidate().getEnergy()
                    + "\nIntelligence: " + this.getCandidate().getIntelligence()
                    + "\nWit: " + this.getCandidate().getWit()
                    + "\nLevel Headedness: " + this.getCandidate().getLevelHeadedness()
                    + "\nSpeak Ability: " + this.getCandidate().getSpeakAbility());
            if (this.getCandidate().checkNull()) {
                this.getAddInput().getInputField().setEnabled(true);
            }
        }
    }
}
