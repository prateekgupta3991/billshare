package com.share.bill.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by prateekgupta on 09/09/17.
 */
@Table(name = "gang")
@Entity
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "gang", fetch = FetchType.EAGER)
    private List<UserGroup> userGroups;

    @OneToMany(mappedBy = "gang", fetch = FetchType.EAGER)
    private List<Bill> bills;

    @OneToOne
    @JoinColumn(name = "admin_user_id", referencedColumnName = "id")
    private User adminUser;

    public Group() {
    }

    public Group(String nam, User adminUser) {
        super();
        this.name = nam;
        this.adminUser = adminUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserGroup> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(List<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public User getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(User adminUser) {
        this.adminUser = adminUser;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userGroups=" + userGroups +
                ", bills=" + bills +
                ", adminUser=" + adminUser +
                '}';
    }
}
