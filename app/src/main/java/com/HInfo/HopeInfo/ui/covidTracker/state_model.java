package com.HInfo.HopeInfo.ui.covidTracker;

public class state_model {

    private String state_name, death, recovered, total, active, incDeath, incRecovered, incConfirmed;

    public state_model() {
    }

    public state_model(String state_name, String death, String recovered, String total, String active, String incDeath, String incRecovered, String incConfirmed) {
        this.state_name = state_name;
        this.death = death;
        this.recovered = recovered;
        this.total = total;
        this.active = active;
        this.incDeath = incDeath;
        this.incRecovered = incRecovered;
        this.incConfirmed = incConfirmed;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public String getDeath() {
        return death;
    }

    public void setDeath(String death) {
        this.death = death;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getIncDeath() {
        return incDeath;
    }

    public void setIncDeath(String incDeath) {
        this.incDeath = incDeath;
    }

    public String getIncRecovered() {
        return incRecovered;
    }

    public void setIncRecovered(String incRecovered) {
        this.incRecovered = incRecovered;
    }

    public String getIncConfirmed() {
        return incConfirmed;
    }

    public void setIncConfirmed(String incConfirmed) {
        this.incConfirmed = incConfirmed;
    }
}
