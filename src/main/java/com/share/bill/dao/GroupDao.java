package com.share.bill.dao;

import com.share.bill.entities.Group;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupDao implements DaoInterface<Group, Long> {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public Session getSession() {
        return hibernateTemplate.getSessionFactory().openSession();
    }

    public void persist(Group entity) {
        getSession().save(entity);
    }

    public void update(Group entity) {
        getSession().update(entity);
    }

    public Group findById(Long id) {
        Group book = (Group) getSession().get(Group.class, id);
        return book;
    }

    public void delete(Group entity) {
        getSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<Group> findAll() {
        List<Group> books = hibernateTemplate.loadAll(Group.class);
        return books;
    }

    public void deleteAll() {
        List<Group> entityList = findAll();
        for (Group entity : entityList) {
            delete(entity);
        }
    }
}
