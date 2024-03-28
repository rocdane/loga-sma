package com.loga.vendor;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public abstract class Repository<T,P> {

    private Class<T> entity;

    private final SessionFactory SESSION_FACTORY = HibernateJpa.getSessionFactory();

    public Repository(Class<T> entity){
        this.entity=entity;
    }

    public SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    public P save(T obj) throws Exception{
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        P result;
        try {
            tx = session.beginTransaction();
            result = (P) session.save(obj);
            tx.commit();
        }catch (Exception e){
            if(tx!=null)
                tx.rollback();
            throw e;
        }
        session.close();
        return result;
    }

    public void persist(T obj) throws Exception{
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(obj);
            tx.commit();
        }catch (Exception e){
            if(tx!=null)
                tx.rollback();
            throw e;
        }
        session.close();
    }

    public void update(T obj) throws Exception{
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(obj);
            tx.commit();
        }catch (Exception e){
            if(tx!=null)
                tx.rollback();
            throw e;
        }
        session.close();
    }

    public void delete(T obj) throws Exception{
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(obj);
            tx.commit();
        }catch (Exception e){
            if(tx != null)
                tx.rollback();
            throw e;
        }
        session.close();
    }

    public T findById(long id){
        Session session = getSessionFactory().openSession();
        T t = session.get(entity,id);
        session.close();
        return t;
    }

    public List<T> findAll(){
        Session session = getSessionFactory().openSession();
        List<T> t = session.createQuery("from "+ entity.getName()).list();
        session.close();
        return t;
    }
}
