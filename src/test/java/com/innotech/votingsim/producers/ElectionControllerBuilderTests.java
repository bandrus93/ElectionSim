package com.innotech.votingsim.producers;

import com.innotech.votingsim.inputs.ActionInput;
import com.innotech.votingsim.inputs.SpinnerInput;
import com.innotech.votingsim.views.ElectionView;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class ElectionControllerBuilderTests {
    private ElectionControllerBuilder testBuilder;
    private ElectionView testView;
    private SpinnerInput testSelector;
    private ActionInput testButton;

    @BeforeSuite
    public void setupTestConstants() {
        testSelector = new SpinnerInput("Election Type:", Arrays.asList("a","b","c").toArray());
        testButton = new ActionInput("Run Election");
    }

    @BeforeGroups({"inputTests"})
    public void setupInputTests() {
        testBuilder = new ElectionControllerBuilder();
    }

    @BeforeGroups({"viewTests"})
    public void setupViewTests() {
        testBuilder = (ElectionControllerBuilder) new ElectionControllerBuilder()
                .addInput(testSelector)
                .addInput(testButton);
        testView = new ElectionView();
    }

    @Test(groups = {"inputTests"})
    public void addInputShouldSetTheElectionSelectorInputGivenASpinnerInput() {
        testBuilder.addInput(testSelector);
        assertEquals(testBuilder.build().getElectionSelector(), testSelector);
    }

    @Test(groups = {"inputTests"})
    public void addInputShouldSetTheRunElectionButtonGivenAnActionInput() {
        testBuilder.addInput(testButton);
        assertEquals(testBuilder.build().getRunInput(), testButton);
    }

    @Test(groups = {"viewTests"})
    public void addViewShouldAddAnElectionViewComponentToTheElectionControllerGivenAViewElement() {
        testBuilder.addView(testView);
        assertEquals(testBuilder.build().getElectionView(), testView);
    }

    @Test(groups = {"viewTests"})
    public void addViewShouldSetTheElectionViewTypeLineGivenAViewElement() {
        testBuilder.addView(testView);
        assertNotNull(testView.getTypeLine());
    }

    @Test(groups = {"viewTests"})
    public void addViewShouldSetTheElectionViewCommandLineGivenAViewElement() {
        testBuilder.addView(testView);
        assertNotNull(testView.getCommandLine());
    }

}
