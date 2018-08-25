package com.share.bill.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by prateekgupta on 09/09/17.
 */
@Table(name = "user_gang")
@Entity
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gang_id", referencedColumnName = "id")
    private Group gang;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public UserGroup() {
    }

    public UserGroup(Group gang, User user) {
        this.gang = gang;
        this.user = user;
    }

    public UserGroup(Group gang) {
        this.gang = gang;
    }

    public UserGroup(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Group getGang() {
        return gang;
    }

    public void setGang(Group gang) {
        this.gang = gang;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserGroup{" +
                "id=" + id +
                ", gang=" + gang +
                ", user=" + user +
                '}';
    }
}
