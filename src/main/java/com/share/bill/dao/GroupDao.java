package com.share.bill.dao;

import com.share.bill.entities.Group;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupDao implements DaoInterface<Group, Long> {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public SessionFactory getSession() {
        return hibernateTemplate.getSessionFactory();
    }

    public void persist(Group entity) {
        Session session = getSession().openSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        session.close();
    }

    public void update(Group entity) {
        Session session = getSession().openSession();
        session.beginTransaction();
        session.saveOrUpdate(entity);
        session.getTransaction().commit();
        session.close();
    }

    public Group findById(Long id) {
        Session session = getSession().openSession();
        Group group = (Group) session.get(Group.class, id);
        session.close();
        return group;
    }

    public void delete(Group entity) {
        Session session = getSession().openSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    public List<Group> findAll() {
        String query = "select grp from Group grp";
        Session session = getSession().openSession();
        List<Group> groups = (List<Group>) session.createQuery(query).list();
        return groups;
    }

    public void deleteAll() {
        List<Group> entityList = findAll();
        for (Group entity : entityList) {
            delete(entity);
        }
    }
}
