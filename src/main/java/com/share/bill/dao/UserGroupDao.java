package com.share.bill.dao;

import com.share.bill.entities.UserGroup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserGroupDao implements DaoInterface<UserGroup, Long> {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public SessionFactory getSession() {
        return hibernateTemplate.getSessionFactory();
    }

    public void persist(UserGroup entity) {
        Session session = getSession().openSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        session.close();
    }

    public void update(UserGroup entity) {
        Session session = getSession().openSession();
        session.beginTransaction();
        session.saveOrUpdate(entity);
        session.getTransaction().commit();
        session.close();
    }

    public UserGroup findById(Long id) {
        Session session = getSession().openSession();
        UserGroup group = (UserGroup) session.get(UserGroup.class, id);
        session.close();
        return group;
    }

    public void delete(UserGroup entity) {
        Session session = getSession().openSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    public List<UserGroup> findAll() {
        String query = "select ugrp from UserGroup ugrp";
        Session session = getSession().openSession();
        List<UserGroup> groups = (List<UserGroup>) session.createQuery(query).list();
        return groups;
    }

    public void deleteAll() {
        List<UserGroup> entityList = findAll();
        for (UserGroup entity : entityList) {
            delete(entity);
        }
    }
}
