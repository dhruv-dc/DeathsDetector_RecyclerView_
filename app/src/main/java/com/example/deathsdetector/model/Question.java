package com.example.deathsdetector.model;

public class Question {

    private String stateNames;
    private int deathsInState;
    private int positiveCasesInState;
    private int discharged;

    public Question() {
    }

    public Question(String stateNames, int deathsInState, int positiveCasesInState, int discharged) {
        this.stateNames = stateNames;
        this.deathsInState = deathsInState;
        this.positiveCasesInState = positiveCasesInState;
        this.discharged = discharged;
    }

    public String getStateNames() {
        return stateNames;
    }

    public void setStateNames(String stateNames) {
        this.stateNames = stateNames;
    }

    public int getDeathsInState() {
        return deathsInState;
    }

    public void setDeathsInState(int deathsInState) {
        this.deathsInState = deathsInState;
    }

    public int getPositiveCasesInState() {
        return positiveCasesInState;
    }

    public void setPositiveCasesInState(int positiveCasesInState) {
        this.positiveCasesInState = positiveCasesInState;
    }

    public int getDischarged() {
        return discharged;
    }

    public void setDischarged(int discharged) {
        this.discharged = discharged;
    }

    @Override
    public String toString() {
        return "Question{" +
                "stateNames='" + stateNames + '\'' +
                ", deathsInState=" + deathsInState +
                ", positiveCasesInState=" + positiveCasesInState +
                ", discharged=" + discharged +
                '}';
    }
}
