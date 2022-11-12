package com.innotech.votingsim.producers;

import com.innotech.votingsim.inputs.ActionInput;
import com.innotech.votingsim.inputs.SpinnerInput;
import com.innotech.votingsim.views.ElectionView;

import java.util.Arrays;

public class ElectionControllerFactory extends ControllerFactory {

    public static ElectionControllerBuilder.ElectionController getInstance() {
        return (ElectionControllerBuilder.ElectionController) new ElectionControllerBuilder()
                .addInput(new SpinnerInput("Election Type:", Arrays.asList("Plurality","Instant Runoff","Approval").toArray()))
                .addInput(new ActionInput("Run Election"))
                .addView(new ElectionView())
                .build();
    }

}
