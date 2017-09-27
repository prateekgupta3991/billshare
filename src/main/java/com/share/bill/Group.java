package com.share.bill;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prateekgupta on 09/09/17.
 */
public class Group {

    private String name;
    private List<User> users;
    private List<Bill> bills;

    public Group(String nam) {
        super();
        this.name = nam;
        this.users = new ArrayList<>();
        this.bills = new ArrayList<>();
    }

    public Group(String name, List<User> users, List<Bill> bills) {
        this.name = name;
        this.users = users;
        this.bills = bills;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
