package com.share.bill.dao;

import com.share.bill.config.RepositoryConfig;
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

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void persist(User entity) {
        getSession().openSession().save(entity);
        getSession().close();
    }

    public void update(User entity) {
        getSession().openSession().saveOrUpdate(entity);
        getSession().close();
    }

    public User findById(Long id) {
        User book = (User) getSession().openSession().get(User.class, id);
        getSession().close();
        return book;
    }

    public void delete(User entity) {
        getSession().openSession().delete(entity);
        getSession().close();
    }

    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        String query = "select usr from User usr";
        List<User> books = (List<User>) getSession().openSession().get(query, User.class);
        getSession().close();
        return books;
    }

    public void deleteAll() {
        List<User> entityList = findAll();
        for (User entity : entityList) {
            delete(entity);
        }
    }

    public User findByEmail(String email) {
        String query = "select usr from User usr where email = ?";
        Object[] queryParam = {email};
        List<User> users = (List<User>) hibernateTemplate.find(query, queryParam);
        return !users.isEmpty() ? users.get(0) : null;
    }

    public List<User> findAllByEmail(List<String> emails) {
        String query = "select usr from User usr where email in ?";
        Object[] queryParam = {emails};
        List<User> users = (List<User>) hibernateTemplate.find(query, queryParam);
        return users;
    }
}
