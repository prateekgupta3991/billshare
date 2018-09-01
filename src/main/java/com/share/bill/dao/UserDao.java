package com.share.bill.dao;

import com.share.bill.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDao implements DaoInterface<User, Long> {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public SessionFactory getSession() {
        return hibernateTemplate.getSessionFactory();
    }

    public void persist(User entity) {
        Session session = getSession().openSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        session.close();
    }

    public void update(User entity) {
        Session session = getSession().openSession();
        session.beginTransaction();
        session.saveOrUpdate(entity);
        session.getTransaction().commit();
        session.close();
    }

    public User findById(Long id) {
        Session session = getSession().openSession();
        User book = (User) session.get(User.class, id);
        session.close();
        return book;
    }

    public void delete(User entity) {
        Session session = getSession().openSession();
        session.delete(entity);
        session.close();
    }

    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        String query = "select usr from User usr";
        Session session = getSession().openSession();
        List<User> books = (List<User>) session.createQuery(query).list();
        session.close();
        return books;
    }

    public void deleteAll() {
        List<User> entityList = findAll();
        for (User entity : entityList) {
            delete(entity);
        }
    }

    public User findByEmail(String email) {
        String query = "select usr from User usr where email = :email";
        Session session = getSession().openSession();
        List<User> users = (List<User>) session.createQuery(query).setParameter("email", email).list();
        session.close();
        return !users.isEmpty() ? users.get(0) : null;
    }

    public List<User> findAllByEmail(List<String> emails) {
        String query = "select usr from User usr where email in :emails";
        Session session = getSession().openSession();
        List<User> users = (List<User>) session.createQuery(query).setParameterList("emails", emails).list();
        session.close();
        return users;
    }

    public List<User> findAllByIds(List<Long> ids) {
        String query = "select usr from User usr where id in :ids";
        Session session = getSession().openSession();
        List<User> users = (List<User>) session.createQuery(query).setParameterList("ids", ids).list();
        session.close();
        return users;
    }
}
