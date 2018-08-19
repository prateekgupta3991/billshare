package com.share.bill.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by prateekgupta on 09/09/17.
 */
@Table(name = "user")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "contact")
    private String contact;

    @Transient
    private Double totalAmount;

    //@ManyToOne(fetch = FetchType.LAZY, targetEntity = Group.class)
    @Transient
    private List<Group> groupsInvolved;

    @Transient
    private Map<Group, Double> groupWiseAmount;

    public User() {
    }

    public User(String nam, String email, String contact) {
        super();
        this.name = nam;
        this.email = email;
        this.contact = contact;
        this.groupsInvolved = new ArrayList<>();
        this.groupWiseAmount = new HashMap<>();
    }

    public User(String name, String email, String contact, Double totalAmount, List<Group> groupsInvolved, Map<Group, Double>
        groupWiseAmount) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.totalAmount = totalAmount;
        this.groupsInvolved = groupsInvolved;
        this.groupWiseAmount = groupWiseAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                ", totalAmount=" + totalAmount +
                ", groupsInvolved=" + groupsInvolved +
                ", groupWiseAmount=" + groupWiseAmount +
                '}';
    }
}
