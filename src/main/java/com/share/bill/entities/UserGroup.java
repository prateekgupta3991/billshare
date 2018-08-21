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

    @Column(name = "gang_id")
    private Long groupId;

    @Column(name = "user_id")
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserGroup{" +
                "id=" + id +
                ", groupId=" + groupId +
                ", userId=" + userId +
                '}';
    }
}
