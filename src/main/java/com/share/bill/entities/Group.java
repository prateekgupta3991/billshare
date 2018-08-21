package com.share.bill.entities;

import javax.persistence.*;
import java.util.ArrayList;
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

    @OneToMany(mappedBy = "id")
    private List<User> users;

    @OneToMany(mappedBy = "id")
    private List<Bill> bills;

    @OneToOne
    private User admin_user;

    public Group(String nam, List<User> userList, User admin_user) {
        super();
        this.name = nam;
        this.users = userList;
        this.bills = new ArrayList<>();
        this.admin_user = admin_user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getAdmin_user() {
        return admin_user;
    }

    public void setAdmin_user(User admin_user) {
        this.admin_user = admin_user;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", users=" + users +
                ", bills=" + bills +
                ", admin_user=" + admin_user +
                '}';
    }
}
