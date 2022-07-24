package com.innotech.votingsim.controllers;

public abstract class Controller {

    public enum ControllerType {
        POPULATION,
        CANDIDATE,
        ELECTION
    }

    private final ControllerType controllerType;

    public  Controller(ControllerType type) {
        this.controllerType = type;
    }

    public ControllerType getType() {
        return this.controllerType;
    }

}
