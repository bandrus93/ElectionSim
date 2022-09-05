package com.innotech.votingsim.producers;

import com.innotech.votingsim.inputs.DecimalInput;
import com.innotech.votingsim.views.PopulationView;

public class PopulationControllerFactory extends ControllerFactory {

    public static PopulationControllerBuilder.PopulationController getInstance() {
        return new PopulationControllerBuilder()
                .addInput(new DecimalInput("Total number of registered voters:"))
                .addInput(new DecimalInput("Percentage of radical left voters:"))
                .addInput(new DecimalInput("Percentage of moderate left voters:"))
                .addInput(new DecimalInput("Percentage of centrist voters:"))
                .addInput(new DecimalInput("Percentage of moderate right voters:"))
                .addInput(new DecimalInput("Percentage of radical right voters:"))
                .addView(new PopulationView())
                .build();
    }

}
