package com.share.bill.dao;

import com.share.bill.entities.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupDao implements DaoInterface<Group, Long> {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public void persist(Group entity) {
        hibernateTemplate.save(entity);
    }

    public void update(Group entity) {
        hibernateTemplate.update(entity);
    }

    public Group findById(Long id) {
        Group book = hibernateTemplate.get(Group.class, id);
        return book;
    }

    public void delete(Group entity) {
        hibernateTemplate.delete(entity);
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
