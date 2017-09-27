package com.share.bill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by prateekgupta on 09/09/17.
 */
public class User {

    private String name;
    private Double totalAmount;
    private List<Group> groupsInvolved;
    private Map<Group, Double> groupWiseAmount;

    public User(String nam) {
        super();
        this.name = nam;
        this.groupsInvolved = new ArrayList<>();
        this.groupWiseAmount = new HashMap<>();
    }

    public User(String name, Double totalAmount, List<Group> groupsInvolved, Map<Group, Double> groupWiseAmount) {
        this.name = name;
        this.totalAmount = totalAmount;
        this.groupsInvolved = groupsInvolved;
        this.groupWiseAmount = groupWiseAmount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<Group> getGroupsInvolved() {
        return groupsInvolved;
    }

    public void setGroupsInvolved(List<Group> groupsInvolved) {
        this.groupsInvolved = groupsInvolved;
    }

    public Map<Group, Double> getGroupWiseAmount() {
        return groupWiseAmount;
    }

    public void setGroupWiseAmount(Map<Group, Double> groupWiseAmount) {
        this.groupWiseAmount = groupWiseAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
