package com.innotech.votingsim.producers;

import com.innotech.votingsim.inputs.ActionInput;
import com.innotech.votingsim.inputs.SpinnerInput;
import com.innotech.votingsim.views.ElectionView;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;

public class ElectionControllerBuilderTests {
    ElectionControllerBuilder testBuilder;

    @BeforeGroups({"inputTests"})
    public void setupInputTests() {
        testBuilder = new ElectionControllerBuilder();
    }

    @BeforeGroups({"viewTests"})
    public void setupViewTests() {
        testBuilder = (ElectionControllerBuilder) new ElectionControllerBuilder()
                .addInput(new SpinnerInput("Election Type:", Arrays.asList("a","b","c").toArray()))
                .addInput(new ActionInput("Run Election"));
    }

    @Test(groups = {"inputTests"})
    public void addInputShouldSetTheElectionSelectorInputGivenASpinnerInput() {
        SpinnerInput testSelector = new SpinnerInput("Election Type:", Arrays.asList("a","b","c").toArray());
        testBuilder.addInput(testSelector);
        assertEquals(testBuilder.build().getElectionSelector(), testSelector);
    }

    @Test(groups = {"inputTests"})
    public void addInputShouldSetTheRunElectionButtonGivenAnActionInput() {
        ActionInput testButton = new ActionInput("Run Election");
        testBuilder.addInput(testButton);
        assertEquals(testBuilder.build().getRunInput(), testButton);
    }

    @Test(groups = {"viewTests"})
    public void addViewShouldAddAnElectionViewComponentToTheElectionControllerGivenAViewElement() {
        ElectionView testView = new ElectionView();
        testBuilder.addView(testView);
        assertEquals(testBuilder.build().getElectionView(), testView);
    }

}
