package com.share.bill.dao;

import com.share.bill.entities.Group;
import com.share.bill.entities.User;
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
public class GroupDao implements DaoInterface<Group, Long> {

    @Autowired
    private HibernateTemplate hibernateTemplate;
//    @Autowired
//    private HibernateUtil hibernateUtil;
//
//    private Session currentSession;
//    private Transaction currentTransaction;
//
//    public GroupDao() {
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
//    public void persist(Group entity) {
//        getCurrentSession().save(entity);
//    }
//
//    public void update(Group entity) {
//        getCurrentSession().update(entity);
//    }
//
//    public Group findById(Long id) {
//        Group book = (Group) getCurrentSession().get(Group.class, id);
//        return book;
//    }
//
//    public void delete(Group entity) {
//        getCurrentSession().delete(entity);
//    }

    @SuppressWarnings("unchecked")
    public List<Group> findAll() {
        List<Group> books = hibernateTemplate.loadAll(Group.class);
        //List<Group> books = (List<Group>) getCurrentSession().createQuery("from group").list();
        return books;
    }

//    public void deleteAll() {
//        List<Group> entityList = findAll();
//        for (Group entity : entityList) {
//            delete(entity);
//        }
//    }
}
