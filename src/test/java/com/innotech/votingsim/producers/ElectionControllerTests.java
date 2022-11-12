package com.innotech.votingsim.producers;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ElectionControllerTests {

    @Test
    public void electionControllerContentWindowShouldContainAScrollableResultTableWhenTheUserClicksRunElectionButton() {
        ElectionControllerBuilder.ElectionController testController = ElectionControllerFactory.getInstance();
        int originalComponentCount = testController.getElectionView().getContentPane().getComponentCount();
        testController.getRunInput().getInputField().doClick();
        assertEquals(testController.getElectionView().getContentPane().getComponentCount(), originalComponentCount + 1);
    }

}
