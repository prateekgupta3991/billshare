package com.share.bill.entities;

import javax.persistence.*;

/**
 * Created by prateekgupta on 09/09/17.
 */
@Table(name = "bill_user_gang")
@Entity
public class BillUserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bill_id", referencedColumnName = "id")
    private Bill bill;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "amount")
    private Double share;

    public BillUserGroup() {
    }

    public BillUserGroup(User user, Double share) {
        this.user = user;
        this.share = share;
    }

    public BillUserGroup(Bill bill, User user, Double share) {
        this.bill = bill;
        this.user = user;
        this.share = share;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getShare() {
        return share;
    }

    public void setShare(Double share) {
        this.share = share;
    }

    @Override
    public String toString() {
        return "BillUserGroup{" +
                "id=" + id +
                ", bill=" + bill +
                ", user=" + user +
                ", share=" + share +
                '}';
    }
}
