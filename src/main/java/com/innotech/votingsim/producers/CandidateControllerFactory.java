package com.innotech.votingsim.producers;

import com.innotech.votingsim.inputs.ActionInput;
import com.innotech.votingsim.inputs.NumeralInput;
import com.innotech.votingsim.inputs.SpinnerInput;
import com.innotech.votingsim.inputs.TextInput;
import com.innotech.votingsim.views.CandidateView;

import java.util.Arrays;

public class CandidateControllerFactory extends ControllerFactory {

    public static CandidateControllerBuilder.CandidateController getInstance() {
        return (CandidateControllerBuilder.CandidateController) new CandidateControllerBuilder()
                .addInput(new TextInput("Name:"))
                .addInput(new SpinnerInput("Alignment:", Arrays.asList("Radical Left", "Moderate Left", "Center", "Moderate Right", "Radical Right").toArray()))
                .addInput(new NumeralInput("Energy:"))
                .addInput(new NumeralInput("Intelligence:"))
                .addInput(new NumeralInput("Wit:"))
                .addInput(new NumeralInput("Level Headedness:"))
                .addInput(new NumeralInput("Speaking Ability:"))
                .addInput(new ActionInput("Add Candidate"))
                .addInput(new ActionInput("Clear All"))
                .addView(new CandidateView())
                .build();
    }

}
