package com.share.bill.entities;

import javax.persistence.*;
import java.util.Map;

/**
 * Created by prateekgupta on 09/09/17.
 */
@Table(name = "bill")
@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "amount")
    private Double billAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    private Group billGroup;

    @Transient
    private Map<User, Contribution> userContributions;

    @Transient
    private Map<User, Contribution> userOwed;


    public Bill() {
    }

    public Bill(String name, Double billAmount, Group billGroup, Map<User, Contribution> userContributions, Map<User, Contribution> userOwed) {
        this.name = name;
        this.billAmount = billAmount;
        this.billGroup = billGroup;
        this.userContributions = userContributions;
        this.userOwed = userOwed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", billAmount=" + billAmount +
                ", billGroup=" + billGroup +
                ", userContributions=" + userContributions +
                ", userOwed=" + userOwed +
                '}';
    }
}
