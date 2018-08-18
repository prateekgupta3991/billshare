package com.share.bill.dao;

import com.share.bill.entities.Bill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BillDao implements DaoInterface<Bill, Long> {

    @Autowired
    private HibernateTemplate hibernateTemplate;
//    @Autowired
//    private HibernateUtil hibernateUtil;
//
//    private Session currentSession;
//    private Transaction currentTransaction;
//
//    public BillDao() {
//    }
//
//    public Session openCurrentSession() {
//        currentSession = hibernateUtil.getSessionFactory().getCurrentSession();
//        //currentSession = session.openSession();
//        return currentSession;
//    }
//
//    public Session openCurrentSessionwithTransaction() {
//        //currentSession = getSessionFactory().openSession();
//        //currentTransaction = currentSession.beginTransaction();
//        currentSession = hibernateUtil.getSessionFactory().getCurrentSession();
//        currentSession.beginTransaction();
//        return currentSession;
//    }
//
//    public void closeCurrentSession() {
//        currentSession.close();
//    }
//
//    public void closeCurrentSessionwithTransaction() {
//        currentTransaction.commit();
//        currentSession.close();
//    }
//
//    private static SessionFactory getSessionFactory() {
//        Configuration configuration = new Configuration().configure();
//        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
//        builder.applySettings(configuration.getProperties());
//        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
//        return sessionFactory;
//    }
//
//    public Session getCurrentSession() {
//        return currentSession;
//    }
//
//    public void setCurrentSession(Session currentSession) {
//        this.currentSession = currentSession;
//    }
//
//    public Transaction getCurrentTransaction() {
//        return currentTransaction;
//    }
//
//    public void setCurrentTransaction(Transaction currentTransaction) {
//        this.currentTransaction = currentTransaction;
//    }
//
//    public void persist(Bill entity) {
//        getCurrentSession().save(entity);
//    }
//
//    public void update(Bill entity) {
//        getCurrentSession().update(entity);
//    }
//
//    public Bill findById(Long id) {
//        Bill book = (Bill) getCurrentSession().get(Bill.class, id);
//        return book;
//    }
//
//    public void delete(Bill entity) {
//        getCurrentSession().delete(entity);
//    }

    @SuppressWarnings("unchecked")
    public List<Bill> findAll() {
        List<Bill> books = hibernateTemplate.loadAll(Bill.class);
        //List<Bill> books = (List<Bill>) getCurrentSession().createQuery("from bill").list();
        return books;
    }

//    public void deleteAll() {
//        List<Bill> entityList = findAll();
//        for (Bill entity : entityList) {
//            delete(entity);
//        }
//    }
}
