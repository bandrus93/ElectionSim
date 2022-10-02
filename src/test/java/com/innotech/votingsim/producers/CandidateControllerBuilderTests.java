package com.innotech.votingsim.producers;

import com.innotech.votingsim.inputs.ActionInput;
import com.innotech.votingsim.inputs.NumeralInput;
import com.innotech.votingsim.inputs.SpinnerInput;
import com.innotech.votingsim.inputs.TextInput;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;

public class CandidateControllerBuilderTests {
    private CandidateControllerBuilder testBuilder;

    @BeforeGroups({"inputTests"})
    public void setupInputTests() {
        testBuilder = new CandidateControllerBuilder();
    }

    @Test(groups = {"inputTests"})
    public void addInputShouldSetNameFieldGivenATextInputWithLabelOfName() {
        TextInput testInput = new TextInput("Name:");
        testBuilder.addInput(testInput);
        assertEquals(testBuilder.build().getNameField(), testInput);
    }

    @Test(groups = {"inputTests"})
    public void addInputShouldSetEnergyFieldGivenANumeralInputWithALabelOfEnergy() {
        NumeralInput testInput = new NumeralInput("Energy:");
        testBuilder.addInput(testInput);
        assertEquals(testBuilder.build().getEnergyField(), testInput);
    }

    @Test(groups = {"inputTests"})
    public void addInputShouldSetAllySelectorGivenASpinnerInputWithALabelOfAlignment() {
        SpinnerInput testInput = new SpinnerInput("Alignment:", Arrays.asList("Radical Left", "Moderate Left", "Center", "Moderate Right", "Radical Right").toArray());
        testBuilder.addInput(testInput);
        assertEquals(testBuilder.build().getAllySelector(), testInput);
    }

    @Test(groups = {"inputTests"})
    public void addInputShouldSetAddCandidateButtonGivenAnActionInputWithALabelOfAddCandidate() {
        ActionInput testInput = new ActionInput("Add Candidate");
        testBuilder.addInput(testInput);
        assertEquals(testBuilder.build().getAddInput(), testInput);
    }

}
