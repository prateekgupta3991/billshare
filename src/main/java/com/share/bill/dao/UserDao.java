package com.share.bill.dao;

import com.share.bill.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDao implements DaoInterface<User, Long> {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public void persist(User entity) {
        hibernateTemplate.persist(entity);
    }

    public void update(User entity) {
        hibernateTemplate.update(entity);
    }

    public User findById(Long id) {
        User book = hibernateTemplate.get(User.class, id);
        return book;
    }

    public void delete(User entity) {
        hibernateTemplate.delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        List<User> books = hibernateTemplate.loadAll(User.class);
        return books;
    }

    public void deleteAll() {
        List<User> entityList = findAll();
        for (User entity : entityList) {
            delete(entity);
        }
    }

    public User findByEmail(String email) {
        String query = "select usr from user usr where email = ?";
        Object[] queryParam = {email};
        List<User> users = (List<User>) hibernateTemplate.find(query, queryParam);
        return !users.isEmpty() ? users.get(0) : null;
    }

    public List<User> findAllByEmail(List<String> emails) {
        String query = "select usr from user usr where email in ?";
        Object[] queryParam = {emails};
        List<User> users = (List<User>) hibernateTemplate.find(query, queryParam);
        return users;
    }
}
