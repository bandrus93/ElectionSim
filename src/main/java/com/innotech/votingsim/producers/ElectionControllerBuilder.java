package com.innotech.votingsim.producers;

import com.innotech.votingsim.inputs.ActionInput;
import com.innotech.votingsim.inputs.ControlInput;
import com.innotech.votingsim.inputs.Controller;
import com.innotech.votingsim.inputs.SpinnerInput;
import com.innotech.votingsim.interfaces.ControllerBuilder;
import com.innotech.votingsim.models.Campaign;
import com.innotech.votingsim.subroutines.ElectionMethod;
import com.innotech.votingsim.subroutines.InstantRunoffMethod;
import com.innotech.votingsim.subroutines.PluralityMethod;
import com.innotech.votingsim.views.ElectionView;
import com.innotech.votingsim.views.ViewElement;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ElectionControllerBuilder implements ControllerBuilder {
    private final ElectionController electionController = new ElectionController();

    @Override
    public ControllerBuilder addInput(ControlInput inputControl) {
        if (inputControl.getLabel().getText().equals("Election Type:")) {
            electionController.setElectionSelector((SpinnerInput) inputControl);
        } else {
            electionController.setElectionRunner((ActionInput) inputControl);
        }
        return this;
    }

    @Override
    public ControllerBuilder addView(ViewElement viewComponent) {
        ElectionView electionView = (ElectionView) viewComponent;
        electionView.setTypeLine(electionView.buildLine(this.electionController.electionSelector));
        electionView.setCommandLine(electionView.buildLine(List.of(this.electionController.electionRunner)));
        electionController.getRunInput().getInputField().setEnabled(false);
        electionView.setLayout();
        electionController.setElectionView(electionView);
        return this;
    }

    @Override
    public ElectionController build() {
        return electionController;
    }

    static class ElectionController extends Controller {
        private Campaign campaign;
        private ElectionMethod method;
        private SpinnerInput electionSelector;
        private ActionInput electionRunner;
        private ElectionView electionView;

        private ElectionController() {
            
        }

        public Campaign getCampaign() {
            return campaign;
        }

        public void setCampaign(Campaign campaign) {
            this.campaign = campaign;
        }

        public ElectionMethod getMethod() {
            return method;
        }

        public void setElectionMethod(String method) {
            switch (method) {
                case "Plurality":
                    this.method = new PluralityMethod(campaign);
                    break;
                case "Instant Runoff":
                    this.method = new InstantRunoffMethod(campaign);
                    break;
                default:
                    System.out.println("Unknown election method");
            }
        }

        public SpinnerInput getElectionSelector() {
            return electionSelector;
        }

        public ActionInput getRunInput() {
            return electionRunner;
        }

        public void setElectionSelector(SpinnerInput electionSelector) {
            this.electionSelector = electionSelector;
        }

        public void setElectionRunner(ActionInput electionRunner) {
            this.electionRunner = electionRunner;
        }

        public ElectionView getElectionView() {
            return electionView;
        }

        public void setElectionView(ElectionView electionView) {
            this.electionView = electionView;
        }

        public void setActionCommands(ActionListener actionListener) {
            electionSelector.getInputField().addActionListener(actionListener);
            electionSelector.getInputField().setActionCommand("bind");
            electionRunner.getInputField().addActionListener(actionListener);
            electionRunner.getInputField().setActionCommand("run");
        }

        @Override
        public String getValue() {
            return "ElectionController";
        }

        @Override
        public JLabel getLabel() {
            return electionRunner.getLabel();
        }

        @Override
        public JComponent getInputField() {
            return electionRunner.getInputField();
        }

    }
}
