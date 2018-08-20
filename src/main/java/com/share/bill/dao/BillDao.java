package com.share.bill.dao;

import com.share.bill.entities.Bill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BillDao implements DaoInterface<Bill, Long> {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public SessionFactory getSession() {
        return hibernateTemplate.getSessionFactory();
    }

    public void persist(Bill entity) {
        Session session = getSession().openSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        session.close();
    }

    public void update(Bill entity) {
        Session session = getSession().openSession();
        session.beginTransaction();
        session.saveOrUpdate(entity);
        session.getTransaction().commit();
        session.close();
    }

    public Bill findById(Long id) {
        Session session = getSession().openSession();
        Bill bill = (Bill) session.get(Bill.class, id);
        session.close();
        return bill;
    }

    public void delete(Bill entity) {
        Session session = getSession().openSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    public List<Bill> findAll() {
        String query = "select bill from Bill bill";
        Session session = getSession().openSession();
        List<Bill> bills = (List<Bill>) session.createQuery(query).list();
        return bills;
    }

    public void deleteAll() {
        List<Bill> entityList = findAll();
        for (Bill entity : entityList) {
            delete(entity);
        }
    }
}
