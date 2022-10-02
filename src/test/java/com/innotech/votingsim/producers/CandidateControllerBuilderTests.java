package com.innotech.votingsim.producers;

import com.innotech.votingsim.inputs.TextInput;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CandidateControllerBuilderTests {

    @Test
    public void addInputShouldSetNameFieldGivenATextInputWithLabelOfName() {
        CandidateControllerBuilder testBuilder = new CandidateControllerBuilder();
        TextInput testInput = new TextInput("Name:");
        testBuilder.addInput(testInput);
        assertEquals(testBuilder.build().getNameField(), testInput);
    }

}
