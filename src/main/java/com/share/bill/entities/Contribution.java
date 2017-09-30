package com.share.bill.entities;

/**
 * Created by prateekgupta on 09/09/17.
 */
public class Contribution {

    private Double shareAmount;
    private Double sharePercentage;

    public Contribution() {
        super();
    }

    public Contribution(Double shareAmount, Double sharePercentage) {
        this.shareAmount = shareAmount;
        this.sharePercentage = sharePercentage;
    }

    public Double getShareAmount() {
        return shareAmount;
    }

    public void setShareAmount(Double shareAmount) {
        this.shareAmount = shareAmount;
    }

    public Double getSharePercentage() {
        return sharePercentage;
    }

    public void setSharePercentage(Double sharePercentage) {
        this.sharePercentage = sharePercentage;
    }
}
