package com.example.allrestapi;

public class ModelCountry {

    private  String flag,country,cases,todaycases,deaths,todaydeaths,recover,active,critical;

    public ModelCountry() {
    }

    public ModelCountry(String flag, String country, String cases, String todaycases, String deaths, String todaydeaths, String recover, String active, String critical) {
        this.flag = flag;
        this.country = country;
        this.cases = cases;
        this.todaycases = todaycases;
        this.deaths = deaths;
        this.todaydeaths = todaydeaths;
        this.recover = recover;
        this.active = active;
        this.critical = critical;
    }

    public ModelCountry(String flag, String country) {
        this.flag = flag;
        this.country = country;
    }

    public ModelCountry(String country) {
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getTodaycases() {
        return todaycases;
    }

    public void setTodaycases(String todaycases) {
        this.todaycases = todaycases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getTodaydeaths() {
        return todaydeaths;
    }

    public void setTodaydeaths(String todaydeaths) {
        this.todaydeaths = todaydeaths;
    }

    public String getRecover() {
        return recover;
    }

    public void setRecover(String recover) {
        this.recover = recover;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCritical() {
        return critical;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }
}
