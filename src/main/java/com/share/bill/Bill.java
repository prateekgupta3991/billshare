package com.share.bill;

import java.util.Map;

/**
 * Created by prateekgupta on 09/09/17.
 */
public class Bill {

    private String name;
    private Double billAmount;
    private Group billGroup;
    private Map<User, Contribution> userContributions;
    private Map<User, Contribution> userOwed;

    public Bill() {
        super();
    }

    public Bill(String name, Double billAmount, Group billGroup, Map<User, Contribution> userContributions, Map<User, Contribution> userOwed) {
        this.name = name;
        this.billAmount = billAmount;
        this.billGroup = billGroup;
        this.userContributions = userContributions;
        this.userOwed = userOwed;
    }

    public Double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(Double billAmount) {
        this.billAmount = billAmount;
    }

    public Group getBillGroup() {
        return billGroup;
    }

    public void setBillGroup(Group billGroup) {
        this.billGroup = billGroup;
    }

    public Map<User, Contribution> getUserContributions() {
        return userContributions;
    }

    public void setUserContributions(Map<User, Contribution> userContributions) {
        this.userContributions = userContributions;
    }

    public Map<User, Contribution> getUserOwed() {
        return userOwed;
    }

    public void setUserOwed(Map<User, Contribution> userOwed) {
        this.userOwed = userOwed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
