package com.share.bill.dao;

import com.share.bill.entities.BillUserGroup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BillUserGroupDao implements DaoInterface<BillUserGroup, Long> {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public SessionFactory getSession() {
        return hibernateTemplate.getSessionFactory();
    }

    public void persist(BillUserGroup entity) {
        Session session = getSession().openSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        session.close();
    }

    public void update(BillUserGroup entity) {
        Session session = getSession().openSession();
        session.beginTransaction();
        session.saveOrUpdate(entity);
        session.getTransaction().commit();
        session.close();
    }

    public BillUserGroup findById(Long id) {
        Session session = getSession().openSession();
        BillUserGroup group = (BillUserGroup) session.get(BillUserGroup.class, id);
        session.close();
        return group;
    }

    public void delete(BillUserGroup entity) {
        Session session = getSession().openSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    public List<BillUserGroup> findAll() {
        String query = "select bugrp from BillUserGroup bugrp";
        Session session = getSession().openSession();
        List<BillUserGroup> groups = (List<BillUserGroup>) session.createQuery(query).list();
        return groups;
    }

    public void deleteAll() {
        List<BillUserGroup> entityList = findAll();
        for (BillUserGroup entity : entityList) {
            delete(entity);
        }
    }

    public void persistAll(List<BillUserGroup> entityList) {
        Session session = getSession().openSession();
        session.beginTransaction();
        for (BillUserGroup userGroup : entityList) {
            session.save(userGroup);
        }
        session.getTransaction().commit();
        session.close();
    }

    public List<BillUserGroup> findBillUserGroupByBillId(Long billId) {
        String query = "select bugrp from BillUserGroup bugrp where bill_id = :billId";
        Session session = getSession().openSession();
        List<BillUserGroup> groups = (List<BillUserGroup>) session.createQuery(query).setParameter("billId", billId).list();
        return groups;
    }
}
