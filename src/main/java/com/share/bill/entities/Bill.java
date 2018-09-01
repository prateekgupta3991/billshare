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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gang_id")
    private Group gang;

    public Bill() {
    }

    public Bill(String name, Double billAmount, Group gang) {
        this.name = name;
        this.billAmount = billAmount;
        this.gang = gang;
    }

    public Bill(String name, Double billAmount) {
        this.name = name;
        this.billAmount = billAmount;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Group getGang() {
        return gang;
    }

    public void setGang(Group gang) {
        this.gang = gang;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", billAmount=" + billAmount +
                ", gang=" + gang +
                '}';
    }
}
