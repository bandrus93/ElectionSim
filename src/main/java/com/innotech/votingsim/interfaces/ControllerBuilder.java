package com.innotech.votingsim.interfaces;

import com.innotech.votingsim.inputs.ControlInput;
import com.innotech.votingsim.inputs.Controller;
import com.innotech.votingsim.views.ViewElement;

public interface ControllerBuilder {
    ControllerBuilder addInput(ControlInput inputControl);
    ControllerBuilder addView(ViewElement viewComponent);
    Controller build();
}
