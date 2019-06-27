package com.trial.rectifyapp;

/**
 * Created by admin on 11-04-2018.
 */

public class fix {

    String Description;
    int Amount,Members;

    public fix(){

    }

    public fix(String description, int amount, int members) {
        Description = description;
        Amount = amount;
        Members = members;
    }
    public String getDescription()
    {
        return Description;
    }
    public int getAmount()
    {
        return Amount;
    }
    public int getMembers()
    {
        return Members;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public void setMembers(int members) {
        Members = members;
    }
}
