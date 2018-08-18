package com.share.bill.dao;

import com.share.bill.config.RepositoryConfig;
import com.share.bill.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDao implements DaoInterface<User, Long> {

//    @Autowired
//    private HibernateUtil hibernateUtil;
//
//    @Autowired
//    private RepositoryConfig repositoryConfig;

    @Autowired
    private HibernateTemplate hibernateTemplate;

//    private Session currentSession;
//    private Transaction currentTransaction;

    public UserDao() {
    }

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
//        //return repositoryConfig.getSessionFactory();
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

//    public void persist(User entity) {
//        getCurrentSession().save(entity);
//    }
//
//    public void update(User entity) {
//        getCurrentSession().update(entity);
//    }
//
//    public User findById(Long id) {
//        User book = (User) getCurrentSession().get(User.class, id);
//        return book;
//    }
//
//    public void delete(User entity) {
//        getCurrentSession().delete(entity);
//    }

    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        List<User> books = hibernateTemplate.loadAll(User.class);
        //List<User> books = (List<User>) getCurrentSession().createQuery("from user").list();
        return books;
    }

//    public void deleteAll() {
//        List<User> entityList = findAll();
//        for (User entity : entityList) {
//            delete(entity);
//        }
//    }
}
