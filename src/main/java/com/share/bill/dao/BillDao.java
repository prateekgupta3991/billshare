package com.share.bill.dao;

import com.share.bill.entities.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BillDao implements DaoInterface<Bill, Long> {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public void persist(Bill entity) {
        hibernateTemplate.save(entity);
    }

    public void update(Bill entity) {
        hibernateTemplate.update(entity);
    }

    public Bill findById(Long id) {
        Bill book = hibernateTemplate.get(Bill.class, id);
        return book;
    }

    public void delete(Bill entity) {
        hibernateTemplate.delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<Bill> findAll() {
        List<Bill> books = hibernateTemplate.loadAll(Bill.class);
        return books;
    }

    public void deleteAll() {
        List<Bill> entityList = findAll();
        for (Bill entity : entityList) {
            delete(entity);
        }
    }
}
