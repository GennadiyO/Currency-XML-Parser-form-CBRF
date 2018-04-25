package com.gazprombank.task4;

public class AddValute {
    private String vsValute;
    private String uprValute;
    private String lwrValute;
    private String uprValuteValue;
    private String lwrValuteValue;
    private String currency;
    private int uprNominal;
    private int lwrNominal;

    public AddValute(String vsValute){
        this.vsValute = vsValute;
        setValutes();
    }
    public void setValutes(){
        String[] words = vsValute.split("/");
        uprValute = words[0];
        lwrValute = words[1];
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public void setUprValuteValue(String uprValuteValue) {
        this.uprValuteValue = uprValuteValue;
    }
    public void setLwrValuteValue(String lwrValuteValue) {
        this.lwrValuteValue = lwrValuteValue;
    }
    public void setUprNominal(int uprNominal) {
        this.uprNominal = uprNominal;
    }
    public void setLwrNominal(int lwrNominal) {
        this.lwrNominal = lwrNominal;
    }
    public String getVsValute() {
        return vsValute;
    }
    public String getCurrency() {
        return currency;
    }
    public String getUprValuteValue() {
        return uprValuteValue;
    }
    public String getLwrValuteValue() {
        return lwrValuteValue;
    }
    public String getUprValute(){
        return uprValute;
    }
    public String getLwrValute(){
        return lwrValute;
    }
    public int getUprNominal() {
        return uprNominal;
    }
    public int getLwrNominal() {
        return lwrNominal;
    }
}
